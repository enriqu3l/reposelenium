package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDateTime;

import config.FrameworkConfig;
import utility.ExcelUtils;
import valueobjects.VOClient;
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
	
	//------------------------- HOTEL RES ------------------------------
	public static VOHotelRes getHotelResDefault(String file) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+file;
		int defaultRow = 1; //Para leer el primer registro del archivo
		VOHotelRes voHotelRes = new VOHotelRes();
		voHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		return voHotelRes;
	}//EndFunction
	
	public  static VOHotelRes getHotelRes(String file,int row) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+file;
		//Candado que limita los valores que puede tener la variable row
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(row<=0){row=1;}
		if(row>max){row=max;}
		VOHotelRes voHotelRes = new VOHotelRes();
		voHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voHotelRes;
	}//EndFunction
	
	public static VOHotelRes getRandomHotelRes(String file) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+file;
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
	
	
	
	//------------------------- CLIENT DATA ------------------------------
	public static VOClient getClientDataDefault(String file) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+file;
		int defaultRow = 1; //Para leer el primer registro del archivo
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		return voClient;
	}
	public static VOClient getClientData(String file, int row) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+file;
		//Candado que limita los valores que puede tener la variable row
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(row<=0){row=1;}
		if(row>max){row=max;}
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voClient;
	}
	
	public static VOClient getRandomClient(String file) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+file;
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
	public static VOCreditCard getCreditCardDefault() {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_CREDITCARDSDATA;
		int row = 1; //Hardcodeada para que lea el primer registro del archivo excel
		
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, row));
		return voCreditCard;
	}//EndFunction
	
	public static VOCreditCard getCreditCard(int row) {
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_CREDITCARDSDATA;
		//Candado que limita los valores que puede tener la variable row
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(row<=0){row=1;}
		if(row>max){row=max;}
		
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, row));
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
	
	
	//------------------------- LOCATORS GENERATED------------------------------
	
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
		//Aqui el codigo para obtener el locator
		
		return "";
	}
}