package com.hawk.selenium2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author dc90726
 * 
 * @date Jul 14, 2014
 */
public class Selenium2Example{
	//create a Firefox case
	//this class depends on interface but not the implements of interface 
	public static void main(String[] args) {
		
		//IEDriverServer.exe ChromeDriver.exe
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\ChromeDriver.exe");
		
		WebDriver driver=new ChromeDriver();
//				new FirefoxDriver();
		
		
		//use get method access Google
		driver.get("http://www.google.com.hk");
		
		//find name element in the html input elements
		WebElement element = driver.findElement(By.name("q"));
		
		//input the content to search
		element.sendKeys("Cheese");
		
		//submit the form, WebDriver will find the form which contains the elements
		//automate
		element.submit();
		
		//print the web's title
		System.out.println("Page title is: " +driver.getTitle());
		
		//the serach page of google must go through the dynamic js
		//wait for loaded,timeout is 10 seconds
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("cheese!");
			}
		});
		
		// will print "cheese! - Google Search" int console
		System.out.println("Page title is: " +driver.getTitle());
		
		//close the browser
		driver.quit();
		
	}
}
