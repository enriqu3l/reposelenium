package pages.pt.hoteles.components;

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

import config.FWConfig;
import helpers.JSWaiter;
import helpers.WaitFor;
import pages.pt.hoteles.HLGlobal;
import utility.BasicUtils;
import utility.FWUtils;
import valueobjects.VOResData;

public class HLWidget extends HLGlobal{
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(HLWidget.class);
	
	public HLWidget(WebDriver _driver) {
		super(_driver);
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
	}
	
	//--------------- Widget Elements - Basados en SPA-Hoteles ----------
		@FindBy(how=How.CSS, using="#ap_booker_Hotel #destination input")
		private WebElement widgetInputDestination;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel form > div > .ptw-errormsg")
		private WebElement widgetErrorPlace;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:first-child .ap_dest_calendar")
		private WebElement widgetInputStartDate;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:last-child .ap_dest_calendar")
		private WebElement widgetInputEndDate;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:first-child .ui-datepicker-trigger")
		private WebElement widgetStartDatePicker;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:last-child .ui-datepicker-trigger")
		private WebElement widgetEndDatePicker;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:first-child .dropdown-menu .ngb-dp-month-name")
		private WebElement widgetStartDateTitle;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:last-child .dropdown-menu .ngb-dp-month-name")
		private WebElement widgetEndDateTitle;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:first-child .ngb-dp-arrow button.btn")
		private WebElement widgetStartDateBeforeMonth;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:first-child .ngb-dp-arrow.right button.btn")
		private WebElement widgetStartDateNextMonth;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:last-child .ngb-dp-arrow button.btn")
		private WebElement widgetEndDateBeforeMonth;
		
		@FindBy(how=How.CSS, using=".ptw-field-date:last-child .ngb-dp-arrow.right button.btn")
		private WebElement widgetEndDateNextMonth;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel #ap_booker_Hotel_rooms")
		private WebElement widgetSelectHotelRooms;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel .ap_booker_Hotel_adults")
		private WebElement widgetSelectHotelAdults;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel .ap_booker_Hotel_minors")
		private WebElement widgetSelectHotelMinors;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel .ptw-submit-btn")
		private WebElement widgetButtonSubmit;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel .ap_booker_Hotelroom")
		private  List<WebElement> widgetAllBlockRooms;
		
		@FindBy(how=How.CSS, using="#ap_booker_Hotel .ap_minorsAges_Hotel_container")
		private  List<WebElement> widgetMinorsAgesHotelContainer;
		
		private By byWidgetStartDateDropdownMenu = By.cssSelector(".ptw-field-date:first-child .dropdown-menu");
		private By byWidgetEndDateDropdownMenu = By.cssSelector(".ptw-field-date:last-child .dropdown-menu");
		private By byWidgetDestinationDropdownMenu = By.cssSelector("#ap_booker_Hotel .dropdown-menu");
		private By byWidgetHotelAdults = By.cssSelector(".ap_booker_Hotel_adults");
		private By byWidgetHotelMinors = By.cssSelector(".ap_booker_Hotel_minors");
		private By byWidgetAllKidsPerRoom = By.cssSelector(".ap_age.ap_Hotel_year");
		private String dayStart = "//*[@id='datepicker-parent']/div/child::div[1]//div[text()='";
		private String dayEnd = "//*[@id='datepicker-parent']/div/child::div[2]//div[text()='";
		
		//++++++++++++++++++++++++++ WIDGET FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++
		public String getDestination() {
			return widgetInputDestination.getAttribute("value").trim();
		}
		
		public String getStartDate() {
			return widgetInputStartDate.getAttribute("value").trim();
		}
		
		public String getEndDate() {
			return widgetInputEndDate.getAttribute("value").trim();
		}
		
		public String getRooms() {
			Select rooms = new Select(widgetSelectHotelRooms);
			return rooms.getFirstSelectedOption().getText().trim();
		}
		
		public void clearDestination() {
			widgetInputDestination.clear(); //Este metodo no lanza evento de tecla
			widgetInputDestination.sendKeys(" "+Keys.BACK_SPACE); //Enviar " " para que lance un evento de tecla
		}
		
		public void setDestin(String destin) {
			widgetInputDestination.clear();
			widgetInputDestination.sendKeys(destin);
			//Wait until dropdown menu appears
			wait.until(ExpectedConditions.presenceOfElementLocated(byWidgetDestinationDropdownMenu));
			widgetInputDestination.sendKeys(Keys.ENTER);
		}
		
		public void setRooms(String roomsNumber) {
			Select rooms = new Select(widgetSelectHotelRooms);
			rooms.selectByVisibleText(roomsNumber);
		}
		
		public void setAdults(int adultsNumber) {
			Assert.assertTrue(adultsNumber>0 && adultsNumber<9,"LAF>>>el parametro adultsNumber esta fuera de rango");		
			Select adults = new Select(widgetSelectHotelAdults);
			adults.selectByVisibleText(Integer.toString(adultsNumber));
		}
		
		public void setOccupants(VOResData voHotelRes) {
			//Aqui el codigo para realizar la seleccion de rooms, adults, kids y agekids
			setRooms(Integer.toString(voHotelRes.getRoomCount()));
			if(widgetAllBlockRooms.size() != voHotelRes.getRoomCount()) {
				 logger.error("No se crearon los suficientes rooms containers, allBlocksRooms:"+widgetAllBlockRooms.size());
				 Assert.fail("LAF>>>No se crearon los suficientes rooms containers, allBlocksRooms:"+widgetAllBlockRooms.size());
			}
			//Ya se crearon los rooms ahora hay que llenar los adultos y niños
			for(int i=0;i<widgetAllBlockRooms.size();i++) {
				WebElement weAdults = widgetAllBlockRooms.get(i).findElement(byWidgetHotelAdults);
				WebElement weKids = widgetAllBlockRooms.get(i).findElement(byWidgetHotelMinors);
				
				Select adults = new Select(weAdults);
				adults.selectByVisibleText(Integer.toString(voHotelRes.getAdultsFromRoom(i)));
				
				Select kids = new Select(weKids);
				kids.selectByVisibleText(Integer.toString(voHotelRes.getKidsFromRoom(i)));
			}
			//Validar que se crearon campos de rooms para los agekids
			if(widgetMinorsAgesHotelContainer.size() != voHotelRes.getRoomCount()) {
				logger.info("voHotelRes.getRoomCount():"+voHotelRes.getRoomCount());
				logger.error("La cantidad de rooms no coincide con la cantidad de agecontainers, widgetMinorsAgesHotelContainer:"+widgetMinorsAgesHotelContainer.size());
				Assert.fail("LAF>>>La cantidad de rooms no coincide con la cantidad de agecontainers, widgetMinorsAgesHotelContainer:"+widgetMinorsAgesHotelContainer.size());
			}
			//Ahora hay que llenar las edades de los niños en cada cuarto
			for(int i=0; i<widgetMinorsAgesHotelContainer.size();i++) {
				//Recorrer cada room
				if(voHotelRes.getKidsFromRoom(i)>0) {
					//Entra solo si el cuarto contiene niños
					List<WebElement> minors = widgetMinorsAgesHotelContainer.get(i).findElements(byWidgetAllKidsPerRoom);
					for(int j=0;j<minors.size();j++) {
						//Recorrer cada niño y setear la edad
						Select minor = new Select(minors.get(j));
						minor.selectByVisibleText(Integer.toString(voHotelRes.getAgeFromAgekids(i, j)));
					}
				}
			}
		}
		
		public void setStartDate(String date) {
			if(date.isEmpty()) {Assert.fail("LAF>>>El parametro date esta vacio");}
			logger.info("Starting setStartDate()");
			logger.info("setStartDate() parametro recibido:"+date);
			//Abrir el calendario si no esta abierto
			if(FWUtils.noExistsElement(driver,byWidgetStartDateDropdownMenu)){widgetStartDatePicker.click();}
			LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
			int day = localDate.getDayOfMonth();
			String actualDate = BasicUtils.toddMMyyyyFormat(widgetStartDateTitle.getText().trim());
			int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
			logger.trace("widgetSetStartDate() TotalMonthDifference: "+TotalMonthDifference);
			if(TotalMonthDifference>0) {
				for(int i=0; i<TotalMonthDifference;i++) {
					widgetStartDateNextMonth.click(); //click hacia adelante
				}
			}else if(TotalMonthDifference<0) {
				for(int i=0; i>TotalMonthDifference;i--) {
					widgetStartDateBeforeMonth.click(); //click hacia atras
				}
			}
			
			String xpath = dayStart+day+"']";
			driver.findElement(By.xpath(xpath)).click();
			logger.trace("Valor de widgetInputStartDate: " + widgetInputStartDate.getAttribute("value"));
		}
		
		public void setEndDate(String date) {
			if(date.isEmpty()) {Assert.fail("LAF>>>El parametro date esta vacio");}
			logger.info("Starting setEndDate()");
			logger.trace("setEndDate() parametro recibido:"+date);
			//Abrir el calendario si no esta abierto
			if(FWUtils.noExistsElement(driver,byWidgetEndDateDropdownMenu)){widgetEndDatePicker.click();}
			LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
			int day = localDate.getDayOfMonth();
			String actualDate = BasicUtils.toddMMyyyyFormat(widgetEndDateTitle.getText().trim());
			int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
			logger.trace("widgetSetEndDate() TotalMonthDifference: "+TotalMonthDifference);
			if(TotalMonthDifference>0) {
				for(int i=0; i<TotalMonthDifference;i++) {
					widgetEndDateNextMonth.click(); //click hacia adelante
				}
			}else if(TotalMonthDifference<0) {
				for(int i=0; i>TotalMonthDifference;i--) {
					widgetEndDateBeforeMonth.click(); //click hacia atras
				}
			}
			String xpath = dayEnd+day+"']";
			driver.findElement(By.xpath(xpath)).click();
			logger.trace("Valor de widgetInputEndDate: " + widgetInputEndDate.getAttribute("value"));
		}
		
		public void setReservation(VOResData voHotelRes){
			logger.info("Starting setReservation()");
			setDestin(voHotelRes.getDestination());
			setStartDate(voHotelRes.getStartDate());
			setEndDate(voHotelRes.getEndDate());
			setOccupants(voHotelRes);
		}
		
		public void clickSubmit() {
			logger.info("Starting clickSubmit()");
			widgetButtonSubmit.click();
			
			//A continuacion pongo un wait para esperar a que el boton lanze la accion
			WaitFor.attributeChanged(byLoaderOverlayPage, "style", "display: none; opacity: 0;");
			JSWaiter.setDriver(driver);
			JSWaiter.sleep(3000);
			waitForContentToBeReady();
		}
		//++++++++++++++++++++++++++ END WIDGET FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++
		
		//++++++++++++++++++++++++++++ VERIFY FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++++
		public boolean verifyAutocompleteDestination(List<String> words) {
			logger.info("Starting verifyAutocompleteDestination()");
			if(words.isEmpty()) {Assert.fail("LAF>>>El parametro words esta vacio");}
			boolean result = true;
			for(int i=0; i<words.size();i++) {
				setDestin(words.get(i).toLowerCase().trim());
				String actual = getDestination().toLowerCase();
				if(!actual.contains(words.get(i).toLowerCase().trim())) {
					result = false;
					break;
				}
				clearDestination();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(byWidgetDestinationDropdownMenu));
			}
			return result;
		}
		
		public boolean verifyOccupantsToBe(VOResData voHotelRes) {
			logger.info("Starting widgetVerifyOccupantsToBe()");
			boolean result = true;
			//Validar que los rooms sean los mismos
			if(!getRooms().equalsIgnoreCase(Integer.toString(voHotelRes.getRoomCount()))) {
				logger.error("Error, no coinciden los rooms");
				return false;
			}
			
			//Validar que se crearon campos para cada room
			if(widgetAllBlockRooms.size() != voHotelRes.getRoomCount()) {
				 logger.error("No se crearon los campos suficientes de rooms, allBlocksRooms:"+widgetAllBlockRooms.size());
				 return false;
			}
			//Validar los adultos y niños en cada cuarto
			for(int i=0;i<widgetAllBlockRooms.size();i++) {
				WebElement weAdults = widgetAllBlockRooms.get(i).findElement(byWidgetHotelAdults);
				WebElement weKids = widgetAllBlockRooms.get(i).findElement(byWidgetHotelMinors);
				
				Select adults = new Select(weAdults);
				String adultsSelected =adults.getFirstSelectedOption().getText();
				if(!Integer.toString(voHotelRes.getAdultsFromRoom(i)).equalsIgnoreCase(adultsSelected)) {
					logger.error("No coinciden Adultos en el room:"+i+", adultsSelected: "+adultsSelected);
					result = false;
					break;
				}
				Select kids = new Select(weKids);
				String kidsSelected = kids.getFirstSelectedOption().getText();
				if(!Integer.toString(voHotelRes.getKidsFromRoom(i)).equalsIgnoreCase(kidsSelected)) {
					logger.error("No coinciden Niños en el room:"+i+", kidsSelected:"+kidsSelected+".");
					result=false;
					break;
				}
			}
			if(!result) {return result;}
			if(voHotelRes.getKidsCount()>0){
				//Validar que se crearon containers para los agekids
				if(widgetMinorsAgesHotelContainer.size() != voHotelRes.getRoomCount()) {
					 logger.error("No se crearon los containers de los agekids, widgetAllBlockMinorsAges:"+widgetMinorsAgesHotelContainer.size());
					 return false;
				}
				//Validar las edades de los niños de cada cuarto
				for(int i=0; i<widgetMinorsAgesHotelContainer.size();i++) {
					//Recorrer cada room
					if(voHotelRes.getKidsFromRoom(i)>0) {
						//Entra solo si el cuarto contiene niños
						List<WebElement> minors = widgetMinorsAgesHotelContainer.get(i).findElements(byWidgetAllKidsPerRoom);
						for(int j=0;j<minors.size();j++) {
							//Recorrer cada niño y setear la edad
							Select minor = new Select(minors.get(j));
							String ageMinorSelected = minor.getFirstSelectedOption().getText();
							if(!Integer.toString(voHotelRes.getAgeFromAgekids(i, j)).equalsIgnoreCase(ageMinorSelected)) {
								logger.error("No coincide Edad del Niño:"+j+", en el room:"+i+", ageMinorSelected:"+ageMinorSelected+".");
								result=false;
								break;
							}
						}
					}
				}
			}else if(voHotelRes.getKidsCount()==0) {
				//Validar que no haya conteniners
				if(widgetMinorsAgesHotelContainer.size()>0) {
					 logger.error("Se encontraron age containers y no deberia haber, widgetAllBlockMinorsAges:"+widgetMinorsAgesHotelContainer.size());
					 result = false;
				}
			}
			return result;
		}

		public boolean verifyReservationToBe(VOResData voHotelRes) {
			logger.info("Starting widgetVerifyReservationInfoToBe()");
			return getDestination().equalsIgnoreCase(voHotelRes.getDestination()) &&
			getStartDate().equals(voHotelRes.getStartDate()) &&
			getEndDate().equals(voHotelRes.getEndDate()) &&
			verifyOccupantsToBe(voHotelRes);
		}
		
		public boolean verifyErrorPlace() {
			wait.until(ExpectedConditions.visibilityOf(widgetErrorPlace));
			if(!(widgetErrorPlace.isDisplayed() && widgetErrorPlace.getText().contains("dest"))) {
				logger.error("No se muestra el mensaje de Error del campo destino");
				return false;
			}else {
				logger.info("Si se muestra el mensaje de Error del campo destino");
				return true;
			}
		}
		
		/**
		 * Esta funcion verifica que se abran y cierren los dropdowns de las fechas en 10 iteraciones
		 */
		public boolean verifyOpenAndCloseDatePickers() {
			logger.info("Starting verifyOpenAndCloseDatePickers()");
			boolean result = true;
			for(int i=0;i<10;i++) {
				//Validar StartDatePicker
				if(FWUtils.existsElement(driver,byWidgetStartDateDropdownMenu)){widgetStartDatePicker.click();}
				widgetStartDatePicker.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(byWidgetStartDateDropdownMenu));
				widgetStartDatePicker.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(byWidgetStartDateDropdownMenu));
				if(FWUtils.existsElement(driver, byWidgetStartDateDropdownMenu)){
					logger.error("Error el StartDatePicker DropDown Menu no se esta cerrando");
					result = false;
					break;
				}
				//Validar EndDatePicker
				if(FWUtils.existsElement(driver,byWidgetEndDateDropdownMenu)){widgetEndDatePicker.click();}
				widgetEndDatePicker.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(byWidgetEndDateDropdownMenu));
				widgetEndDatePicker.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(byWidgetEndDateDropdownMenu));
				if(FWUtils.existsElement(driver, byWidgetEndDateDropdownMenu)){
					logger.error("Error el EndDatePicker DropDown Menu no se esta cerrando");
					result = false;
					break;
				}
			}
			if(result) {logger.info("widgetVerifyOpenAndCloseDatePickers PASS");}
			return result;
		}
		
		public boolean verifyCurrentUrlDateOnDatePickers() {
			logger.info("Starting widgetVerifyCurrentDateOnDatePickers()");
			String inputStartDate = getStartDate();
			boolean result1 = BasicUtils.checkValueOnUrlParam(driver.getCurrentUrl(),"checkin",inputStartDate);
			String inputEndDate = getEndDate();
			boolean result2 = BasicUtils.checkValueOnUrlParam(driver.getCurrentUrl(),"checkout",inputEndDate);
			if(!result1) {
				logger.error("La fecha de StartDate no coincide con la URL");
				Assert.fail("LAF>>>La fecha de StartDate no coincide con la URL");
			}else if(!result2){
				logger.error("La fecha de EndDate no coincide con la URL");
				Assert.fail("LAF>>>La fecha de EndDate no coincide con la URL");
			}else {
				logger.info("verifyUrlStartDateToBe PASS");
			}
			return result1 && result2;
		}
}