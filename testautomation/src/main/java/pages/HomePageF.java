package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import valueobjects.VOHotelRes;

public class HomePageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HomePageF.class);
	
	//Constructor
	public HomePageF(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 30),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("pricetravel."));
	}
	
	//--------------- Widget------------------------
	//@FindBy(how=How.CSS, using="#ptw-menu > ul")
	@FindBy(how=How.CSS, css="#ptw-menu > ul")
	@CacheLookup
	private WebElement widgetMenu;
	
	By byWidgetProductActive = By.cssSelector("li.ptw-active > a");
	By byWidgetAutocompleteDropdownMenu = By.cssSelector(".pt-customJqueryUi .ui-autocomplete");
	
	//--------------- Widget Hoteles ---------------
	@FindBy(how=How.ID, id="var1_1")
	@CacheLookup
	private WebElement widgetRadioButtonHotel;
	
	@FindBy(how=How.ID, id="var1_3")
	@CacheLookup
	private WebElement widgetRadioButtonHotelFlight;
	
	@FindBy(how=How.ID, id="ap_dest_hotel")
	@CacheLookup
	private WebElement widgetInputDestHotel;
	
	@FindBy(how=How.ID, id="ap_dest_start")
	@CacheLookup
	private WebElement widgetInputDestStartHotel;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form .ptw-field-date:nth-child(1) .ui-datepicker-trigger")
	@CacheLookup
	private WebElement widgetImageDestStartHotelTrigger;
	
	@FindBy(how=How.ID, id="ap_dest_end")
	@CacheLookup
	private WebElement widgetInputDestEndHotel;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form .ptw-field-date:nth-child(2) .ui-datepicker-trigger")
	@CacheLookup
	private WebElement widgetImageDestEndHotelTrigger;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_rooms")
	@CacheLookup
	private WebElement widgetSelectBookerHotelRooms;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_adults0")
	@CacheLookup
	private WebElement widgetSelectBookerHotelAdults0;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_minors0")
	@CacheLookup
	private WebElement widgetSelectBookerHotelMinors0;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form .ptw-submit-btn")
	@CacheLookup
	private WebElement widgetButtonSearchHoteles;
	
	//--------------- Widget Paquetes ---------------
	@FindBy(how=How.ID, id="var3_3")
	@CacheLookup
	private WebElement widgetRadioButtonHotelFlightPackage;
	
	@FindBy(how=How.ID, id="ap_origin_flightPackage")
	@CacheLookup
	private WebElement widgetInputOriginFlightPackage;
	
	@FindBy(how=How.ID, id="ap_dest_start")
	@CacheLookup
	private WebElement widgetInputDestFlightPackage;
	
	@FindBy(how=How.ID, id="ap_flightPackage_start")
	@CacheLookup
	private WebElement widgetInputFlightPackageStart;
	
	@FindBy(how=How.ID, id="ap_flightPackage_end")
	@CacheLookup
	private WebElement widgetInputFlightPackageEnd;
	
	@FindBy(how=How.CSS, css="#ap_booker_FlightPackage_form .ptw-submit-btn")
	@CacheLookup
	private WebElement widgetButtonSearchPaquetes;
	
	//--------------- Widget Vuelos ---------------
	@FindBy(how=How.ID, id="var2_2")
	@CacheLookup
	private WebElement widgetRadioButtonFlight;
	
	@FindBy(how=How.ID, id="var2_3")
	@CacheLookup
	private WebElement widgetRadioButtonFlightPackage;
	
	@FindBy(how=How.CSS, css="#ap_booker_Flight_form .ptw-submit-btn")
	@CacheLookup
	private WebElement widgetButtonSearchFlight;
	
	//Falta de agregar elementos de los otros productos....

	public void widgetSelectHotelDestin(String destin) {
		logger.info("Starting widgetSelectHotelDestin()");
		widgetInputDestHotel.clear();
		widgetInputDestHotel.sendKeys(destin);
		logger.trace("Destin: "+ destin);
		wait.until(ExpectedConditions.attributeContains(byWidgetAutocompleteDropdownMenu, "style", "display: block;"));
		widgetInputDestHotel.sendKeys(Keys.ENTER);
	}
	
	public void widgetSelectHotelStartDate(String startDate) {
		widgetInputDestStartHotel.clear();
		widgetInputDestStartHotel.sendKeys(startDate);
		logger.trace("Start Date: "+startDate);
	}
	
	public void widgetSelectHotelEndDate(String endDate) {
		widgetInputDestEndHotel.clear();
		widgetInputDestEndHotel.sendKeys(endDate);
		logger.trace("End Date: "+endDate);
	}
	
	public void widgetSelectHotelOccupants(VOHotelRes voHotelRes) {
		//Image_destEndHotelTrigger.click();  esconde el calendario
		Select rooms = new Select(widgetSelectBookerHotelRooms);
		rooms.selectByVisibleText(Integer.toString(voHotelRes.getRoomCount()));
		logger.trace("Rooms: "+voHotelRes.getRoomCount());
		Select adults = new Select(widgetSelectBookerHotelAdults0);
		adults.selectByVisibleText(Integer.toString(voHotelRes.getAdultsFromRoom(0)));
		logger.trace("Adults: "+voHotelRes.getAdultsFromRoom(0));
		Select kids = new Select(widgetSelectBookerHotelMinors0);
		kids.selectByVisibleText(Integer.toString(voHotelRes.getKidsFromRoom(0)));
		logger.trace("Kids: "+voHotelRes.getKidsFromRoom(0));
		widgetButtonSearchHoteles.click();
		logger.info("Ending SearchHotel()");
	}
	
	public void widgetSearchHotel(VOHotelRes voHotelRes){
		logger.info("Starting SearchHotel()");
		widgetVerifyProductSelected("Hoteles");
		widgetSelectHotelDestin(voHotelRes.getDestination());
		widgetSelectHotelStartDate(voHotelRes.getStartDate());
		widgetSelectHotelEndDate(voHotelRes.getEndDate());
		widgetSelectHotelOccupants(voHotelRes);
	}
	
	public void widgetVerifyProductSelected(String product) {
		logger.info("Starting verifyProductSelectedOnWidgetMenu()");
		if(!widgetMenu.findElement(byWidgetProductActive).getText().equals(product)) {
			widgetMenu.findElement(By.linkText(product) ).click();
		}
		logger.info("Ending verifyProductSelectedOnWidgetMenu()");
	}
	
	//PENDIENTE POR CONSTRUIR
	//Funcion para revisar cada link, cada item dentro del home page!!!
	public void VerifyAllHomeItems() {
		
	}
}