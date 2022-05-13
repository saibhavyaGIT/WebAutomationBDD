package com.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseTestPage {
	
	
public static WebDriver driver;
	
	public static WebDriver getDriver(){
		return driver;
	}
	//Method to verify Title
	public void verifyPageURL(String title){
		try {
			Assert.assertEquals(driver.getTitle(), title);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	//Method to mouse hover to child element	
	public void mouseHoversubnode(String parentnode,String subnode){
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.xpath(parentnode))).moveToElement(driver.findElement(By.xpath(subnode))).click().build().perform();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Method to mousehover an element
	public void mouseHover(String parentnode){
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.xpath(parentnode))).build().perform();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Method to highlight an element
	public void highlightElement(Object element){
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			for(int i =0;i<2;i++){
			jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,"border: 3px solid green;");
			Thread.sleep(2000);
			jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//method to click using Javascript 
	public void jsclick(String element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try{
					
			System.out.println("XPATH:::: "+element);
			waitForElement(element);
		      js.executeScript("arguments[0].click();", driver.findElement(By.xpath(element)));
		      Thread.sleep(2000);
		}catch(StaleElementReferenceException | InterruptedException e){
			/*driver.navigate().refresh();
			 js.executeScript("arguments[0].click();", driver.findElement(By.xpath(element)));*/
	        
	      }
	}
	
	//Method for Explicit wait
	public String waitForElement(String item) { 
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element = wait.until( ExpectedConditions.elementToBeClickable(By.xpath(item))); 
	return item; 
	}
	//Method to click 
	public void click(String locator){
		try {
			driver.findElement(By.xpath(locator)).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Method to get Text from webElement
	public String getText(String locator){
		String itemcount = null;
		try {

			itemcount = driver.findElement(By.xpath(locator)).getText();} catch (Exception e) {
		}
			// TODO: handle exception
			
		return itemcount;
		
	}
	//Method to verofy Element present 
	public void isElementPresent(String locator ,String Element){
		try{
		if(driver.findElement(By.xpath(locator))!=null){
			Assert.assertTrue(true, "Less Price Item is present in the cart ::"+ Element);
		}else{
			Assert.assertFalse(false, "Less Price Item is not present in the cart");
		}} catch (Exception e) {
		}
	}
//Method to take a screenshot
	public static void screenshot(String...args){
		String a ;
		if(args.length>0){
			a=args[0];
		}
		File src= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		try {
		 // now copy the  screenshot to desired location using copyFile //method
			if(args.length>0)
		FileUtils.copyFile(src, new File("./Images/"+args[0]+System.currentTimeMillis()+".png"));
			else
				FileUtils.copyFile(src, new File("./Images/"+System.currentTimeMillis()+".png"));
		}
		 
		catch (IOException e)
		 {
		  System.out.println(e.getMessage());
		 
		 }
	}
	//Method to get Text form List of WebElements
	public ArrayList ArraygetText(List<WebElement> pricelist){
		ArrayList<String> y= new ArrayList<>();
		for(int i=0;i<pricelist.size();i++){
			System.out.println(i +":: "+ pricelist.get(i).getText());
			
			y.add(pricelist.get(i).getText().trim());
			//sortcosts.add(pricelist.get(i).getText());
		}
		return y;
	}
	//Method to get an Index using value in a List
	public int getIndexfromArray(List<String> itemnameList,String element){
		
        int index = 0 ;
         
        for(int i = 0; i < itemnameList.size(); i++) {
            if(itemnameList.get(i) == element) {
                index = i;
                break;
            }
        }
         
        System.out.println("Index of "+element+" is : "+index);
		
		
		return index;
		
	}
}
