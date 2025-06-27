package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_employeepage {
	public purna_employeepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="sr_no")
	public WebElement txt_sr_no;
	
	@FindBy(id="name")
	public WebElement txt_name;
	
	@FindBy(id="date_of_joining")
	public WebElement txt_date_of_joining;
	
	@FindBy(id="department")
	public WebElement txt_department;
	
	@FindBy(id="designation")
	public WebElement txt_designation;
	
	@FindBy(id="mobile")
	public WebElement txt_mobile;
	
	@FindBy(id="altr_cont")
	public WebElement txt_altr_cont;
	
	@FindBy(id="pan_no")
	public WebElement txt_pan_no;
	
	@FindBy(id="email")
	public WebElement txt_email;
	
	@FindBy(id="area")
	public WebElement txt_area;
	
	@FindBy(id="region")
	public WebElement txt_region;
	
	@FindBy(id="birthdate")
	public WebElement txt_birthdate;
	
	@FindBy(id="bloodgroup")
	public WebElement txt_bloodgroup;
	
	@FindBy(id="address")
	public WebElement txt_address;
	
	@FindBy(id="passport")
	public WebElement txt_passport;
	
	@FindBy(id="emp_id")
	public WebElement txt_emp_id;
	
	@FindBy(id="bank_name")
	public WebElement txt_bank_name;
	
	@FindBy(id="bank_addr")
	public WebElement txt_bank_addr;
	
	@FindBy(id="acc_no")
	public WebElement txt_acc_no;

	@FindBy(id="ifsc_code")
	public WebElement txt_ifsc_code;
	
	@FindBy(id="salary")
	public WebElement txt_salary;
	
	@FindBy(id="no_of_days")
	public WebElement txt_no_of_days;
	
	@FindBy(id="myButton")
	public WebElement btn_calculate;
	
	@FindBy(id="btn")
	public WebElement btn_save_empdetails;
	
	@FindBy(xpath="(//a[@href='employee_dashboard.php'])[2]")
	public WebElement btn_empdashboard;
	
}