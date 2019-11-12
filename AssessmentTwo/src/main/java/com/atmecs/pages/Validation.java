package com.atmecs.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.atmecs.config.Constants;
import com.atmecs.utils.ExcelReader;
import com.atmecs.utils.TestBase;

public class Validation extends TestBase{
	public ExcelReader readExcel=new ExcelReader(Constants.TESTDATA_PATH);

	String url;
	String expectedUrl;
	public void userOnRespectivePage(WebDriver driver,String sheetName,int rownum,int colnum) {
		url = driver.getCurrentUrl();
		expectedUrl = readExcel.getData(sheetName,rownum,colnum);
		Assert.assertEquals(url, expectedUrl);		
	}
}
