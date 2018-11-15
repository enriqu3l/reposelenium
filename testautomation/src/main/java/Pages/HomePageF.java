package Pages;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataObjects.DOHotelRes;

public class HomePageF {
	private WebDriverWait wait;
	private WebDriver driver;
	
	//Constructor
	public HomePageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30),this);
	}
	
	//By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");
	//WebElement WEpassword = wait.until(ExpectedConditions.elementToBeClickable(password));
	
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
	
	public void SearchHotelDefault() throws InterruptedException {
		verifyProductSelectedOnWidgetMenu("Hoteles");
		
		Input_destHotel.sendKeys("Cancun");
		Thread.sleep(5000); //No eliminar hasta no solucionar el wait del dropdown ajax!!!
		Input_destHotel.sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		Input_destStartHotel.click();
		Input_destStartHotel.clear();
		Input_destStartHotel.sendKeys("04/02/2019");
		//driver.findElement(By.linkText("Sig>")).click();
		
		Thread.sleep(2000);
		Input_destEndHotel.click();
		Input_destEndHotel.clear();
		Input_destEndHotel.sendKeys("05/02/2019");
		
		Thread.sleep(3000);
		Image_destEndHotelTrigger.click();
		
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByVisibleText("1");
		
		Select adults = new Select(Select_bookerHotelAdults0);
		adults.selectByVisibleText("1");
		
		Thread.sleep(2000);
		Button_search.click();
	}
	
	public void SearchHotel(DOHotelRes DO_HotelRes) throws InterruptedException, AWTException {
		verifyProductSelectedOnWidgetMenu("Hoteles");
		
		//Mover el mouse al Input Destino
		//Point coordinates = driver.findElement(By.id("ap_dest_start")).getLocation();
		//Robot robot = new Robot();
		//robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
		
        Point location = Input_destHotel.getLocation();
        Robot robot = new Robot();
		robot.mouseMove(location.getX(),location.getY()+120);
		
		//Los actions no mueven el mouse, solo realizan acciones a nivel de DOM!!
		//Actions a = new Actions(driver);
		//a.moveToElement(Input_destHotel).build().perform();
		//-------------------------------------------------------------------------------
		
		Input_destHotel.clear();
		Input_destHotel.sendKeys(DO_HotelRes.getDestination());
		Thread.sleep(5000);
		Input_destHotel.sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		Input_destStartHotel.clear();
		Input_destStartHotel.sendKeys(DO_HotelRes.getStartDate());
		
		Thread.sleep(3000);
		Input_destEndHotel.clear();
		Input_destEndHotel.sendKeys(DO_HotelRes.getEndDate());
		
		Thread.sleep(3000);
		Image_destEndHotelTrigger.click();
		
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByVisibleText("1");
		
		Select adults = new Select(Select_bookerHotelAdults0);
		adults.selectByVisibleText(Integer.toString(DO_HotelRes.getAdults()));
		
		//Nos brincamos la parte de la seleccion de menores
		
		Thread.sleep(3000);
		Button_search.click();
		
	}
	
	public void verifyProductSelectedOnWidgetMenu(String product) {
		if(!widgetMenu.findElement(By.cssSelector("li.ptw-active > a")).getText().equals(product)) {
			widgetMenu.findElement(By.linkText(product) ).click();
		}
	}
	
	public void SelectProduct(String product) {
		widgetMenu.findElement(By.linkText(product) ).click();
	}
	
	public void SearchProduct(String product, String origin, String destin) throws InterruptedException {
		System.out.println("Type: "+product+" Origen:"+origin);
		
		//Primero seleccionar el tipo de producto(Paquete|Hotel|Vuelo|Etc..) dentro del widget
		widgetMenu.findElement(By.linkText(product) ).click();
		
		//Ingresar Los valores al widget
		//InputOrigin.sendKeys(origin);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
		//Thread.sleep(3000);
		//List <WebElement> listItems = driver.findElements(By.xpath("//li[.='" + origin + "']"));
		//listItems.get(0).click();
		//InputOrigin.sendKeys(Keys.ENTER);
		
		//InputDestin.sendKeys(destin);
		//Thread.sleep(3000);
		//InputDestin.sendKeys(Keys.ENTER);
		
		//InputStartDate.click();
		
		//InputStartDate.findElement(By.cssSelector("#ptw-ui-datepicker-div > div.ui-datepicker-group.ui-datepicker-group-last > table > tbody > tr:nth-child(1) > td:nth-child(4) > a")).click();
		//wait.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(user);
				
	}
	
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