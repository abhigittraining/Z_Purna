package com.purna_data.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.purna_data.libraries.BaseClass;
import com.purna_data.libraries.ExcelUtils;
import com.purna_data.libraries.Utilities;
import com.purna_data.pages.purna_dashboardpage;
import com.purna_data.pages.purna_employeedashboardpage;
import com.purna_data.pages.purna_employeepage;
import com.purna_data.pages.purna_loginpage;

public class purna_employee_test extends BaseClass {

	purna_loginpage loginpage;
	purna_dashboardpage dashboardpage;
	purna_employeedashboardpage empdashboardpage;
	purna_employeepage emppage;
	Utilities utils;

	@BeforeMethod
	public void login() {
		initialization();
		loginpage = new purna_loginpage(driver);
		dashboardpage = new purna_dashboardpage(driver);
		empdashboardpage = new purna_employeedashboardpage(driver);
		emppage = new purna_employeepage(driver);
		utils = new Utilities();
		loginpage.loginpurna(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getEmployeeData() {
		Object[][] data = ExcelUtils.getTestData("employee");
		return data;
	}

	@Test(dataProvider = "getEmployeeData")
	public void adding_new_employee(/*String SR_NO,*/ String NAME, String DATE_OF_JOINING, String DEPARTMENT,
			String DESIGNATION, String MOBILE, String ALTR_CONTACT, String PAN_NO, String EMAIL, String AREA,
			String REGION, String BIRTHDATE, String BLOODGROUP, String ADDRESS, String PASSPORT, String EMP_ID,
			String BANK_NAME, String BANK_ADDR, String ACC_NO, String IFSC_CODE, String SALARY, String NO_OF_DAYS)
	{
		utils.clickElement(driver, dashboardpage.link_main);
		utils.clickElement(driver, dashboardpage.link_employee);
//		Thread.sleep(300);
		utils.clickElement(driver, empdashboardpage.btn_addnewemp);
//		emppage.txt_sr_no.clear();
//		emppage.txt_sr_no.sendKeys(SR_NO);
		emppage.txt_name.sendKeys(NAME);
		String emp_name = emppage.txt_name.getAttribute("value");
		emppage.txt_date_of_joining.sendKeys(DATE_OF_JOINING);
		emppage.txt_department.sendKeys(DEPARTMENT);
		emppage.txt_designation.sendKeys(DESIGNATION);
		emppage.txt_mobile.sendKeys(MOBILE);
		emppage.txt_altr_cont.sendKeys(ALTR_CONTACT);
		emppage.txt_pan_no.sendKeys(PAN_NO);
		emppage.txt_email.sendKeys(EMAIL);
		emppage.txt_area.sendKeys(AREA);
		emppage.txt_region.sendKeys(REGION);
		emppage.txt_birthdate.sendKeys(BIRTHDATE);
		emppage.txt_bloodgroup.sendKeys(BLOODGROUP);
		emppage.txt_address.sendKeys(ADDRESS);
		emppage.txt_passport.sendKeys(PASSPORT);
		emppage.txt_emp_id.sendKeys(EMP_ID);
		emppage.txt_bank_name.sendKeys(BANK_NAME);
		emppage.txt_bank_addr.sendKeys(BANK_ADDR);
		emppage.txt_acc_no.sendKeys(ACC_NO);
		emppage.txt_ifsc_code.sendKeys(IFSC_CODE);
		emppage.txt_salary.sendKeys(SALARY);
		emppage.txt_no_of_days.sendKeys(NO_OF_DAYS);
		utils.clickElement(driver, emppage.btn_calculate);
		utils.clickElement(driver, emppage.btn_save_empdetails);
//		utils.syncElement(driver, null, "alertPresent");
		utils.handleAlert(driver).accept();
		utils.clickElement(driver, emppage.btn_empdashboard);
		
		int row = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr")).size();
		int col = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//thead//th")).size();
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				String table_data = driver.findElement(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr[" + i + "]//td[" + j + "]")).getText();
				if (table_data.equalsIgnoreCase(emp_name)) {
					String valid = table_data;
					Assert.assertEquals(emp_name, valid);
					System.out.println("\u001B[1m Employee Details Matched... \u001B[0m");
					break;
				}
			}
		}
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='" + NAME + "']")).getText(), NAME);
//		driver.findElement(By.xpath("//a[@href='view_employee.php?sr_no=" + SR_NO + "']")).click();
//		List<WebElement> txtbox_employee_details = driver.findElements(By.xpath("//input[@readonly]"));		
//		for(int i = 0 ; i < txtbox_employee_details.size(); i++) {
//			String s1 = txtbox_employee_details.get(i).getAttribute("name");
//			String s2 = txtbox_employee_details.get(i).getAttribute("value");
//			System.out.println(s1 + " ---> " + s2);
//		}
	}

	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
}
