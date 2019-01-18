package pages;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

/**
 * Esta clase contiene todos los elementos, acciones y verificaciones necesarios para la pagina SPA Hotel List
 *
 */
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
	
	private By byLoaderOverlayPage = By.cssSelector(".loader-overlay.ng-trigger");
	private By byLoaderButton = By.cssSelector(".list-product-rate .loader");
	private By byLoaderOverlayFiltros = By.cssSelector(".card-body .loader-overlay");
	
	@FindBy(how=How.CSS, css=".loader__title")
	private WebElement loaderTitle;
	
	@FindBy(how=How.CSS, css=".spinner")
	private WebElement spiner;
	
	
	//-------------- Header Section --------------------------------
	private By byPageHeaderTitle = By.cssSelector(".page-header .page-header-title");
	
	@FindBy(how=How.CSS, css=".page-header .page-header-title")
	private WebElement pageHeaderTitle;
	
	
	//-------------- List Section ----------------------------------
	@FindBy(how=How.CSS, css=".list-product .list-product-rate .loader")
	private List<WebElement> listListProductRateLoaderButton; //No lo use porque entraba PageFactory y tardaba 30seg
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	private List<WebElement> list_allBlocksResults;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block .list-product-rate .list-product-rate-action a")
	private WebElement list_buttonFirstItem;
	
	private By byListListProduct = By.cssSelector(".list-product");
	private By byListProductRateFinal = By.cssSelector(".list-product-rate .product-rate-final");
	private By byListButtonSeeOffer = By.cssSelector(".list-product-rate .list-product-rate-action .btn");
	private By byListListProductRateLoaderButton = By.cssSelector(".list-product .list-product-rate .loader");
	
	
	//--------------- Lateral Widget elements - Basados en SPA-Hoteles ----------
	@FindBy(how=How.CSS, css="#ptw-container #destination")
	private WebElement widgetInputDestination;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ap_dest_calendar")
	private WebElement widgetInputStartDate;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ap_dest_calendar")
	private WebElement widgetInputEndDate;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ui-datepicker-trigger")
	private WebElement widgetStartDatePicker;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ui-datepicker-trigger")
	private WebElement widgetEndDatePicker;
	
	@FindBy(how=How.CSS, css="#start-datepicker .dropdown-menu .ngb-dp-month-name")
	private WebElement widgetStartDateTitle;
	
	@FindBy(how=How.CSS, css="#end-datepicker .dropdown-menu .ngb-dp-month-name")
	private WebElement widgetEndDateTitle;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow button.btn")
	private WebElement widgetStartDateBeforeMonth;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow.right button.btn")
	private WebElement widgetStartDateNextMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow button.btn")
	private WebElement widgetEndDateBeforeMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow.right button.btn")
	private WebElement widgetEndDateNextMonth;
	
	@FindBy(how=How.CSS, css="#ptw-container #ap_booker_Hotel_rooms")
	private WebElement widgetSelectHotelRooms;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_adults")
	private WebElement widgetSelectHotelAdults;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_minors")
	private WebElement widgetSelectHotelMinors;
	
	@FindBy(how=How.CSS, css="#ptw-container .ptw-submit-btn")
	private WebElement widgetButtonSubmit;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotelroom")
	private  List<WebElement> widgetAllBlockRooms;
	
	private By byWidgetStartDateDropdownMenu = By.cssSelector("#start-datepicker .dropdown-menu");
	private By byWidgetEndDateDropdownMenu = By.cssSelector("#end-datepicker .dropdown-menu");
	private By byWidgetDestinationDropdownMenu = By.cssSelector("#ptw-container .dropdown-menu");
	private By byWidgetHotelAdults = By.cssSelector(".ap_booker_Hotel_adults");
	private By byWidgetHotelMinors = By.cssSelector(".ap_booker_Hotel_minors");
	
	
	//--------------- Lateral Pagination Elements - Basados en SPA-Hoteles!! ----------
	private By byPagingNext = By.xpath("/html/body/app-root/div/app-list/div[2]/div[2]/app-pager/nav/ul/li[8]/a/span");
	//@FindBy(how=How.XPATH, xpat="/html/body/app-root/div/app-list/div[2]/div[2]/app-pager/nav/ul/li[8]/a/span/span")
	//WebElement pagination_Next;
	
	
	//Esta funcion se diseño pensando solo en la funcionalidad de las SPA
	public void listSelectFirstHotelAvailable() {
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForContentToBeReady();
		
		Assert.assertTrue(BasicUtils.existsElement(driver,byListListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(list_allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: "+list_allBlocksResults.size());
		
		int index = listGetIndexOfFirstHotelAvailable(list_allBlocksResults);
		Assert.assertFalse(CoreConfig.FAULTVALUE==index,"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		WebElement Button_seeOffer = list_allBlocksResults.get(index).findElement(byListButtonSeeOffer);
		Button_seeOffer.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	public void listSelectHotelByIndex(int index) throws InterruptedException {
		Assert.assertTrue((index>=0 && index<FrameworkConfig.TOTALRECORDSPERPAGES),"LAF>>>Parametro invalido, index tiene que ser menor a 20!.");
		
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForContentToBeReady();
		
		Assert.assertTrue(BasicUtils.existsElement(driver, byListListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(list_allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: " + list_allBlocksResults.size());
		
		//Aqui no estoy verificando si tiene disponibilidad, simplemente lo estoy seleccionando
		
		WebElement Button_seeOffer = list_allBlocksResults.get(index).findElement(byListButtonSeeOffer);
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
		
		widgetInputDestination.clear();
		widgetInputDestination.sendKeys(destin);
		
		//Wait until dropdown menu appears
		wait.until(ExpectedConditions.presenceOfElementLocated(byWidgetDestinationDropdownMenu));
		
		widgetInputDestination.sendKeys(Keys.ENTER);
	}
	
	public void widgetChangeAdults(int adultsNumber) {
		logger.info("Starting widget_changeAdults()");
		//Me falta poner candados para validar el parametro
		
		waitForContentToBeReady();
		
		Select adults = new Select(widgetSelectHotelAdults);
		adults.selectByValue(Integer.toString(adultsNumber));
	}
	
	public void widgetClickSubmit() {
		logger.info("Starting widget_search()");
		
		waitForContentToBeReady();
		String url = driver.getCurrentUrl();
		
		widgetButtonSubmit.click();
		
		
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
		
		widgetInputDestination.clear();
		widgetInputDestination.sendKeys(voHotelResNew.getDestination());
		widgetChangeStartDate(voHotelResNew.getStartDate());
		widgetChangeEndDate(voHotelResNew.getEndDate());
		
		//Aqui el codigo para realizar la seleccion de rooms, adults, kids y agekids
		
		Select rooms = new Select(widgetSelectHotelRooms);
		rooms.selectByValue(Integer.toString(voHotelResNew.getRoomCount()));
		//rooms.selectByIndex(voHotelResNew.getRoomCount()-1); //-1 porque es Base 0
		
		//Aqui ya se crearon los rooms ahora hay que llenar los adultos y niños
		if(widgetAllBlockRooms.size() != voHotelResNew.getRoomCount()) {
			 logger.error("No se crearon los campos suficientes de rooms, allBlocksRooms:"+widgetAllBlockRooms.size());
			 Assert.fail("LAF>>>No se crearon los campos suficientes de rooms, allBlocksRooms:"+widgetAllBlockRooms.size());
		}
		
		for(int i=0;i<widgetAllBlockRooms.size();i++) {
			WebElement weAdults = widgetAllBlockRooms.get(i).findElement(byWidgetHotelAdults);
			WebElement weKids = widgetAllBlockRooms.get(i).findElement(byWidgetHotelMinors);
			
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
		widgetInputDestination.clear();
		widgetInputDestination.sendKeys(voHotelRes.getDestination());
		widgetChangeStartDate(voHotelRes.getStartDate());		
		widgetChangeEndDate(voHotelRes.getEndDate());
		Select rooms = new Select(widgetSelectHotelRooms);
		rooms.selectByValue("1");
		Select adults = new Select(widgetSelectHotelAdults);
		adults.selectByValue(Integer.toString(voHotelRes.getAdults()));
		Select kids = new Select(widgetSelectHotelMinors);
		kids.selectByValue("0");
		widgetClickSubmit();
	}
	
	//En construccion, ya mero esta lista!!!
	public void widgetChangeStartDate(String date) {
		//Me falta poner candados para validar el parametro	
		
		logger.info("Starting widget_selectStartDate()");
		waitForContentToBeReady(); //Esperar a que se quite el overlay
		
		//Abrir el calendario si no esta abierto
		if(BasicUtils.noExistsElement(driver,byWidgetStartDateDropdownMenu)){widgetStartDatePicker.click();}
		
		LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
		int day = localDate.getDayOfMonth();
		String actualDate = BasicUtils.toddMMyyyyFormat(widgetStartDateTitle.getText().trim());
		
		int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
		logger.trace("widget_selectStartDate() TotalMonthDifference: "+TotalMonthDifference);
		if(TotalMonthDifference>0) {
			for(int i=0; i<TotalMonthDifference;i++) {
				widgetStartDateNextMonth.click(); //click hacia adelante
			}
		}else if(TotalMonthDifference<0) {
			for(int i=0; i>TotalMonthDifference;i--) {
				widgetStartDateBeforeMonth.click(); //click hacia atras
			}
		}
		String xpath = "//*[@id='start-datepicker']//div[text()='"+day+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String selector = "#start-datepicker div[aria-label*=' "+day+" '].ngb-dp-day div";
		//driver.findElement(By.cssSelector(selector)).click();
		
		logger.trace("Valor de widget_Input_startDate: " + widgetInputStartDate.getAttribute("value"));
	}
	
	//En construccion, ya mero esta lista!!!
	public void widgetChangeEndDate(String date) {
		//Me falta poner candados para validar el parametro
		
		logger.info("Starting widget_selectEndDate()");
		logger.trace("actualDate:  "+date);
		waitForContentToBeReady(); //Esperar a que se quite el overlay
		//Abrir el calendario si no esta abierto
		if(BasicUtils.noExistsElement(driver,byWidgetEndDateDropdownMenu)){widgetEndDatePicker.click();}
		
		LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
		int day = localDate.getDayOfMonth();
		String actualDate = BasicUtils.toddMMyyyyFormat(widgetEndDateTitle.getText().trim());
		logger.trace("actualDate:  "+actualDate);
		int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
		logger.trace("widget_selectEndDate() TotalMonthDifference: "+TotalMonthDifference);
		if(TotalMonthDifference>0) {
			for(int i=0; i<TotalMonthDifference;i++) {
				widgetEndDateNextMonth.click(); //click hacia adelante
			}
		}else if(TotalMonthDifference<0) {
			for(int i=0; i>TotalMonthDifference;i--) {
				widgetEndDateBeforeMonth.click(); //click hacia atras
			}
		}
		
		String xpath = "//*[@id='end-datepicker']//div[text()='"+day+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String selector = "#end-datepicker div[aria-label*=' " + Integer.toString(day) + " '].ngb-dp-day div";
		//driver.findElement(By.cssSelector(selector)).click();
		
		logger.trace("Valor de widget_Input_startDate: " + widgetInputEndDate.getAttribute("value"));
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
		
		driver.findElement(byPagingNext).click();
	}
	//++++++++++++++++++++++++++ END PAGING FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++++
	
	
	//+++++++++++++++++++++++++++ VERIFY FUNCTIONS +++++++++++++++++++++++++++++++++++++++++++++
	public void widgetVerifyDestinationToBe(String expected) {
		waitForContentToBeReady();
		
		String actual = widgetInputDestination.getText().trim();
		if(actual.isEmpty()) {
			//Si getText no funciona uso el metodo getAttribute
			actual = widgetInputDestination.getAttribute("value").trim();
		}
		logger.trace("actual:"+actual);
		
		if(!actual.contains(expected)) {
			logger.error("Actual value: ("+actual+") in widget_Input_destination is not equal to expected value: ("+expected+")");
			Assert.fail("LAF>>>Actual value: ("+actual+") in widget_Input_destination is not equal to expected value: ("+expected+")");
		}
	}
	
	public void widgetVerifyStartDateToBe(String expected) {
		waitForContentToBeReady();
		
		String actual = widgetInputStartDate.getText().trim();
		if(actual.isEmpty()) {
			//Si getText no funciona uso el metodo getAttribute
			actual = widgetInputStartDate.getAttribute("value").trim();
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
	
	public void listVerifyResultListHasElements() {
		Assert.assertTrue(BasicUtils.existsElement(driver,byListListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(list_allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Tamaño de la lista: "+list_allBlocksResults.size());
	}
	
	public void widgetVerifyAutocompleteDestination(List<String> data) {
		logger.info("Starting widget_changeDestin()");
		//Me falta poner candados para validar el parametro
		
		waitForContentToBeReady();
		
		for(int i=0; i<data.size();i++) {
			widgetInputDestination.clear();
			widgetInputDestination.sendKeys(data.get(i).toLowerCase().trim());
			
			//Wait until dropdown menu appears
			wait.until(ExpectedConditions.presenceOfElementLocated(byWidgetDestinationDropdownMenu));
			widgetInputDestination.sendKeys(Keys.ENTER);
			
			String actual = widgetInputDestination.getText().toLowerCase().trim();
			if(actual.isEmpty()) {
				//Si getText no funciona uso getAttribute
				actual = widgetInputDestination.getAttribute("value").toLowerCase().trim();
			}
			
			Assert.assertTrue(actual.contains(data.get(i).toLowerCase().trim()),
					"El input destination no contiene la palabra buscada:"+data.get(i).trim());
			
			widgetInputDestination.clear();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byWidgetDestinationDropdownMenu));
		}
	}
	
	
	
	/**
	 * @param expected - String - Valor a evaluar que este presente en la URL
	 * @param encoding - Boolean - Para indicar si se quiere codigicar el parametro antes de buscarlo en la URL
	 * @author enrique.lopez
	 */
	public void verifyUrlContains(String expected, boolean encoding) {
		String expected2 = expected;
		if(encoding) {
			try {
				expected2= URLEncoder.encode(expected, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String current = driver.getCurrentUrl();
		if(current.contains(expected2)) {
			logger.info("Assert Ok. URL contains:"+expected2);
		}else {
			logger.error("Error. URL does not contain: "+expected2);
			Assert.fail("Error. URL does not contain: "+expected2);
		}
	}
	
	
	//+++++++++++++++++++++++++++++++++++ WAITS ++++++++++++++++++++++++++++++++++++++++++++++++
	public void waitForLoaderButtons() {
		////Este primer metodo se tardaba porque entraba el PageFactory y esperaba 30 seg!!
		//wait.until(ExpectedConditions.invisibilityOfAllElements(listListProductRateLoaderButton)); 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byListListProductRateLoaderButton));
		
		/*Todas esas validaciones no son necesarias dado que la funcion invisibilityOfAllElements
		 * revisa la funcion isDisplayed y ademas si detecta una excepcion retorna verdadero
		if(BasicUtils.existsElement(driver, byListListProduct)) {
			List<WebElement> allLoaderButtons;
			allLoaderButtons = listListProduct.findElements(byLoaderButton);
			if(!allLoaderButtons.isEmpty()) {
				//Esperar a que se quiten todos los overlays de los botones
				wait.until(ExpectedConditions.invisibilityOfAllElements(allLoaderButtons));
			}
		}*/
	}
	
	public void waitForOverlay() {
		//Esperar a que se quite el overlay
		//wait.until(WaitFor.attributeValue(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		wait.until(ExpectedConditions.attributeContains(byLoaderOverlayPage, "style", "display: none; opacity: 0;"));
	}
	
	public void waitForContentToBeReady() {
		waitForOverlay();
		waitForLoaderButtons();
		
		/*Por lo pronto agregue este sleep con 3 segundos en lo que verifico que todo funciona correctamente!!
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//--------------------------- FUNCIONES PRIVADAS -------------------------------------------
	private int listGetIndexOfFirstHotelAvailable(List<WebElement> allSearchResults) {
		Assert.assertFalse(allSearchResults.isEmpty(),"ENF>>>El parametro de la lista de resultados esta vacia!.");
		
		int index = CoreConfig.FAULTVALUE;
		for (int i = 0; i < allSearchResults.size(); i++) {
			WebElement listProductBlock = allSearchResults.get(i);
			wait.until( ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, byListButtonSeeOffer));
			
			if(!listProductBlock.findElements(byListProductRateFinal).isEmpty()) {
				WebElement rateFinal = listProductBlock.findElement(byListProductRateFinal);
				if (!rateFinal.getText().isEmpty()) {
					int i_masuno = i+1;
					logger.trace("listGetIndexOfFirstHotelAvailable() - Se encontro disp. en el Hotel No: " + i_masuno);
					logger.trace("listGetIndexOfFirstHotelAvailable() - Tarifa encontrada: " + rateFinal.getText());
					index = i;
					break;
				}
			}
			else{
				logger.trace("getItemNum_firstHotelAvailable() - No se encontro tarifa $ en el item: " + i);
			}
		}
		if(CoreConfig.FAULTVALUE==index){logger.error("listGetIndexOfFirstHotelAvailable() - No se encontro ningun hotel con disponibilidad!.");}
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
