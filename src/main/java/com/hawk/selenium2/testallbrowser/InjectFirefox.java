package com.hawk.selenium2.testallbrowser;

import org.junit.Test;

import com.hawk.selenium2.testallbrowser.SeleUtil.BrowserType;

public class InjectFirefox {
	@Test
	public void setFirefox() throws Exception {
		Selenium2SimpleTest.setDriver(SeleUtil.getWebDriver(BrowserType.FIREFOX));
	}
}
