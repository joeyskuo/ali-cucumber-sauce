package com.kuos.appium.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import com.kuos.appium.base.BaseTest;
import com.kuos.appium.pagemethods.HomePageMethods;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CategoriesListTest extends BaseTest{
	    
	public static void main(String[] args) throws MalformedURLException {

		
		// Test class for isolated testing, step definitions in separate file
		
		
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TouchAction t = new TouchAction(driver);
		HomePageMethods app = new HomePageMethods(driver);
		
//		Scenario: User views All Categories		
		app.tapAllCategButton();
		app.captureList();
		app.navigateHome();
		app.removeCouponPopup();

//		Scenario Outline: User searches for product 
		
		app.searchFor("phone");
		app.navigateHome();

//
//		Scenario: User adds flash deal item to cart 
		
		app.tapFlashDeal();
		app.tapFirstFlashDeal();
		app.fillProductParams();
		t.tap(240, 1711).perform();

	}

}
