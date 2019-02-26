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
		logger.info("Navigate to:"+url);
		driver.navigate().to(url);
	}

	public void navigateForward() {
		logger.info("Navigate Forward to:");
		driver.navigate().forward();
	}

	public void navigateBack() {
		logger.info("Navigate Back");
		driver.navigate().back();
	}

	public void refresh() {
		logger.info("Refresh:");
		driver.navigate().refresh();
	}

	public String getCurrentURL() {
		logger.info("get Current Url:");
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		logger.info("get Title:");
		return driver.getTitle();
	}

	public int getTabsCount() {
		logger.info("get Tabs Count:");
		return driver.getWindowHandles().size();
	}
}