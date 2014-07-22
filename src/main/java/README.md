### [Selenium automates browsers](http://docs.seleniumhq.org/)

## What is Selenium. An [OpenQA project](http://www.openqa.org/) 
 - Selenium IDE
 - Selenium Remote Control (*deprecated*)
 - Selenium WebDriver (*Selenium 1.0 + WebDriver = Selenium 2.0*)
 - Selenium Grid
 
## Why Selenium 
 - Selenium IDE
	 - create quick bug reproduction scripts
	 - create scripts to aid in automation-aided exploratory testing
 - Selenium WebDriver
	 - create robust, browser-based regression automation
	 - scale and distribute scripts across many environments
 
## How to use Selenium
   - Maven POM files
   
    ```sh
		<!-- servlet-api jetty selenium-java junit4.9 -->
		<!-- so we just need to add this dependency-->
	    <dependency>
	        <groupId>org.seleniumhq.selenium</groupId>
	        <artifactId>selenium-server</artifactId>
	        <version>2.42.2</version>
	    </dependency>  
	    
	    <!--guava json  -->
	   <!--  <dependency>
	        <groupId>org.seleniumhq.selenium</groupId>
	        <artifactId>selenium-api</artifactId>
	        <version>2.42.2</version>
	    </dependency>  -->
	    
	    
	    <!--selenium-chrome-driver selenium-htmlunit-driver selenium-firefox-driver
	    	selenium-ie-driver selenium-safari-driver selenium-iphone-driver
	    	selenium-android-driver 
	      -->
	   <!--  <dependency>
	        <groupId>org.seleniumhq.selenium</groupId>
	        <artifactId>selenium-java</artifactId>
	        <version>2.42.2</version>
	    </dependency>  --> 
	    
	    <!-- selenium-remote-driver  commons-io -->
	   <!--  <dependency>
	        <groupId>org.seleniumhq.selenium</groupId>
	        <artifactId>selenium-firefox-driver</artifactId>
	        <version>2.42.2</version>
	    </dependency> -->  
	    
	    <!-- selenium-remote-driver -->
	    <!-- <dependency>
	        <groupId>org.seleniumhq.selenium</groupId>
	        <artifactId>selenium-chrome-driver</artifactId>
	        <version>2.42.2</version>
	    </dependency>  --> 
	    
	    <!-- guava selenium-java  -->
	    <!--  <dependency>
	         <groupId>com.opera</groupId>
	         <artifactId>operadriver</artifactId>
			 <version>1.5</version>
	     </dependency> -->
	         
	    <!-- selenium-remote-driver  -->
	    <!--  <dependency>
	            <groupId>org.seleniumhq.selenium</groupId>
	            <artifactId>selenium-ie-driver</artifactId>
	            <version>2.42.2</version>
	     </dependency> -->
	    
	    <!--  json selenium-api  guava  -->
	  	<!--  <dependency>
	      <groupId>org.seleniumhq.selenium</groupId>
	      <artifactId>selenium-remote-driver</artifactId>
	      <version>2.42.2</version>
	    </dependency>  -->
	   ``` 
	   
   - Put browser driver in the brwoser pugin's root directory (here we don't have permission
		to add files to the `C:\Program Files\` directory, so i copy the browser's home directory
		simply under our project,[document](http://docs.seleniumhq.org/download/))
	 	- `chromedriver.exe`
	 	- `IEDriverServer.exe`
	 	
   - Set the System property
		- Chrome 
			- ```System.setProperty(CHROME_DRIVER,CHROME_DRIVER_URL);```
		- Firefox
			- ```System.setProperty(FIREFOX_DRIVER,FIREFOX_DRIVER_URL);```
		- IE
			- ```System.setProperty(IE_DRIVER,IE_DRIVER_URL);```
		```sh
		* CHROME_DRIVER="webdriver.chrome.driver";
		* FIREFOX_DRIVER="webdriver.firefox.bin";
		* IE_DRIVER="webdriver.ie.driver";
		```
	
   - Binding it with junit test and assert the result
		
   - We should be carefull with the Selenium libs.
		- To run the Selenium 2 projects we should use the `guava15.0+.jar`
		  but not the `guava11.0.2.jar` which we use in Selenium 1	     
	    
   - Here is a simple test case
	 	
	 	```sh
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
		}
	```
	 
	 * Step 1 : load the page
	 * Step 2 : find the web element
	 * Step 3 : do your actions, `sendKeys("")`, `perform()` actions
	 * Step 4 : submit or click
	 * Step 5 : close the tab and browser
	 	
	 ```sh	
	 *  dr.findElement(By.id("id")); 
     * 	dr.findElement(By.className("classNmae"));  
     * 	dr.findElement(By.tagName("tag"));
     * 	dr.findElement(By.cssSelector("className|name|id"));// .dijit.dijitItems the second . delegate blank space  
     * 	dr.findElement(By.name("name"));  
     * 	dr.findElement(By.linkText("fullLink")); 
     * 	dr.findElement(By.partialLinkText("link"));
     * 	dr.findElement(By.xpath("//input[@type='radio']"));
	 ```  
	    
	    