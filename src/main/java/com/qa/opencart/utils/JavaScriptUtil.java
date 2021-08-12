package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	
    public JavaScriptUtil(WebDriver driver){
    	this.driver = driver;
    }
    
	public String getTitleByJS(){
	JavascriptExecutor js = (JavascriptExecutor) driver;
	return js.executeScript("return document.title;").toString();
	}
	
	public void refreshBrowserByJS(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
		}
	
	public void generateAlert(String message){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('"+message+"')");
		}
	
	public String getPageInnerText(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
		}
	
	public void drawBorder(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		}
	
	public void clickElementByJS(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		}
	
	public void scrollPageDown(){ // Scroll down till bottom
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
	
	public void scrollPageUp(){ // Scroll up till above
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		}
	
	public void scrollPageDown(String height){ // Scroll down till certain height
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '"+height+"')");
		}
	
	public void scrollIntoView(WebElement element){ // Scroll till particular element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		}
	
	public void sendKeysUsingWithId(String id, String value){ // Scroll till particular element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('"+id+"').value = '"+value+"'");
		}
	
	
}
