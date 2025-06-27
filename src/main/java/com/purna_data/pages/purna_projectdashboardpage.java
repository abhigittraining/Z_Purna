package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_projectdashboardpage {
	public purna_projectdashboardpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Add New project']")
	public WebElement btn_addnewproject;
	
	@FindBy(xpath = "//a[@href='view_project.php?sr_no=5']")
	public WebElement btn_view;
}
