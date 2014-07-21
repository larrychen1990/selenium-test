
	selenium2标准html控件操作 
	1.Click on link
	[java] view plaincopyprint?
	WebElement oClickElem = oWebDriver.findElement(By.linkText("Next"));   
	oClickElem.click();   
	 
	2.Type characters
	[c-sharp] view plaincopyprint?
	oInputElement = oWebDriver.findElement(By.xpath("//*[@name='first-name']"));  // XPATH locator.  
	oInputElement.sendKeys("Xuan");  
	   
	oInputElement = oWebDriver.findElement(By.cssSelector("input[name='last-name']"));  // CSS locator.(2010-05-30: IE driver doesn't support CSS locator.)  
	oInputElement.sendKeys("Ngo");  
	   
	oInputElement = oWebDriver.findElement(By.className("red"));  // Class Name locator: the value of the "class" attribute.  
	oInputElement.sendKeys("my password");  
	   
	oInputElement = oWebDriver.findElement(By.id("unique-id"));   // ID locator. Type Chinese characters. You need to change the encoding of your editor.  
	oInputElement.sendKeys("中文");  
	   
	oInputElement = oWebDriver.findElement(By.name("key-press")); // Name locator.  
	oInputElement.sendKeys("auto complete");  
	 
	3.Check radio buttons and checkboxes
	[c-sharp] view plaincopyprint?
	// Radio Button: Check Monday using XPATH locator.  
	WebElement oRadioBtn = oWebDriver.findElement(By.xpath("//input[@value='Mon']"));  
	oRadioBtn.click();  
	   
	// Checkbox: Uncheck Apple using CSS selector.  
	WebElement oCheckBoxApple = oWebDriver.findElement(By.cssSelector("input[name='apple']")); // 2010-06-01: IE Driver doesn't support cssSelector yet.  
	oCheckBoxApple.click();  
	   
	// Checkbox: Check Orange using CSS selector.  
	WebElement oCheckBoxOrange = oWebDriver.findElement(By.cssSelector("input[name='orange']")); // 2010-06-01: IE Driver doesn't support cssSelector yet.  
	oCheckBoxOrange.click();      
	 
	4.Select single and multiple options
	[c-sharp] view plaincopyprint?
	 /** 
	     *  Single Selection: Select July. 
	 */  
	// Find <select> element of "Single selection" using ID locator.  
	Select oSingleSelection = new Select(oWebDriver.findElement(By.id("single-selection")));  
	   
	// Select <option ...>July</option>  
	oSingleSelection.selectByVisibleText("July");  
	   
	/** 
	 * Multiple Selections: Use variations of locators to select February, August and November. 
	 */  
	// Find <select> element of "Multiple selection" using XPATH locator.  
	Select oMulitpleSelection = new Select(oWebDriver.findElement(By.xpath("//select[@multiple='multiple' and @size=12]")));  
	   
	// Clear all selected entries.  
	oMulitpleSelection.deselectAll();  
	   
	// Select February, August and November using different functions.  
	oMulitpleSelection.selectByIndex(1); // February  
	oMulitpleSelection.selectByValue("Aug"); // Select <option ... value="Aug">...</option>  
	oMulitpleSelection.selectByVisibleText("November"); // Select <option ...>November</option>  
	   
	 
	5.Overloading findElement() to add waiting time
	[java] view plaincopyprint?
	/** 
	 * Before finding the element, keep waiting until the element is found or a timeout is expired. 
	 * @param webDriver 
	 * @param by 
	 * @param timeout Timeout in milliseconds. 
	 * @return Return the WebElement found. 
	 */  
	private WebElement findElement(WebDriver webDriver, By by, int timeout)  
	{  
	  int iSleepTime = 1000;  
	  for(int i=0; i<timeout; i+=iSleepTime)  
	  {  
	    List<WebElement> oWebElements = webDriver.findElements(by);  
	    if(oWebElements.size()>0)  
	    {  
	      return oWebElements.get(0);  
	    }  
	    else  
	    {  
	      try  
	      {  
	        Thread.sleep(iSleepTime);  
	        System.out.println(String.format("%s: Waited for %d milliseconds.[%s]", new java.util.Date().toString(), i+iSleepTime, by));            
	      }  
	      catch(InterruptedException ex)   
	      {  
	        throw new RuntimeException(ex);  
	      }  
	    }  
	  }  
	   
	  // Can't find 'by' element. Therefore throw an exception.  
	  String sException = String.format("Can't find %s after %d milliseconds.", by, timeout);  
	  throw new RuntimeException(sException);      
	}  
	 
	6.Click on any html element using different locators
	[c-sharp] view plaincopyprint?
	WebElement oClickElem = null;  
	   
	// Using name locator: Find and click on the Next link.  
	oClickElem = oWebDriver.findElement(By.name("submit_button")); // Find Next link.  
	oClickElem.click(); // Click on the element found(i.e. Next).  
	pause(1000); // Pause so that you can see that Selenium had clicked.  
	   
	// Using ID locator: Find and click on the Next link.  
	oClickElem = oWebDriver.findElement(By.id("unique-link-id")); // Find Next link.  
	oClickElem.click(); // Click on the element found(i.e. Next).  
	pause(1000); // Pause so that you can see that Selenium had clicked.  
	   
	// Using XPATH locator: Find and click on the Next link.  
	oClickElem = oWebDriver.findElement(By.xpath("//li[contains(text(), 'using the xpath')]")); // Find Next link.  
	oClickElem.click(); // Click on the element found(i.e. Next).  
	pause(3000); // Pause so that you can see that Selenium had clicked.  
	 
	7.自定义暂停
	[c-sharp] view plaincopyprint?
	public static void pause(final int iTimeInMillis)  
	{  
	    try  
	    {  
	      Thread.sleep(iTimeInMillis);  
	    }  
	    catch(InterruptedException ex)  
	    {  
	      System.out.println(ex.getMessage());  
	    }  
	 }  
	selenium使用xpath查找html元素
	1.button
	[xhtml] view plaincopyprint?
	//button[contains(text(),'someText')]  
	
