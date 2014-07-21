package com.hawk.selenium2;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @date Jul 14, 2014
 */
public class Selenium2Test1 {
	
	private WebDriver driver;
	private WebElement element;
	private static Log logger=LogFactory.getLog(Selenium2Test1.class);
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
//		useFirefox();
//		useIE();
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
		closeWebDriver(driver);
	}

	@Test
	public void testFirst() {
		
		// use get method access Google
		driver.get(BASE_URL);
//		navigate(URL);
		
		
		// find name element in the html input elements
		// by name
		element = driver.findElement(By.name("q"));
		
//		getElementsByBlah("cheese");
		
		// input the search content
		element.sendKeys("Cheese");
		
		// submit the form, WebDriver will find the form which contains the
		// elements automation
		element.submit();
		
		// print the web's title, Cheese - Google æ�œå°‹
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

	private static void closeWebDriver(WebDriver driver){
		if(driver == null)
			return;
		try{
			String current = driver.getWindowHandle();
			Set<String> otherWins = driver.getWindowHandles();
			for(String winId : otherWins)
				if(winId.equals(current))
					continue;
				else
					driver.switchTo().window(winId).close();
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex.getCause());
		}finally{
			try{
				driver.close();
				driver.quit();
			}catch(Exception ex){}
		}
	}
	
	private void navigate(String url) {
		// the same to driver.get("http://www.google.com.au");
		driver.navigate().to(url);
		
//		driver.navigate().forward();
//		
//		driver.navigate().back();
		
	}
	private WebElement getElementsByBlah(String tag) {
		// by id
//		element= driver.findElement(By.id("coolestWidgetEvah"));
		
		// by class name
//		 List cheeses = driver.findElements(By.className("cheese"));
		
		// by Tag name
//		 element= driver.findElement(By.tagName("iframe"));
		
		// ã€€By Link Text
//		driver.findElement(By.linkText("cheese"));
		
		// By Partial Link Text
//		driver.findElement(By.partialLinkText("cheese"));
		
		// by CSS we can use sizzle directly
//		WebElement cheese = driver.findElement(By
//				.cssSelector("#food span.dairy.aged"));
		
		// By XPath
//		List inputs = driver.findElements(By.xpath("//input"));
		
		// XPATHè¡¨è¾¾å¼� HtmlUnit Driver FireFox Driver IE Driver
		// input 1 2 2
		// INPUT 0 2 0
		
		return element;
	}
	
	private void select() {
		Select select = new Select(driver.findElement(By.tagName("select")));
		
		select.deselectAll();
		
		select.selectByVisibleText("Edam");
		
	}
	
	private void cookie() {
		// è®¾ç½®å…¨å±€Cookie
		
		Cookie cookie = new Cookie("key", "value");
		
		driver.manage().addCookie(cookie);
		
		// è¾“å‡ºå½“å‰�ç½‘é¡µæ‰€æœ‰å�¯ç”¨çš„Cookie
		
		Set<Cookie> cookies = driver.manage().getCookies();
		
		 for (Cookie loadedCookie : cookies) {
		
		 System.out.println(String.format("%s -> %s", loadedCookie.getName(),
		 loadedCookie.getValue()));
		 
		 // delete By Cookie
		 driver.manage().deleteCookie(loadedCookie);
		
		 }
		
		// there are three ways to delete Cookie
		
		// By name
		
		driver.manage().deleteCookieNamed("CookieName");
		
		// delete By Cookie
//		driver.manage().deleteCookie(loadedCookie);
		
		// Or all of them
		
		driver.manage().deleteAllCookies();
		
	}
	
	private void daD() {
		WebElement element = driver.findElement(By.name("source"));

		WebElement target = driver.findElement(By.name("target"));

		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
}
