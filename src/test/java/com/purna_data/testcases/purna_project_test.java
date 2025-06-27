package com.purna_data.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.purna_data.libraries.BaseClass;
import com.purna_data.libraries.ExcelUtils;
import com.purna_data.libraries.Utilities;
import com.purna_data.pages.purna_dashboardpage;
import com.purna_data.pages.purna_loginpage;
import com.purna_data.pages.purna_projectdashboardpage;
import com.purna_data.pages.purna_projectpage;

public class purna_project_test extends BaseClass {

	purna_loginpage loginpage;
	purna_dashboardpage dashboardpage;
	purna_projectdashboardpage projectdashboardpage;
	purna_projectpage projectpage;
	Utilities utils;

	@BeforeMethod
	public void login() {
		initialization();
		loginpage = new purna_loginpage(driver);
		dashboardpage = new purna_dashboardpage(driver);
		projectdashboardpage = new purna_projectdashboardpage(driver);
		projectpage = new purna_projectpage(driver);
		utils = new Utilities();
		loginpage.loginpurna(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProjectData() {
		Object[][] data = ExcelUtils.getTestData("project");
		return data;
	}

	@Test(dataProvider = "getProjectData")
	public void adding_new_project(/*String SR_NO,*/ String PROJECT_CODE, String PROJECT_NAME, String REMARK,
			String BUDGET_AMOUNT, String CUSTOMER_NAME)
	{
		utils.clickElement(driver, dashboardpage.link_main);
		utils.clickElement(driver, dashboardpage.link_project);
//		Thread.sleep(300);
		utils.clickElement(driver, projectdashboardpage.btn_addnewproject);
//		projectpage.txt_sr_no.clear();
//		projectpage.txt_sr_no.sendKeys(SR_NO);
		projectpage.txt_project_code.sendKeys(PROJECT_CODE);
		projectpage.txt_project_name.sendKeys(PROJECT_NAME);
		String project_name = projectpage.txt_project_name.getAttribute("value");
		projectpage.txt_remark.sendKeys(REMARK);
		projectpage.txt_budget_amount.sendKeys(BUDGET_AMOUNT);
//		Thread.sleep(200);
		utils.doDropDownSelectByValue(projectpage.dropdown_cust_name, CUSTOMER_NAME);
		projectpage.btn_save_projectdetails.click();
//		utils.syncElement(driver, null, "alertPresent");
		utils.handleAlert(driver).accept();
		utils.clickElement(driver, projectpage.btn_projectdetails);
		
		int row = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr")).size();
		int col = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//thead//th")).size();
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				String table_data = driver.findElement(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr[" + i + "]//td[" + j + "]")).getText();
				if (table_data.equalsIgnoreCase(project_name)) {
					String valid = table_data;
					Assert.assertEquals(project_name, valid);
					System.out.println("\u001B[1m Project Details Matched... \u001B[0m");
					break;
				}
			}
		}
	}

	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
}

// project module is depended on customer model