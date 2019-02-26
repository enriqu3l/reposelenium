package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDateTime;

import config.FWConfig;
import utility.BasicUtils;
import utility.ExcelUtils;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

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
	
	//------------------------- HOTEL RES ------------------------------
	public static VOResData getResDataDefault(String file) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		int defaultRow = 1; //Para leer el primer registro del archivo
		VOResData voHotelRes = new VOResData();
		voHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		return voHotelRes;
	}//EndFunction
	
	public  static VOResData getResData(String file,int row) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		//Candado que limita los valores que puede tener la variable row
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(row<=0){row=1;}
		if(row>max){row=max;}
		VOResData voResData = new VOResData();
		voResData.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voResData;
	}//EndFunction
	
	public  static VOResData getResData(String file,String cellValue) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		if(cellValue.isEmpty()){
			System.out.println("Error. No se puede buscar un valor vacio!");
			return null;
		}
		VOResData voResData = new VOResData();
		voResData.setDataUsingList(ExcelUtils.getRow(filePath, cellValue));
		return voResData;
	}//EndFunction
	
	public static VOResData getResDataRandom(String file) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		int min = 1;  //Comienza en 1 porque la fila de cabecera es la 0
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max:
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		System.out.println("Info - DDManager - getRandomHotelRes randomNum: "+randomNum);
		VOResData voResData = new VOResData();
		voResData.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return voResData;
	}//EndFunction
	
	
	
	//------------------------- CLIENT DATA ------------------------------
	public static VOClient getClientDataDefault(String file) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		int defaultRow = 1; //Para leer el primer registro del archivo
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		return voClient;
	}
	public static VOClient getClientData(String file, int row) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		//Candado que limita los valores que puede tener la variable row
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(row<=0){row=1;}
		if(row>max){row=max;}
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voClient;
	}
	
	public static VOClient getClientRandom(String file) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		int min = 1;
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return voClient;
	}
	
	
	//-------------------------CREDIT CARD------------------------------
	public static VOCreditCard getCreditCardDefault(String file) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		int row = 1; //Hardcodeada para que lea el primer registro del archivo excel
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voCreditCard;
	}
	
	public static VOCreditCard getCreditCardDefault() {
		String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CREDITCARDSDATA;
		int row = 1; //Hardcodeada para que lea el primer registro del archivo excel
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voCreditCard;
	}//EndFunction
	
	public static VOCreditCard getCreditCard(int row) {
		String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CREDITCARDSDATA;
		//Candado que limita los valores que puede tener la variable row
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(row<=0){row=1;}
		if(row>max){row=max;}
		
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voCreditCard;
	}
	
	public static VOCreditCard getCreditCardRandom() {
		String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CREDITCARDSDATA;
		int min = 1;
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return voCreditCard;
	}
	
	
	//------------------------- LOCATORS GENERATED------------------------------
	
	//Necesito verificar si funciona correctamente 
	public static boolean saveLocator(String locator) {
		String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_LOCATORSGENERATED;
		List<String> data = new ArrayList<String>();
		data.add(locator);
		data.add(LocalDateTime.now().toString("dd/MM/yyyy_HH:mm:ss"));
		ExcelUtils.saveNewRowInExistingFile(filePath, data);
		return true;
	}
	
	public static String getLocator(int item) {
		//Aqui el codigo para obtener el locator
		
		return "";
	}
	
	//-------------------------- Landing Pages ----------------------------------
	/**
	 * Regresa un String con la URL de una Landing Page de SPA Hotel List
	 * Esta funcion lee el registro 1 del archivo Excel que recibe como parametro
	 * @param file String con el nombre del archivo de Excel
	 * @return String con una URL de la LP
	 */
	public static String getLandingPageHLDefault(String file) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		int defaultRow = 1; //Para leer el primer registro del archivo
		List<String> data = ExcelUtils.getRow(filePath, defaultRow);
		return BasicUtils.createUrlLandingPageHL(data);
	}
	
	public static String getLandingPageHL(String file, int row) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		List<String> data = ExcelUtils.getRow(filePath, row);
		return BasicUtils.createUrlLandingPageHL(data);
	}
	
	public static String getLandingPageHL(String file, String cellValue) {
		String filePath = FWConfig.PATH_DATASOURCE+file;
		List<String> data = ExcelUtils.getRow(filePath, cellValue);
		return BasicUtils.createUrlLandingPageHL(data);
	}
}