package testautomation;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import Utility.BasicUtils;

public class testautomation {

	public static void main(String[] args) {
		/* Ejecucion con Maven
		 * mvn clean -----Limpa valores de antiguas ejecuciones
		 * mvn compile ---Verifica que no existan errores
		 * mvn test ------Ejecutas todos los test que se encuentren en la carpeta src/test/java
		 * mvn test -PHappypath --Para ejecutar con un perfil deseado
		 */
		
		
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
		
		/*String date = BasicUtils.getCurrentDatePlusMonth(1);
		String date2 = BasicUtils.getCurrentDatePlusMonth2(1);
		System.out.println("Fecha1 mas 1 mes: "+date);
		System.out.println("Fecha2 mas 1 mes: "+date2);
		*/
		
		/*
		ClassLoader classLoader = ExcelUtils.class.getClassLoader();
		String file = classLoader.getResource("SourceDataFiles/HotelResData.xlsx").getFile();
		System.out.println("File path: "+file);
		*/
		
		/*String date = LocalDateTime.now().toString("dd/MM/yyyy_HH:mm:ss");
		System.out.println("Fecha de ahora: "+date);
		*/
		
		String x = " noviembre 2018";
		String titledate = x.toUpperCase().trim();
		int titleyear = Integer.parseInt(titledate.substring(titledate.length()-4, titledate.length()));
		String titlemonth = titledate.substring(0,titledate.length()-4).trim();
		System.out.println("resultado year:"+titleyear);
		System.out.println("resultado month:"+titlemonth+"<--");
		int monthInteger = BasicUtils.toMonthNumber(titlemonth);
		System.out.println("resultado month:"+monthInteger+"<--");
		
		String date="23/04/2019";
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		System.out.println("resultado:"+day);
		System.out.println("resultado:"+month);
		System.out.println("resultado:"+year);
	}
}