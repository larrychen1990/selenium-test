package com.hawk.selenium2;

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

import com.google.common.io.Files;

/**
 *
 * @date Jul 16, 2014
 */
public class SeleniumUtil {

	private static JavascriptExecutor jse;
	private static Log logger = LogFactory.getLog(SeleniumUtil.class);

	/**
	 * 
	 * @param iTimeInMillis
	 * void
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
	 * @return
	 * boolean
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
	 * @return
	 * WebElement
	 */
	public static WebElement findElementsAndWait(WebDriver webDriver, By by, int timeout) {
		int iSleepTime = 1000;
		for (int i = 0; i < timeout; i += iSleepTime) {
			List<WebElement> webElements = webDriver.findElements(by);
			if (webElements.size() > 0) {
				return webElements.get(0);
			} else {
				try {
					Thread.sleep(iSleepTime);
					logger.error(String
							.format("%s: Waited for %d milliseconds.[%s]",
									new Date().toString(), i
											+ iSleepTime, by));
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
	 * void
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

	private static void injectjQuery() {
		jse.executeScript(" var headID = "
				+ "document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';"
				+ "newScript.src = "
				+ "'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}
}
