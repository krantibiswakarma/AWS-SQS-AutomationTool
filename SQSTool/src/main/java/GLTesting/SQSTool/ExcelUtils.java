package GLTesting.SQSTool;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelUtils {
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	
	
	// 1. Open the excel file
	public static void setExcelFile(String path, String sheetName){
		
		FileInputStream ExcelFile = null;
		try {
			ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
	}
	
    // 2. Read the test data from the excel sheet	
	public static String getCellData(int rowNum, int colNum) {
		try {
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);

			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			Reporter.log("Error: unable to get cell data...................."+rowNum+"colNum: "+colNum);
			return "";
		}
	}
	
	// 3. Get total row number 
	public static int getRowCount(){
		
		int number = 0;
		try{
	      number = ExcelWSheet.getLastRowNum()+1;
		}catch(Exception e){}
		 
		return number;
	}
	
	public static ArrayList<String> getAllCellData(String path, String sheetName, int colNum){
		System.out.println("Reading values from excel file...");
		setExcelFile(path, sheetName);
		int rowCount = getRowCount();
		ArrayList<String> rowValues = new ArrayList<String>();
		for(int i=1;i<=rowCount-1;i++) {
				rowValues.add(getCellData(i, colNum));
			}
		return rowValues;
	}

}