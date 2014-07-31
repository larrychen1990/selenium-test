package com.hawk.selenium2.testallbrowser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hawk.selenium2.testallbrowser.SeleUtil.BrowserType;

public class Selenium2SimpleTest {

	private WebDriver driver;
	protected static BrowserType bt;

	private static final String BASE_URL = "http://www.baidu.com";

	@Before
	public void setup() throws Exception {
		driver = SeleUtil.getWebDriver(getBt());
	}

	@After
	public void tearDown() {
		SeleUtil.closeWebDriver(driver);
	}

	@Test
	public void testFirst() {

//		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();

//		keyboard.sendKeys(Keys.F12);
		
//		 keyboard.pressKey(Keys.F12);
//		 //do stuff...
//		 keyboard.releaseKey(Keys.F12);
		 driver.getWindowHandle();
		 
		driver.get(BASE_URL);
		driver.switchTo().frame(1);
		driver.getWindowHandle();
		
		driver.switchTo().alert();
		driver.switchTo().frame("");
		driver.switchTo().window("");
		
//		Alert alert=new Alert();
		
		System.out.println(driver.getPageSource());
		
		WebElement element = driver.findElement(By.id("kw"));
		System.out.println(element);
//		SeleUtil.pause(2);
//		WebElement element = driver.findElement(By.name("q"));
//		element.sendKeys("Cheese");
//
//		element.submit();
//		System.out.println("done");
	}

	public static BrowserType getBt() {
		return bt;
	}

	public static void setBt(BrowserType bt) {
		Selenium2SimpleTest.bt = bt;
	}

}
