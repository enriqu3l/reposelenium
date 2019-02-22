package pages.pt.general;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FWConfig;

public class Browser {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(Browser.class);

	private int tabs;

	public Browser(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
		updateTabsCount();
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void navigateForward(String url) {
		driver.navigate().forward();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public int getTabsCount() {
		return tabs;
	}
	
	public void updateTabsCount() {
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		tabs = browserTabs.size();
	}

	public void switchToNewTabIfOpened() {
		// Obtener las tabs existentes
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		logger.trace("Cantidad de tabs: " + browserTabs.size());
		int current = browserTabs.size()-1;
		if (browserTabs.size() > tabs) {
			// En caso de haber mas de 1 tab, switchear a esa nueva tab.
			driver.switchTo().window(browserTabs.get(current)); // La primer tab comienza con 0
			logger.trace("Switching to tab: "+current);
			updateTabsCount();
		}
	}
}