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

public class HLPaging extends HLGlobal {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(HLPaging.class);

	public HLPaging(WebDriver _driver) {
		super(_driver);
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT), this);
	}

	// --------------- Paging Elements - Basados en SPA-Hoteles ----------
	@FindBy(how = How.CSS, using = ".pagination > *:last-child a")
	private WebElement pagingNextPage;
	private By byPagingNextPage = By.cssSelector(".pagination > *:last-child a");

	// ++++++++++++++++++++++++++ PAGING FUNCTIONS ++++++++++++++++++++++++++
	public void clickOnNextPage() {
		logger.info("Starting pagingClickOnNextPage!");

		/*
		 * List<WebElement> elements= driver.findElements(byPagingNextPage); WebElement
		 * we = elements.get(elements.size() - 1); we.click();
		 */
		String url = driver.getCurrentUrl();
		pagingNextPage.click();

		// Espero a que el boton lanze una nueva url
		// (tiempo que tarda el boton en lanzar la accion)
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
		JSWaiter.setDriver(driver);
		JSWaiter.sleep(3000);
		waitForContentToBeReady();
	}
	// ++++++++++++++++++++++++++ END PAGING FUNCTIONS ++++++++++++++++++++++++++

}