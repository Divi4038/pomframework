package com.qa.opencart.test;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ErrorMSG;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup(){
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test
	public void accPageTitleTest(){
		String title = accPage.getAccPageTitle();
		System.out.println("Account page tilte is: " + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE, ErrorMSG.ACC_PAGE_TITLE_ERROR);
	}
	
	@Test
	public void accPageHeaderTest(){
		String header = accPage.GetAccPageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER, ErrorMSG.ACC_PAGE_HEADER_ERROR);
	}
	
	@Test
	public void accSesctionsListTest(){
		List<String> secList = accPage.getAccountSectionList();
		secList.stream().forEach(e -> System.out.println(e));
		Collections.sort(Constants.EXP_ACC_SEC_LIST);
		Assert.assertEquals(secList, Constants.EXP_ACC_SEC_LIST);
	}
	
	@Test
	public void logoutLinkTest(){
		Assert.assertTrue(accPage.isLogoutExist(), ErrorMSG.LOGOUT_LINK_NOT_PRESENT);
		
	}
}
