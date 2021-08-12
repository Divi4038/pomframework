package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	public String getAccPageTitle() {
		return elementUtil.waitForTitle(5, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public String getAccPageUrl() {
		return elementUtil.getPageUrl();
	}
	
	public String GetAccPageHeader() {
		return elementUtil.doGetText(header);
		
	}
	
	public List<String> getAccountSectionList(){
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> AccSecList = elementUtil.waitForVisibilityOfElements(accSections, 5);
		
		for (WebElement e : AccSecList){
			accSecValList.add(e.getText());	
		} 
		Collections.sort(accSecValList);
		return accSecValList;			
	}
	
	public boolean isLogoutExist(){
		return elementUtil.doIsDisplayed(logoutLink);
		
	}
	
	//Search Feature
	
	public SearchResultPage doSearch(String productName){
		System.out.println("Searching the product :" +productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		
		return new SearchResultPage(driver); 
		
	}

}
