package pages.pt.hoteles.components;

import java.util.List;

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

import config.CoreConfig;
import config.FWConfig;
import pages.pt.hoteles.HLGlobal;
import utility.FWUtils;

public class HLList extends HLGlobal {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(HLList.class);

	public HLList(WebDriver _driver) {
		super(_driver);
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT), this);
	}

	// -------------- List Section ----------------------------------
	@FindBy(how = How.CSS, using = "pth-list .list-product-block")
	private List<WebElement> listAllBlocksResults;

	@FindBy(how = How.CSS, using = ".list-product-block .list-product-rate .list-product-rate-action a")
	private WebElement listButtonFirstItem; // primer boton

	private By byListListProduct = By.cssSelector("pth-list");
	private By byListProductRateFinal = By.cssSelector(".list-product-rate .product-rate-final");
	private By byListProductHotelName = By.cssSelector(".list-product-item-content .list-product-name");
	private By byListButtonSeeOffer = By.cssSelector(".list-product-rate .list-product-rate-action .btn");
	private By byListListProductRateLoaderButton = By.cssSelector(".list-product-block .list-product-rate .loader");

	// ++++++++++++++++++++++++++ LIST FUNCTIONS ++++++++++++++++++++++++++
	public void selectFirstHotelAvailable() {
		int index = getIndexOfFirstHotelAvailable();
		Assert.assertFalse(CoreConfig.FAULTVALUE == index,
				"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		clickButtonSeeOffer(index);
	}

	public void clickButtonSeeOffer(int btnIndex) {
		Assert.assertTrue((btnIndex >= 0 && btnIndex < FWConfig.TOTALRECORDSPERPAGES),
				"LAF>>>Parametro invalido, index tiene que ser menor a 20!.");
		verifyResultListHasElements();
		int tabs = driver.getWindowHandles().size();
		WebElement buttonSeeOffer = listAllBlocksResults.get(btnIndex).findElement(byListButtonSeeOffer);
		buttonSeeOffer.click();
		FWUtils.switchToNewTabIfOpened(driver, tabs);
	}

	public void verifyResultListHasElements() {
		Assert.assertFalse(listAllBlocksResults.isEmpty(), "ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Cantidad de bloques (hoteles) en la lista de resultados: " + listAllBlocksResults.size());
	}

	// --------------------------- FUNCIONES PRIVADAS ---------------------------
	private int getIndexOfFirstHotelAvailable() {
		logger.trace("Starting listGetIndexOfFirstHotelAvailable()");
		verifyResultListHasElements();
		int index = CoreConfig.FAULTVALUE;
		for (int i = 0; i < listAllBlocksResults.size(); i++) {
			WebElement listProductBlock = listAllBlocksResults.get(i);
			wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, byListButtonSeeOffer));
			if (!listProductBlock.findElements(byListProductRateFinal).isEmpty()) {
				WebElement rateFinal = listProductBlock.findElement(byListProductRateFinal);
				WebElement hotelName = listProductBlock.findElement(byListProductHotelName);
				if (!rateFinal.getText().isEmpty()) {
					int i_masuno = i + 1;
					logger.trace("Se encontro disp. en el Hotel No: " + i_masuno);
					logger.trace("Nombre del Hotel: " + hotelName.getText());
					logger.trace("Tarifa encontrada: " + rateFinal.getText());
					index = i;
					break;
				}
			} else {
				logger.trace("No se encontro tarifa $ en el item: " + i);
				logger.trace("Nombre del Hotel: " + listProductBlock.findElement(byListProductHotelName).getText());
			}
		}
		if (CoreConfig.FAULTVALUE == index) {
			logger.error("No se encontro ningun hotel con disponibilidad!.");
		}
		return index;
	}

	private int getIndexOfHotelByName(String hotel) {
		// Aqui el codigo para buscar el itemNumber por el nombre del hotel
		return 0;
	}

	private int getIndexOfHotelByHotelId(int hotelId) {
		// Aqui el codigo para buscar el itemNumber por Id del Hotel
		return 0;
	}
}