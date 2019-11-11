package com.atmecs.testscript;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.config.Constants;
import com.atmecs.utils.ExcelReader;
import com.atmecs.utils.TestBase;

public class TutorialsNinja extends TestBase {
	public static ExcelReader readExcel=new ExcelReader(Constants.TUTORIALSNINJATESTDATA_PATH);

	@BeforeTest
	public void startBrowser() throws Exception {
		openBrowser();
	}

	@Test(priority=0)
	public void validateHomepage() throws Exception {
		String actualTitle=driver.getTitle();
		String expectedTitle=readExcel.getData("validatehomepage", 1, 0);
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Homepage is validated");
	}
	
	@Test(priority=1)
	public void addToCart() throws Exception  {
		helper.clickElement(driver, property.properties("loc_macbook_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clearValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.sendValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH), "2");
		helper.clickElement(driver, property.properties("loc_arttocart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		driver.navigate().back();
		helper.clickElement(driver, property.properties("loc_iphone_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clearValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.sendValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH), "3");
		helper.clickElement(driver, property.properties("loc_arttocart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		driver.navigate().back();
	}
	
	@Test(priority=2)
	public void verifyProductInCart() throws Exception {
		helper.clickElement(driver, property.properties("loc_carttotal_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_viewcart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
	}

	
	@AfterTest
	public void endBrowser() {
		closeBrowser();
	}
}
