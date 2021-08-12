package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver; // Creating the Reference of the driver
	private ElementUtil elementUtil;
	
	//1. Private By locators:
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgotPasswordLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	

	//2. Constructor --> To get driver in this class --> TO avoid nullPointer in actions
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page actions (Methods)
	
	public String getLoginPageTitle(){
		return elementUtil.waitForTitle(5, Constants.LOGIN_PAGE_TITLE);	
	}
	
	public String getLoginPageUrl(){
		return elementUtil.getPageUrl();
	}
	
	public boolean isForgotPasswordLinkExist(){
		return elementUtil.doIsDisplayed(forgotPasswordLink);
	}
	
	public AccountsPage doLogin(String un, String pwd){
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
}
