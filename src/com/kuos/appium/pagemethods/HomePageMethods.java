package com.kuos.appium.pagemethods;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;

import com.kuos.appium.util.ElementOperator;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePageMethods {
    
	// Without Page Object pattern
	
	AndroidDriver driver;
	TouchAction t;
	
	public HomePageMethods(AndroidDriver driver) {
        this.driver = driver;
        this.t = new TouchAction(driver);
	}

    private final static String ALL_CATEGORIES_BUTTON_AUIA = "new UiSelector().text(\"All Categories\")";
    private final static String FLASH_DEAL_HOOK_RESID = "resourceId(\"com.alibaba.aliexpresshd:id/iv_item2_0\")";
    private final static String FLASH_DEAL_ITEM_ID = "com.alibaba.aliexpresshd:id/view_1";
    private final static String FLASH_DEAL_FIRST_ITEM_XPATH = "(//android.widget.ImageView[@resource-id='com.alibaba.aliexpresshd:id/iv_block0'])[1]";
    private final static String FLASH_DEAL_PARAM_HOOK_RESID = "text(\"Select\")";
    private final static String HOME_MENU_ID = "com.alibaba.aliexpresshd:id/menu_overflow";
    private final static String HOME_MENU_HOME_BUTTON_AUIA = "text(\"Home\")";
    private final static String COUPON_POPUP_AUIA = "new UiSelector().description(\"GET YOUR COUPON NOW\")";
    private final static String SEARCH_BAR_SELECT_ID = "com.alibaba.aliexpresshd:id/search_hint";
    private final static String SEARCH_BAR_INPUT_ID = "com.alibaba.aliexpresshd:id/abs__search_src_text";
    
    public void navigateHome(){
		System.out.println("Returning Home...");
		driver.findElementById(HOME_MENU_ID).click();
		driver.findElementByAndroidUIAutomator(HOME_MENU_HOME_BUTTON_AUIA).click();
    }
    
    public void tapAllCategButton(){
    	driver.findElementByAndroidUIAutomator(ALL_CATEGORIES_BUTTON_AUIA).click();
    }
    
    public void removeCouponPopup(){
		try {
			AndroidElement popup = (AndroidElement) driver.findElementByAndroidUIAutomator(
					COUPON_POPUP_AUIA);
			if(popup.isEnabled()){
				Point point = popup.getLocation();
				t.tap(point.x+200, point.y+250).perform();
			}
		}	catch(Exception e) {
			System.out.println("No popup found");
		}
    }
    
    
    public Set<String> captureList(){
		ElementOperator op = new ElementOperator();
		Set<String> categories = new HashSet<String>();
		
		// Scroll through entire list and get all categories **Create better method
		categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Shoes\"))");
		categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Weddings & Events\"))");
		categories.addAll(op.getTextList(driver.findElementsByXPath("//android.widget.TextView")));
			
		return categories;
    }
    
	public void searchFor(String query){
		driver.findElementById(SEARCH_BAR_SELECT_ID).click();
		
		try {
			driver.findElementByAndroidUIAutomator("text(\"OK\")").click();
		} catch(Exception e){
			System.out.println("No popup found");
		}
		
		driver.findElementById(SEARCH_BAR_INPUT_ID).sendKeys(query);
		driver.pressKeyCode(AndroidKeyCode.ENTER);
	}
	
	public void tapFlashDeal(){
		
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView("+ FLASH_DEAL_HOOK_RESID  +")");
		driver.findElementById(FLASH_DEAL_ITEM_ID).click();
		
	}
    
    public void tapFirstFlashDeal(){
    	driver.findElementByXPath(FLASH_DEAL_FIRST_ITEM_XPATH).click();
    	
    }
    
    public void fillProductParams(){
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ FLASH_DEAL_PARAM_HOOK_RESID  +")").click();
		driver.findElementByXPath("//android.widget.RadioGroup[1]//android.widget.CompoundButton[1]").click();
		driver.findElementByXPath("//android.widget.RadioGroup[2]//android.widget.CompoundButton[1]").click();
    }
    
}
