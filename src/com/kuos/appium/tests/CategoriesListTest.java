package com.kuos.appium.tests;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.kuos.appium.base.BaseTest;
import com.kuos.appium.util.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CategoriesListTest extends BaseTest{
	    
	public static void main(String[] args) throws MalformedURLException {

		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ElementOperator op = new ElementOperator();
		Set<String> categories = new HashSet<String>();
		
		// Scroll through entire list and get all categories **Create better method
		driver.findElementByAndroidUIAutomator("text(\"All Categories\")").click();
		categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Shoes\"))");
		categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Weddings & Events\"))");
		categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
		
		for(String category: categories){
			System.out.println(category);
		}

	}

}
