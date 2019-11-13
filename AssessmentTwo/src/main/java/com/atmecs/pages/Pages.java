package com.atmecs.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.atmecs.config.Constants;
import com.atmecs.helper.MyException;
import com.atmecs.utils.ExcelReader;
import com.atmecs.utils.TestBase;

public class Pages extends TestBase {
	public ExcelReader readExcel = new ExcelReader(Constants.TESTDATA_PATH);

	String url;
	String expectedUrl;

	public void userOnRespectivePage(WebDriver driver,String sheetName,int rownum,int colnum) {
	    url = driver.getCurrentUrl();
		expectedUrl = readExcel.getData(sheetName,rownum,colnum);
		Assert.assertEquals(url, expectedUrl);	
	}
	
	public void addToCart() throws MyException, IOException {
		helper.clickElement(driver, property.properties("loc_macbook_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.clearValues(driver,
				property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		helper.sendValues(driver, property.properties("loc_productsquantity_lbl", Constants.TUTORIALSNINJALOCATOR_PATH),
				readExcel.getData("tutorialsninja", 1, 1));
		helper.clickElement(driver, property.properties("loc_arttocart_lbl", Constants.TUTORIALSNINJALOCATOR_PATH));
		driver.navigate().back();
	}
}
