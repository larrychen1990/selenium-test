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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Selenium2WaitCommands {

	private WebDriver driver;
	private WebElement element;

	private static final String BASE_URL = "http://www.google.com";

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
		driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
		// driver.manage().timeouts().setScriptTimeout(1000,TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testFirst() {

		driver.get(BASE_URL);
		
//		element = driver.findElement(By.name("q2"));

//		element=fluentWait(driver, By.name("q2"));
		
		element=expectedConditionsCommands(driver, By.name("q2"));
		
		element.sendKeys("Cheese");

		element.submit();

		// print the web's title, Cheese - Google æ�œå°‹
		System.out.println("Page title is: " + driver.getTitle());

		System.out.println("Page title is: " + driver.getTitle());

	}

	public WebElement fluentWait(WebDriver driver, final By locator) {
		// for its presence once every 5 seconds.

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);//if didn't ignoring the exception, the timeout will be 10 as default

		WebElement element = (WebElement) wait
				.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return driver.findElement(locator);
					}
				});

		return element;
	}

	public WebElement expectedConditionsCommands(WebDriver driver, final By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 12);

		WebElement element = wait.until(ExpectedConditions
				.elementToBeClickable(locator));

		return element;
	}
}
