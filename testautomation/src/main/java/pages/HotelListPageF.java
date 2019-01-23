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
import helpers.WaitFor;
import utility.BasicUtils;
import valueobjects.VOHotelRes;

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
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 20),this);
		
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
	private By byListProductHotelName = By.cssSelector(".list-product-item-content .list-product-name");
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
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_minorsAges_Hotel_container")
	private  List<WebElement> widgetAllBlockMinorsAges;
	
	private By byWidgetStartDateDropdownMenu = By.cssSelector("#start-datepicker .dropdown-menu");
	private By byWidgetEndDateDropdownMenu = By.cssSelector("#end-datepicker .dropdown-menu");
	private By byWidgetDestinationDropdownMenu = By.cssSelector("#ptw-container .dropdown-menu");
	private By byWidgetHotelAdults = By.cssSelector(".ap_booker_Hotel_adults");
	private By byWidgetHotelMinors = By.cssSelector(".ap_booker_Hotel_minors");
	private By byWidgetAllKidsPerRoom = By.cssSelector(".ap_age.ap_Hotel_year");
	
	//--------------- Paging Elements - Basados en SPA-Hoteles ----------
	@FindBy(how=How.CSS, css=".pagination > *:last-child a")
	private  WebElement pagingNextPage;
	private By byPagingNextPage = By.cssSelector(".pagination > *:last-child a");
	
	//Esta funcion se dise�o pensando solo en la funcionalidad de las SPA
	public void listSelectFirstHotelAvailable() {
		//Esperar a que se quite el overlay, falla en PTCOMMXTest, porque la pagina de Test no usa SPA.
		waitForContentToBeReady();
		
		Assert.assertTrue(BasicUtils.existsElement(driver,byListListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(list_allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Cantidad de bloques (hoteles) en la lista de resultados: "+list_allBlocksResults.size());
		
		int index = listGetIndexOfFirstHotelAvailable(list_allBlocksResults);
		Assert.assertFalse(CoreConfig.FAULTVALUE==index,"LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.");
		WebElement seeOfferButton = list_allBlocksResults.get(index).findElement(byListButtonSeeOffer);
		seeOfferButton.click();
		
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
		logger.trace("Cantidad de bloques (hoteles) en la lista de resultados: " + list_allBlocksResults.size());
		
		//Aqui no estoy verificando si tiene disponibilidad, simplemente lo estoy seleccionando
		
		WebElement seeOfferButton = list_allBlocksResults.get(index).findElement(byListButtonSeeOffer);
		seeOfferButton.click();
		
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
		logger.info("Starting widgetClickSubmit()");
		
		waitForContentToBeReady();
		String url = driver.getCurrentUrl();
		widgetButtonSubmit.click();
		
		//A continuacion pongo un wait para esperar a que el boton lanze la accion
		
		//Esperar a que la url cambie, No lo estoy usando porque falla cuando no hay cambios en la url
		//wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
		
		//Agrego este Delay dado que tengo problemas cuando no cambia la url
		WaitFor.attributeChanged(byLoaderOverlayPage, "style", "display: none; opacity: 0;");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//En construccion!!!!!!!
	public void widgetChangeSearch(VOHotelRes voHotelRes){
		logger.info("Starting widgetChangeSearch()");
		waitForContentToBeReady();
		
		widgetInputDestination.clear();
		widgetInputDestination.sendKeys(voHotelRes.getDestination());
		//Wait until dropdown menu appears
		wait.until(ExpectedConditions.presenceOfElementLocated(byWidgetDestinationDropdownMenu));
		widgetInputDestination.sendKeys(Keys.ENTER);
		
		widgetChangeStartDate(voHotelRes.getStartDate());
		widgetChangeEndDate(voHotelRes.getEndDate());
		//Aqui el codigo para realizar la seleccion de rooms, adults, kids y agekids
		Select rooms = new Select(widgetSelectHotelRooms);
		rooms.selectByValue(Integer.toString(voHotelRes.getRoomCount()));
		//rooms.selectByIndex(voHotelResNew.getRoomCount()-1); //-1 porque es Base 0
		if(widgetAllBlockRooms.size() != voHotelRes.getRoomCount()) {
			 logger.error("No se crearon los campos suficientes de rooms, allBlocksRooms:"+widgetAllBlockRooms.size());
			 Assert.fail("LAF>>>No se crearon los campos suficientes de rooms, allBlocksRooms:"+widgetAllBlockRooms.size());
		}
		//Ya se crearon los rooms ahora hay que llenar los adultos y ni�os
		for(int i=0;i<widgetAllBlockRooms.size();i++) {
			WebElement weAdults = widgetAllBlockRooms.get(i).findElement(byWidgetHotelAdults);
			WebElement weKids = widgetAllBlockRooms.get(i).findElement(byWidgetHotelMinors);
			
			Select adults = new Select(weAdults);
			adults.selectByValue(Integer.toString(voHotelRes.getAdultsFromRoom(i)));
			
			Select kids = new Select(weKids);
			kids.selectByValue(Integer.toString(voHotelRes.getKidsFromRoom(i)));
		}
		//Ahora hay que llenar las edades de los ni�os en cada cuarto
		for(int i=0; i<widgetAllBlockMinorsAges.size();i++) {
			//Recorrer cada room
			if(voHotelRes.getKidsFromRoom(i)>0) {
				//Entra solo si el cuarto contiene ni�os
				List<WebElement> minors = widgetAllBlockMinorsAges.get(i).findElements(byWidgetAllKidsPerRoom);
				for(int j=0;j<minors.size();j++) {
					//Recorrer cada ni�o y setear la edad
					Select adults = new Select(minors.get(j));
					adults.selectByValue(Integer.toString(voHotelRes.getAgeFromAgekids(i, j)));
				}
			}
		}
	}
	
	//En construccion, ya mero esta lista!!!
	public void widgetChangeStartDate(String date) {
		//Me falta poner candados para validar el parametro	
		logger.info("Starting widgetChangeStartDate()");
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
		
		logger.info("Starting widgetChangeEndDate()");
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
	public void pagingClickOnNextPage() {
		waitForContentToBeReady();
		logger.info("Starting pagingClickOnNextPage!");
		
		/*List<WebElement> elements= driver.findElements(byPagingNextPage);
		WebElement we = elements.get(elements.size() - 1);
		we.click();*/
		String url = driver.getCurrentUrl();
		pagingNextPage.click();
		
		//Espero a que el boton lanze una nueva url
		//(tiempo que tarda el boton en lanzar la accion)
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
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
		if(!actual.contains(expected)) {
			logger.error("Actual destin: ("+actual+") is not as expeted: ("+expected+")");
			Assert.fail("LAF>>>Actual destin: ("+actual+") is not as expeted: ("+expected+")");
		}else {
			logger.info("widgetVerifyDestinationToBe PASS");
		}
	}
	
	public void widgetVerifyStartDateToBe(String expected) {
		waitForContentToBeReady();
		String actual = widgetInputStartDate.getAttribute("value").trim();
		logger.trace("Fecha en el input StartDate:"+actual);
		if(!actual.contains(expected)) {
			logger.error("Actual date: ("+actual+") is not as expeted: ("+expected+")");
			Assert.fail("LAF>>>Actual date: ("+actual+") is not as expeted: ("+expected+")");
		}else {
			logger.info("widgetVerifyStartDateToBe PASS");
		}
	}
	
	public void widgetVerifyEndDateToBe(String expected) {
		waitForContentToBeReady();
		String actual = widgetInputEndDate.getAttribute("value").trim();
		logger.trace("Fecha en el input StartDate:"+actual);
		if(!actual.contains(expected)) {
			logger.error("Actual date: ("+actual+") is not as expeted: ("+expected+")");
			Assert.fail("LAF>>>Actual date: ("+actual+") is not as expeted: ("+expected+")");
		}else {
			logger.info("widgetVerifyEndDateToBe PASS");
		}
	}
	
	public void widgetVerifyAutocompleteDestination(List<String> data) {
		logger.info("Starting widgetVerifyAutocompleteDestination()");
		//Me falta poner candados para validar el parametro
		
		waitForContentToBeReady();
		for(int i=0; i<data.size();i++) {
			widgetInputDestination.clear();
			widgetInputDestination.sendKeys(data.get(i).toLowerCase().trim());
			//Wait until dropdown menu appears
			wait.until(ExpectedConditions.presenceOfElementLocated(byWidgetDestinationDropdownMenu));
			widgetInputDestination.sendKeys(Keys.ENTER);
			String actual = widgetInputDestination.getAttribute("value").toLowerCase().trim();
			Assert.assertTrue(actual.contains(data.get(i).toLowerCase().trim()),
					"El input destination no contiene la palabra buscada:"+data.get(i).trim());
			widgetInputDestination.clear();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byWidgetDestinationDropdownMenu));
		}
	}
	
	public void listVerifyResultListHasElements() {
		Assert.assertTrue(BasicUtils.existsElement(driver,byListListProduct),"ENF>>>No se encontro ninguna lista de resultados!.");
		Assert.assertFalse(list_allBlocksResults.isEmpty(),"ENF>>>La lista de resultados esta vacia!.");
		logger.trace("Cantidad de bloques (hoteles) en la lista de resultados: "+list_allBlocksResults.size());
	}
	
	/**
	 * @param expected - String - Valor a evaluar que este presente en la URL
	 * @param encoding - Boolean - Para indicar si se quiere codificar el parametro antes de buscarlo en la URL
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
	
	public void verifyHeaderTitleToBe(String title) {
		waitForContentToBeReady();
		
		String actual = pageHeaderTitle.getText().trim();
		String expected = title;
		if(!actual.contains(expected)) {
			logger.error("Text: ("+actual+") in pageHeaderTitle does not contain the expected: ("+expected+")");
			Assert.fail("LAF>>>Text: ("+actual+") in pageHeaderTitle does not contain the expected: ("+expected+")");
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
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//--------------------------- FUNCIONES PRIVADAS -------------------------------------------
	private int listGetIndexOfFirstHotelAvailable(List<WebElement> allSearchResults) {
		logger.trace("Starting listGetIndexOfFirstHotelAvailable()");
		Assert.assertFalse(allSearchResults.isEmpty(),"ENF>>>El parametro de la lista de resultados esta vacia!.");
		
		int index = CoreConfig.FAULTVALUE;
		for (int i = 0; i < allSearchResults.size(); i++) {
			WebElement listProductBlock = allSearchResults.get(i);
			wait.until( ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, byListButtonSeeOffer));
			
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
