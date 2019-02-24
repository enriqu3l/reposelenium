package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FWUtils {

	// Check if a element is present
	public static boolean existsElement(WebDriver driver, By by) {
		return !driver.findElements(by).isEmpty();
	}

	// Check if a element is NOT present
	public static boolean noExistsElement(WebDriver driver, By by) {
		return driver.findElements(by).isEmpty();
	}

	//Convert "WebElement" to "By"
	public static By toByVal(WebElement we) {
		// By format = "[foundFrom] -> locator: term"
		// see RemoteWebElement toString() implementation
		String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
		String locator = data[0];
		String term = data[1];
		switch (locator) {
		case "xpath":
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		return (By) we;
	}

	// Guardar un ScreenShot
	public static void ScreenShot(WebDriver d) {
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("C:/Selenium/Test/Screen" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}// End of function

	// Guardar un ScreenShot y permite ponerle un nombre a la captura
	public static void ScreenShot(WebDriver d, String _name) {
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src,
					new File("C:/PTFrameworkData/Screen_" + _name + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}// End of function

	/**
	 * Guardar un ScreenShot y permite ponerle un nombre a la captura y decidir
	 * donde guardar la captura. La imagen se guarda en formato png
	 * 
	 * @param webDriver
	 * @param name
	 * @param path
	 */
	public static void ScreenShot(WebDriver d, String _name, String _path) {
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(_path + "Screen_" + _name + "_" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}// End of function
	
	
	//++++++++++++++++ Browser functions +++++++++++++++++++
	
	//Swichea a una nueva tab si la hay
	public static void switchToNewTabIfOpened(WebDriver driver, int tabsCount) {
		System.out.println("Before, tabsCount: " + tabsCount);
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("After, tabsCount: " + browserTabs.size());
		if (browserTabs.size() > tabsCount) {
			driver.switchTo().window(browserTabs.get(browserTabs.size()-1)); // La primer tab comienza con 0
			System.out.println("Switching to tab: "+browserTabs.size());
		}
	}
}
