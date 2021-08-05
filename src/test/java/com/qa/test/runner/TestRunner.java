package com.qa.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * 
 * @author Jaga
 *
 */

@CucumberOptions(features = "src/test/resources/features/", glue = { "com/qa/test/steps", "com/qa/test/common",
		"com/qa/test/base" }, tags = "@get", plugin = { "pretty", "json:target/cucumber/report/report.json",
				"junit:target/cucumber/report/report.xml" }, publish = true, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
