package pages.pt.hoteles.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FWConfig;
import helpers.JSWaiter;
import pages.pt.hoteles.HLGlobal;

public class HLFilters extends HLGlobal {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(HLFilters.class);

	public HLFilters(WebDriver _driver) {
		super(_driver);
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT), this);
	}

	// --------------- Filters Elements - Basados en SPA-Hoteles ----------
	@FindBy(how = How.CSS, using = "")
	private WebElement filer;
	private By byFilter = By.cssSelector("");

	// ++++++++++++++++++++++++++ FILTERS FUNCTIONS ++++++++++++++++++++++++++
	
	
	// ++++++++++++++++++++++++++ END PAGING FUNCTIONS ++++++++++++++++++++++++++

}