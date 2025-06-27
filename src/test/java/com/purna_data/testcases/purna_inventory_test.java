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
import com.purna_data.pages.purna_inventorydashboardpage;
import com.purna_data.pages.purna_inventorypage;
import com.purna_data.pages.purna_loginpage;

public class purna_inventory_test extends BaseClass{
	
	purna_loginpage loginpage;
	purna_dashboardpage dashboardpage;
	purna_inventorydashboardpage inventorydashboardpage;
	purna_inventorypage inventorypage;
	Utilities utils;

	@BeforeMethod
	public void login() {
		initialization();
		loginpage = new purna_loginpage(driver);
		dashboardpage = new purna_dashboardpage(driver);
		inventorydashboardpage = new purna_inventorydashboardpage(driver);
		inventorypage = new purna_inventorypage(driver);
		utils = new Utilities();
		loginpage.loginpurna(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getInventoryData(){
		Object[][] data = ExcelUtils.getTestData("inventory");
		return data;
	}
	@Test(dataProvider = "getInventoryData")
	public void adding_new_inventory(/*String SR_NO,*/ String ITEM_CODE, String ITEM_NAME, String UNITS,
			String SUPPLIER_PRODUCT_CODE, String DAWING_NO, String SPECIFICATION_NO, String WEIGHT, String REMARKS,
			String RATE, String DISCOUNT, String QUANTITY)
	{
		utils.clickElement(driver, dashboardpage.link_main);
		utils.clickElement(driver, dashboardpage.link_inventory);
		utils.clickElement(driver, inventorydashboardpage.btn_addnewitem);
//		inventorypage.txt_sr_no.clear();
//		inventorypage.txt_sr_no.sendKeys(SR_NO);
		inventorypage.txt_itemcode.sendKeys(ITEM_CODE);
		inventorypage.txt_itemname.sendKeys(ITEM_NAME);
		String item_name = inventorypage.txt_itemname.getAttribute("value");
		utils.doDropDownSelectByVisibleText(inventorypage.dropdown_units, UNITS);
		inventorypage.txt_supp_product_code.sendKeys(SUPPLIER_PRODUCT_CODE);
		inventorypage.txt_drawing_no.sendKeys(DAWING_NO);
		inventorypage.txt_spec_no.sendKeys(SPECIFICATION_NO);
		inventorypage.txt_weight.sendKeys(WEIGHT);
		inventorypage.txt_packing.clear();
		inventorypage.txt_packing.sendKeys(REMARKS);
		inventorypage.txt_rate.sendKeys(RATE);
		inventorypage.txt_discount.clear();
		inventorypage.txt_discount.sendKeys(DISCOUNT);
		inventorypage.txt_qty.sendKeys(QUANTITY);
		utils.clickElement(driver, inventorypage.txt_amount);
		utils.clickElement(driver, inventorypage.btn_save_item);
		utils.clickElement(driver, inventorypage.btn_itemdetails);
		
		int row = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr")).size();
		int col = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//thead//th")).size();
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				String table_data = driver.findElement(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr[" + i + "]//td[" + j + "]")).getText();
				if (table_data.equalsIgnoreCase(item_name)) {
					String valid = table_data;
					Assert.assertEquals(item_name, valid);
					System.out.println("\u001B[1m Inventory Details Matched \u001B[0m");
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
