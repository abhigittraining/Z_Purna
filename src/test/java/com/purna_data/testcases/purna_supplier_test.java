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
import com.purna_data.pages.purna_supplierdashboardpage;
import com.purna_data.pages.purna_supplierpage;

public class purna_supplier_test extends BaseClass {

	purna_loginpage loginpage;
	purna_dashboardpage dashboardpage;
	purna_supplierdashboardpage supplierdashboardpage;
	purna_supplierpage supplierpage;
	Utilities utils;
	ExcelUtils el;

	@BeforeMethod
	public void login() {
		initialization();
		loginpage = new purna_loginpage(driver);
		dashboardpage = new purna_dashboardpage(driver);
		supplierdashboardpage = new purna_supplierdashboardpage(driver);
		supplierpage = new purna_supplierpage(driver);
		utils = new Utilities();
		loginpage.loginpurna(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSupplierData() {
		Object[][] data = ExcelUtils.getTestData("supp");
		return data;
	}

	@Test(dataProvider = "getSupplierData")
	public void adding_new_supplier(/*String vendorcode,*/ String suppliername, String contactno, String address,
			String emailid, String contactperson, String contno, String gst, String pan, String bankname,
			String bankadd, String accno, String ifsc)
	{
		utils.clickElement(driver, dashboardpage.link_main);
		utils.clickElement(driver, dashboardpage.link_supplier);
//		Thread.sleep(300);
		utils.clickElement(driver, supplierdashboardpage.btn_addnewsupplier);
//		supplierpage.txt_sr_no.clear();
//		supplierpage.txt_sr_no.sendKeys(vendorcode);
		supplierpage.txt_supplier_name.sendKeys(suppliername);
		String supplier_name = supplierpage.txt_supplier_name.getAttribute("value");
		supplierpage.txt_contact_no.sendKeys(contactno);
		supplierpage.txt_address.clear();
		supplierpage.txt_address.sendKeys(address);
		supplierpage.txt_email_id.sendKeys(emailid);
		supplierpage.txt_contact_person.sendKeys(contactperson);
		supplierpage.txt_cont_no.sendKeys(contno);
		supplierpage.txt_gst_no.sendKeys(gst);
		supplierpage.txt_pan_no.sendKeys(pan);
		supplierpage.txt_bank_name.sendKeys(bankname);
		supplierpage.txt_bank_addr.sendKeys(bankadd);
		supplierpage.txt_account_no.sendKeys(accno);
		supplierpage.txt_ifsc_code.sendKeys(ifsc);
		utils.clickElement(driver, supplierpage.btn_save_supplierdetails);
//		utils.syncElement(driver, null, "alertPresent");
		utils.handleAlert(driver).accept();
		utils.clickElement(driver, supplierpage.btn_supplierdetails);
		
		int row = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr")).size();
		int col = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//thead//th")).size();
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				String table_data = driver.findElement(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr[" + i + "]//td[" + j + "]")).getText();
				if (table_data.equalsIgnoreCase(supplier_name)) {
					String valid = table_data;
					Assert.assertEquals(supplier_name, valid);
					System.out.println("\u001B[1m Supplier Details Matched... \u001B[0m");
					break;
				}
			}
		}
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}