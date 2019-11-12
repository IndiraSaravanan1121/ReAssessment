package com.atmecs.testscript;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atmecs.config.Constants;
import com.atmecs.utils.ExcelReader;
import com.atmecs.utils.TestBase;

public class TutorialsNinja extends TestBase {

	public ExcelReader readExcel = new ExcelReader(Constants.TESTDATA_PATH);

	@BeforeTest
	@Parameters("url")
	public void startBrowser(String url) throws Exception {
		openBrowser();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void validateHomepage() throws Exception {
		log.info("1.HomePage Validation");
		String actualTitle = driver.getTitle();
		String expectedTitle = readExcel.getData("tutorialsninja", 1, 0);
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("HomePage is validated");
	}

	@Test(priority = 1)
	public void addToCart() throws Exception {
		log.info("2.Add product to the cart");
		helper.clickElement(driver, property.properties("loc_macbook_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clearValues(driver,
				property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.sendValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH),
				readExcel.getData("tutorialsninja", 1, 1));
		helper.clickElement(driver, property.properties("loc_arttocart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		driver.navigate().back();
		helper.clickElement(driver, property.properties("loc_iphone_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clearValues(driver,
				property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.sendValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH),
				readExcel.getData("tutorialsninja", 1, 2));
		helper.clickElement(driver, property.properties("loc_arttocart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		driver.navigate().back();
	}

	@Test(priority = 2)
	public void verifyProductInCart() throws Exception {
		log.info("3.Verify product available in the cart");
		helper.clickElement(driver, property.properties("loc_carttotal_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_viewcart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		String macbookQuantity = helper.getText(driver,
				property.properties("loc_macbookquantity_txt", Constants.TUTORIALSNINJALOCATOR_PATH));
		System.out.println(macbookQuantity);
		String iphoneQuantity = helper.getText(driver,
				property.properties("loc_iphonequantity_txt", Constants.TUTORIALSNINJALOCATOR_PATH));
		System.out.println(iphoneQuantity);

	}

	@Test(priority = 3)
	public void validateProductDetail() throws Exception {
		log.info("4.Validate product details");
		helper.clickElement(driver, property.properties("loc_macbook_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		String actualAvailability = helper.getText(driver,
				property.properties("loc_productavailability_txt", Constants.TUTORIALSNINJALOCATOR_PATH));
		String expectedAvailability = readExcel.getData("validateproductdetail", 1, 0);
		Assert.assertEquals(actualAvailability, expectedAvailability);
		String actualPriceAndTax = helper.getText(driver,
				property.properties("loc_productpriceandtax_txt", Constants.TUTORIALSNINJALOCATOR_PATH));
		String expectedPriceAndTax = readExcel.getData("validateproductdetail", 1, 0);
		Assert.assertEquals(actualPriceAndTax, expectedPriceAndTax);

	}

	@Test(priority = 4)
	public void verifyGrandTotal() throws Exception {
		log.info("5.Verify grant total and its update");
		String grandTotal = helper.getText(driver,
				property.properties("loc_grandtotal_txt", Constants.TUTORIALSNINJALOCATOR_PATH));
		System.out.println(grandTotal);
		helper.clickElement(driver,
				property.properties("loc_removecartitem_btn", Constants.TUTORIALSNINJALOCATOR_PATH));
		String updatedGrandTotal = helper.getText(driver,
				property.properties("loc_grandtotal_txt", Constants.TUTORIALSNINJALOCATOR_PATH));
		System.out.println(updatedGrandTotal);
	}

	@AfterTest
	public void endBrowser() {
		closeBrowser();
	}
}
