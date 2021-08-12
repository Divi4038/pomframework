package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class is to initialize driver and properties file
 * @author Divyang
 * @return this will return WebDriver
 */
public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	private OptionsManager optionsManager;
	
	public static String highlight = null;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver init_driver(Properties prop){
		
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is : " + browserName);
		
		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")){
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else{
			System.out.println("Please pass the correct browser : " + browserName);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver(){ // This webDriver will be called in a synchronized way for parallel execution
		return tlDriver.get();
	}
	
	/**
	 * 
	 * @return this method will return properties object
	 */
	public Properties init_prop(){
		FileInputStream ip = null;
		prop = new Properties();
		
		String env = System.getProperty("env");
		System.out.println("Running on Environment: --> on Prod");
		
		if(env==null){
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		}
		
		else {
			System.out.println("Running on Environment: --> " + env);
			try {
			switch (env) {
			case "qa":
					ip = new FileInputStream("./src/test/resources/config/config_qa.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/config_stage.properties");
			break;
			
			default:
				break;
			}
			}
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		try {
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot(){
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis();
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
}
