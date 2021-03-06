package pages.interjet;

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
import utility.BasicUtils;
import utility.FWUtils;

public class HotelListPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HotelListPage.class);
	
	public HotelListPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_INTERJET);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_INTERJET),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/hoteles"));
		waitForContentToBeReady();
	}
	
	
	//-------------- Header Section --------------------------------
	private By byPageHeaderTitle = By.cssSelector(".page-header .page-header-title");
	
	@FindBy(how=How.CSS, css=".page-header .page-header-title")
	private WebElement pageHeaderTitle;
	
	
	//-------------- List Section ----------------------------------	
	@FindBy(how=How.CSS, css="#HotelListContainer .hotel-item")
	private List<WebElement> listAllBlocksResults;
	
	@FindBy(how=How.CSS, css=".hotel-item .roomsrow .parent .right a")
	private WebElement listButtonFirstItem; //primer boton
	
	private By byListListProduct = By.cssSelector("#HotelListContainer");
	private By byListProductRateFinal = By.cssSelector(".datarow .boxPri .HotPri");
	private By byListProductHotelName = By.cssSelector(".datarow .hotInf .hotNam a");
	private By byListReserveButton = By.cssSelector(".roomsrow .parent .right a");
	//private By byListListProductRateLoaderButton = By.cssSelector("#HotelListContainer .datarow .boxPri .....");
	
	
	public void listSelectFirstHotelAvailable() {
		int index = listGetIndexOfFirstHotelAvailable();
		Assert.assertFalse(CoreConfig.FAULTVALUE==index,"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		listClickButtonSeeOffer(index);
	}
	
	public void listClickButtonSeeOffer(int btnIndex){
		Assert.assertTrue((btnIndex>=0 && btnIndex<FWConfig.TOTALRECORDSPERPAGES),"LAF>>>Parametro invalido, index tiene que ser menor a 20!.");
		listVerifyResultListHasElements();
		int tabs = driver.getWindowHandles().size();
		WebElement buttonSeeOffer = listAllBlocksResults.get(btnIndex).findElement(byListReserveButton);
		buttonSeeOffer.click();
		FWUtils.switchToNewTabIfOpened(driver, tabs);
	}
	
	public void listVerifyResultListHasElements() {
		Assert.assertTrue(FWUtils.existsElement(driver,byListListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(listAllBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Cantidad de bloques (hoteles) en la lista de resultados: "+listAllBlocksResults.size());
	}
	
	public void verifyUrlStartDateToBe(String value) {
		boolean result = BasicUtils.checkValueOnUrlParam(driver.getCurrentUrl(),"checkin",value);
		if(!result) {
			logger.error("La fecha de StartDate no coincide con la URL");
			Assert.fail("LAF>>>La fecha de StartDate no coincide con la URL");
		}else {
			logger.info("verifyUrlStartDateToBe PASS");
		}
	}
	
	public void verifyUrlEndDateToBe(String value) {
		boolean result = BasicUtils.checkValueOnUrlParam(driver.getCurrentUrl(),"checkout",value);
		if(!result) {
			logger.error("La fecha de EndDate no coincide con la URL");
			Assert.fail("LAF>>>La fecha de EndDate no coincide con la URL");
		}else {
			logger.info("verifyUrlEndDateToBe PASS");
		}
	}
	
	public void verifyHeaderTitleToBe(String title) {
		String actual = pageHeaderTitle.getText().trim();
		String expected = title;
		if(!actual.contains(expected)) {
			logger.error("Text: ("+actual+") in pageHeaderTitle does not contain the expected: ("+expected+")");
			Assert.fail("LAF>>>Text: ("+actual+") in pageHeaderTitle does not contain the expected: ("+expected+")");
		}
	}
	
	//+++++++++++++++++++++++++++++++++++ WAITS ++++++++++++++++++++++++++++++++++++++++++++++++
	public void waitForContentToBeReady() {
		//En lo que veo como crear un wait adecuado, solo espero a que este el listado
		wait.until(ExpectedConditions.presenceOfElementLocated(byListListProduct));
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//--------------------------- FUNCIONES PRIVADAS -------------------------------------------
	private int listGetIndexOfFirstHotelAvailable() {
		logger.trace("Starting listGetIndexOfFirstHotelAvailable()");
		listVerifyResultListHasElements();
		int index = CoreConfig.FAULTVALUE;
		for (int i = 0; i < listAllBlocksResults.size(); i++) {
			WebElement listProductBlock = listAllBlocksResults.get(i);
			//wait.until( ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, byListReserveButton));
			if(!listProductBlock.findElements(byListProductRateFinal).isEmpty()) {
				WebElement rateFinal = listProductBlock.findElement(byListProductRateFinal);
				WebElement hotelName = listProductBlock.findElement(byListProductHotelName);
				if (!rateFinal.getText().isEmpty()) {
					int i_masuno = i+1;
					logger.trace("Se encontro disp. en el Hotel No: " + i_masuno);
					logger.trace("Nombre del Hotel: "+hotelName.getText());
					logger.trace("Tarifa encontrada: " + rateFinal.getText());
					index = i;
					break;
				}
			}
			else{
				logger.trace("No se encontro tarifa $ en el item: " + i);
				logger.trace("Nombre del Hotel: "+listProductBlock.findElement(byListProductHotelName).getText());
			}
		}
		if(CoreConfig.FAULTVALUE==index){logger.error("No se encontro ningun hotel con disponibilidad!.");}
		return index;
	}
	private int listGetIndexOfHotelByName(String hotel) {
		//Aqui el codigo para buscar el itemNumber por el nombre del hotel
		return 0;
	}
	private int listGetIndexOfHotelByHotelId(int hotelId) {
		//Aqui el codigo para buscar el itemNumber por Id del Hotel
		return 0;
	}
}
