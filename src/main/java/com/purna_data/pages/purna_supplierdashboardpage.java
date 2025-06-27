package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_supplierdashboardpage {
	public purna_supplierdashboardpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="btn_new_party")
	public WebElement btn_addnewsupplier;
	
	@FindBy(xpath="//a[@href='view_supplier.php?sr_no=3']")
	public WebElement btn_view;
}
