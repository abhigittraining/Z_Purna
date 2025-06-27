package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_supplierpage {
	public purna_supplierpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="sr_no")
	public WebElement txt_sr_no;
	
	@FindBy(id="supplier_name")
	public WebElement txt_supplier_name;
	
	@FindBy(id="contact_no")
	public WebElement txt_contact_no;
	
	@FindBy(id="address")
	public WebElement txt_address;
	
	@FindBy(id="email_id")
	public WebElement txt_email_id;
	
	@FindBy(id="contact_person")
	public WebElement txt_contact_person;
	
	@FindBy(id="cont_no")
	public WebElement txt_cont_no;
	
	@FindBy(id="gst_no")
	public WebElement txt_gst_no;
	
	@FindBy(id="pan_no")
	public WebElement txt_pan_no;
	
	@FindBy(id="bank_name")
	public WebElement txt_bank_name;
	
	@FindBy(id="bank_addr")
	public WebElement txt_bank_addr;
	
	@FindBy(id="account_no")
	public WebElement txt_account_no;
	
	@FindBy(id="ifsc_code")
	public WebElement txt_ifsc_code;
	
	@FindBy(id="btn")
	public WebElement btn_save_supplierdetails;
	
	@FindBy(xpath="(//a[@href='supplier_dashboard.php'])[2]")
	public WebElement btn_supplierdetails;
}