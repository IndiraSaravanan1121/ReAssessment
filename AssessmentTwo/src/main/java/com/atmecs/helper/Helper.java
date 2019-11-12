package com.atmecs.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.atmecs.utils.TestBase;

/**
 * This class
 * 
 * @author indira.saravanan
 *
 */
public class Helper extends TestBase {
	public WebElement webElement = null;

	public WebElement findLocators(WebDriver driver, String locatorType) {
		String[] locator = locatorType.split(":");
		try {
			switch (locator[0]) {
			case "class":
				webElement = driver.findElement(By.className(locator[1]));
				break;
			case "css":
				webElement = driver.findElement(By.cssSelector(locator[1]));
				break;
			case "id":
				webElement = driver.findElement(By.id(locator[1]));
				break;
			case "linktext":
				webElement = driver.findElement(By.linkText(locator[1]));
				break;
			case "name":
				webElement = driver.findElement(By.name(locator[1]));
				break;
			case "partiallinktext":
				webElement = driver.findElement(By.partialLinkText(locator[1]));
				break;
			case "tagname":
				webElement = driver.findElement(By.tagName(locator[1]));
				break;
			case "xpath":
				webElement = driver.findElement(By.xpath(locator[1]));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webElement;

	}

	public void clickElement(WebDriver driver, String element) {
		webElement = findLocators(driver, element);
		webElement.click();
	}

	public String getText(WebDriver driver, String element) {
		webElement = findLocators(driver, element);
		return webElement.getText();
	}

	public void moveToElements(WebDriver driver, String element) {
		Actions actions = new Actions(driver);
		webElement = findLocators(driver, element);
		actions.moveToElement(webElement);
	}

	public void isDisplay(WebDriver driver, String element) {
		webElement = findLocators(driver, element);
		webElement.isDisplayed();
	}

	public void sendValues(WebDriver driver, String element, String values) {
		webElement = findLocators(driver, element);
		webElement.sendKeys(values);
	}

	public void clearValues(WebDriver driver, String element) {
		webElement = findLocators(driver, element);
		webElement.sendKeys(Keys.BACK_SPACE);
	}
}
