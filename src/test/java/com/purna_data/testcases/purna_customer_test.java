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
import com.purna_data.pages.purna_customerdashboardpage;
import com.purna_data.pages.purna_customerpage;
import com.purna_data.pages.purna_dashboardpage;
import com.purna_data.pages.purna_loginpage;

public class purna_customer_test extends BaseClass {

	purna_loginpage loginpage;
	purna_dashboardpage dashboardpage;
	purna_customerdashboardpage customerdashboardpage;
	purna_customerpage customerpage;
	Utilities utils;
	ExcelUtils el;

	@BeforeMethod
	public void login() {
		initialization();
		loginpage = new purna_loginpage(driver);
		dashboardpage = new purna_dashboardpage(driver);
		customerdashboardpage = new purna_customerdashboardpage(driver);
		customerpage = new purna_customerpage(driver);
		utils = new Utilities();
		el = new ExcelUtils();
		loginpage.loginpurna(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getCustomerData() {
		Object[][] data = ExcelUtils.getTestData("customer");
		return data;
	}

	@Test(dataProvider = "getCustomerData")
	public void adding_new_customer(/*String CUSTOMER_NO,*/ String CUSTOMER_NAME, String CONTACT_NO, String BILLING_ADDRESS,
			String SHIPPING_ADDRESS, String E_MAIL_ID, String CONTACT_PERSON, String CONTACT, String GST_NO,
			String PAN_NO, String VENDOR_CODE)
	{
		utils.clickElement(driver, dashboardpage.link_main);
		utils.clickElement(driver, dashboardpage.link_customer);
//		Thread.sleep(300);
		utils.clickElement(driver, customerdashboardpage.btn_addnewcustomer);	
//		utils.clickElement(driver, customerpage.txt_customerno);
//		customerpage.txt_customerno.click();
//		customerpage.txt_customerno.clear();
//		customerpage.txt_customerno.sendKeys(CUSTOMER_NO);
		customerpage.txt_customer_name.sendKeys(CUSTOMER_NAME);
		String customer_name = customerpage.txt_customer_name.getAttribute("value");
		customerpage.txt_contact_no.sendKeys(CONTACT_NO);
		customerpage.txt_billing_address.clear();
		customerpage.txt_billing_address.sendKeys(BILLING_ADDRESS);
		customerpage.txt_shipping_address.clear();
		customerpage.txt_shipping_address.sendKeys(SHIPPING_ADDRESS);
		customerpage.txt_email.sendKeys(E_MAIL_ID);
		customerpage.txt_contact_person.sendKeys(CONTACT_PERSON);
		customerpage.txt_person_no.sendKeys(CONTACT);
		customerpage.txt_gst_no.sendKeys(GST_NO);
		customerpage.txt_pan_no.sendKeys(PAN_NO);
		customerpage.txt_vendor_code_no.sendKeys(VENDOR_CODE);
		utils.clickElement(driver, customerpage.btn_save_customer_details);
//		utils.syncElement(driver, null, "alertPresent");
		utils.handleAlert(driver).accept();
		utils.clickElement(driver, customerpage.btn_customer_details);
		
		int row = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr")).size();
		int col = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//thead//th")).size();
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				String table_data = driver.findElement(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr[" + i + "]//td[" + j + "]")).getText();
				if (table_data.equalsIgnoreCase(customer_name)) {
					String valid = table_data;
					Assert.assertEquals(customer_name, valid);
					System.out.println("\u001B[1m Customer Details Matched \u001B[0m");
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
