package com.hawk.selenium2.testallbrowser;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Selenium2SimpleTest {

	private static WebDriver driver;


	private static final String BASE_URL = "http://www.google.com.au";


	@After
	public void tearDown() {
		SeleUtil.closeWebDriver(driver);
	}

	@Test
	public void testFirst() {
		driver.get(BASE_URL);
		SeleUtil.pause(2);
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Cheese");
		element.submit();
		System.out.println("done");
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Selenium2SimpleTest.driver = driver;
	}


	
	

}
