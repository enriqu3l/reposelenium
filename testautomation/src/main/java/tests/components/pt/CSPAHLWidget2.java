package tests.components.pt;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import config.FWConfig;
import helpers.DDManager;
import pages.pt.Pages;
import valueobjects.VOResData;

public class CSPAHLWidget2 {
	private static Logger logger = LogManager.getLogger(CSPAHLWidget2.class);

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
		pages.stageHotelListPage_Initialize();
		// <Then>
		//Verificar que el autocompletado del campo destino funcione correctamente
		pages.stageHotelListPage.widgetVerifyAutocompleteDestination(data);
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
		pages.stageHotelListPage_Initialize();
		// <Then>
		//Verificar que las fechas iniciales sean las que tenga la URL
		pages.stageHotelListPage.widgetVerifyCurrentUrlDateOnDatePickers();
		//Verificar que que el datepicker se abra y se cierre en 10 iteraciones
		pages.stageHotelListPage.widgetVerifyOpenAndCloseDatePickers();
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
		pages.stageHotelListPage_Initialize();
		pages.stageHotelListPage.widgetClearDestination();
		pages.stageHotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que se muestre el mensaje de error si no se ingresa un destino
		pages.stageHotelListPage.widgetVerifyErrorPlace();
		
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
		VOResData voHotelResNew = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		// <When>
		pages.stageHotelListPage_Initialize();
		pages.stageHotelListPage.widgetSetDestin(voHotelResNew.getDestination());
		pages.stageHotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que el widget tiene el nuevo destino
		pages.stageHotelListPage.widgetVerifyDestinationToBe(voHotelResNew.getDestination());		
		//Verificar que el Page Header Title tiene el nuevo destino
		pages.stageHotelListPage.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		//Verificar que tenemos resultados
		pages.stageHotelListPage.listVerifyResultListHasElements();
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
		VOResData voHotelResNew = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA,2);
		Pages pages = new Pages(driver);
		// <When>
		pages.stageHotelListPage_Initialize();
		pages.stageHotelListPage.widgetSetStartDate(voHotelResNew.getStartDate());
		pages.stageHotelListPage.widgetSetEndDate(voHotelResNew.getEndDate());
		pages.stageHotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que el widget tiene la nueva fecha
		pages.stageHotelListPage.widgetVerifyStartDateToBe(voHotelResNew.getStartDate());
		pages.stageHotelListPage.widgetVerifyEndDateToBe(voHotelResNew.getEndDate());
		//Verificar que la url tiene la nueva fecha
		pages.stageHotelListPage.verifyUrlStartDateToBe(voHotelResNew.getStartDate("yyyy-MM-dd"));
		pages.stageHotelListPage.verifyUrlEndDateToBe(voHotelResNew.getEndDate("yyyy-MM-dd"));
		//Verificar que la lista de resultados tenga elementos
		pages.stageHotelListPage.listVerifyResultListHasElements();
	}
	
	/**
	 * Este testcase verifica que se puedan cambiar los ocupantes
	 * Nota: Se esta leyendo el row 11 del archivo de excel
	 * Se verifica:
	 * 1.- Que los ocupantes sean los que se seleccionaron
	 * 2.- Que la lista de resultados tenga elementos
	 * @param driver
	 */
	public static void searchDifferentOccupantsTest(WebDriver driver) {
		// <Setup>
		logger.info("Starting searchDifferentOccupantsTest");
		Reporter.log("Starting searchDifferentOccupantsTest");
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA,11);// Aqui leo el row 11 donde tengo varios rooms
		Pages pages = new Pages(driver);
		// <When>
		pages.stageHotelListPage_Initialize();
		pages.stageHotelListPage.widgetSetOccupants(voHotelRes);
		pages.stageHotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que los ocupantes sean los que se seleccionaron
		pages.stageHotelListPage.widgetVerifyOccupantsToBe(voHotelRes);
		//Verificar que la lista de resultados tenga elementos
		pages.stageHotelListPage.listVerifyResultListHasElements();
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
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA,12);
		Pages pages = new Pages(driver);
		// <When>
		pages.stageHotelListPage_Initialize();
		pages.stageHotelListPage.widgetSetReservation(voHotelRes);
		pages.stageHotelListPage.widgetClickSubmit();
		// <Then>
		//Verificar que la info de la reservacion sea la que se selecciono
		pages.stageHotelListPage.widgetVerifyReservationToBe(voHotelRes);
		//Verificar que la lista de resultados tenga elementos
		pages.stageHotelListPage.listVerifyResultListHasElements();
	}
	
	//Funciones para pruebas durante el desarrollo
	public static void prueba(WebDriver driver) {
		//Esta funcion la uso para pruebas durante el desarrollo
		//Estoy revisando el comportamiento cuando realizo una busqueda pero sin realizar cambios...
		
		//<Setup>
		logger.info("Starting prueba");
		Reporter.log("Starting prueba");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOResData voHotelResNew = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 1);
		Pages pages = new Pages(driver);
		//<When>
		pages.stageHotelListPage_Initialize();
		pages.stageHotelListPage.widgetSetDestin(voHotelResNew.getDestination());
		pages.stageHotelListPage.widgetClickSubmit();
		//<Then>
		//Verificar que el widget tiene el nuevo destino
		pages.stageHotelListPage.widgetVerifyDestinationToBe(voHotelResNew.getDestination());		
		//Verificar que el Page Header Title tiene el nuevo destino
		pages.stageHotelListPage.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		//Verificar que tenemos resultados
		pages.stageHotelListPage.listVerifyResultListHasElements();
	}
}
