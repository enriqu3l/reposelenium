package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import config.FrameworkConfig;
import utility.ExcelUtils;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelRes;

public class DDManager {
	/*Ya no usare resources internos, ahora los archivos estaran fuera del proyecto
	public static String rHotelResData = "SourceDataFiles/HotelResData.xlsx";
	public static String rCreditCardData = "SourceDataFiles/CreditCardsData.xlsx";
	public static String rClientData = "SourceDataFiles/ClientData.xlsx";
	public static String rLocatorsGenerated = "SourceDataFiles/LocatorsGenerated.xlsx";
	*/
	
	//URGENTE!!!!
	//No olvidar mencionar que los arhivos de excel necesitan eliminar las filas que queden vacias
	//cada que se edite el archivo, esto porque si las funciones detectan que hay algo como: "" dentro
	//de una celda la consideran como que si tiene contenido, esto se fixeara mas adelante.
	
	//Constructor
	public DDManager() {
	}
	
	//-------------------------HOTEL RES------------------------------
	public static VOHotelRes getHotelResDefault() {
		LocalDate startDate;
		LocalDate endDate;
		VOHotelRes DOHotelRes = new VOHotelRes();
		DOHotelRes.setIdHotelRes("HR001");
		DOHotelRes.setDestination(FrameworkConfig.R_DESTIN);
		startDate = LocalDate.now().plusMonths(FrameworkConfig.R_STARTDATE_PlusMonth);
		DOHotelRes.setStartDate(startDate.toString("dd/MM/yyyy"));
		endDate = startDate.plusDays(FrameworkConfig.R_ENDDATE_PlusDay);
		DOHotelRes.setEndDate(endDate.toString("dd/MM/yyyy"));
		DOHotelRes.setAdults(FrameworkConfig.R_ADULTS);
		DOHotelRes.setKids(FrameworkConfig.R_KIDS);
		return DOHotelRes;
	}//EndFunction
	
	public  static VOHotelRes getHotelRes(int item) {
		String filePath = FrameworkConfig.DATASOURCE_PATH+FrameworkConfig.FILE_HotelResData;
		
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		VOHotelRes DOHotelRes = new VOHotelRes();
		DOHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return DOHotelRes;
	}//EndFunction
	
	public static VOHotelRes getRandomHotelRes() {
		String filePath = FrameworkConfig.DATASOURCE_PATH+FrameworkConfig.FILE_HotelResData;
		
		int min = 1;  //Comienza en 1 porque la fila de cabecera es la 0
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max:
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		System.out.println("Info - DDManager - getRandomHotelRes randomNum: "+randomNum);
		
		VOHotelRes DOHotelRes = new VOHotelRes();
		DOHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return DOHotelRes;
	}//EndFunction
	
	//-------------------------CREDIT CARD------------------------------
	public static VOCreditCard getCreditCardDefault() {
		//Me falta jalar los datos desde la Clase GeneralConfig
		VOCreditCard DOCreditCard = new VOCreditCard();
		DOCreditCard.setNumber(Long.parseLong("4772130000000003"));
		DOCreditCard.setHolderName("Virginia Chavez");
		DOCreditCard.setMonth("08");
		DOCreditCard.setYear("2021");
		DOCreditCard.setCCV(114);
		DOCreditCard.setCountry("MX");
		DOCreditCard.setCP(44777);
		DOCreditCard.setBank("Bancomer");
		DOCreditCard.setPayNetwork(FrameworkConfig.CardTypes.VISA);
		return DOCreditCard;
	}//EndFunction
	
	public static VOCreditCard getCreditCard(int item) {
		String filePath = FrameworkConfig.DATASOURCE_PATH+FrameworkConfig.FILE_CreditCardsData;
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		VOCreditCard DOCreditCard = new VOCreditCard();
		DOCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return DOCreditCard;
	}
	
	public static VOCreditCard getRandomCreditCard() {
		String filePath = FrameworkConfig.DATASOURCE_PATH+FrameworkConfig.FILE_CreditCardsData;
		int min = 1;
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		VOCreditCard DOCreditCard = new VOCreditCard();
		DOCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return DOCreditCard;
	}
	
	//Aun no la usar�, hasta hacer la modificacion para que el archivo LocatorsGenerated.xlsx
	//sea alojado directamente en C en vez de alojarlo dentro del proyecto
	public static boolean saveLocator(String locator) {
		String filePath = FrameworkConfig.DATASOURCE_PATH+FrameworkConfig.FILE_LocatorsGenerated;
		List<String> data = new ArrayList<String>();
		data.add(locator);
		data.add(LocalDateTime.now().toString("dd/MM/yyyy_HH:mm:ss"));
		ExcelUtils.saveNewRowInExistingFile(filePath, data);
		return true;
	}
	
	public static String getLocator(int item) {
		//Aqui el codigo para obtener el locator en base a el listado
		
		return "";
	}
}