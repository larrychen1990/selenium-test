package com.hawk.selenium2;


import static org.junit.Assert.*;  
import java.util.*;  
import org.junit.*;  
import org.openqa.selenium.*;  
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;  
  
public class Selenium2{  
	
	
    WebDriver driver ;
    JavascriptExecutor jse ;
  
    @Before
    public void setup() {
    	System.setProperty("webdriver.chrome.driver","./src/main/java/com/hawk/browser/Chrome/Application/chromedriver.exe");
    	driver= new ChromeDriver();  
    	jse= (JavascriptExecutor) driver;  
    	
    }
    @Test  
    public void jQueryTest() {  
        driver.get("http://www.baidu.com/");  
        injectjQueryIfNeeded();  
        List<WebElement> elements = (List<WebElement>) jse  
                .executeScript("return jQuery.find('#nv a')");  
        assertEquals(7, elements.size()); 
        for (int i = 0; i < elements.size(); i++) {  
            System.out.print(elements.get(i).getText() + "ã€�");  
        }  
        driver.close(); 
        driver.quit();
    }  
    private void injectjQueryIfNeeded() {  
        if (!jQueryLoaded())  
            injectjQuery();  
    }  
  
    public Boolean jQueryLoaded() {  
        Boolean loaded;  
        try {  
            loaded = (Boolean) jse.executeScript("return " + "jQuery()!=null");  
        } catch (WebDriverException e) {  
            loaded = false;  
        }  
        return loaded;  
    }  
  
    public void injectjQuery() {  
        jse.executeScript(" var headID = "  
                + "document.getElementsByTagName(\"head\")[0];"  
                + "var newScript = document.createElement('script');"  
                + "newScript.type = 'text/javascript';" 
                + "newScript.src = "  
                + "'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"  
                + "headID.appendChild(newScript);");  
    }  
}  