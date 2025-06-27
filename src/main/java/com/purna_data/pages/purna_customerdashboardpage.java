package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_customerdashboardpage {
	public purna_customerdashboardpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="btn_new_party")
	public WebElement btn_addnewcustomer;
	
	@FindBy(xpath="//a[@href='view_customer.php?sr_no=1']")
	public WebElement btn_viewcustdetails;
}
