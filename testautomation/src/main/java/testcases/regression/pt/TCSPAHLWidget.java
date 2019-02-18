package testcases.regression.pt;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import config.FWConfig;
import helpers.DDManager;
import pages.pt.Pages;
import testbases.TBRegressionSPAHL;
import valueobjects.VOResData;

public class TCSPAHLWidget extends TBRegressionSPAHL{

	@Test(enabled = true)
	public void autocompleteDestinationTest() {
		Reporter.log("Starting test1");
		logger.info("Starting test1");
		// <Setup>
		logger.info("Starting autocompleteDestinationTest");
		Reporter.log("Starting autocompleteDestinationTest");
		// Por lo pronto esta harcodeado a las siguientes palabras de busqueda
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
		// Verificar que el autocompletado del campo destino funcione correctamente
		pages.hotelListPage.widgetVerifyAutocompleteDestination(data);
	}

	@Test(enabled = true)
	public void datePickersFunctionalityTest() {
		Reporter.log("Starting test2");
		logger.info("Starting test2");
		// <Setup>
		logger.info("Starting datePickersFunctionalityTest");
		Reporter.log("Starting datePickersFunctionalityTest");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		// <Then>
		// Verificar que las fechas iniciales sean las que tenga la URL
		pages.hotelListPage.widgetVerifyCurrentUrlDateOnDatePickers();
		// Verificar que que el datepicker se abra y se cierre en 10 iteraciones
		pages.hotelListPage.widgetVerifyOpenAndCloseDatePickers();
	}

	@Test(enabled = true)
	public void searchWithEmptyFieldsTest() {
		Reporter.log("Starting test3");
		logger.info("Starting test3");
		// <Setup>
		logger.info("Starting searchWithEmptyFieldsTest");
		Reporter.log("Starting searchWithEmptyFieldsTest");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetClearDestination();
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		// Verificar que se muestre el mensaje de error si no se ingresa un destino
		pages.hotelListPage.widgetVerifyErrorPlace();
	}

	@Test(enabled = true)
	public void searchDifferentDestinTest() {
		Reporter.log("Starting test4");
		logger.info("Starting test4");
		// <Setup>
		logger.info("Starting searchDifferentDestinTest");
		Reporter.log("Starting searchDifferentDestinTest");
		VOResData voHotelResNew = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetDestin(voHotelResNew.getDestination());
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		// Verificar que el widget tiene el nuevo destino
		pages.hotelListPage.widgetVerifyDestinationToBe(voHotelResNew.getDestination());
		// Verificar que el Page Header Title tiene el nuevo destino
		pages.hotelListPage.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		// Verificar que tenemos resultados
		pages.hotelListPage.listVerifyResultListHasElements();
	}

	@Test(enabled = true)
	public void searchDifferentDatesTest() {
		Reporter.log("Starting test5");
		logger.info("Starting test5");
		// <Setup>
		logger.info("Starting searchDifferentDatesTest");
		Reporter.log("Starting searchDifferentDatesTest");
		VOResData voHotelResNew = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetStartDate(voHotelResNew.getStartDate());
		pages.hotelListPage.widgetSetEndDate(voHotelResNew.getEndDate());
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		// Verificar que el widget tiene la nueva fecha
		pages.hotelListPage.widgetVerifyStartDateToBe(voHotelResNew.getStartDate());
		pages.hotelListPage.widgetVerifyEndDateToBe(voHotelResNew.getEndDate());
		// Verificar que la url tiene la nueva fecha
		pages.hotelListPage.verifyUrlStartDateToBe(voHotelResNew.getStartDate("yyyy-MM-dd"));
		pages.hotelListPage.verifyUrlEndDateToBe(voHotelResNew.getEndDate("yyyy-MM-dd"));
		// Verificar que la lista de resultados tenga elementos
		pages.hotelListPage.listVerifyResultListHasElements();
	}

	@Test(enabled = true)
	public void searchDifferentOccupantsTest() {
		Reporter.log("Starting test6");
		logger.info("Starting test6");
		// <Setup>
		logger.info("Starting searchDifferentOccupantsTest");
		Reporter.log("Starting searchDifferentOccupantsTest");
		// Aqui leo el row 11 donde tengo varios rooms
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 11);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetOccupants(voHotelRes);
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		// Verificar que los ocupantes sean los que se seleccionaron
		pages.hotelListPage.widgetVerifyOccupantsToBe(voHotelRes);
		// Verificar que la lista de resultados tenga elementos
		pages.hotelListPage.listVerifyResultListHasElements();
	}

	@Test(enabled = true)
	public void searchDifferentReservationTest() {
		Reporter.log("Starting test7");
		logger.info("Starting test7");
		// <Setup>
		logger.info("Starting searchDifferentReservationTest");
		Reporter.log("Starting searchDifferentReservationTest");
		// Obtengo el row 12 del archivo
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 12);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetReservation(voHotelRes);
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		// Verificar que la info de la reservacion sea la que se selecciono
		pages.hotelListPage.widgetVerifyReservationToBe(voHotelRes);
		// Verificar que la lista de resultados tenga elementos
		pages.hotelListPage.listVerifyResultListHasElements();
	}

	@Test(enabled = true)
	public void prueba() {
		Reporter.log("Starting prueba");
		logger.info("Starting prueba");
		// Esta funcion la uso para pruebas durante el desarrollo
		// Estoy revisando el comportamiento cuando realizo una busqueda pero sin
		// realizar cambios...

		// <Setup>
		logger.info("Starting prueba");
		Reporter.log("Starting prueba");
		// Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOResData voHotelResNew = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 1);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetDestin(voHotelResNew.getDestination());
		pages.hotelListPage.widgetClickSubmit();
		// <Then>
		// Verificar que el widget tiene el nuevo destino
		pages.hotelListPage.widgetVerifyDestinationToBe(voHotelResNew.getDestination());
		// Verificar que el Page Header Title tiene el nuevo destino
		pages.hotelListPage.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		// Verificar que tenemos resultados
		pages.hotelListPage.listVerifyResultListHasElements();
	}
}
