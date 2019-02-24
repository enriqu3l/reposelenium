package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	//Esta funcion ya tiene la capacidad de leer archivos externos y recursos internos
	//Funcion que devuleve una fila, el parametro row=4 devuelve la fila 5
	/**
	 * Esta funcion lee un archivo de excel y regresa la fila indicada. Base 0
	 * Esta funcion ya tiene la capacidad de leer archivos externos y recursos internos
	 * Si el path contiene C: lo considera como archivo externo
	 * @param _filePath Path del archivo
	 * @param _rowNumber Numero de fila
	 * @return Lista-String- Regresa un listado con los datos de la fila
	 */
	public static List<String> getRow(String _filePath, int _rowNumber){
		int rowNumber = _rowNumber;
		List<String> data = new ArrayList<String>();
		FileInputStream inputStream;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				inputStream = new FileInputStream(new File(_filePath));
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				inputStream = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int i=0;
			String val;
			DataFormatter formatter = new DataFormatter();
			while(null!=sheet.getRow(rowNumber).getCell(i)) {
				val = formatter.formatCellValue(sheet.getRow(rowNumber).getCell(i));
				data.add(val);
				i++;
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.getMessage();
			System.out.println("ExcelUtils.getRow() "+e.toString());
		}
		return data;
	}
	
	/**
	 * Esta funcion lee un archivo de excel y regresa la fila que contenga el valor pasado en el segundo parametro
	 * Esta funcion ya tiene la capacidad de leer archivos externos y recursos internos
	 * Si el path contiene C: lo considera como archivo externo
	 * @param _filePath Path del archivo
	 * @param _cellValue Valor a buscar en la primer celda de la fila
	 * @return Lista-String- Regresa un listado con los datos de la fila
	 */
	public static List<String> getRow(String _filePath, String _cellValue){
		List<String> data = new ArrayList<String>();
		FileInputStream inputStream;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				inputStream = new FileInputStream(new File(_filePath));
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				inputStream = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			String val;
			DataFormatter formatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			boolean finded = false;
			while(rowIterator.hasNext()) {
				row = rowIterator.next();
				val = formatter.formatCellValue(row.getCell(0)).trim();
				if(val.equalsIgnoreCase(_cellValue.trim())) {
					finded=true;
					int i=0;
					while(null!=row.getCell(i)) {
						data.add(formatter.formatCellValue(row.getCell(i)).trim());
						i++;
					}
					break;
				}
			}
			if(!finded) {System.out.println("No se encontro el valor deseado en ninguna fila");}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.getMessage();
			System.out.println("ExcelUtils.getRow() "+e.toString());
		}
		return data;
	}
	
	//Aun no funciona, aun no jala todos los datos correctamente!!!
	public static String[][] getDataTableFormatR(String _filePath, int _InitRow, int _InitColumn, int _EndRow, int _EndColumn) {
		//Validar Parametros y mandar logs si hay errores en ellos
		if(_filePath.equals("")) {System.out.println("Error - Ruta del archivo de Excel es incorrecto");return null;}
		if(_InitRow == 0 && _InitRow > 1000 ) {System.out.println("Error - Valor de Fila Inicial fuera de rango(1-999)");return null;}
		if(_InitColumn == 0 && _InitColumn > 1000) {System.out.println("Error - Valor de Columna Inicial fuera de rango(1-999)");return null;}
		if(_EndRow == 0 && _EndRow > 1000) {System.out.println("Error - Valor de Fila Final fuera de rango(1-999)");return null;}
		if(_EndColumn == 0 && _EndColumn > 1000) {System.out.println("Error - Valor de Columna Final fuera de rango(1-999)");return null;}
		if(_EndRow < _InitRow) {System.out.println("Error - Valor de Fila Inicial no puede ser mayor a Fila Final");return null;}
		if(_EndColumn < _InitColumn) {System.out.println("Error - Valor de Columna Inicial no puede ser mayor a Columna Final");return null;}

		int rowSize = _EndRow - _InitRow + 1;
		int columnSize = _EndColumn - _InitColumn + 1;

		String data[][] = new String[rowSize][columnSize];

		@SuppressWarnings("unused")
		String hoja = "Hoja1";
		
		
		FileInputStream fileIS;
		
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				fileIS = new FileInputStream(new File(_filePath));
			}
			else {
				//ClassLoader classLoader = getClass().getClassLoader();  //este no funciona en método estatico
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				fileIS = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			// leer archivo excel
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fileIS);
			//obtener la hoja que se va leer
			XSSFSheet sheet = workbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();

			Row row;

			int i=0; //f2,c2 al f8,c2
			int j=0;
			int igood=0;
			int jgood=0;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext() && i<_EndRow) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;

				//se recorre cada celda
				while (cellIterator.hasNext() && j<_EndColumn) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();

					//igood=i-_InitRow+1;
					//jgood=j-_InitColumn+1;
					//Guardo solo si esta dentro del rango
					if(i >= _InitRow-1 && j >= _InitColumn-1) {
						System.out.println("["+igood+"]["+jgood+"]: "+cell.getStringCellValue()+" | ");
						data[igood][jgood]=cell.getStringCellValue();
					}
					j++;
					if(j >= _InitColumn-1) {jgood++;}
				}
				j=0;
				jgood=0;
				System.out.println();
				i++;
				if(i >= _InitRow-1) {igood++;}
			}
			i=0;
		} catch (Exception e) {
			e.getMessage();
			System.out.println("ExcelUtils.getDataTableFormatR() "+e.toString());
		}
		return data;
	}
	
	public static String[][] getDataFromExcel(String _filePath) {
		String data[][] = new String[20][2];
		
		FileInputStream fileIS;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				fileIS = new FileInputStream(new File(_filePath));
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				fileIS = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			// leer archivo excel
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fileIS);
			//obtener la hoja que se va leer
			XSSFSheet sheet = workbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			
			int i=0;
			int j=0;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					System.out.print(cell.getStringCellValue()+"["+ i +"]["+ j +"]: | ");
					data[i][j]=cell.getStringCellValue();
					j++;
				}
				j=0;
				System.out.println();
				i++;
			}
			i=0;
		} catch (Exception e) {
			e.getMessage();
		}
		
		return data;
	}
	
	public static String[][] getDataFromExcel(String _filePath,int _row, int _column) {
		String data[][] = new String[_row][_column];
		
		FileInputStream fileIS;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				fileIS = new FileInputStream(new File(_filePath));
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				fileIS = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			// leer archivo excel
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fileIS);
			//obtener la hoja que se va leer
			XSSFSheet sheet = workbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			
			int i=0;
			int j=0;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext() && i<_row) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()&&j<_column) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					System.out.print("["+i+"]["+j+"]: "+cell.getStringCellValue()+" | ");
					data[i][j]=cell.getStringCellValue();
					j++;
				}
				j=0;
				System.out.println();
				i++;
			}
			i=0;
		} catch (Exception e) {
			e.getMessage();
		}
		
		return data;
	}
	
	//Esta funcion ya tiene la capacidad de leer archivos externos y recursos internos
	/**
	 * @param _filePath
	 * @return
	 */
	public static int getRowCount(String _filePath) {
		int row=0;
		
		FileInputStream inputStream;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				inputStream = new FileInputStream(new File(_filePath));
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				inputStream = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			row = sheet.getLastRowNum()+1;//Le sumo 1 porque getLastRowNum devulve la ultima fila en "base 0"
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.getMessage();
			System.out.println("ExcelUtils.getRowCountR() "+e.toString());
		}
		return row;
	}
	
	//Esta funcion ya tiene la capacidad de leer archivos externos y recursos internos
	public static int getColumnCount(String _filePath) {
		int column = 0;
		FileInputStream inputStream;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				inputStream = new FileInputStream(new File(_filePath));
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				inputStream = new FileInputStream(classLoader.getResource(_filePath).getFile());
			}
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			column = sheet.getRow(0).getLastCellNum(); //Obtiene el numero de la ultima celda de la primer fila
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.getMessage();
			System.out.println("ExcelUtils.getColumnCountR() "+e.toString());
		}
		return column;
	}
	
	
	//+++++++++++++++Create Excel Files Functions++++++++++++++++++++++
	
	//Esta funcion aun no esta lista, necesito revisarla,
	//esta guardando el file de target y no el de src
	public static void saveNewRowInExistingFile(String _filePath, List<String> data) {
		int row = 0;
		int i = 0;
		File file=null;
		FileInputStream inputStream;
		try{
			if(_filePath.contains("C:")){
				//Para el caso que sea un archivo externo al proyecto
				//Aqui le pasamos como parametro un file
				file = new File(_filePath);
				inputStream = new FileInputStream(file);
			}
			else {
				//Para el caso que sea un recurso dentro del proyecto
				//Aquile pasamos como parametro un String
				ClassLoader classLoader = ExcelUtils.class.getClassLoader();
				file = new File( classLoader.getResource(_filePath).getFile() );
				inputStream = new FileInputStream(file);
			}
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			row=sheet.getLastRowNum();
			XSSFRow newRow = sheet.createRow(row + 1);
			Iterator<String> iterator = data.iterator();
			while (iterator.hasNext()) {
				newRow.createCell(i).setCellValue(iterator.next());
				System.out.println("Iterator ["+i+"]: "+data.get(i));
				i++;
			}
			//Guardarmos cambios y cerraramos
		    FileOutputStream outputStream = new FileOutputStream(file);
		    workbook.write(outputStream);
		    workbook.close();
		    outputStream.close();
		    inputStream.close();
		    System.out.println("Ya se termino de editar el archivo!");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("ExcelUtils.saveLocatorData() "+e.toString());
		}
	}
	
	public static void CrearExcelWithData(String _fileName, String _path, String _data[][]){
		
		String nombreArchivo = _fileName;
		String rutaArchivo = _path + nombreArchivo;
		String hoja="Hoja1";
		
		XSSFWorkbook libro= new XSSFWorkbook(); //HSSFWorkbook para versiones 93/97
		XSSFSheet hoja1 = libro.createSheet(hoja);
		//cabecera de la hoja de excel
		String [] header= new String[]{"Nombre", "Email","Password","Telefono"};
		
		
		String document[][] = new String[20][4];
		document = _data;
		
		/*contenido de la hoja de excel
		String [][] document= new String [][]{
				{"Juan","juan@test.com","123@123","50508296"},
				{"Carlos","carlos@test.com","123@123","25468524"},
				{"Axel","axel@test.com","ABC@ABC","15875421"},
				{"Alex","alex@test.com","123@123","15875268"},
				{"Erick","erick@test.com","123@123","88915615"}
		};*/
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);
        
		//generar los datos para el documento
		for (int i = 0; i <= document.length; i++) {
			XSSFRow row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <header.length; j++) {
				if (i==0) {//para la cabecera
						XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
						cell.setCellStyle(style); // se añade el style crea anteriormente 
						cell.setCellValue(header[j]);//se añade el contenido					
				}else{//para el contenido
					XSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
					cell.setCellValue(document[i-1][j]); //se añade el contenido
				}				
			}
		}
		
		//Aqui se crea el archivo (en caso de que ya exista se elimina y se crea uno nuevo)
		File file = new File(rutaArchivo);
		try (FileOutputStream fileOuS = new FileOutputStream(file)){						
			if (file.exists()) {// si el archivo existe se elimina
				file.delete();
				System.out.println("Archivo eliminado");
			}
			libro.write(fileOuS);
			fileOuS.flush();
			fileOuS.close();
			libro.close();
			System.out.println("Archivo Creado");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void EjemploCrearExcel(){
		String nombreArchivo = "Test.xlsx";
		String rutaArchivo = "C:\\Selenium\\Test\\" + nombreArchivo;
		String hoja="Hoja1";
		XSSFWorkbook libro= new XSSFWorkbook(); //Para versiones 93/97 usar HSSFWorkbook 
		XSSFSheet hoja1 = libro.createSheet(hoja);
		//cabecera de la hoja de excel
		String [] header= new String[]{"Nombre", "Email","Password","Telefono"};
		//contenido de la hoja de excel
		String [][] document= new String [][]{
				{"Juan","juan@test.com","123@123","50508296"},
				{"Carlos","carlos@test.com","123@123","25468524"},
				{"Axel","axel@test.com","ABC@ABC","15875421"},
				{"Alex","alex@test.com","123@123","15875268"},
				{"Erick","erick@test.com","123@123","88915615"}
		};
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);
        
		//generar los datos para el documento
		for (int i = 0; i <= document.length; i++) {
			XSSFRow row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <header.length; j++) {
				if (i==0) {//para la cabecera
						XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
						cell.setCellStyle(style); // se añade el style crea anteriormente 
						cell.setCellValue(header[j]);//se añade el contenido					
				}else{//para el contenido
					XSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
					cell.setCellValue(document[i-1][j]); //se añade el contenido
				}				
			}
		}
		
		File file;
		file = new File(rutaArchivo);
		try (FileOutputStream fileOuS = new FileOutputStream(file)){						
			if (file.exists()) {// si el archivo existe se elimina
				file.delete();
				System.out.println("Archivo eliminado");
			}
			libro.write(fileOuS);
			fileOuS.flush();
			fileOuS.close();
			System.out.println("Archivo Creado");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			libro.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] requestData() {
		String name;
		String email;
		String pass;
		String phone;
		String data[][] = new String[20][4];
		
		Scanner scn = new Scanner(System.in);
		int resp = 0;
		int i=0;
		do{
			System.out.println("Ingresa tu Nombre: ");
			name = scn.nextLine();
			System.out.println("Ingresa tu email: ");
			email = scn.nextLine();
			System.out.println("Ingresa tu password: ");
			pass = scn.nextLine();
			System.out.println("Ingresa tu telefono: ");
			phone = scn.nextLine();
			
			data[i][0]=name;
			data[i][1]=email;
			data[i][2]=pass;
			data[i][3]=phone;
			
			System.out.println("Deseas ingresar otro registro: 1-SI | 2-NO ");
			resp = scn.nextInt();
			scn.nextLine();
			i++;
		}while(resp==1);
		scn.close();
		return data;
	}
	
	//Este es otro ejemplo de como editar un archivo
	//No lo estoy usando!
	public static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
	    // Obtain a workbook from the excel file
		//La raiz donde comienza a buscar un archivo es donde se encuentra el archivo pom.xml
	    Workbook workbook = WorkbookFactory.create(new File("existing-spreadsheet.xlsx"));

	    // Get Sheet at index 0
	    Sheet sheet = workbook.getSheetAt(0);
	    // Get Row at index 1
	    Row row = sheet.getRow(1);
	    // Get the Cell at index 2 from the above row
	    Cell cell = row.getCell(2);

	    // Create the cell if it doesn't exist
	    if (cell == null)
	        cell = row.createCell(2);

	    // Update the cell's value
	    cell.setCellType(CellType.STRING);
	    cell.setCellValue("Updated Value");

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("existing-spreadsheet.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();

	    // Closing the workbook
	    workbook.close();
	}
}


//Pruebas durante la construccion de esta libreria

/*String data[][];
data = ExcelUtils.getDataTableFormat("ProductHotelData.xlsx","C:\\Automation\\",1,1,4,5);
data = ExcelUtils.getDataTableFormatR("SourceDataFiles/HotelResData.xlsx", 1, 1, 1, 12);
System.out.println("Data [0][0]: " + data[0][0]);
*/

/*
int row = ExcelUtils.getRowCountR("SourceDataFiles/HotelResData.xlsx");
int column = ExcelUtils.getColumnCountR("SourceDataFiles/HotelResData.xlsx");
System.out.println("Numero de Rows: " + row);
System.out.println("Numero de Columns: " + column);
*/

/*
List<String> row = ExcelUtils.getRowR("SourceDataFiles/HotelResData.xlsx",4);
int i=0;
Iterator<String> iterator = row.iterator();
while (iterator.hasNext()) {
	i++;
    System.out.println("item["+i+"]: "+iterator.next());
}
*/
