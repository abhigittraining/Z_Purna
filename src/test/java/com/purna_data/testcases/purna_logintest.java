package com.purna_data.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.purna_data.libraries.BaseClass;
import com.purna_data.pages.purna_loginpage;

public class purna_logintest extends BaseClass{
	
	purna_loginpage loginpage;
	@BeforeMethod
	public void init() {
		initialization();
		loginpage = new purna_loginpage(driver);
	}
	
	@Test(priority = -1)
	public void loginsuccess() {
		loginpage.loginpurna(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(driver.getCurrentUrl(), "https://purnadata.in/PurnaDemo/sale_dashboard.php");
		System.out.println("login successful");
	}
	@Test
	public void loginfail() {
		loginpage.loginpurna("admin1" , "admin1");
		Assert.assertEquals(driver.getCurrentUrl(), "https://purnadata.in/PurnaDemo/index.php");
		System.out.println("login failed");
	}
	
	@AfterMethod
	public void closebrowser() {
		driver.quit();
	}
}
