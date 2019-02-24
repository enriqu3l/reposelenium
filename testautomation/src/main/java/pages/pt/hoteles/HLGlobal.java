package pages.pt.hoteles;

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
import org.testng.Assert;

import config.FWConfig;
import pages.pt.hoteles.components.HLWidget;
import utility.BasicUtils;

public class HLGlobal {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(HLWidget.class);

	public HLGlobal(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT), this);
	}

	// -------------- Header Section --------------------------------
	protected By byPageHeaderTitle = By.cssSelector(".page-header .page-header-title");

	@FindBy(how = How.CSS, using = ".page-header .page-header-title")
	protected WebElement pageHeaderTitle;

	protected By byLoaderOverlayPage = By.xpath("//*[@class='loader__title']/parent::*/parent::*");
	// se usaba antes en HotelListPageOld
	// private By byLoaderOverlayPage2 = By.cssSelector(".loader-overlay.ng-trigger");
	protected By byLoaderOverlayFiltros = By.cssSelector(".card-body .loader-overlay");
	protected By byListProductRateLoaderButton = By.cssSelector(".list-product-block .list-product-rate .loader");

	@FindBy(how = How.CSS, using = ".loader__title")
	protected WebElement loaderTitle;

	@FindBy(how = How.CSS, using = ".spinner")
	protected WebElement spiner;

	// ++++++++++++++++++++++++++++++++ Global Functions +++++++++++++++++++++++++++
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getHeaderTitle() {
		return pageHeaderTitle.getText().trim();
	}
	
	// +++++++++++++++++++++++++++++++++++ WAITS +++++++++++++++++++++++++++++++++++
	public void waitForLoaderButtons() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byListProductRateLoaderButton));
		// La funcion invisibilityOfElementLocated revisa si isDisplayed y si no existe
		// retorna verdadero
	}

	public void waitForOverlay() {
		// Esperar a que se quite el overlay
		wait.until(ExpectedConditions.attributeContains(byLoaderOverlayPage, "style", "display: none; opacity: 0;"));
	}

	public void waitForContentToBeReady() {
		waitForOverlay();
		waitForLoaderButtons();
		logger.info("waitForContentToBeReady -> Content Loaded");
	}

	public void verifyUrlStartDateToBe(String value) {
		boolean result = BasicUtils.checkValueOnUrlParam(driver.getCurrentUrl(), "checkin", value);
		if (!result) {
			logger.error("La fecha de StartDate no coincide con la URL");
			Assert.fail("LAF>>>La fecha de StartDate no coincide con la URL");
		} else {
			logger.info("La fecha de StartDate coincide con la URL");
		}
	}

	public void verifyUrlEndDateToBe(String value) {
		boolean result = BasicUtils.checkValueOnUrlParam(driver.getCurrentUrl(), "checkout", value);
		if (!result) {
			logger.error("La fecha de EndDate no coincide con la URL");
			Assert.fail("LAF>>>La fecha de EndDate no coincide con la URL");
		} else {
			logger.info("La fecha de EndDate coincide con la URL");
		}
	}
}
