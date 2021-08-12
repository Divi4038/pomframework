package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void loginPageUrlTest(){
		String url = loginPage.getLoginPageUrl();
		System.out.println("Login Page URL is : " + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_URL_VALUE));
	}
	
	@Test(priority=3)
	public void forgotPasswordLinkTest(){
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test(priority=4)
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}
