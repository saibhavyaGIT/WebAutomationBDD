package com.automation.stepDefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import com.automation.utilities.BaseTestPage;
import com.automation.utilities.BrowserInitialization;
import com.automation.utilities.ReadPropertyFile;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AcceptanceCritera extends BaseTestPage {
	//xpath;s
	String categories_xpath="//div[@class='main-menu']//div[@class='container']/ul/li[@class='menu-item menu-item-has-children dropdown']";
	String clothing_xpath="//div[@class='main-menu']//div[@class='container']/ul/li[@class='menu-item menu-item-has-children dropdown']/ul//li//a[@title='Clothing']";
	String clothingpagetitle="//h1[@class='page-title']";
	String addtocartItems="//h1[@class='page-title']/parent::article//ul//a[text()='Add to cart']";
	String shoppingcart="//div[@class='heading-row row']//i[@class='la la-shopping-bag']";
	String noOFitemsInCart="//div[@class='heading-row row']//i[@class='la la-shopping-bag']/span";
	
	
	//xpath's for removal of high price items
	String remove="//td[@class='product-price']//bdi[contains(text(),'";
	String remove1 ="')]//ancestor::td/preceding-sibling::td[@class='product-name']/a[contains(text(),'";
	String remove2 ="')]/parent::td//preceding-sibling::td[@class='product-remove']/a";
	
	//xpath's for webtable item prices and item names
	String prices="//td[@class='product-price']//bdi";
	String names="//td[@class='product-name']//a";
	
	//Objects creation for usage inthe code
	List <String> pricesList ;
	List <String> ItemnameList ;
	String leastPriceitemname;
	String leastpricevalue;
	String expecetdTitle="TESTSCRIPTDEMO – Automation Practice";
	
	 @Given("^The user login in to Test Demo application$")
	 public void open_browser_and_Enter_url()  {
		 
		 try {
			 driver= BrowserInitialization.browser();			
			 driver.get(ReadPropertyFile.getPropertyvalue("BaseURL"));
			 screenshot();
			 verifyPageURL(expecetdTitle);
			 Reporter.log("User Successfully loged into the Demo application ");
		} catch (Exception e) {
			// TODO: handle exception
			 screenshot();
			 System.out.println("Exception in Given : ^user login in to application$ ");
			 
		}
	 }
		@Given("^I add four different products to my wish list$") 
		 public void validate_add_fourProducts_InWishList() throws InterruptedException {
			mouseHoversubnode(categories_xpath, clothing_xpath);
			Assert.assertTrue(driver.findElement(By.xpath(clothingpagetitle))!=null);
			screenshot();
			List<WebElement> addTocart = driver.findElements(By.xpath(addtocartItems));
			
			int i=0;
			//Added 4 items in to wish list randomly
			for(WebElement cartitem :addTocart){
							Thread.sleep(2000);
				cartitem.click();
				i++;
				if(i==4)
					break;
				}
			Reporter.log("User Successfully added 4 items in to CART Randomly");
			}
			
	
		
		@When("^I view my wishlist table$")
		public void view_wishList_Table(){
		//focus on the element with Actions class
			mouseHover(shoppingcart);
			highlightElement(shoppingcart);
			//Clicked on shoppingcart icon
			jsclick(shoppingcart);
			screenshot();
			Reporter.log("User navigated to My Cart");
		}
		
		@Then("^I find total four selected items inthe wishlist$")
		public void validate_Number_ofItems_in_the_Cart(){
			//Asserting the count of records in the Cart
			Assert.assertEquals("4", getText(noOFitemsInCart).trim());
		Reporter.log("User verified the count of items in the Cart");
		}
		
		@When("^I search for lowest price product$")
		public void find_lowest_Price_Item_InCart(){
			
			
			//fetching all Price and ItemName WebElements	
			List<WebElement> pricelist = driver.findElements(By.xpath(prices));
			List<WebElement> nameslist=driver.findElements(By.xpath(names));
			//getting all the values of Price and Items in the webTable
			 pricesList =ArraygetText(pricelist);
			 ItemnameList =ArraygetText(nameslist);
						
			//fetching the least price form the list 
			 leastpricevalue= Collections.min(pricesList);			
			
			//fetching the less price item name with getting less price value
			 leastPriceitemname=driver.findElement(By.xpath(remove+leastpricevalue.substring(1)+"')]//ancestor::td/preceding-sibling::td[@class='product-name']/a")).getText();
		 Reporter.log("Lowest price Item Name::"+leastPriceitemname + "  Lowest Price Value:: "+ leastpricevalue );
			}
					
	
		

		@And("^I am able to addthe lowest price item to my cart$")
		public void add_the_Lowest_Price_Item(){
			//getting index of less Price Item name
			int indexItems = getIndexfromArray(ItemnameList,leastPriceitemname);
			int indexpricevalue = getIndexfromArray(pricesList,leastpricevalue);
			System.out.println("Itemname index :: "+indexItems +"Pricevalue index"+indexpricevalue);
			//less price item details are excluded from arrays for smoother removal of costlier items for table 
			pricesList.remove(indexpricevalue);
			ItemnameList.remove(indexItems);
			
			//loop to remove higher price items form table and we will have only less price item
			for(int i=0;i<pricesList.size();i++){				
				System.out.println("Price :: "+pricesList.get(i));
				jsclick(remove+pricesList.get(i).substring(1)+remove1+ItemnameList.get(i)+remove2);}
			
			screenshot();
			Reporter.log("User successfully can view the lowest price item details in the cart");
			
		}
			
		@Then("^I am able to verify the item in my cart$")
		public void verify_the_item_myCart(){
			driver.navigate().refresh();
			//clicking on MY cart and refreshing the page to view the item
			driver.findElement(By.xpath(shoppingcart)).click();
			isElementPresent(remove+leastpricevalue+remove1+leastPriceitemname+remove2 , leastpricevalue);
			driver.close();
			Reporter.log("User verified the item presence in the cart and logged out");
		}
		
		
		
}
