package com.atmecs.testscript;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.config.Constants;
import com.atmecs.utils.ExcelReader;
import com.atmecs.utils.TestBase;

public class HeatClinic extends TestBase {
	public ExcelReader readExcel=new ExcelReader(Constants.HEATCLINICTESTDATA_PATH);

	@BeforeTest
	public void startBrowser() throws Exception {
		openBrowser();
	}

	@Test(priority = 0)
	public void verifyUserOnRespectivePage() throws Exception {
		validate.userOnRespectivePage(driver, "validateuserpage", 1, 0);
		helper.clickElement(driver, property.properties("loc_hotsauce_lbl", Constants.HEATCLINICLOCATOR_PATH));
		validate.userOnRespectivePage(driver, "validateuserpage", 2, 0);
		helper.clickElement(driver, property.properties("loc_merchandise_lbl", Constants.HEATCLINICLOCATOR_PATH));
		validate.userOnRespectivePage(driver, "validateuserpage", 3, 0);
		helper.clickElement(driver, property.properties("loc_clearance_lbl", Constants.HEATCLINICLOCATOR_PATH));
		validate.userOnRespectivePage(driver, "validateuserpage", 4, 0);
		helper.clickElement(driver, property.properties("loc_newtohotsauce_lbl", Constants.HEATCLINICLOCATOR_PATH));
		validate.userOnRespectivePage(driver, "validateuserpage", 5, 0);
		helper.clickElement(driver, property.properties("loc_faq_lbl", Constants.HEATCLINICLOCATOR_PATH));
		validate.userOnRespectivePage(driver, "validateuserpage", 6, 0);
	}

	@Test(priority=1)
	public void verifyViewingMensText() throws Exception {
	helper.moveToElements(driver, property.properties("loc_merchandise_lbl", Constants.HEATCLINICLOCATOR_PATH));
	helper.clickElement(driver, property.properties("loc_mens_lbl", Constants.HEATCLINICLOCATOR_PATH));
	helper.isDisplay(driver, property.properties("loc_viewingmenu_lbl", Constants.HEATCLINICLOCATOR_PATH));
	}
	
	@Test(priority=2)
	public void buyHabaneroShirt() throws Exception {
		helper.clickElement(driver, property.properties("loc_buynow_lbl", Constants.HEATCLINICLOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_redcolor_btn", Constants.HEATCLINICLOCATOR_PATH));
		helper.clickElement(driver, property.properties("loc_shirtsize_btn", Constants.HEATCLINICLOCATOR_PATH));
		String personalizedName=readExcel.getData("personalizename",1,0);
		helper.sendValues(driver, property.properties("loc_personalizedname_txt", Constants.HEATCLINICLOCATOR_PATH),
				personalizedName);
		helper.clickElement(driver, property.properties("loc_buynow_btn", Constants.HEATCLINICLOCATOR_PATH));
	}
	
	@Test(priority=3)
	public void validateCartItem() throws Exception {
		helper.clickElement(driver, property.properties("loc_carttotal_btn", Constants.HEATCLINICLOCATOR_PATH));
		String actualProductDetail=helper.getText(driver, property.properties("loc_productinfo_txt", Constants.HEATCLINICLOCATOR_PATH));
		String expectedproductDetail=readExcel.getData("validateproductdetail",1,0);
		Assert.assertEquals(actualProductDetail,expectedproductDetail);
	}
	
	@Test(priority=4)
	public void verifyTotalPriceUpdate() {
		
	}

	@AfterTest
	public void endBrowser() {
		closeBrowser();
	}
}
