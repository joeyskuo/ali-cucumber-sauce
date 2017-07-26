package com.kuos.appium.util;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.android.AndroidElement;

public class ElementOperator {

	public List<String> getTextList(List<AndroidElement> textviews){
		List<String> textList = new ArrayList<String>();
		
		for(AndroidElement element: textviews){
			textList.add(element.getText());
		}
		return textList;
	}
}
