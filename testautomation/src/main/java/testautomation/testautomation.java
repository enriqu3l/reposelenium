package testautomation;

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
		
		String date = BasicUtils.getCurrentDatePlusMonth(1);
		String date2 = BasicUtils.getCurrentDatePlusMonth2(1);
		
		System.out.println("Fecha1 mas 1 mes: "+date);
		System.out.println("Fecha2 mas 1 mes: "+date2);
		
	}
}