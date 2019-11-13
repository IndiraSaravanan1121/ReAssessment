package com.atmecs.testscript;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atmecs.config.Constants;
import com.atmecs.helper.MyException;
import com.atmecs.utils.ExcelReader;
import com.atmecs.utils.TestBase;

public class HeatClinic extends TestBase {
	public ExcelReader readExcel = new ExcelReader(Constants.TESTDATA_PATH);

	@BeforeTest
	public void startBrowser() throws Exception {
		try {
			openBrowser();
			driver.get(property.properties("heatclinicurl", Constants.CONFIG_PATH));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MyException ex) {
			log.error("exception occur" + ex.getMessage());
		}
	}

	@Test(priority = 0)
	public void verifyUserOnRespectivePage() throws Exception {
		log.info("1.Verify user redirect to respective page");
		pages.userOnRespectivePage(driver, "validateuserpage", 1, 0);
		helper.clickElement(driver, property.properties("loc_hotsauce_lbl", Constants.HEATCLINICLOCATOR_PATH));
		pages.userOnRespectivePage(driver, "validateuserpage", 2, 0);
		helper.clickElement(driver, property.properties("loc_merchandise_lbl", Constants.HEATCLINICLOCATOR_PATH));
		pages.userOnRespectivePage(driver, "validateuserpage", 3, 0);
		helper.clickElement(driver, property.properties("loc_clearance_lbl", Constants.HEATCLINICLOCATOR_PATH));
		pages.userOnRespectivePage(driver, "validateuserpage", 4, 0);
		helper.clickElement(driver, property.properties("loc_newtohotsauce_lbl", Constants.HEATCLINICLOCATOR_PATH));
		pages.userOnRespectivePage(driver, "validateuserpage", 5, 0);
		helper.clickElement(driver, property.properties("loc_faq_lbl", Constants.HEATCLINICLOCATOR_PATH));
		pages.userOnRespectivePage(driver, "validateuserpage", 6, 0);
	}

	@Test(priority = 1)
	public void verifyViewingMensText() throws Exception {
		log.info("2.Verify viewing mens text is diaplayed");
		helper.moveToElements(driver, property.properties("loc_merchandise_lbl", Constants.HEATCLINICLOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_mens_lbl", Constants.HEATCLINICLOCATOR_PATH));
		helper.isDisplay(driver, property.properties("loc_viewingmenu_lbl", Constants.HEATCLINICLOCATOR_PATH));
	}

	@Test(priority = 2)
	public void buyHabaneroShirt() throws Exception {
		log.info("3.Buy Habanero shirt with specified color and size");
		helper.clickElement(driver, property.properties("loc_buynow_lbl", Constants.HEATCLINICLOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_redcolor_btn", Constants.HEATCLINICLOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_shirtsize_btn", Constants.HEATCLINICLOCATOR_PATH));
		String personalizedName = readExcel.getData("personalizename", 1, 0);
		helper.sendValues(driver, property.properties("loc_personalizedname_txt", Constants.HEATCLINICLOCATOR_PATH),
				personalizedName);
		helper.clickElement(driver, property.properties("loc_buynow_btn", Constants.HEATCLINICLOCATOR_PATH));
	}

	@Test(priority = 3)
	public void validateCartItem() throws Exception {
		log.info("4.Validate item in the cart");
		helper.clickElement(driver, property.properties("loc_carttotal_btn", Constants.HEATCLINICLOCATOR_PATH));
		String actualProductDetail = helper.getText(driver,
				property.properties("loc_productinfo_txt", Constants.HEATCLINICLOCATOR_PATH));
		String expectedproductDetail = readExcel.getData("validateproductdetail", 1, 0);
		Assert.assertEquals(actualProductDetail, expectedproductDetail);
	}

	@Test(priority = 4)
	public void verifyTotalPriceUpdate() {

	}

	@AfterTest
	public void endBrowser() {
		try {
			closeBrowser();
		} catch (MyException ex) {
			log.error("exception occur" + ex.getMessage());
		}
	}
}
