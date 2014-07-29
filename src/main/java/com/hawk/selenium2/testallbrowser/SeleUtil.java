package com.hawk.selenium2.testallbrowser;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Predicate;
import com.google.common.io.Files;

public class SeleUtil {

	private static JavascriptExecutor jse;
	private static Log logger = LogFactory.getLog(SeleUtil.class);

	private static final String FIREFOX_DRIVER = "webdriver.firefox.bin";

	private static final String CHROME_DRIVER_URL = "./src/main/java/com/hawk/browser/Chrome/Application/chromedriver.exe";
	private static final String FIREFOX_DRIVER_URL = "./src/main/java/com/hawk/browser/firefox/Mozilla Firefox/firefox.exe";
	private static final String IE_DRIVER_URL = "./src/main/java/com/hawk/browser/ie/Internet Explorer/IEDriverServer.exe";

	public static  WebDriver getWebDriver(BrowserType browserType) throws Exception {
		switch (browserType) {
		case FIREFOX:
			return getFirefox();
		case IE:
			return getIE();
		case CHROME:
			return getChrome();
		default:
			throw new RuntimeException("Browser type unsupported");
		}
	}

	public enum BrowserType {
		FIREFOX, IE, CHROME, HTMLUNIT
	}

	private static WebDriver getChrome() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				CHROME_DRIVER_URL);
		return new ChromeDriver();
	}
	
	private static WebDriver getFirefox() {
		System.setProperty(FIREFOX_DRIVER, FIREFOX_DRIVER_URL);
		return new FirefoxDriver();
	}

	private static WebDriver getIE() {
		System.setProperty(
				InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,
				IE_DRIVER_URL);

		DesiredCapabilities capad = new DesiredCapabilities();
		capad.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		return new InternetExplorerDriver(capad);

	}
	
	/**
	 * 
	 * @param iTimeInMillis
	 *            void
	 */
	public static void pause(final int iTimeInMillis) {
		try {
			Thread.sleep(iTimeInMillis);
		} catch (InterruptedException ex) {
			logger.error(ex.getMessage(), ex.getCause());
		}
	}

	/**
	 * 
	 * @param driver
	 * @return boolean
	 */
	public static boolean isAlertExist(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 
	 * @param webDriver
	 * @param by
	 * @param timeout
	 * @return WebElement
	 */
	public static WebElement findElementsAndWait(WebDriver webDriver, By by,
			int timeout) {
		int iSleepTime = 1000;
		for (int i = 0; i < timeout; i += iSleepTime) {
			List<WebElement> webElements = webDriver.findElements(by);
			if (webElements.size() > 0) {
				return webElements.get(0);
			} else {
				try {
					Thread.sleep(iSleepTime);
					logger.error(String.format(
							"%s: Waited for %d milliseconds.[%s]",
							new Date().toString(), i + iSleepTime, by));
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
		// Can't find 'by' element. Therefore throw an exception.
		String sException = String.format(
				"Can't find %s after %d milliseconds.", by, timeout);
		throw new RuntimeException(sException);
	}

	/**
	 * 
	 * @param dr
	 * @return void
	 */
	public static void LoadAndExecuteJQuery(ChromeDriver dr) {
		jse = (JavascriptExecutor) dr;
		injectjQueryIfNeeded();
	}

	/**
	 * 
	 * @param driver
	 * @return void
	 */
	public static void closeWebDriver(WebDriver driver) {
		if (driver == null)
			return;
		try {
			String current = driver.getWindowHandle();
			Set<String> otherWins = driver.getWindowHandles();
			for (String winId : otherWins)
				if (winId.equals(current))
					continue;
				else
					driver.switchTo().window(winId).close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex.getCause());
		} finally {
			try {
				driver.close();
				driver.quit();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 
	 * @param dr
	 * @param path
	 * @param name
	 * @param oper
	 *            void
	 */
	public static void TakeScreenshot(ChromeDriver dr, String path,
			String name, String oper) {
		try {

			Thread.currentThread().sleep(3000);

			File screenshotFile = dr.getScreenshotAs(OutputType.FILE);

			String savePath = name + oper;

			if (!savePath.endsWith(".png")) {
				path = path + ".png";
			}

			Files.copy(screenshotFile, new File(path + savePath));

		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

	private static void injectjQueryIfNeeded() {
		if (!jQueryLoaded())
			injectjQuery();
	}

	private static Boolean jQueryLoaded() {
		Boolean loaded;
		try {
			loaded = (Boolean) jse.executeScript("return " + "jQuery()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}
		return loaded;
	}

	/**
	 * load the JQuery
	 * 
	 * 
	 */
	private static void injectjQuery() {
		jse.executeScript(" var headID = "
				+ "document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';"
				+ "newScript.src = "
				+ "'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}

	public static Predicate<WebDriver> textIsPresent(WebDriver driver,
			String text) {
		final String t = text;
		return new Predicate<WebDriver>() {
			public boolean apply(WebDriver driver) {
				return isTextPresent(driver, t);
			}
		};
	}

	protected static boolean isTextPresent(WebDriver driver, String text) {
		return driver.getPageSource().contains(text);
	}
}
