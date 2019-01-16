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
import config.FrameworkConfig;
import utility.BasicUtils;
import valueobjects.VOHotelRes;
import valueobjects.VOHotelResNew;

public class HotelListPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HotelListPageF.class);
	
	public HotelListPageF(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		logger.info("Start HomePage constructor");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 20),this);
		logger.info("Launched initElements");
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/hoteles/"));
	}
	
	//Ejemplos de como buscar attributos en xpath y cssSelector
	//By overlayOcultoXPath = By.xpath("//*[@class='loader-overlay'][contains(@style, 'display: none')]");
	//By overlayOculto = By.cssSelector(".loader-overlay[style='display: none; opacity: 0;']");
	
	By byLoaderOverlayPage = By.cssSelector(".loader-overlay.ng-trigger");
	By byLoaderButton = By.cssSelector(".list-product-rate .loader");
	By byLoaderOverlayFiltros = By.cssSelector(".card-body .loader-overlay");
	
	@FindBy(how=How.CSS, css=".loader__title")
	WebElement loaderTitle;
	
	@FindBy(how=How.CSS, css=".spinner")
	WebElement spiner;
	
	
	//-------------- Header Section --------------------------------
	By byPageHeaderTitle = By.cssSelector(".page-header .page-header-title");
	
	@FindBy(how=How.CSS, css=".page-header .page-header-title")
	WebElement pageHeaderTitle;
	
	
	//-------------- List Section ----------------------------------
	@FindBy(how=How.CSS, css=".list-product")
	WebElement listProduct;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allBlocksResults;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block .list-product-rate .list-product-rate-action a")
	WebElement Button_firstItem;
	
	By byListProduct = By.cssSelector(".list-product");
	By byProductRateFinal = By.cssSelector(".list-product-rate .product-rate-final");
	By byButton_seeOffer = By.cssSelector(".list-product-rate .list-product-rate-action .btn");
	
	
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
	WebElement widget_Select_hotelRooms;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_adults")
	WebElement widget_Select_hotelAdults;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_minors")
	WebElement widget_Select_hotelMinors;
	
	@FindBy(how=How.CSS, css="#ptw-container .ptw-submit-btn")
	WebElement widget_Button_submit;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotelroom")
	List<WebElement> allBlockRooms;
	
	By widget_startDate_dropdownMenu = By.cssSelector("#start-datepicker .dropdown-menu");
	By widget_endDate_dropdownMenu = By.cssSelector("#end-datepicker .dropdown-menu");
	By widget_dropdownmenu = By.cssSelector("#ptw-container .dropdown-menu");
	By widget_hotelAdults = By.cssSelector(".ap_booker_Hotel_adults");
	By widget_hotelMinors = By.cssSelector(".ap_booker_Hotel_minors");
	
	
	//--------------- Lateral Pagination Elements - Basados en SPA-Hoteles!! ----------
	By byPaginationNext = By.xpath("/html/body/app-root/div/app-list/div[2]/div[2]/app-pager/nav/ul/li[8]/a/span");
	//@FindBy(how=How.XPATH, xpat="/html/body/app-root/div/app-list/div[2]/div[2]/app-pager/nav/ul/li[8]/a/span/span")
	//WebElement pagination_Next;
	
	
	//Esta funcion se diseño pensando solo en la funcionalidad de las SPA
	public void selectFirstHotelAvailable() {
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForContentToBeReady();
		
		Assert.assertTrue(BasicUtils.existsElement(driver,byListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: "+allBlocksResults.size());
		
		int index = getItemNum_firstHotelAvailable(allBlocksResults);
		Assert.assertFalse(CoreConfig.FAULTVALUE==index,"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		WebElement Button_seeOffer = allBlocksResults.get(index).findElement(byButton_seeOffer);
		Button_seeOffer.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	public void selectHotel(int index) throws InterruptedException {
		Assert.assertTrue((index>=0 && index<FrameworkConfig.TOTALRECORDSPERPAGES),"LAF>>>Parametro invalido, index tiene que ser menor a 20!.");
		
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForContentToBeReady();
		
		Assert.assertTrue(BasicUtils.existsElement(driver, byListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: " + allBlocksResults.size());
		
		//Aqui no estoy verificando si tiene disponibilidad, simplemente lo estoy seleccionando
		
		WebElement Button_seeOffer = allBlocksResults.get(index).findElement(byButton_seeOffer);
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
	
	public void widgetChangeDestin(String destin) {
		logger.info("Starting widget_changeDestin()");
		//Me falta poner candados para validar el parametro
		
		waitForContentToBeReady();
		
		widget_Input_destination.clear();
		widget_Input_destination.sendKeys(destin);
		
		//Wait until dropdown menu appears
		wait.until(ExpectedConditions.presenceOfElementLocated(widget_dropdownmenu));
		
		widget_Input_destination.sendKeys(Keys.ENTER);
	}
	
	public void widgetChangeAdults(int adultsNumber) {
		logger.info("Starting widget_changeAdults()");
		//Me falta poner candados para validar el parametro
		
		waitForContentToBeReady();
		
		Select adults = new Select(widget_Select_hotelAdults);
		adults.selectByValue(Integer.toString(adultsNumber));
	}
	
	public void widgetClickSubmit() {
		logger.info("Starting widget_search()");
		
		waitForContentToBeReady();
		String url = driver.getCurrentUrl();
		
		widget_Button_submit.click();
		
		
		//IMPORTANTE: Si realizo una busqueda con el mismo destino o algun mismo dato,
		//la url sera la misma y ocurrira un error!!!!!!! Necesito corregir esto!!!
		//Espero a que el boton lanze una nueva url
		//(tiempo que tarda el boton en lanzar la accion)
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
		
		//Agrego este Delay dado que tengo problemas cuando cambio solo el destino
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//En construccion!!!!!!!
	public void widgetChangeSearch(VOHotelResNew voHotelResNew){
		logger.info("Starting widget_changeSearch()");
		waitForContentToBeReady();
		
		widget_Input_destination.clear();
		widget_Input_destination.sendKeys(voHotelResNew.getDestination());
		widgetChangeStartDate(voHotelResNew.getStartDate());
		widgetChangeEndDate(voHotelResNew.getEndDate());
		
		//Aqui el codigo para realizar la seleccion de rooms, adults, kids y agekids
		
		Select rooms = new Select(widget_Select_hotelRooms);
		rooms.selectByValue(Integer.toString(voHotelResNew.getRoomCount()));
		//rooms.selectByIndex(voHotelResNew.getRoomCount()-1); //-1 porque es Base 0
		
		//Aqui ya se crearon los rooms ahora hay que llenar los adultos y niños
		if(allBlockRooms.size() != voHotelResNew.getRoomCount()) {
			 logger.error("No se crearon los campos suficientes de rooms, allBlocksRooms:"+allBlockRooms.size());
			 Assert.fail("LAF>>>No se crearon los campos suficientes de rooms, allBlocksRooms:"+allBlockRooms.size());
		}
		
		for(int i=0;i<allBlockRooms.size();i++) {
			WebElement weAdults = allBlockRooms.get(i).findElement(widget_hotelAdults);
			WebElement weKids = allBlockRooms.get(i).findElement(widget_hotelMinors);
			
			//adults.sendKeys(Integer.toString(voHotelResNew.getAdultsFromRoom(i)));
			Select adults = new Select(weAdults);
			adults.selectByValue(Integer.toString(voHotelResNew.getAdultsFromRoom(i)));
			
			Select kids = new Select(weKids);
			kids.selectByValue(Integer.toString(voHotelResNew.getKidsFromRoom(i)));
		}
		
		//Ahora hay que llenar las edades de los niños
		
		
		widgetClickSubmit();
	}
	
	//Ya se construyó pero aun no se ha probado!!
	public void widgetChangeSearchOld(VOHotelRes voHotelRes){
		logger.info("Starting widget_changeSearch()");
		widget_Input_destination.clear();
		widget_Input_destination.sendKeys(voHotelRes.getDestination());
		widgetChangeStartDate(voHotelRes.getStartDate());		
		widgetChangeEndDate(voHotelRes.getEndDate());
		Select rooms = new Select(widget_Select_hotelRooms);
		rooms.selectByValue("1");
		Select adults = new Select(widget_Select_hotelAdults);
		adults.selectByValue(Integer.toString(voHotelRes.getAdults()));
		Select kids = new Select(widget_Select_hotelMinors);
		kids.selectByValue("0");
		widgetClickSubmit();
	}
	
	//En construccion, ya mero esta lista!!!
	public void widgetChangeStartDate(String date) {
		//Me falta poner candados para validar el parametro	
		
		logger.info("Starting widget_selectStartDate()");
		waitForContentToBeReady(); //Esperar a que se quite el overlay
		
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
		
		logger.trace("Valor de widget_Input_startDate: " + widget_Input_startDate.getAttribute("value"));
	}
	
	//En construccion, ya mero esta lista!!!
	public void widgetChangeEndDate(String date) {
		//Me falta poner candados para validar el parametro
		
		logger.info("Starting widget_selectEndDate()");
		logger.trace("actualDate:  "+date);
		waitForContentToBeReady(); //Esperar a que se quite el overlay
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
		
		logger.trace("Valor de widget_Input_startDate: " + widget_Input_endDate.getAttribute("value"));
	}
	//++++++++++++++++++++++++++ END WIDGET FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++
	
	
	//++++++++++++++++++++++++++ PAGING FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++++
	public void goToNextPage() {
		waitForContentToBeReady();
		logger.info("Starting click on next page!!");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(byPaginationNext).click();
	}
	//++++++++++++++++++++++++++ END PAGING FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++++
	
	
	//+++++++++++++++++++++++++++ VERIFY FUNCTIONS +++++++++++++++++++++++++++++++++++++++++++++
	public void verifyWidgetInputDestinationToBe(String expected) {
		waitForContentToBeReady();
		
		String actual = widget_Input_destination.getText().trim();
		if(actual.isEmpty()) {
			//Si getText no funciona uso el metodo getAttribute
			actual = widget_Input_destination.getAttribute("value").trim();
		}
		logger.trace("actual:"+actual);
		
		if(!actual.contains(expected)) {
			logger.error("Actual value: ("+actual+") in widget_Input_destination is not equal to expected value: ("+expected+")");
			Assert.fail("LAF>>>Actual value: ("+actual+") in widget_Input_destination is not equal to expected value: ("+expected+")");
		}
	}
	
	public void verifyWidgetStartDateToBe(String expected) {
		waitForContentToBeReady();
		
		String actual = widget_Input_startDate.getText().trim();
		if(actual.isEmpty()) {
			//Si getText no funciona uso el metodo getAttribute
			actual = widget_Input_startDate.getAttribute("value").trim();
		}
		logger.trace("actual:"+actual);
		
		if(!actual.contains(expected)) {
			logger.error("Actual value: ("+actual+") in widget_Input_startDate is not equal to expected value: ("+expected+")");
			Assert.fail("LAF>>>Actual value: ("+actual+") in widget_Input_startDate is not equal to expected value: ("+expected+")");
		}
	}
	
	public void verifyHeaderTitleToBe(String title) {
		waitForContentToBeReady();
		
		String actual = pageHeaderTitle.getText().trim();
		String expected = title;
		if(!actual.contains(expected)) {
			logger.error("Text: ("+actual+") in pageHeaderTitle does not contain the expected: ("+expected+")");
			Assert.fail("LAF>>>Text: ("+actual+") in pageHeaderTitle does not contain the expected: ("+expected+")");
		}
	}
	
	public void verifyResultListHasElements() {
		Assert.assertTrue(BasicUtils.existsElement(driver,byListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: "+allBlocksResults.size());
	}
	
	public void verifyWidgetInputDestinationAutocomplete(List<String> data) {
		logger.info("Starting widget_changeDestin()");
		//Me falta poner candados para validar el parametro
		
		waitForContentToBeReady();
		
		for(int i=0; i<data.size();i++) {
			widget_Input_destination.clear();
			widget_Input_destination.sendKeys(data.get(i).toLowerCase().trim());
			
			//Wait until dropdown menu appears
			wait.until(ExpectedConditions.presenceOfElementLocated(widget_dropdownmenu));
			widget_Input_destination.sendKeys(Keys.ENTER);
			
			String actual = widget_Input_destination.getText().toLowerCase().trim();
			if(actual.isEmpty()) {
				//Si getText no funciona uso getAttribute
				actual = widget_Input_destination.getAttribute("value").toLowerCase().trim();
			}
			
			Assert.assertTrue(actual.contains(data.get(i).toLowerCase().trim()),
					"El input destination no contiene la palabra buscada:"+data.get(i).trim());
			
			widget_Input_destination.clear();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(widget_dropdownmenu));
		}
	}
	
	//En construccion!!!
	public void verifyURLToContain(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	//+++++++++++++++++++++++++++++++++++ WAITS ++++++++++++++++++++++++++++++++++++++++++++++++
	public void waitForOverlayButtons() {
		if(BasicUtils.existsElement(driver, byListProduct)) {
			List<WebElement> allLoaderButtons;
			allLoaderButtons = listProduct.findElements(byLoaderButton);
			if(!allLoaderButtons.isEmpty()) {
				//Esperar a que se quiten todos los overlays de los botones
				wait.until(ExpectedConditions.invisibilityOfAllElements(allLoaderButtons));
			}
		}
	}
	
	public void waitForOverlay() {
		//Esperar a que se quite el overlay
		//wait.until(WaitFor.attributeValue(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		wait.until(ExpectedConditions.attributeContains(byLoaderOverlayPage, "style", "display: none; opacity: 0;"));
	}
	
	public void waitForContentToBeReady() {
		waitForOverlay();
		waitForOverlayButtons();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//--------------------------- FUNCIONES PRIVADAS -------------------------------------------
	private int getItemNum_firstHotelAvailable(List<WebElement> allSearchResults) {
		Assert.assertFalse(allSearchResults.isEmpty(),"ENF>>>El parametro de la lista de resultados esta vacia!.");
		
		int index = CoreConfig.FAULTVALUE;
		for (int i = 0; i < allSearchResults.size(); i++) {
			WebElement listProductBlock = allSearchResults.get(i);
			wait.until( ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, byButton_seeOffer));
			
			if(!listProductBlock.findElements(byProductRateFinal).isEmpty()) {
				WebElement rateFinal = listProductBlock.findElement(byProductRateFinal);
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
		if(CoreConfig.FAULTVALUE==index){logger.error("getItemNum_firstHotelAvailable() - No se encontro ningun hotel con disponibilidad!.");}
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
