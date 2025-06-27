package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_projectpage {
	public purna_projectpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="sr_no")
	public WebElement txt_sr_no ;
	
	@FindBy(id="project_code")
	public WebElement txt_project_code;
	
	@FindBy(id="project_name")
	public WebElement txt_project_name;
	
	@FindBy(id="remark")
	public WebElement txt_remark;
	
	@FindBy(id="budget_amount")
	public WebElement txt_budget_amount;
	
	@FindBy(id="cust_name")
	public WebElement dropdown_cust_name;
	
	@FindBy(id="btn")
	public WebElement btn_save_projectdetails;
	
	@FindBy(xpath="(//a[@href=\"project_dashboard.php\"])[2]")
	public WebElement btn_projectdetails;
}