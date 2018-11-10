package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String[][] getDataTableFormat(String _file, String _path, int _InitRow, int _InitColumn, int _EndRow, int _EndColumn) {
		//Validar Parametros y mandar logs si hay errores en ellos
		if(_file.equals("")) {System.out.println("Error - nombre del archivo de Excel es incorrecto");}
		if(_path.equals("")) {System.out.println("Error - Ruta del archivo de Excel es incorrecto");}
		if(_InitRow == 0 && _InitRow > 1000 ) {System.out.println("Error - Valor de Fila Inicial fuera de rango(1-999)");}
		if(_InitColumn == 0 && _InitColumn > 1000) {System.out.println("Error - Valor de Columna Inicial fuera de rango(1-999)");}
		if(_EndRow == 0 && _EndRow > 1000) {System.out.println("Error - Valor de Fila Final fuera de rango(1-999)");}
		if(_EndColumn == 0 && _EndColumn > 1000) {System.out.println("Error - Valor de Columna Final fuera de rango(1-999)");}
		if(_EndRow < _InitRow) {System.out.println("Error - Valor de Fila Inicial no puede ser mayor a Fila Final");}
		if(_EndColumn < _InitColumn) {System.out.println("Error - Valor de Columna Inicial no puede ser mayor a Columna Final");}
		
		int rowSize = _EndRow - _InitRow + 1;
		int columnSize = _EndColumn - _InitColumn + 1;
		
		String data[][] = new String[rowSize][columnSize];
		String nombreArchivo = _file;
		String rutaArchivo = _path + nombreArchivo;
		
		@SuppressWarnings("unused")
		String hoja = "Hoja1";
 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
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
						System.out.print("["+igood+"]["+jgood+"]: "+cell.getStringCellValue()+" | ");
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
		}
		
		return data;
	}
	
	public static String[][] getDataFromExcel(String _file, String _path) {
		String data[][] = new String[20][2];
		String nombreArchivo = _file;
		String rutaArchivo = _path + nombreArchivo;
		
		@SuppressWarnings("unused")
		String hoja = "Hoja1";
 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
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
	
	public static String[][] getDataFromExcel(String _file, String _path,int _row, int _column) {
		String data[][] = new String[_row][_column];
		String nombreArchivo = _file;
		String rutaArchivo = _path + nombreArchivo;
		
		@SuppressWarnings("unused")
		String hoja = "Hoja1";
 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void EjemploCrearExcel(){
		String nombreArchivo = "Test.xlsx";
		String rutaArchivo = "C:\\Selenium\\Test\\" + nombreArchivo;
		String hoja="Hoja1";
		XSSFWorkbook libro= new XSSFWorkbook(); //HSSFWorkbook para versiones 93/97
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
		return data;
	}
}
