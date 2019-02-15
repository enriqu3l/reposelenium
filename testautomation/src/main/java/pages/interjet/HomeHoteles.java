package pages.interjet;

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

import config.FWConfig;
import valueobjects.VOResData;

public class HomeHoteles {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HomeHoteles.class);
	
	//Constructor
	public HomeHoteles(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_INTERJET);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_INTERJET),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("interjetvacations."));
	}
	
	//--------------- Widget------------------------
	By byWidgetAutocompleteDropdownMenu = By.cssSelector(".pt-customJqueryUi .ui-autocomplete");
	
	//--------------- Widget Hoteles ---------------
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
		widgetImageDestEndHotelTrigger.click(); //Para ocultar el date picker dropdown
		logger.trace("End Date: "+endDate);
	}
	
	public void widgetSelectHotelOccupants(VOResData voHotelRes) {
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
		logger.info("Ending SearchHotel()");
	}
	
	public void widgetClickSearchButton() {
		widgetButtonSearchHoteles.click();
	}
	
	public void widgetSearchHotel(VOResData voHotelRes){
		logger.info("Starting SearchHotel()");
		widgetSelectHotelDestin(voHotelRes.getDestination());
		widgetSelectHotelStartDate(voHotelRes.getStartDate());
		widgetSelectHotelEndDate(voHotelRes.getEndDate());
		widgetSelectHotelOccupants(voHotelRes);
	}
	
	//PENDIENTE POR CONSTRUIR
	//Funcion para revisar cada link, cada item dentro del home page!!!
	public void VerifyAllHomeItems() {
		
	}
}