package com.qa.opencart.test;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import junit.framework.Assert;

public class ProductInfoPageTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productCountTest() {
		searchResPage = accPage.doSearch("iMac");
		Assert.assertTrue(searchResPage.getProductResultsCount() == 1);
	}

	@Test
	public void productInfoHeaderTest() {
		searchResPage = accPage.doSearch("iMac");
		productInfoPage = searchResPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductHeaderText(), "iMac");
	}
	
	@Test
	public void productImagesTest(){
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("Macbook Pro");
		Assert.assertTrue(productInfoPage.getProductImageCount() == 4);
	}
	
	@Test
	public void getProductInfoTest(){
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("Macbook Pro");
		Map<String, String> actProductMetaData = productInfoPage.getProductInformation();
		actProductMetaData.forEach((k,v) -> System.out.println (k + " : " + v));
		
		Assert.assertEquals(actProductMetaData.get("name"), "Macbook Pro");
		Assert.assertEquals(actProductMetaData.get("Brand"), "Apple");
		Assert.assertEquals(actProductMetaData.get("Availability"), "Out Of Stock");
		Assert.assertEquals(actProductMetaData.get("price"), "$2,000.00");
		
		softAssert.assertAll();	
	}
	
	@Test
	public void addToCartTest(){
		
		
	}
}