package com.hawk.selenium2;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Selenium2DragAndDrop {

	private WebDriver driver;
	private WebElement element;

	private static final String BASE_URL = "https://www.google.com.au/#q=scroll+bar";

	private static final String CHROME_DRIVER_URL = "./src/main/java/com/hawk/browser/Chrome/Application/chromedriver.exe";

	@Before
	public void setup() throws Exception {
		useChrome();
	}

	private void useChrome() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				CHROME_DRIVER_URL);
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testFirst() {

		driver.get(BASE_URL);
		
		element = driver.findElement(By.id("cnt"));
		
		
		Actions dragger = new Actions(driver);
		
		
		 int numberOfPixelsToDragTheScrollbarDown = 50;

	        for (int i=10;i<500;i=i+numberOfPixelsToDragTheScrollbarDown){
	            try{
	        // this causes a gradual drag of the scroll bar, 10 units at a time
	        dragger.moveToElement(element).clickAndHold().moveByOffset(0, i).release().perform();
//	        Thread.sleep(1000L);
	            }catch(Exception e1){}
	        } 
		

		System.out.println("Page title is: " + driver.getTitle());

	}
}
