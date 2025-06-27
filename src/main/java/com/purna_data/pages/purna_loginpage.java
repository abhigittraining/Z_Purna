package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_loginpage {

	public purna_loginpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	public WebElement txtbox_username;

	@FindBy(id = "password")
	public WebElement txtbox_password;

	@FindBy(name = "submit")
	public WebElement btn_login;

	public void loginpurna(String username, String password) {
		txtbox_username.sendKeys(username);
		txtbox_password.sendKeys(password);
		btn_login.click();
	}
}