package com.hawk.selenium2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class Selenium1Test extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {

		selenium = new DefaultSelenium("localhost", 4444, "*chrome",
				"http://www.baidu.com/");// 这里如果运行不了，修改浏览器为 *firefox 或 *iexplore
		selenium.start();
	}

	@Test
	public void testTest() throws Exception {
		// selenium.open("/index.html"); 可以增加页面类型
		//selenium.windowsMaximize(); 将来浏览器窗口放大
		selenium.open("/");
		
		selenium.type("id=kw", "selenium");
		// selenium.waitForPageToLoad("30000");
		selenium.click("id=su");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
