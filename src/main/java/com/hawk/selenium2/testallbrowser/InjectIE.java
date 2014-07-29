package com.hawk.selenium2.testallbrowser;

import org.junit.Test;

import com.hawk.selenium2.testallbrowser.SeleUtil.BrowserType;

public class InjectIE {
	@Test
	public void setIE() throws Exception {
		Selenium2SimpleTest.setBt(BrowserType.IE);	}
}
