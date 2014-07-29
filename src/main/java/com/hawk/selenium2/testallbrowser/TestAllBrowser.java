package com.hawk.selenium2.testallbrowser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
//	InjectChrome.class,
//	Selenium2SimpleTest.class,
//	InjectFirefox.class,
//	Selenium2SimpleTest.class,
	InjectIE.class,
	Selenium2SimpleTest.class
})
public class TestAllBrowser {
	
}
