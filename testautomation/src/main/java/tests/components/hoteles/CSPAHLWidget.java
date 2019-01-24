package tests.components.hoteles;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import config.FrameworkConfig;
import helpers.DDManager;
import pages.Pages;
import valueobjects.VOHotelRes;

public class CSPAHLWidget {
	private static Logger logger = LogManager.getLogger(CSPAHLWidget.class);

	/**
	 * Este testcase verifica que el autocomplete se active con X palabras diferentes.
	 * 
	 * @param driver - WebDriver
	 * @throws InterruptedException
	 * @author enrique.lopez
	 */
	public static void autocompleteDestinationTest(WebDriver driver){
		// <Setup>
		logger.info("Starting autocompleteDestinationTest");
		Reporter.log("Starting autocompleteDestinationTest");
		//Por lo pronto esta harcodeado a las siguientes palabras de busqueda
		List<String> data = new ArrayList<String>();
		data.add("canc");
		data.add("nueva");
		data.add("vegas");
		data.add("vallarta");
		data.add("bogot");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		// <Then>
		//Verificar que el autocompletado del campo destino funcione correctamente
		pages.hotelListPage.widgetVerifyAutocompleteDestination(data);
	}
	
	/**
	 * Este testcase verifica que funcionen correctamente los datepickers
	 * Se verifica:
	 * 1.- Que las fechas sean las correctas de acuerdo a la URL
	 * 2.- Que el dropdown menu se abra y cierre correctamente en 10 iteraciones 
	 * @param driver
	 * @author enrique.lopez
	 */
	public static void datePickersFunctionalityTest(WebDriver driver) {
		// <Setup>
		logger.info("Starting datePickersFunctionalityTest");
		Reporter.log("Starting datePickersFunctionalityTest");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		// <Then>
		//Verificar que las fechas iniciales sean las que tenga la URL
		pages.hotelListPage.widgetVerifyCurrentUrlDateOnDatePickers();
		//Verificar que que el datepicker se abra y se cierre en 10 iteraciones
		pages.hotelListPage.widgetVerifyOpenAndCloseDatePickers();
	}
	
	/**
	 * Este testcase verifica que no se pueda consultar si se tienen campos vacios
	 * Solo el campo "destino" puede estar vacio, por lo que solo se verifica este.
	 * @param driver
	 */
	public static void searchWithEmptyFieldsTest(WebDriver driver){
		// <Setup>
		logger.info("Starting searchWithEmptyFieldsTest");
		Reporter.log("Starting searchWithEmptyFieldsTest");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetClearDestination();
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que se muestre el mensaje de error si no se ingresa un destino
		pages.hotelListPage.widgetVerifyErrorPlace();
		
	}
	
	/**
	 * Este testcase valida que el campo destination del widget funcione correctamente.
	 * Se verifica:
	 * 1.- Que el campo sea actualizado despues de aplicar la busqueda
	 * 2.- Que el HeaderTitle cambie al destino buscado
	 * 3.- Que la pagina resultante de la busqueda contenga listado de hoteles
	 * 
	 * @param driver
	 * @throws InterruptedException
	 * @author enrique.lopez
	 */
	public static void searchDifferentDestinTest(WebDriver driver){
		// <Setup>
		logger.info("Starting searchDifferentDestinTest");
		Reporter.log("Starting searchDifferentDestinTest");
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectDestin(voHotelResNew.getDestination());
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que el widget tiene el nuevo destino
		pages.hotelListPage.widgetVerifyDestinationToBe(voHotelResNew.getDestination());		
		//Verificar que el Page Header Title tiene el nuevo destino
		pages.hotelListPage.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		//Verificar que tenemos resultados
		pages.hotelListPage.listVerifyResultListHasElements();
	}
	
	/**
	 * Este testcase verifica que se puedan cambiar las fechas de la reservacion
	 * Se verifica:
	 * 1.- Que el widget tiene la nueva fecha
	 * 2.- Que la url tiene la nueva fecha
	 * 3.- Que la lista de resultados tenga elementos
	 * @param driver
	 */
	public static void searchDifferentDatesTest(WebDriver driver) {
		// <Setup>
		logger.info("Starting searchDifferentDatesTest");
		Reporter.log("Starting searchDifferentDatesTest");
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA,2);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectStartDate(voHotelResNew.getStartDate());
		pages.hotelListPage.widgetSelectEndDate(voHotelResNew.getEndDate());
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que el widget tiene la nueva fecha
		pages.hotelListPage.widgetVerifyStartDateToBe(voHotelResNew.getStartDate());
		pages.hotelListPage.widgetVerifyEndDateToBe(voHotelResNew.getEndDate());
		//Verificar que la url tiene la nueva fecha
		pages.hotelListPage.verifyUrlStartDateToBe(voHotelResNew.getStartDate("yyyy-MM-dd"));
		pages.hotelListPage.verifyUrlEndDateToBe(voHotelResNew.getEndDate("yyyy-MM-dd"));
		//Verificar que la lista de resultados tenga elementos
		pages.hotelListPage.listVerifyResultListHasElements();
	}
	
	/**
	 * Este testcase verifica que se puedan cambiar los ocupantes
	 * Se verifica:
	 * 1.- Que los ocupantes sean los que se seleccionaron
	 * 2.- Que la lista de resultados tenga elementos
	 * @param driver
	 */
	public static void searchDifferentOccupantsTest(WebDriver driver) {
		// <Setup>
		logger.info("Starting searchDifferentOccupantsTest");
		Reporter.log("Starting searchDifferentOccupantsTest");
		VOHotelRes voHotelRes = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA,11);// Aqui leo el row 11 donde tengo varios rooms
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectOccupants(voHotelRes);
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que los ocupantes sean los que se seleccionaron
		pages.hotelListPage.widgetVerifyOccupantsToBe(voHotelRes);
		//Verificar que la lista de resultados tenga elementos
		pages.hotelListPage.listVerifyResultListHasElements();
	}
	
	/**
	 * Este testcase verifica que se puedan cambiar toda la reservacion
	 * Se verifica:
	 * 1.- Que la info de la reservacion sea la que se selecciono
	 * 2.- Que la lista de resultados tenga elementos
	 * @param driver
	 */
	public static void searchDifferentReservationTest(WebDriver driver) {
		// <Setup>
		logger.info("Starting searchDifferentReservationTest");
		Reporter.log("Starting searchDifferentReservationTest");
		// Obtengo el row 12 del archivo
		VOHotelRes voHotelRes = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA,12);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectReservation(voHotelRes);
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que la info de la reservacion sea la que se selecciono
		pages.hotelListPage.widgetVerifyReservationToBe(voHotelRes);
		//Verificar que la lista de resultados tenga elementos
		pages.hotelListPage.listVerifyResultListHasElements();
	}
	
	//Funciones para pruebas durante el desarrollo
	public static void prueba(WebDriver driver) {
		//Esta funcion la uso para pruebas durante el desarrollo
		//Estoy revisando el comportamiento cuando realizo una busqueda pero sin realizar cambios...
		
		//<Setup>
		logger.info("Starting prueba");
		Reporter.log("Starting prueba");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA, 1);
		Pages pages = new Pages(driver);
		//<When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectDestin(voHotelResNew.getDestination());
		pages.hotelListPage.widgetClickSubmit();
		//<Then>
		//Verificar que el widget tiene el nuevo destino
		pages.hotelListPage.widgetVerifyDestinationToBe(voHotelResNew.getDestination());		
		//Verificar que el Page Header Title tiene el nuevo destino
		pages.hotelListPage.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		//Verificar que tenemos resultados
		pages.hotelListPage.listVerifyResultListHasElements();
	}
}
