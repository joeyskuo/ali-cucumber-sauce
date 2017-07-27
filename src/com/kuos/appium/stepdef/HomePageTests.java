package com.kuos.appium.stepdef;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;

import com.kuo.appium.base.BaseTest;
import com.kuos.appium.util.ElementOperator;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class HomePageTests extends BaseTest{
	
	static AndroidDriver<AndroidElement> driver;
	static TouchAction t;
	
	@Given("^User starts app$")
	public void start_app() throws MalformedURLException{
			System.out.println("This should only run once");
			driver = Capabilities();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			t = new TouchAction(driver);
	}
	
	@After
	public void after_tests() {
		System.out.println("Returning Home...");
		driver.findElementById("com.alibaba.aliexpresshd:id/menu_overflow").click();
		driver.findElementByAndroidUIAutomator("text(\"Home\")").click();
		try {
			AndroidElement popup = driver.findElementByAndroidUIAutomator(
					"new UiSelector().description(\"GET YOUR COUPON NOW\")");
			if(popup.isEnabled()){
				Point point = popup.getLocation();
				t.tap(point.x+200, point.y+250).perform();
			}
		}	catch(Exception e) {
			System.out.println("No popup found");
		}
	}
	
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		System.out.println("Starting new Scenario....");
	}

	@When(".*taps ([^\"]*)$")
	public void tap_Element(String element) throws Throwable {
		if(element.contains("All Categories")){
			driver.findElementByAndroidUIAutomator("text(\"All Categories\")").click();
		} else if(element.contains("Flash Deals")){
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"com.alibaba.aliexpresshd:id/iv_item2_0\"))");
			driver.findElementById("com.alibaba.aliexpresshd:id/view_1").click();
		}
		// add to cart, all categories, flash deals
		System.out.println(element);
	}

	@Then("^a list of ([^\"]*) is displayed$")
	public void list_results(String results) throws Throwable {
		ElementOperator op = new ElementOperator();
		Set<String> categories = new HashSet<String>();
		
		if(results.contains("categories")){
			// Scroll through entire list and get all categories **Create better method
			categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Shoes\"))");
			categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Weddings & Events\"))");
			categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
			
			for(String category: categories){
				System.out.println(category);
			}
		}
		// relevant search results, all categories
		System.out.println(results);
	}
	

	@When("^selects the first product$")
	public void selects_the_first_product() throws Throwable {
		driver.findElementByXPath("(//android.widget.ImageView[@resource-id='com.alibaba.aliexpresshd:id/iv_block0'])[1]").click();
	}

	@When("^fills desired conditions$")
	public void fills_desired_conditions() throws Throwable {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Select\"))").click();
		driver.findElementByXPath("//android.widget.RadioGroup[1]//android.widget.CompoundButton[1]").click();
		driver.findElementByXPath("//android.widget.RadioGroup[2]//android.widget.CompoundButton[1]").click();
		t.tap(240, 1711).perform();
	}

	@Then("^Item is added to cart$")
	public void item_is_added_to_cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the user searches for \"([^\"]*)\"$")
	public void the_user_searches_for(String query) throws Throwable {
		driver.findElementById("com.alibaba.aliexpresshd:id/search_hint").click();
		
		try {
			driver.findElementByAndroidUIAutomator("text(\"OK\")").click();
		} catch(Exception e){
			System.out.println("No popup found");
		}
		
		driver.findElementById("com.alibaba.aliexpresshd:id/abs__search_src_text").sendKeys(query);
		driver.pressKeyCode(AndroidKeyCode.ENTER);
	}


}
