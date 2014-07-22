package com.hawk.selenium2;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @date Jul 14, 2014
 */
public class Selenium2SimpleTest {
	
	private WebDriver driver;
	private WebElement element;
	
	private static final String BASE_URL="http://www.google.com.au";
	
	private static final String CHROME_DRIVER="webdriver.chrome.driver";
	private static final String FIREFOX_DRIVER="webdriver.firefox.bin";
	private static final String IE_DRIVER="webdriver.ie.driver";
	
	private static final String CHROME_DRIVER_URL="./src/main/java/com/hawk/browser/Chrome/Application/chromedriver.exe";
	private static final String FIREFOX_DRIVER_URL="./src/main/java/com/hawk/browser/firefox/Mozilla Firefox/firefox.exe";
	private static final String IE_DRIVER_URL="./src/main/java/com/hawk/browser/ie/Internet Explorer/IEDriverServer.exe";
	
	
	
	@Before
	public void setup() {
		useChrome();
	}
	
	private void useChrome() {
		System.setProperty(CHROME_DRIVER,CHROME_DRIVER_URL);
		driver = new ChromeDriver();
	}
	
	private void useFirefox() {
		System.setProperty(FIREFOX_DRIVER,FIREFOX_DRIVER_URL);
		driver =new FirefoxDriver();
	}

	private void useIE() {
		System.setProperty(IE_DRIVER,IE_DRIVER_URL);
		driver = new InternetExplorerDriver();
	}
	
	@After
	public void tearDown() {
		
		// close the tab
		driver.close();
		// close the browser
		driver.quit();
	}

	@Test
	public void testFirst() {
		
		// load page
		driver.get(BASE_URL);
		
		// find name element in the html input elements
		element = driver.findElement(By.name("q"));
		
		assertNotNull(element);
		
		// input the search content
		element.sendKeys("Cheese");
		
		// submit the form, WebDriver will find the form which contains the
		// elements automation
		element.submit();
		
		// print the web's title, Cheese - Google 搜尋
		System.out.println("Page title is: " + driver.getTitle());
		
		// the serach page of google must go through the dynamic js
		// wait for loaded,timeout is 10 seconds
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase()
						.startsWith("cheese - google search");
			}
		});
		
		// will print "cheese! - Google Search" int console
		System.out.println("Page title is: " + driver.getTitle());

	}
	
}
