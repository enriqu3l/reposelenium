package testautomation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Utility.ExcelUtils;

public class testautomation {

	public static void main(String[] args) {
		/*String data[][];
		data = ExcelUtils.getDataTableFormat("ProductHotelData.xlsx","C:\\Automation\\",1,1,4,5);
		data = ExcelUtils.getDataTableFormatR("SourceDataFiles/HotelResData.xlsx", 1, 1, 1, 12);
		System.out.println("Data [0][0]: " + data[0][0]);
		*/
		
		
		//int row = ExcelUtils.getRowCountR("SourceDataFiles/HotelResData.xlsx");
		//int column = ExcelUtils.getColumnCountR("SourceDataFiles/HotelResData.xlsx");
		//System.out.println("Numero de Rows: " + row);
		//System.out.println("Numero de Columns: " + column);
		
		
		/*
		List<String> row = ExcelUtils.getRowR("SourceDataFiles/HotelResData.xlsx",4);
		int i=0;
		Iterator<String> iterator = row.iterator();
		while (iterator.hasNext()) {
			i++;
		    System.out.println("item["+i+"]: "+iterator.next());
		}
		*/
		
		
		/*Aun no soluciono el error de editar y guardar el recurso excel
		 * Se esta guardando el archivo de target pero no el de src
		 * Estare guardando capturas de pantalla en lo que veo que hacer
		 * con este asunto.
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		List<String> data = new ArrayList<String>();
		data.add("45234932");
		data.add(currentDate);
		System.out.println("Lista Dato 1: "+data.get(0));
		System.out.println("Lista Dato 1: "+data.get(1));
		ExcelUtils.saveNewRowInExistingFile("SourceDataFiles/LocatorsGenerated.xlsx", data);
		*/
	}
}