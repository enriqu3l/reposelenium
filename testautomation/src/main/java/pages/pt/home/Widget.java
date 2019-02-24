package pages.pt.home;

import java.util.List;

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

public class Widget{
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(Widget.class);
	
	public Widget(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
	}

	//--------------- Widget------------------------
	//@FindBy(how=How.CSS, using="#ptw-menu > ul")
	@FindBy(how=How.CSS, using="#ptw-menu > ul")
	@CacheLookup
	private WebElement widgetMenu;
	
	By byWidgetProductActive = By.cssSelector("li.ptw-active > a");
	By byWidgetAutocompleteDropdownHotelDest = By.cssSelector(".pt-customJqueryUi #ui-id-1");
	By byWidgetAutocompleteDropdownPackageOrigin = By.cssSelector(".pt-customJqueryUi #ui-id-2");
	By byWidgetAutocompleteDropdownPackageDest = By.cssSelector(".pt-customJqueryUi #ui-id-3");
	private By byWidgetAgeKid = By.cssSelector(".ap_age");
	
	
	//--------------- Widget Hoteles ---------------
	@FindBy(how=How.ID, using="var1_1")
	@CacheLookup
	private WebElement widgetRadioButtonHotel;
	
	@FindBy(how=How.ID, using="var1_3")
	@CacheLookup
	private WebElement widgetRadioButtonHotelFlight;
	
	@FindBy(how=How.ID, using="ap_dest_hotel")
	@CacheLookup
	private WebElement widgetInputDestHotel;
	
	@FindBy(how=How.ID, using="ap_dest_start")
	@CacheLookup
	private WebElement widgetInputDestStartHotel;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ptw-field-date:nth-child(1) .ui-datepicker-trigger")
	@CacheLookup
	private WebElement widgetImageDateStartHotelTrigger;
	
	@FindBy(how=How.ID, using="ap_dest_end")
	@CacheLookup
	private WebElement widgetInputDestEndHotel;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ptw-field-date:nth-child(2) .ui-datepicker-trigger")
	@CacheLookup
	private WebElement widgetImageDateEndHotelTrigger;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_rooms")
	@CacheLookup
	private WebElement widgetSelectBookerHotelRooms;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_adults0")
	@CacheLookup
	private WebElement widgetSelectBookerHotelAdults0;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_minors0")
	@CacheLookup
	private WebElement widgetSelectBookerHotelMinors0;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ptw-submit-btn")
	@CacheLookup
	private WebElement widgetButtonSearchHoteles;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ap_booker_Hotelroom")
	private  List<WebElement> widgetAllBlockRooms;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ap_minorsAges_Hotel_container")
	private  List<WebElement> widgetMinorsAgesHotelContainer;
	
	private By byWidgetHotelAdults = By.cssSelector(".ap_booker_Hotel_adults");
	private By byWidgetHotelMinors = By.cssSelector(".ap_booker_Hotel_minors");
	
	//--------------- Widget Paquetes ---------------
	@FindBy(how=How.ID, using="var3_3")
	@CacheLookup
	private WebElement widgetRadioButtonHotelFlightPackage;
	
	@FindBy(how=How.ID, using="ap_origin_flightPackage")
	@CacheLookup
	private WebElement widgetInputOriginFlightPackage;
	
	@FindBy(how=How.ID, using="ap_dest_flightPackage")
	@CacheLookup
	private WebElement widgetInputDestFlightPackage;
	
	@FindBy(how=How.ID, using="ap_flightPackage_start")
	@CacheLookup
	private WebElement widgetInputFlightPackageStart;
	
	@FindBy(how=How.ID, using="ap_flightPackage_end")
	@CacheLookup
	private WebElement widgetInputFlightPackageEnd;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ptw-field-date:nth-child(1) .ui-datepicker-trigger")
	@CacheLookup
	private WebElement widgetImageDateStartPackageTrigger;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ptw-field-date:nth-child(2) .ui-datepicker-trigger")
	@CacheLookup
	private WebElement widgetImageDateEndPackageTrigger;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ptw-submit-btn")
	@CacheLookup
	private WebElement widgetButtonSearchPackage;
	
	@FindBy(how=How.ID, using="ap_booker_FlightPackage_rooms")
	@CacheLookup
	private WebElement widgetSelectBookerPackageRooms;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ap_booker_FlightPackageroom")
	private  List<WebElement> widgetAllBlockPackageRooms;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ap_minorsAges_FlightPackage_container")
	private  List<WebElement> widgetMinorsAgesPackageContainer;
	
	private By byWidgetPackageAdults = By.cssSelector(".ap_booker_FlightPackage_adults");
	private By byWidgetPackageMinors = By.cssSelector(".ap_booker_FlightPackage_minors");
	
	//--------------- Widget Vuelos ---------------
	@FindBy(how=How.ID, using="var2_2")
	@CacheLookup
	private WebElement widgetRadioButtonFlight;
	
	@FindBy(how=How.ID, using="var2_3")
	@CacheLookup
	private WebElement widgetRadioButtonFlightPackage;
	
	@FindBy(how=How.CSS, using="#ap_booker_Flight_form .ptw-submit-btn")
	@CacheLookup
	private WebElement widgetButtonSearchFlight;
	
	//Falta de agregar elementos de los otros productos....

	//+++++++++++++++++ Hotels  Methods ++++++++++++++++++++++++++++++
	public void setHotelDestin(String destin) {
		logger.info("Starting widgetSelectHotelDestin()");
		widgetInputDestHotel.clear();
		widgetInputDestHotel.sendKeys(destin);
		logger.trace("Destin: "+ destin);
		wait.until(ExpectedConditions.attributeContains(byWidgetAutocompleteDropdownHotelDest, "style", "display: block;"));
		widgetInputDestHotel.sendKeys(Keys.ENTER);
	}
	
	public void setHotelStartDate(String startDate) {
		widgetInputDestStartHotel.clear();
		widgetInputDestStartHotel.sendKeys(startDate);
		logger.trace("Start Date: "+startDate);
	}
	
	public void setHotelEndDate(String endDate) {
		widgetInputDestEndHotel.clear();
		widgetInputDestEndHotel.sendKeys(endDate);
		//Aqui voy a cerrar el datepicker dropdown haciendo click en el icono
		widgetImageDateEndHotelTrigger.click();
		logger.trace("End Date: "+endDate);
	}
	
	public void setOccupants(String product,VOResData voHotelRes) {
		List<WebElement> lweBlockRooms = null;
		List<WebElement> lweMinorsAgeContainer = null;
		WebElement weRooms = null;
		By byAdults = null;
		By byKids = null;
		By byAgeKid = null;
		if(product.equalsIgnoreCase("hoteles")) {
			logger.info("Seleccionando ocupantes de hoteles");
			lweBlockRooms = widgetAllBlockRooms;
			lweMinorsAgeContainer = widgetMinorsAgesHotelContainer;
			weRooms = widgetSelectBookerHotelRooms;
			byAdults = byWidgetHotelAdults;
			byKids = byWidgetHotelMinors;
			byAgeKid = byWidgetAgeKid;
		}else if(product.equalsIgnoreCase("paquetes")) {
			logger.info("Seleccionando ocupantes de paquetes");
			lweBlockRooms = widgetAllBlockPackageRooms;
			lweMinorsAgeContainer = widgetMinorsAgesPackageContainer;
			weRooms = widgetSelectBookerPackageRooms;
			byAdults = byWidgetPackageAdults;
			byKids = byWidgetPackageMinors;
			byAgeKid = byWidgetAgeKid;
		}else if(product.equalsIgnoreCase("vuelos")){
			logger.info("Seleccionando ocupantes de vuelos");
			//aun no esta implementado!
			Assert.fail("El product:("+product+") aun no esta implementado!");
		}else {
			Assert.fail("El parametro product:("+product+") no es valido!");
		}
		//Aqui el codigo para realizar la seleccion de rooms, adults, kids y agekids
		Select rooms = new Select(weRooms);
		rooms.selectByVisibleText(Integer.toString(voHotelRes.getRoomCount()));
		if(lweBlockRooms.size() != voHotelRes.getRoomCount()) {
			 logger.error("No se crearon los suficientes rooms containers, allBlocksRooms:"+lweBlockRooms.size());
			 Assert.fail("LAF>>>No se crearon los suficientes rooms containers, allBlocksRooms:"+lweBlockRooms.size());
		}
		//Ya se crearon los rooms ahora hay que llenar los adultos y niños
		for(int i=0;i<lweBlockRooms.size();i++) {
			WebElement weAdults = lweBlockRooms.get(i).findElement(byAdults);
			WebElement weKids = lweBlockRooms.get(i).findElement(byKids);
			
			Select adults = new Select(weAdults);
			adults.selectByVisibleText(Integer.toString(voHotelRes.getAdultsFromRoom(i)));
			
			Select kids = new Select(weKids);
			kids.selectByVisibleText(Integer.toString(voHotelRes.getKidsFromRoom(i)));
		}
		//Validar que se crearon campos de rooms para los agekids
		if(lweMinorsAgeContainer.size() != voHotelRes.getRoomCount()) {
			 logger.error("La cantidad de rooms no coincide con la cantidad de agecontainers, widgetMinorsAgesHotelContainer:"+lweMinorsAgeContainer.size());
			 Assert.fail("LAF>>>La cantidad de rooms no coincide con la cantidad de agecontainers, widgetMinorsAgesHotelContainer:"+lweMinorsAgeContainer.size());
		}
		//Ahora hay que llenar las edades de los niños en cada cuarto
		for(int i=0; i<lweMinorsAgeContainer.size();i++) {
			//Recorrer cada room
			if(voHotelRes.getKidsFromRoom(i)>0) {
				//Entra solo si el cuarto contiene niños
				List<WebElement> minors = lweMinorsAgeContainer.get(i).findElements(byAgeKid);
				for(int j=0;j<minors.size();j++) {
					//Recorrer cada niño y setear la edad
					Select minor = new Select(minors.get(j));
					minor.selectByVisibleText(Integer.toString(voHotelRes.getAgeFromAgekids(i, j)));
				}
			}
		}
	}
	
	public void clickSearchHotelButton() {
		widgetButtonSearchHoteles.click();
	}
	
	public void searchHotel(VOResData voResData){
		logger.info("Starting SearchHotel()");
		setProduct("Hoteles");
		setHotelDestin(voResData.getDestination());
		setHotelStartDate(voResData.getStartDate());
		setHotelEndDate(voResData.getEndDate());
		//widgetSetHotelOccupantsOld(voHotelRes);  //Version anterior que NO sirve para seleccionar niños
		setOccupants("hoteles",voResData);
	}
	
	
	//++++++++++++++++++ Package Methods ++++++++++++++++++++++++
	public void setPackageOrigin(String origin) {
		logger.info("Starting widgetSetPackageOrigin()");
		widgetInputOriginFlightPackage.clear();
		widgetInputOriginFlightPackage.sendKeys(origin);
		logger.trace("Origin: "+ origin);
		wait.until(ExpectedConditions.attributeContains(byWidgetAutocompleteDropdownPackageOrigin, "style", "display: block;"));
		widgetInputOriginFlightPackage.sendKeys(Keys.ENTER);
	}
	
	public void setPackageDestin(String destin) {
		logger.info("Starting widgetSetPackageDestin()");
		widgetInputDestFlightPackage.clear();
		widgetInputDestFlightPackage.sendKeys(destin);
		logger.trace("Destin: "+ destin);
		wait.until(ExpectedConditions.attributeContains(byWidgetAutocompleteDropdownPackageDest, "style", "display: block;"));
		widgetInputDestFlightPackage.sendKeys(Keys.ENTER);
	}
	
	public void setPackageStartDate(String startDate) {
		widgetInputFlightPackageStart.clear();
		widgetInputFlightPackageStart.sendKeys(startDate);
		logger.trace("Start Date: "+startDate);
	}
	
	public void setPackageEndDate(String endDate) {
		widgetInputFlightPackageEnd.clear();
		widgetInputFlightPackageEnd.sendKeys(endDate);
		//Aqui voy a cerrar el datepicker dropdown haciendo click en el icono
		widgetImageDateEndPackageTrigger.click();
		logger.trace("End Date: "+endDate);
	}
	
	public void clickSearchPackageButton() {
		//JSWaiter.setDriver(driver);
		//JSWaiter.waitUntilJSReady();
		widgetButtonSearchPackage.click();
	}
	
	public void searchPackage(VOResData voResData){
		logger.info("Starting widgetSearchPackage()");
		setProduct("Paquetes");
		setPackageOrigin(voResData.getOrigin());
		setPackageDestin(voResData.getDestination());
		setPackageStartDate(voResData.getStartDate());
		setPackageEndDate(voResData.getEndDate());
		setOccupants("paquetes",voResData);
	}
	
	public void setProduct(String product) {
		logger.info("Starting widgetSetProduct()");
		if(!widgetMenu.findElement(byWidgetProductActive).getText().equals(product)) {
			widgetMenu.findElement(By.linkText(product) ).click();
		}
		logger.info("Ending widgetSetProduct()");
	}
}
