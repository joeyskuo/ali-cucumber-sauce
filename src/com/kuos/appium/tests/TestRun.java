package com.kuos.appium.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/com/kuos/cucumber/features",
		glue={"com/kuos/appium/stepdef"}
		)

public class TestRun {
	
}
