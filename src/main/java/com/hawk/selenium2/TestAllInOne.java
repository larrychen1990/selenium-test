package com.hawk.selenium2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
  
public class TestAllInOne {  
    public static final String BASE_URL = "http://www.baidu.com";  
    
    private static Log logger=LogFactory.getLog(TestAllInOne.class);
  
    public static void main(String[] args) throws Exception {  
		System.setProperty("webdriver.chrome.driver", "./src/main/java/com/hawk/browser/Chrome/Application/chromedriver.exe");
        
        
        //打开firefox  
        ChromeDriver dr = new ChromeDriver();  
          
        //访问页面  
        dr.get(BASE_URL);  
//      dr.navigate().to(BASE_URL);//与dr.get()效果相同  
//      dr.navigate().forward();//让浏览器前进一步  
//      dr.navigate().back();//让浏览器后退一步  
        logger.info(dr.getPageSource());  
          
        //等待页面加载完毕，直到条件满足  
        (new WebDriverWait(dr, 10)).until(new ExpectedCondition<Boolean>() {  
            public Boolean apply(WebDriver dr) {  
            	logger.info("web driver wait...");  
                int index = dr.getPageSource().indexOf("京ICP证030173号");  
                if(index != -1){  
                	logger.info("web driver wait, true...");  
                    return true;  
                }else{  
                	logger.info("web driver wait, false...");  
                    return false;  
                }  
            }  
        });  
          
        //等待页面加载完毕，直到条件满足，一个while循环，也挺简单的吧  
        /* 
        while(true){ 
             Log.Info("wait for..."); 
            int index = dr.getPageSource().indexOf("京ICP证030173号"); 
            if(index != -1){ 
                Log.Info("wait for, true..."); 
                break; 
            } 
            Thread.sleep(1 * 1000); 
        }*/  
          
        //等待元素加载完成  
//      (new WebDriverWait(dr, 10)).until(  
//          ExpectedConditions.presenceOfElementLocated(By.id("kw"))  
//      );  
          
        //等待元素加载完成，并可点击  
//      WebDriverWait wait = new WebDriverWait(dr, 10);  
//      wait.until(ExpectedConditions.elementToBeClickable(By.id("su")));  
          
        //全局性的影响，只要WebDriver不消失，每隔10秒检查元素是否加载完成，直到加载完成。  
//      dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
//      dr.get("http://somedomain/url_that_delays_loading");  
//      dr.findElement(By.id("myDynamicElement"));  
          
//      WebElement we = dr.findElement(By.id("kw"));  
//      we.sendKeys("selenium");  
//      we.click();  
          
//      dr.findElement(By.id("元素id"));//最常用  
//      dr.findElement(By.className("class名称"));//比较常用  
//      dr.findElement(By.tagName("tag名称"));//比较常用  
//      dr.findElement(By.cssSelector("css选择器"));//比较常用  
//      dr.findElement(By.name("元素的name属性"));//比较常用  
//      dr.findElement(By.linkText("超链接的内容"));//不常用  
//      dr.findElement(By.partialLinkText("超链接的部分内容"));//不常用  
//      dr.findElement(By.xpath("xpath表达式"));//不常用  
          
        //1:用java从文本中读取jQuery，并用WebDriver加载jQuery  
        SeleniumUtil.LoadAndExecuteJQuery(dr);  
        //2:用WebDriver执行你写的jQuery代码，可以返回单个元素，也可以返回元素数组，或无返回值  
        //执行你的jQuery代码，既然能执行自已的jQuery代码，就能做任何事情了  
        dr.executeScript("$('#kw1').val('selenium'); $('#kw1').val($('#kw1').val() + ' java');");  
        
        //you can  submit the search content by click the button
        dr.findElement(By.id("su1")).click();
        
        //也可以用WebDriver API进行页面元素的操作  
//        WebElement el = dr.findElement(By.id("kw1"));  
//        el.sendKeys("selenium java"); 
//        el.submit();
          
        //WebDriver专用的select下接框处理类  
        /* 
        List<WebElement> elList = dr.findElements(By.tagName("select")); 
        if(elList != null && elList.size() > 0){ 
            Select select = new Select(elList.get(0)); 
            select.deselectAll(); 
            select.selectByVisibleText("Edam"); 
            select.selectByIndex(1); 
            select.selectByValue("10010"); 
        }*/  
          
        //window和frame的切换  
        /* 
        dr.switchTo().window("xxName"); 
        dr.switchTo().frame("xxName"); 
        */  
          
        //浏览器自带的alert弹出框的处理  
        /* 
        Alert alert = dr.switchTo().alert(); 
        if(alert != null){ 
            alert.accept(); 
            alert.dismiss(); 
            alert.sendKeys("collonn"); 
        }*/  
          
        /* 
        //用jQuery查找一个元素 
        WebElement wea = (WebElement)dr.executeScript("return $('#kw').get(0);"); 
        //用jQuery查找一组元素 
        List<WebElement> weaList = (List<WebElement>)dr.executeScript("return $('.stu_info').get();"); 
        //返回Boolean 
        Boolean flagB = (Boolean)dr.executeScript("return true;"); 
        //返回Double 
        Double flagD = (Double)dr.executeScript("return 12.12;"); 
        //返回Long 
        Long flagL = (Long)dr.executeScript("return 123;"); 
        //返回String 
        String flagS = (String)dr.executeScript("return 'collonn';"); 
        //返回String数组 
        List<String> flagSs = (List<String>)dr.executeScript("var ary = ['a', 'b', 'c']; return ary;"); 
        */  
          
        //保存网面截图  
        SeleniumUtil.TakeScreenshot(dr, "../selenium-test/src/main/java/com/hawk/selenium2/screenshot/","selenium java",".png");  
          
        //关闭浏览器  
        dr.quit();  
    }  
  
}  