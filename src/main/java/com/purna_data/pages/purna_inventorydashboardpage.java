package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_inventorydashboardpage {
	public purna_inventorydashboardpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="btn_new_party")
	public WebElement btn_addnewitem;
	
	@FindBy(xpath = "//a[@href='view_purchase_item.php?sr_no=1']")
	public WebElement btn_view;
}
