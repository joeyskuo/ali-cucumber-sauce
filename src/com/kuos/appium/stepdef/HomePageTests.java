package com.kuos.appium.stepdef;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.kuos.appium.base.BaseTest;
import com.kuos.appium.pagemethods.HomePageMethods;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;

public class HomePageTests extends BaseTest{
	
	AndroidDriver<AndroidElement> driver;
	TouchAction t;
	HomePageMethods app; 
	
	@Given("^User starts app$")
	public void start_app() throws MalformedURLException{
			System.out.println("This should only run once");
			driver = Capabilities();
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			//t = new TouchAction(driver);
			app = new HomePageMethods(driver);
	}
	
	@After
	public void after_tests() {
		app.navigateHome();
		app.removeCouponPopup();
	}
	
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		System.out.println("Starting new Scenario....");
	}

	@When(".*taps ([^\"]*)$")
	public void tap_Element(String element) throws Throwable {
		
		switch(element){
			case "All Categories": 	
				app.tapAllCategButton();
				break;
			
			case "Flash Deals": 	
				app.tapFlashDeal();
				break;				
			
			default:
				System.out.println("No case for element " + element);				
		}
		System.out.println(element);
	}

	@Then("^a list of ([^\"]*) is displayed$")
	public void list_results(String results) throws Throwable {

		switch(results){
		
		case "all categories": 	
			
			List<String> categoryList = Arrays.asList(
					"Apparel for Men", "Apparel for Women", "Automobiles & Motorcycles", "Beauty & Health",
					"Cellphones & Telecommunications", "Computer & Office", "Consumer Electronics", "Furniture",
					"Electronic Components & Supplies", "Hair Extensions & Wigs", "Home & Garden", "Home Improvement",
					"Jewelry & Accessories", "Lights & Lighting", "Luggage & Bags", "Mother & Kids",
					"Novelty & Special Use", "Office & School", "Security & Protection", "Shoes", "Watches",
					"Sports & Entertainment", "Toys & Hobbies", "Weddings & Events");
			
			Set<String> categoriesShown = app.captureList();
			Assert.assertTrue(categoriesShown.containsAll(categoryList)); 	
			break;
		
		case "relevant search results": 	
			//
			break;				
		
		default:
			System.out.println("No case for list of:" + results);				
	
		}
	}	
	
	

	@When("^selects the first product$")
	public void selects_the_first_product() throws Throwable {
		app.tapFirstFlashDeal();
	}

	@When("^fills desired conditions$")
	public void fills_desired_conditions() throws Throwable {
		app.fillProductParams();
		//t.tap(240, 1711).perform();
	}

	@Then("^Item is added to cart$")
	public void item_is_added_to_cart() throws Throwable {

		// Assert Cart has item; User is on Cart Activity with correct item
		
	}

	@When("^the user searches for \"([^\"]*)\"$")
	public void the_user_searches_for(String query) throws Throwable {
		
		app.searchFor(query);
		
	}


}
