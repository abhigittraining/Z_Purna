package com.purna_data.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class purna_inventorypage {
	public purna_inventorypage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "sr_no")
	public WebElement txt_sr_no;
	
	@FindBy(id = "itemcode")
	public WebElement txt_itemcode;
	
	@FindBy(id = "itemname")
	public WebElement txt_itemname;
	
	@FindBy(name = "units")
	public WebElement dropdown_units;
	
	@FindBy(id = "supp_product_code")
	public WebElement txt_supp_product_code;
	
	@FindBy(id = "drawing_no")
	public WebElement txt_drawing_no;
	
	@FindBy(id = "spec_no")
	public WebElement txt_spec_no;
	
	@FindBy(id = "weight")
	public WebElement txt_weight;
	
	@FindBy(id = "packing")
	public WebElement txt_packing;
	
	@FindBy(id = "txt_rate")
	public WebElement txt_rate;
	
	@FindBy(id = "txt_discount")
	public WebElement txt_discount;
	
	@FindBy(id = "txt_qty")
	public WebElement txt_qty;
	
	@FindBy(id = "txt_amount")
	public WebElement txt_amount;
	
	@FindBy(name = "submit")
	public WebElement btn_save_item;
	
	@FindBy(xpath = "(//a[@href='purchase_item_dashboard.php'])[2]")
	public WebElement btn_itemdetails;
}