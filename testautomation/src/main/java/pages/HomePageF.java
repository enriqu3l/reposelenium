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
	
	//Esta es la URL a la que estare llegando:
	//https://www.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F04&checkout=2019%2F02%2F05&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1		
	//Si no envio parametros:
	//https://www.pricetravel.com/hoteles/cancun-area
	//usará los valores por default para la busqueda
	
	
	//--------------- Widget--------------------
	@FindBy(how=How.CSS, css="#ptw-menu > ul")
	@CacheLookup
	private WebElement widgetMenu;
	
	By widgetProductActive = By.cssSelector("li.ptw-active > a");
	By widgetAutocompleteDropdownMenu = By.cssSelector(".pt-customJqueryUi .ui-autocomplete");
	
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
	
	//Aun falta de agregar los otros productos....

	
	public void widgetSearchHotel(VOHotelRes voHotelRes){
		logger.info("Starting SearchHotel()");
		verifyProductSelectedOnWidgetMenu("Hoteles");
		widgetInputDestHotel.clear();
		widgetInputDestHotel.sendKeys(voHotelRes.getDestination());
		logger.trace("Destin: "+ voHotelRes.getDestination());
		wait.until(ExpectedConditions.attributeContains(widgetAutocompleteDropdownMenu, "style", "display: block;"));
		widgetInputDestHotel.sendKeys(Keys.ENTER);
		widgetInputDestStartHotel.clear();
		widgetInputDestStartHotel.sendKeys(voHotelRes.getStartDate());
		logger.trace("Start Date: "+voHotelRes.getStartDate());
		widgetInputDestEndHotel.clear();
		widgetInputDestEndHotel.sendKeys(voHotelRes.getEndDate());
		logger.trace("End Date: "+voHotelRes.getEndDate());
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
	
	public void verifyProductSelectedOnWidgetMenu(String product) {
		logger.info("Starting verifyProductSelectedOnWidgetMenu()");
		if(!widgetMenu.findElement(widgetProductActive).getText().equals(product)) {
			widgetMenu.findElement(By.linkText(product) ).click();
		}
		logger.info("Ending verifyProductSelectedOnWidgetMenu()");
	}
	
	//PENDIENTE POR CONSTRUIR
	//Funcion para revisar cada link, cada item dentro del home page!!!
	public void VerifyAllHomeItems() {
		
	}
}

/*Knowledge

1.- Cuando se abre una nueva tab, asegurarse de seleccionarla
porque el driver no switchea por si solo a la nueva tab!!

2.- Si primero se inicializa un webElement (con pageFactory o usando findElement())
y despues se usa un wait para esperar a que carge un contenido, no se podra encontrar ni usar!!
Te esperara un sin fin de errores!!!
Lo correcto es poner un wait hasta que el elemento este cargado en la pagina y despues
inicializarlo con PageFactory o con findElement().
Por todo lo anterior se recomienda esperar con un wait ExpectedCondition a que
el contenido esta propiamente cargado para despues ya inicializar webElements
*/

/*Mover el mouse al "Input Destino" para que no afecte la seleccion de los elementos
try {
	Point location = Input_destHotel.getLocation();
    Robot robot;
	robot = new Robot();
	robot.mouseMove(location.getX(),location.getY()+120);
} catch (AWTException e) {
	e.printStackTrace();
	System.out.println("Error moviendo mouse: "+e.toString());
}*/

/*
 * Los actions no mueven el mouse, solo realizan acciones a nivel de DOM!!
 * Actions a = new Actions(driver);
 * a.moveToElement(Input_destHotel).build().perform();
*/