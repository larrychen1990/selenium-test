package com.hawk.selenium2.testallbrowser;

import org.junit.Test;

import com.hawk.selenium2.testallbrowser.SeleUtil.BrowserType;

public class InjectChrome {

	@Test
	public void setChrome() throws Exception {
		Selenium2SimpleTest.setDriver(SeleUtil.getWebDriver(BrowserType.CHROME));
	}
}
