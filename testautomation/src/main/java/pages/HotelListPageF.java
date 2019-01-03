package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.CoreConfig;
import utility.BasicUtils;
import valueobjects.VOHotelRes;

public class HotelListPageF {
	enum searchPages{
		FisrtPage,TwoPages,ThreePages,AllPages
	}
	
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HotelListPageF.class);
	
	public HotelListPageF(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 20),this);
	}
	
	//Ejemplos de como buscar attributos en xpath y cssSelector
	By overlayOcultoXPath = By.xpath("//*[@class='loader-overlay'][contains(@style, 'display: none')]");
	By overlayOculto = By.cssSelector(".loader-overlay[style='display: none; opacity: 0;']");
	
	By loaderOverlayPage = By.cssSelector(".loader-overlay.ng-trigger");
	By loaderButton = By.cssSelector(".list-product-rate .loader");
	By loaderOverlayFiltros = By.cssSelector(".card-body .loader-overlay");
	
	@FindBy(how=How.CSS, css=".loader__title")
	WebElement loaderTitle;
	
	@FindBy(how=How.CSS, css=".spinner")
	WebElement spiner;
	
	//-------------- List Section ----------------------------------
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allBlocksResults;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block .list-product-rate .list-product-rate-action a")
	WebElement Button_firstItem;
	
	By BYlistProduct = By.cssSelector(".list-product");
	By BYproductRateFinal = By.cssSelector(".list-product-rate .product-rate-final");
	By BYButton_seeOffer = By.cssSelector(".list-product-rate .list-product-rate-action .btn");
	
	//--------------- Lateral Widget elements - Basados en SPA-Hoteles!! ----------
	@FindBy(how=How.CSS, css="#ptw-container #destination")
	WebElement widget_Input_destination;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ap_dest_calendar")
	WebElement widget_Input_startDate;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ap_dest_calendar")
	WebElement widget_Input_endDate;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ui-datepicker-trigger")
	WebElement widget_startDatePicker;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ui-datepicker-trigger")
	WebElement widget_endDatePicker;
	
	@FindBy(how=How.CSS, css="#start-datepicker .dropdown-menu .ngb-dp-month-name")
	WebElement widget_startDate_Title;
	
	@FindBy(how=How.CSS, css="#end-datepicker .dropdown-menu .ngb-dp-month-name")
	WebElement widget_endDate_Title;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow button.btn")
	WebElement widget_startDate_beforeMonth;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow.right button.btn")
	WebElement widget_startDate_nextMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow button.btn")
	WebElement widget_endDate_beforeMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow.right button.btn")
	WebElement widget_endDate_nextMonth;
	
	@FindBy(how=How.CSS, css="#ptw-container #ap_booker_Hotel_rooms")
	WebElement widget_Select_bookerHotelRooms;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_adults")
	WebElement widget_Select_bookerHotelAdults;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_minors")
	WebElement widget_Select_bookerHotelMinors;
	
	@FindBy(how=How.CSS, css="#ptw-container .ptw-submit-btn")
	WebElement widget_Button_search;
	
	By widget_startDate_dropdownMenu = By.cssSelector("#start-datepicker .dropdown-menu");
	By widget_endDate_dropdownMenu = By.cssSelector("#end-datepicker .dropdown-menu");
	By widget_dropdownmenu = By.cssSelector("#ptw-container .dropdown-menu");
	
	//Esta funcion se diseño pensando solo en la funcionalidad de las SPA
	public void SelectFirstHotelAvailable() {
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForOverlay();
		
		Assert.assertTrue(BasicUtils.existsElement(driver,BYlistProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: "+allBlocksResults.size());
		
		int index = getItemNum_firstHotelAvailable(allBlocksResults);
		Assert.assertFalse(CoreConfig.FaultValue==index,"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		WebElement Button_seeOffer = allBlocksResults.get(index).findElement(BYButton_seeOffer);
		Button_seeOffer.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	public void SelectHotel(int index) throws InterruptedException {
		Assert.assertTrue(index<20,"LAF>>>Parametro invalido, index tiene que ser menor a 20!.");
		
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForOverlay();

		Assert.assertTrue(BasicUtils.existsElement(driver, BYlistProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: " + allBlocksResults.size());
		
		Assert.assertTrue(CoreConfig.FaultValue==index,"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		WebElement Button_seeOffer = allBlocksResults.get(index).findElement(BYButton_seeOffer);
		Button_seeOffer.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	public void verifyIfANewTabOpened() {
		//Obtener las tabs existentes
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		if(browserTabs.size()>1) {
			//En caso de haber mas de 1 tab, switchear a esa nueva tab.
			driver.switchTo().window(browserTabs.get(1)); //La primer tab comienza con 0 por eso seleccionamos la 1
		}
		logger.trace("Cantidad de tabs: "+browserTabs.size());
		
		//switch to new tab
		//driver.switchTo().window(browserTabs.get(1));
		//check is it correct page opened or not (e.g. check page's title) then close tab and get back
		//driver.close();
		//driver.switchTo().window(browserTabs.get(0));
	}
	
	//++++++++++++++++++++++++++ WIDGET FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++
	
	public void widget_changeDestin(String destin) {
		logger.info("Starting widget_changeDestin()");
		//Me falta poner candados para validar el parametro
		
		waitForOverlay();
		
		widget_Input_destination.clear();
		widget_Input_destination.sendKeys(destin);
		
		//Wait until dropdown menu appears
		wait.until(ExpectedConditions.presenceOfElementLocated(widget_dropdownmenu));
		
		widget_Input_destination.sendKeys(Keys.ENTER);
	}
	
	public void widget_changeAdults(int adultsNumber) {
		logger.info("Starting widget_changeAdults()");
		//Me falta poner candados para validar el parametro
		
		waitForOverlay();
		
		Select adults = new Select(widget_Select_bookerHotelAdults);
		adults.selectByValue(Integer.toString(adultsNumber));
	}
	
	public void widget_search() {
		logger.info("Starting widget_search()");
		
		waitForOverlay();
		
		String url = driver.getCurrentUrl();
		widget_Button_search.click();
		
		//Espero a que el boton lanze una nueva url
		//(tiempo que tarda el boton en lanzar la accion)
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
	}
	
	//Ya se construyó pero aun no se ha probado!!
	public void widget_changeSearch(VOHotelRes DO_HotelRes){
		logger.info("Starting widget_changeSearch()");
		widget_Input_destination.clear();
		widget_Input_destination.sendKeys(DO_HotelRes.getDestination());
		widget_selectStartDate(DO_HotelRes.getStartDate());		
		widget_selectEndDate(DO_HotelRes.getEndDate());
		Select rooms = new Select(widget_Select_bookerHotelRooms);
		rooms.selectByValue("1");
		Select adults = new Select(widget_Select_bookerHotelAdults);
		adults.selectByValue(Integer.toString(DO_HotelRes.getAdults()));
		Select kids = new Select(widget_Select_bookerHotelMinors);
		kids.selectByValue("0");
		widget_search();
	}
	
	//En construccion, ya mero esta lista!!!
	public void widget_selectStartDate(String date) {
		//Me falta poner candados para validar el parametro		
		
		logger.info("Starting widget_selectStartDate()");
		//Esperar a que se quite el overlay
		waitForOverlay();
		
		//Abrir el calendario si no esta abierto
		if(BasicUtils.noExistsElement(driver,widget_startDate_dropdownMenu)){widget_startDatePicker.click();}
		
		LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
		int day = localDate.getDayOfMonth();
		String actualDate = BasicUtils.toddMMyyyyFormat(widget_startDate_Title.getText().trim());
		
		int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
		logger.trace("widget_selectStartDate() TotalMonthDifference: "+TotalMonthDifference);
		if(TotalMonthDifference>0) {
			for(int i=0; i<TotalMonthDifference;i++) {
				widget_startDate_nextMonth.click(); //click hacia adelante
			}
		}else if(TotalMonthDifference<0) {
			for(int i=0; i>TotalMonthDifference;i--) {
				widget_startDate_beforeMonth.click(); //click hacia atras
			}
		}
		String xpath = "//*[@id='start-datepicker']//div[text()='"+day+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String selector = "#start-datepicker div[aria-label*=' "+day+" '].ngb-dp-day div";
		//driver.findElement(By.cssSelector(selector)).click();
		
		logger.info("Valor de widget_Input_startDate: " + widget_Input_startDate.getText());
		
	}
	//En construccion, ya mero esta lista!!!
	public void widget_selectEndDate(String date) {
		//Me falta poner candados para validar el parametro
		
		logger.info("Starting widget_selectEndDate()");
		logger.trace("actualDate:  "+date);
		//Esperar a que se quite el overlay
		waitForOverlay();
		//Abrir el calendario si no esta abierto
		if(BasicUtils.noExistsElement(driver,widget_endDate_dropdownMenu)){widget_endDatePicker.click();}
		
		LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
		int day = localDate.getDayOfMonth();
		String actualDate = BasicUtils.toddMMyyyyFormat(widget_endDate_Title.getText().trim());
		logger.trace("actualDate:  "+actualDate);
		int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
		logger.trace("widget_selectEndDate() TotalMonthDifference: "+TotalMonthDifference);
		if(TotalMonthDifference>0) {
			for(int i=0; i<TotalMonthDifference;i++) {
				widget_endDate_nextMonth.click(); //click hacia adelante
			}
		}else if(TotalMonthDifference<0) {
			for(int i=0; i>TotalMonthDifference;i--) {
				widget_endDate_beforeMonth.click(); //click hacia atras
			}
		}
		
		String xpath = "//*[@id='end-datepicker']//div[text()='"+day+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String selector = "#end-datepicker div[aria-label*=' " + Integer.toString(day) + " '].ngb-dp-day div";
		//driver.findElement(By.cssSelector(selector)).click();
		
		logger.info("Valor de widget_Input_startDate: " + widget_Input_endDate.getText());
	}
	
	//++++++++++++++++++++++++++ END WIDGET FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++
	
	
	//++++++++++++++++++++++++++++ Wait for Overlay ++++++++++++++++++++++++++++++++++++++++++++
	public void waitForOverlay() {
		//Esperar a que se quite el overlay
		//wait.until(WaitFor.attributeValue(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		wait.until(ExpectedConditions.attributeContains(loaderOverlayPage, "style", "display: none; opacity: 0;"));
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//--------------------------- FUNCIONES PRIVADAS -------------------------------------------
	private int getItemNum_firstHotelAvailable(List<WebElement> allSearchResults) {
		Assert.assertFalse(allSearchResults.isEmpty(),"ENF>>>El parametro de la lista de resultados esta vacia!.");
		
		int index = CoreConfig.FaultValue;
		for (int i = 0; i < allSearchResults.size(); i++) {
			WebElement listProductBlock = allSearchResults.get(i);
			wait.until( ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, BYButton_seeOffer));
			
			if(!listProductBlock.findElements(BYproductRateFinal).isEmpty()) {
				WebElement rateFinal = listProductBlock.findElement(BYproductRateFinal);
				if (!rateFinal.getText().isEmpty()) {
					int i_masuno = i+1;
					logger.trace("getItemNum_firstHotelAvailable() - Se encontro disp. en el Hotel No: " + i_masuno);
					logger.trace("getItemNum_firstHotelAvailable() - Tarifa encontrada: " + rateFinal.getText());
					index = i;
					break;
				}
			}
			else{
				logger.trace("getItemNum_firstHotelAvailable() - No se encontro tarifa $ en el item: " + i);
			}
		}
		if(CoreConfig.FaultValue==index){logger.error("getItemNum_firstHotelAvailable() - No se encontro ningun hotel con disponibilidad!.");}
		return index;
	}
	private int getItemNum_byHotelName(String hotel) {
		//Aqui el codigo para buscar el itemNumber por el nombre del hotel
		return 0;
	}
	private int getItemNum_byHotelId(int hotelId) {
		//Aqui el codigo para buscar el itemNumber por Id del Hotel
		return 0;
	}
}
