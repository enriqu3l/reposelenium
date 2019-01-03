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
		logger.info("Start HomePage constructor");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 30),this);
		logger.info("Launched initElements");
	}
	
	//Esta es la URL a la que estare llegando:
	//https://www.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F04&checkout=2019%2F02%2F05&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1		
	//Si no envio parametros:
	//https://www.pricetravel.com/hoteles/cancun-area
	//usará los valores por default para la busqueda
	
	
	@FindBy(how=How.CSS, css="#ptw-menu > ul")
	@CacheLookup
	private WebElement widgetMenu;
	
	//---Hoteles---
	@FindBy(how=How.ID, id="var1_1")
	@CacheLookup
	private WebElement RadioButton_hotel;
	
	@FindBy(how=How.ID, id="var1_3")
	@CacheLookup
	private WebElement RadioButton_hotelFlight;
	
	@FindBy(how=How.ID, id="ap_dest_hotel")
	@CacheLookup
	private WebElement Input_destHotel;
	
	@FindBy(how=How.ID, id="ap_dest_start")
	@CacheLookup
	private WebElement Input_destStartHotel;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form  div.ptw-field-group.ptw-field-dates > div:nth-child(1) > img.ui-datepicker-trigger")
	@CacheLookup
	private WebElement Image_destStartHotelTrigger;
	
	@FindBy(how=How.ID, id="ap_dest_end")
	@CacheLookup
	private WebElement Input_destEndHotel;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form  div.ptw-field-group.ptw-field-dates > div:nth-child(2) > img.ui-datepicker-trigger")
	@CacheLookup
	private WebElement Image_destEndHotelTrigger;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_rooms")
	@CacheLookup
	private WebElement Select_bookerHotelRooms;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_adults0")
	@CacheLookup
	private WebElement Select_bookerHotelAdults0;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_minors0")
	@CacheLookup
	private WebElement Select_bookerHotelMinors0;
	
	//---Paquetes---
	@FindBy(how=How.ID, id="var3_3")
	@CacheLookup
	private WebElement RadioButton_hotelFlightPackage;
	
	@FindBy(how=How.ID, id="ap_origin_flightPackage")
	@CacheLookup
	private WebElement Input_originFlightPackage;
	
	@FindBy(how=How.ID, id="ap_dest_start")
	@CacheLookup
	private WebElement Input_destFlightPackage;
	
	@FindBy(how=How.ID, id="ap_flightPackage_start")
	@CacheLookup
	private WebElement Input_flightPackageStart;
	
	@FindBy(how=How.ID, id="ap_flightPackage_end")
	@CacheLookup
	private WebElement Input_flightPackageEnd;
	
	//---Vuelos---
	@FindBy(how=How.ID, id="var2_2")
	@CacheLookup
	private WebElement RadioButton_Flight;
	
	@FindBy(how=How.ID, id="var2_3")
	@CacheLookup
	private WebElement RadioButton_FlightPackage;
	
	//Aun falta de agregar los otros productos....
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form > div > div.ptw-buttons > input.ap-data_engine-Hotel.ap_booker_submit.ptw-btn.ptw-submit-btn")
	@CacheLookup
	private WebElement Button_search;
	
	
	//--------------- Widget--------------------
	By productActive = By.cssSelector("li.ptw-active > a");
	By waitdropdownmenu = By.cssSelector(".pt-customJqueryUi .ui-autocomplete");
	
	public void SearchHotel(VOHotelRes DO_HotelRes){
		logger.info("Starting SearchHotel()");
		verifyProductSelectedOnWidgetMenu("Hoteles");
		Input_destHotel.clear();
		Input_destHotel.sendKeys(DO_HotelRes.getDestination());
		wait.until(ExpectedConditions.attributeContains(waitdropdownmenu, "style", "display: block;"));
		Input_destHotel.sendKeys(Keys.ENTER);
		Input_destStartHotel.clear();
		Input_destStartHotel.sendKeys(DO_HotelRes.getStartDate());
		Input_destEndHotel.clear();
		Input_destEndHotel.sendKeys(DO_HotelRes.getEndDate());
		//Image_destEndHotelTrigger.click();  esconde el calendario
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByVisibleText("1");
		Select adults = new Select(Select_bookerHotelAdults0);
		adults.selectByValue(Integer.toString(DO_HotelRes.getAdults()));
		Select kids = new Select(Select_bookerHotelMinors0);
		kids.selectByValue("0");
		Button_search.click();
		logger.info("Ending SearchHotel()");
	}
	
	public void verifyProductSelectedOnWidgetMenu(String product) {
		logger.info("Starting verifyProductSelectedOnWidgetMenu()");
		if(!widgetMenu.findElement(productActive).getText().equals(product)) {
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