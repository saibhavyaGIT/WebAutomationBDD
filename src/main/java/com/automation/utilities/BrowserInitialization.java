package com.automation.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserInitialization extends BaseTestPage {
		
	public static WebDriver browser(){
		 String browser= ReadPropertyFile.getPropertyvalue("driver.name");
			if(browser.equalsIgnoreCase("chromedriver")){
				System.setProperty("webdriver.chrome.driver",ReadPropertyFile.getPropertyvalue("chrome.driver"));
				driver = new ChromeDriver();
			}
			else if (browser.equalsIgnoreCase("firefoxdriver")) {
				System.setProperty("webdriver.gecko.driver", ReadPropertyFile.getPropertyvalue("gecko.driver"));
				 FirefoxOptions options = new FirefoxOptions();
					options.setBinary(ReadPropertyFile.getPropertyvalue("firefoxPath"));
					driver = new FirefoxDriver(options);
				
			}else if (browser.equalsIgnoreCase("iedriver")) {
				System.setProperty("webdriver.ie.driver", ReadPropertyFile.getPropertyvalue("ie.driver"));
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			return driver;
	}
	
}
