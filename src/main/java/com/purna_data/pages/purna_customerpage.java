package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_customerpage {
	public purna_customerpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="sr_no")
	public WebElement txt_customerno;
	
	@FindBy(id="customer_name")
	public WebElement txt_customer_name;
	
	@FindBy(id="contact_no")
	public WebElement txt_contact_no;
	
	@FindBy(id="billing_address")
	public WebElement txt_billing_address;
	
	@FindBy(id="shipping_address")
	public WebElement txt_shipping_address;
	
	@FindBy(id="email")
	public WebElement txt_email;
	
	@FindBy(id="contact_person")
	public WebElement txt_contact_person;

	@FindBy(id="person_no")
	public WebElement txt_person_no;
	
	@FindBy(id="gst_no")
	public WebElement txt_gst_no;
	
	@FindBy(id="pan_no")
	public WebElement txt_pan_no;
	
	@FindBy(id="vendor_code_no")
	public WebElement txt_vendor_code_no;
	
	@FindBy(id="btn")
	public WebElement btn_save_customer_details;
	
	@FindBy(xpath="(//a[@href='customer_dashboard.php'])[2]")
	public WebElement btn_customer_details;
}