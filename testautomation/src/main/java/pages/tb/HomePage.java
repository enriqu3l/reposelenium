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

import config.FWConfig;
import valueobjects.VOResData;

public class HomePage {
	private WebDriverWait wait;
	private WebDriver driver;
	
	//Constructor
	public HomePage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,FWConfig.WAIT_TB);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, FWConfig.WAITPF_TB),this);
	}
	
	@FindBy(how=How.CSS, css="#ptw-menu > ul")
	@CacheLookup
	private WebElement widgetMenu;
	
	//---Hoteles---
	@FindBy(how=How.XPATH, using="//*[@id='pestanahoteles']/a")
	private WebElement Button_hotel;
	
	@FindBy(how=How.ID, using="ap_dest_hotel")
	private WebElement Input_destHotel;
	
	@FindBy(how=How.ID, using="ap_dest_start")
	private WebElement Input_destStartHotel;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form  div.ptw-field-group.ptw-field-dates > div:nth-child(1) > img.ui-datepicker-trigger")
	private WebElement Image_destStartHotelTrigger;
	
	@FindBy(how=How.ID, using="ap_dest_end")
	private WebElement Input_destEndHotel;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form  div.ptw-field-group.ptw-field-dates > div:nth-child(2) > img.ui-datepicker-trigger")
	private WebElement Image_destEndHotelTrigger;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_rooms")
	private WebElement Select_bookerHotelRooms;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_adults0")
	private WebElement Select_bookerHotelAdults0;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_minors0")
	private WebElement Select_bookerHotelMinors0;
	
	//Aun falta de agregar los otros productos....
	
	@FindBy(how=How.CSS, css="#ap_booker_Hotel_form > div > div.ptw-buttons > input.ap-data_engine-Hotel.ap_booker_submit.ptw-btn.ptw-submit-btn")
	@CacheLookup
	private WebElement Button_search;
	
	
	public void setDestin(String text) {
		Input_destHotel.sendKeys(text);
	}
	
	public void setStartDate(String text) {
		Input_destStartHotel.sendKeys(text);
	}
	
	public void setEndDate(String text) {
		Input_destEndHotel.sendKeys(text);
	}
	
	//------------By--------------------
	By productActive = By.cssSelector("li.ptw-active > a");
	public void searchHotel(VOResData voHotelRes){
		selectProductOnWidgetMenu("Hoteles");
		Input_destHotel.clear();
		Input_destHotel.sendKeys(voHotelRes.getDestination());
		Input_destStartHotel.clear();
		Input_destStartHotel.sendKeys(voHotelRes.getStartDate());		
		Input_destEndHotel.clear();
		Input_destEndHotel.sendKeys(voHotelRes.getEndDate());
		//Image_destEndHotelTrigger.click();  esconde el calendario
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByVisibleText(Integer.toString(voHotelRes.getRoomCount()));
		Select adults = new Select(Select_bookerHotelAdults0);
		adults.selectByValue(Integer.toString(voHotelRes.getAdultsFromRoom(0)));
		Select kids = new Select(Select_bookerHotelMinors0);
		kids.selectByValue("0");
		Button_search.click();
	}
	
	public void selectProductOnWidgetMenu(String product) {
		widgetMenu.findElement(By.linkText(product) ).click();
	}
}