package com.qa.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.qa.test.logger.LoggerHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class TestBase {

	private static File file;
	private static FileInputStream fileInputStream;
	public static Properties properties;
	private static String propertyFilePath = "//src//main//resources//configurations//";
	private static String testConfigFile = "TestConfig.properties";

	private static Logger log = LoggerHelper.getLogger(TestBase.class);

	@Before
	public static void before() throws Exception {
		try {
			loadPropertyFile();
		} catch (Exception ex) {
			log.info("Error occured while initialization of test execution" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	@After
	public void after(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				log.info("FAILED ***** : " + scenario.getName());
			} else {
				log.info("PASSED ***** : " + scenario.getName());
			}
		} catch (Exception ex) {
			log.info("Error occured while closing of test execution" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public static void loadPropertyFile() throws IOException {
		try {
			properties = new Properties();
			file = new File(System.getProperty("user.dir") + propertyFilePath + testConfigFile);
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException ex) {
				log.info("======================== [ Property file " + testConfigFile
						+ " not found ] ========================");
				ex.printStackTrace();
			}
			try {
				properties.load(fileInputStream);
				log.info("======================== [ Property File Load Successful ] ========================");
			} catch (IOException ex) {
				log.info("Error occured while load property file" + "\n" + ex);
				Assert.assertFalse(true);
			}

		} finally {
			fileInputStream.close();
		}
	}

	public static String getTestData(String property) {
		String dataFromPropFile = null;
		try {
			dataFromPropFile = properties.getProperty(property).trim();
		} catch (Exception ex) {
			log.info("Error occured while retrieve data from property file" + "\n" + ex);
			Assert.assertFalse(true);
		}
		return dataFromPropFile;
	}

}
