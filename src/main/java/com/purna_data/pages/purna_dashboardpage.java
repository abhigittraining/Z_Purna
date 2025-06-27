package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_dashboardpage {
	public purna_dashboardpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//span[@class='menu-text'])[2]")
	public WebElement link_main;
	
	@FindBy(xpath="//a[@href='customer_dashboard.php']")
	public WebElement link_customer;
	
	@FindBy(xpath="//a[@href='employee_dashboard.php']")
	public WebElement link_employee;
	
	@FindBy(xpath="//a[@href='project_dashboard.php']")
	public WebElement link_project;
	
	@FindBy(xpath="//a[@href='supplier_dashboard.php']")
	public WebElement link_supplier;
	
	@FindBy(xpath="//a[@href='purchase_item_dashboard.php']")
	public WebElement link_inventory;
}
