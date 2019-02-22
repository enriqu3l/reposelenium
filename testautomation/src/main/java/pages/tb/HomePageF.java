package pages.tb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import valueobjects.VOResData;

public class HomePageF {
	private WebDriverWait wait;
	private WebDriver driver;
	
	//Constructor
	public HomePageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30),this);
	}
	
			
	//Si no envio parametros:
	//https://www.tiquetesbaratos.com//hoteles/cancun-area
	//usar� los valores por default para la busqueda
	
	@FindBy(how=How.CSS, css="#ptw-menu > ul")
	@CacheLookup
	private WebElement widgetMenu;
	
	//---Hoteles---
	@FindBy(how=How.XPATH, id="//*[@id='pestanahoteles']/a")
	private WebElement Button_hotel;
	
	@FindBy(how=How.XPATH, id="//*[@id='ap_dest_hotel']")
	private WebElement Input_destHotel;
	
	@FindBy(how=How.ID, id="ap_dest_start")
	private WebElement Input_destStartHotel;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form  div.ptw-field-group.ptw-field-dates > div:nth-child(1) > img.ui-datepicker-trigger")
	private WebElement Image_destStartHotelTrigger;
	
	@FindBy(how=How.ID, id="ap_dest_end")
	private WebElement Input_destEndHotel;
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form  div.ptw-field-group.ptw-field-dates > div:nth-child(2) > img.ui-datepicker-trigger")
	private WebElement Image_destEndHotelTrigger;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_rooms")
	private WebElement Select_bookerHotelRooms;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_adults0")
	private WebElement Select_bookerHotelAdults0;
	
	@FindBy(how=How.ID, id="ap_booker_Hotel_minors0")
	private WebElement Select_bookerHotelMinors0;
	
	
	
	//Aun falta de agregar los otros productos....
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form > div > div.ptw-buttons > input.ap-data_engine-Hotel.ap_booker_submit.ptw-btn.ptw-submit-btn")
	@CacheLookup
	private WebElement Button_search;
	
	
	//------------By--------------------
	By productActive = By.cssSelector("li.ptw-active > a");
	public void SearchHotel(VOResData voHotelRes){
		verifyProductSelectedOnWidgetMenu("Hoteles");
		Input_destHotel.clear();
		Input_destHotel.sendKeys(voHotelRes.getDestination());
		Input_destStartHotel.clear();
		Input_destStartHotel.sendKeys(voHotelRes.getStartDate());		
		Input_destEndHotel.clear();
		Input_destEndHotel.sendKeys(voHotelRes.getEndDate());
		//Image_destEndHotelTrigger.click();  esconde el calendario
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByVisibleText("1");
		Select adults = new Select(Select_bookerHotelAdults0);
		adults.selectByValue(Integer.toString(voHotelRes.getAdultsFromRoom(0)));
		Select kids = new Select(Select_bookerHotelMinors0);
		kids.selectByValue("0");
		Button_search.click();
	}
	
	public void verifyProductSelectedOnWidgetMenu(String product) {
		if(!widgetMenu.findElement(productActive).getText().equals(product)) {
			widgetMenu.findElement(By.linkText(product) ).click();
		}
	}
	
	//PENDIENTE POR CONSTRUIR
	//Funcion para revisar cada link, cada item dentro del home page!!!
	public void VerifyAllHomeItems() {
		
	}
}