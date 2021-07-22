package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
	 // private static XSSFSheet ExcelWSheet;
	  static Workbook ExcelWBook = null;
      static Sheet  ExcelWSheet =null;
      private static org.apache.poi.ss.usermodel.Cell Cell;
      
      
      public static void setExcelFile(String path) throws IOException {
		
		 FileInputStream ExcelFile = new FileInputStream(path);
		
		if(path.endsWith(".xlsx")){
			
         ExcelWBook = new XSSFWorkbook(ExcelFile);
         ExcelWSheet = ExcelWBook.getSheetAt(0);
		 }else if(path.endsWith(".xls")){
			 ExcelWBook = new HSSFWorkbook(ExcelFile);
			
	         ExcelWSheet = ExcelWBook.getSheetAt(0);
		 }
	}
	
      public static String getindexSheet(){
		return ExcelWSheet.getSheetName();
		
	}
	
      public static int getRowCount(String SheetName) {
		
		int iNumber=0;
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		iNumber=ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum()+1;
		return iNumber;
		
	}

	public static String getCellData(int rownum, int Colnum, String Sheetname) {
	
		try{
		ExcelWSheet = ExcelWBook.getSheet(Sheetname);
		Cell = ExcelWSheet.getRow(rownum).getCell(Colnum);
        String CellData = Cell.getStringCellValue();
       
        return CellData;
		}catch (Exception e) {
			return"";
		}
	}

	public static int getRowContains(String sTestcaseName, int colnum, String Sheetname) {
		int iRowNum=0;
		int rowCount = Excelutils.getRowCount(Sheetname);
		for (; iRowNum<rowCount; iRowNum++){
			if  (Excelutils.getCellData(iRowNum,colnum,Sheetname).equalsIgnoreCase(sTestcaseName)){
				break;
			}
		}       		
		return iRowNum;
	}

	public static int getTestStepsCount(String sheetname, String sTestCaseID, int iTeststart) {
		for(int i=iTeststart;i<=Excelutils.getRowCount(sheetname);i++){
			if(!sTestCaseID.equals(Excelutils.getCellData(i, 0, sheetname))){
				int number = i;
				return number;      				
				}
			}
		ExcelWSheet = ExcelWBook.getSheet(sheetname);
		int number=ExcelWSheet.getLastRowNum()+1;
		return number;
	}

	
	}
