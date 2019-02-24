package pages.pt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FWConfig;

public class Browser {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(Browser.class);

	public Browser(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
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
		return driver.getWindowHandles().size();
	}
}