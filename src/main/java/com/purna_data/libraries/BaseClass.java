package com.purna_data.libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	
	private static Logger logger = LoggerFactory.getLogger(BaseClass.class);
	
	public BaseClass(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/other/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public void initialization(){
		String browserName = prop.getProperty("browser");		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ prop.getProperty("chromedriver.path"));			
			driver = new ChromeDriver(); 
			logger.info("Launching chrome browser");
		}
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ prop.getProperty("iedriver.path"));				
			driver = new InternetExplorerDriver(); 
			logger.info("Launching IE browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(ExcelUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ExcelUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		logger.info("Launched browser successfully");
	}
}
