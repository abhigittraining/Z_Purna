
package com.purna_data.libraries;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilities {
	
	private static Logger logger = LoggerFactory.getLogger(Utilities.class);

	public void syncElement(WebDriver driver, WebElement element, String conditionForWait) {
		try {
			switch (conditionForWait) {
			case "ToVisible":
				new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
				break;

			case "ToClickable":
				new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
				break;

			case "ToInvisible":
				new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOf(element));
				break;

			case "alertPresent":
				new WebDriverWait(driver, 15).until(ExpectedConditions.alertIsPresent());

			default:
				throw new Exception("Invalid Condition " + conditionForWait);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void syncMultipleElement(WebDriver driver, List<WebElement> elementsList, String conditionForWait) {
		try {
			switch (conditionForWait) {
			case "ToVisible":
				new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElements(elementsList));
				break;

			case "ToInvisible":
				new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOfAllElements(elementsList));
				break;

			default:
				throw new Exception("Invalid Condition " + conditionForWait);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickElement(WebDriver driver, WebElement element) {
		syncElement(driver, element, "ToClickable");

		try {
			if (!element.isDisplayed()) {
				scrollToWebElement(driver, element);
			}
			element.click(); 
//			((JavascriptExecutor) driver).executeScript("argument[0].click();", //
//					element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToWebElement(WebDriver driver, WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickElementByJSE(WebDriver driver, WebElement element) {
		syncElement(driver, element, "ToVisible");
		try {
			((JavascriptExecutor) driver).executeScript("argument[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveToElement(WebDriver driver, WebElement element) {
		try {
			new Actions(driver).moveToElement(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendKeysThruAction(WebDriver driver, WebElement element, String stringToInput) {
		try {
			new Actions(driver).sendKeys(element, stringToInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectOptionFromList(WebDriver driver, List<WebElement> webElementList, String optionValue,
			String actionType) {
		try {
			for (WebElement webElement : webElementList) {
				if (webElement.getText().trim().equalsIgnoreCase(optionValue.trim())) {
					if (actionType.trim().equalsIgnoreCase("click"))
						clickElement(driver, webElement);
					else
						moveToElement(driver, webElement);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sleepInMilliSeconds(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getRandomString(int stringLength, String dataType) {

		StringBuilder builder = new StringBuilder();
		String stringType = null;

		switch (dataType) {
		case "Numbers":
			stringType = "0123456789";
			break;

		case "Alpha":
			stringType = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
			break;

		case "AlphaNumbers":
			stringType = "A0a1B2b3C4c5D6d7E8e9F0f1G2g3H4h5I6i7J8j9K0k1L2l3M4m5N6n7O8o9P0p1Q2q3R4r5S6s7T8t9U0u1V2v3W4w5X6x7Y8y9Z0z1";
			break;

		default:
			logger.info("Invalid input");
		}
		try {
			while (stringLength != 0) {
				int characterIndex = (int) (Math.random() * stringType.length());
				builder.append(stringType.charAt(characterIndex));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public WebElement waitForElementWithFluentWait(WebDriver driver, WebElement element, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// *******************Drop down utils *************** public void
	public void doDropDownSelectByVisibleText(WebElement element, String value) { 
		Select select = new Select(element); 
		select.selectByVisibleText(value); 
	}

	public void doDropDownSelectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void doDropDownSelectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public List<String> getAllDropDownOptionsList(WebElement element) {

		List<String> optionsValueList = new ArrayList<String>();

		Select select = new Select(element);
		List<WebElement> optionsList = select.getOptions();

		for (int i = 0; i < optionsList.size(); i++) {
			String text = optionsList.get(i).getText();
			optionsValueList.add(text);
		}

		return optionsValueList;

	}

	public void selectValueFromDropDown(WebElement element, String value) {

		Select select = new Select(element);
		List<WebElement> optionsList = select.getOptions();

		for (int i = 0; i < optionsList.size(); i++) {
			String text = optionsList.get(i).getText();
			if (text.equals(value)) {
				optionsList.get(i).click();
				break;
			}
		}

	}

	public void selectDropDownValueWithoutSelectClass(WebDriver driver, By locator, String value) {
		List<WebElement> industry_options_list = driver.findElements(locator);
		for (WebElement ele : industry_options_list) {
			String text = ele.getText();
			if (text.equals(value)) {
				ele.click();
				break;
			}
		}
	}

	public Alert handleAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert;
	}
}
