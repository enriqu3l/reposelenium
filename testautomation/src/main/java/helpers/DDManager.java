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
import valueobjects.VOHotelResNew;

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
	
	//------------------------- HOTEL RES NEW ------------------------------
	public static VOHotelResNew getHotelResNewDefault() {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_HPHOTELRESDATA;
		int defaultRow = 1; //Para leer el primer registro del archivo
		VOHotelResNew voHotelResNew = new VOHotelResNew();
		voHotelResNew.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		return voHotelResNew;
	}//EndFunction
	
	public  static VOHotelResNew getHotelResNew(int item) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_HPHOTELRESDATA;
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		VOHotelResNew voHotelResNew = new VOHotelResNew();
		voHotelResNew.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return voHotelResNew;
	}//EndFunction
	
	public static VOHotelResNew getRandomHotelResNew() {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_HOTELLISTWIDGETDATA;
		int min = 1;  //Comienza en 1 porque la fila de cabecera es la 0
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max:
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		System.out.println("Info - DDManager - getRandomHotelRes randomNum: "+randomNum);
		VOHotelResNew voHotelResNew = new VOHotelResNew();
		voHotelResNew.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return voHotelResNew;
	}//EndFunction
	
	//-------------------------HOTEL RES------------------------------
	public static VOHotelRes getHotelResDefault() {
		//Ya quedo obsoleta esta funcion, ahora ya uso el nuevo formato de archivo excel
		//Uso la informacion del FrameworkConfig--
		LocalDate startDate;
		LocalDate endDate;
		VOHotelRes voHotelRes = new VOHotelRes();
		voHotelRes.setIdHotelRes("HR001");
		voHotelRes.setDestination(FrameworkConfig.R_DESTIN);
		startDate = LocalDate.now().plusMonths(FrameworkConfig.R_STARTDATE_PLUSMONTH);
		voHotelRes.setStartDate(startDate.toString("dd/MM/yyyy"));
		endDate = startDate.plusDays(FrameworkConfig.R_ENDDATE_RESDAYS);
		voHotelRes.setEndDate(endDate.toString("dd/MM/yyyy"));
		voHotelRes.setAdults(FrameworkConfig.R_ADULTS);
		voHotelRes.setKids(FrameworkConfig.R_KIDS);
		return voHotelRes;
	}//EndFunction
	
	public  static VOHotelRes getHotelRes(int item) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_HOTELRESDATA;
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		VOHotelRes voHotelRes = new VOHotelRes();
		voHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return voHotelRes;
	}//EndFunction
	
	public static VOHotelRes getRandomHotelRes() {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_HOTELRESDATA;
		int min = 1;  //Comienza en 1 porque la fila de cabecera es la 0
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max:
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		System.out.println("Info - DDManager - getRandomHotelRes randomNum: "+randomNum);
		VOHotelRes voHotelRes = new VOHotelRes();
		voHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return voHotelRes;
	}//EndFunction
	
	//------------------------ HotelListWidget ------------------------
	
	
	
	
	//-------------------------CREDIT CARD------------------------------
	public static VOCreditCard getCreditCardDefault() {
		//Por lo pronto tengo hardcodeada esta funcion!!!
		//Me falta jalar los datos desde la Clase GeneralConfig
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setNumber(Long.parseLong("4772130000000003"));
		voCreditCard.setHolderName("Virginia Chavez");
		voCreditCard.setMonth("08");
		voCreditCard.setYear("2021");
		voCreditCard.setCCV(114);
		voCreditCard.setCountry("MX");
		voCreditCard.setCP(44777);
		voCreditCard.setBank("Bancomer");
		voCreditCard.setPayNetwork(FrameworkConfig.CardTypes.VISA);
		return voCreditCard;
	}//EndFunction
	
	public static VOCreditCard getCreditCard(int item) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_CREDITCARDSDATA;
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return voCreditCard;
	}
	
	public static VOCreditCard getRandomCreditCard() {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_CREDITCARDSDATA;
		int min = 1;
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return voCreditCard;
	}
	
	//Necesito verificar si funciona correctamente 
	public static boolean saveLocator(String locator) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_LOCATORSGENERATED;
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