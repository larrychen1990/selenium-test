package com.hawk.selenium2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
  
public class Selenium2TestAll {  
    public static final String BASE_URL = "http://www.baidu.com";  
    
    private static Log logger=LogFactory.getLog(Selenium2TestAll.class);
  
    public static void main(String[] args) throws Exception {  
		System.setProperty("webdriver.chrome.driver", "./src/main/java/com/hawk/browser/Chrome/Application/chromedriver.exe");
        
        
        //æ‰“å¼€firefox  
        ChromeDriver dr = new ChromeDriver();  
          
        //è®¿é—®é¡µé�¢  
        dr.get(BASE_URL);  
//      dr.navigate().to(BASE_URL);//ä¸Ždr.get()æ•ˆæžœç›¸å�Œ  
//      dr.navigate().forward();//è®©æµ�è§ˆå™¨å‰�è¿›ä¸€æ­¥  
//      dr.navigate().back();//è®©æµ�è§ˆå™¨å�Žé€€ä¸€æ­¥  
        logger.info(dr.getPageSource());  
          
        //ç­‰å¾…é¡µé�¢åŠ è½½å®Œæ¯•ï¼Œç›´åˆ°æ�¡ä»¶æ»¡è¶³  
        (new WebDriverWait(dr, 10)).until(new ExpectedCondition<Boolean>() {  
            public Boolean apply(WebDriver dr) {  
            	logger.info("web driver wait...");  
                int index = dr.getPageSource().indexOf("äº¬ICPè¯�030173å�·");  
                if(index != -1){  
                	logger.info("web driver wait, true...");  
                    return true;  
                }else{  
                	logger.info("web driver wait, false...");  
                    return false;  
                }  
            }  
        });  
          
        //ç­‰å¾…é¡µé�¢åŠ è½½å®Œæ¯•ï¼Œç›´åˆ°æ�¡ä»¶æ»¡è¶³ï¼Œä¸€ä¸ªwhileå¾ªçŽ¯ï¼Œä¹ŸæŒºç®€å�•çš„å�§  
        /* 
        while(true){ 
             Log.Info("wait for..."); 
            int index = dr.getPageSource().indexOf("äº¬ICPè¯�030173å�·"); 
            if(index != -1){ 
                Log.Info("wait for, true..."); 
                break; 
            } 
            Thread.sleep(1 * 1000); 
        }*/  
          
        //ç­‰å¾…å…ƒç´ åŠ è½½å®Œæˆ�  
//      (new WebDriverWait(dr, 10)).until(  
//          ExpectedConditions.presenceOfElementLocated(By.id("kw"))  
//      );  
          
        //ç­‰å¾…å…ƒç´ åŠ è½½å®Œæˆ�ï¼Œå¹¶å�¯ç‚¹å‡»  
//      WebDriverWait wait = new WebDriverWait(dr, 10);  
//      wait.until(ExpectedConditions.elementToBeClickable(By.id("su")));  
          
        //å…¨å±€æ€§çš„å½±å“�ï¼Œå�ªè¦�WebDriverä¸�æ¶ˆå¤±ï¼Œæ¯�éš”10ç§’æ£€æŸ¥å…ƒç´ æ˜¯å�¦åŠ è½½å®Œæˆ�ï¼Œç›´åˆ°åŠ è½½å®Œæˆ�ã€‚  
//      dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
//      dr.get("http://somedomain/url_that_delays_loading");  
//      dr.findElement(By.id("myDynamicElement"));  
          
//      WebElement we = dr.findElement(By.id("kw"));  
//      we.sendKeys("selenium");  
//      we.click();  
          
//      dr.findElement(By.id("å…ƒç´ id"));//æœ€å¸¸ç”¨  
//      dr.findElement(By.className("classå��ç§°"));//æ¯”è¾ƒå¸¸ç”¨  
//      dr.findElement(By.tagName("tagå��ç§°"));//æ¯”è¾ƒå¸¸ç”¨  
//      dr.findElement(By.cssSelector("cssé€‰æ‹©å™¨"));//æ¯”è¾ƒå¸¸ç”¨  
//      dr.findElement(By.name("å…ƒç´ çš„nameå±žæ€§"));//æ¯”è¾ƒå¸¸ç”¨  
//      dr.findElement(By.linkText("è¶…é“¾æŽ¥çš„å†…å®¹"));//ä¸�å¸¸ç”¨  
//      dr.findElement(By.partialLinkText("è¶…é“¾æŽ¥çš„éƒ¨åˆ†å†…å®¹"));//ä¸�å¸¸ç”¨  
//      dr.findElement(By.xpath("xpathè¡¨è¾¾å¼�"));//ä¸�å¸¸ç”¨  
          
        //1:ç”¨javaä»Žæ–‡æœ¬ä¸­è¯»å�–jQueryï¼Œå¹¶ç”¨WebDriveråŠ è½½jQuery  
        SeleUtil.LoadAndExecuteJQuery(dr);  
        //2:ç”¨WebDriveræ‰§è¡Œä½ å†™çš„jQueryä»£ç �ï¼Œå�¯ä»¥è¿”å›žå�•ä¸ªå…ƒç´ ï¼Œä¹Ÿå�¯ä»¥è¿”å›žå…ƒç´ æ•°ç»„ï¼Œæˆ–æ— è¿”å›žå€¼  
        //æ‰§è¡Œä½ çš„jQueryä»£ç �ï¼Œæ—¢ç„¶èƒ½æ‰§è¡Œè‡ªå·²çš„jQueryä»£ç �ï¼Œå°±èƒ½å�šä»»ä½•äº‹æƒ…äº†  
        dr.executeScript("$('#kw1').val('selenium'); $('#kw1').val($('#kw1').val() + ' java');");  
        
        //you can  submit the search content by click the button
        dr.findElement(By.id("su1")).click();
        
        //ä¹Ÿå�¯ä»¥ç”¨WebDriver APIè¿›è¡Œé¡µé�¢å…ƒç´ çš„æ“�ä½œ  
//        WebElement el = dr.findElement(By.id("kw1"));  
//        el.sendKeys("selenium java"); 
//        el.submit();
          
        //WebDriverä¸“ç”¨çš„selectä¸‹æŽ¥æ¡†å¤„ç�†ç±»  
        /* 
        List<WebElement> elList = dr.findElements(By.tagName("select")); 
        if(elList != null && elList.size() > 0){ 
            Select select = new Select(elList.get(0)); 
            select.deselectAll(); 
            select.selectByVisibleText("Edam"); 
            select.selectByIndex(1); 
            select.selectByValue("10010"); 
        }*/  
          
        //windowå’Œframeçš„åˆ‡æ�¢  
        /* 
        dr.switchTo().window("xxName"); 
        dr.switchTo().frame("xxName"); 
        */  
          
        //æµ�è§ˆå™¨è‡ªå¸¦çš„alertå¼¹å‡ºæ¡†çš„å¤„ç�†  
        /* 
        Alert alert = dr.switchTo().alert(); 
        if(alert != null){ 
            alert.accept(); 
            alert.dismiss(); 
            alert.sendKeys("collonn"); 
        }*/  
          
        /* 
        //ç”¨jQueryæŸ¥æ‰¾ä¸€ä¸ªå…ƒç´  
        WebElement wea = (WebElement)dr.executeScript("return $('#kw').get(0);"); 
        //ç”¨jQueryæŸ¥æ‰¾ä¸€ç»„å…ƒç´  
        List<WebElement> weaList = (List<WebElement>)dr.executeScript("return $('.stu_info').get();"); 
        //è¿”å›žBoolean 
        Boolean flagB = (Boolean)dr.executeScript("return true;"); 
        //è¿”å›žDouble 
        Double flagD = (Double)dr.executeScript("return 12.12;"); 
        //è¿”å›žLong 
        Long flagL = (Long)dr.executeScript("return 123;"); 
        //è¿”å›žString 
        String flagS = (String)dr.executeScript("return 'collonn';"); 
        //è¿”å›žStringæ•°ç»„ 
        List<String> flagSs = (List<String>)dr.executeScript("var ary = ['a', 'b', 'c']; return ary;"); 
        */  
          
        //ä¿�å­˜ç½‘é�¢æˆªå›¾  
        SeleUtil.TakeScreenshot(dr, "../selenium-test/src/main/java/com/hawk/selenium2/screenshot/","selenium java",".png");  
          
        //å…³é—­æµ�è§ˆå™¨  
        dr.quit();  
    }  
  
}  