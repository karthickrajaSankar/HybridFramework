package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelTestdata {
	  
	
	 static Workbook ExcelWBook = null;
     static Sheet  ExcelWSheet =null;
   
     
      private static org.apache.poi.ss.usermodel.Cell Cell;
	  private static Object Celldata;
      XSSFRow row ;
	public static String getcelldata(String pathTestdata, String testdata_sheet, String sdata) {
		
		FileInputStream ExcelFile = null;
		
		try {
			ExcelFile = new FileInputStream(pathTestdata);
			
			if(pathTestdata.endsWith(".xlsx")){
				
		         ExcelWBook = new XSSFWorkbook(ExcelFile);
		         ExcelWSheet = ExcelWBook.getSheetAt(0);
				 }else if(pathTestdata.endsWith(".xls")){
					 ExcelWBook = new HSSFWorkbook(ExcelFile);
					
			         ExcelWSheet = ExcelWBook.getSheetAt(0);
				 }
			
		} catch (FileNotFoundException e) {
			Log.warn("test data file not found in "+pathTestdata+" <font color='red'>Failed</font></br>");
			System.out.println("Test data file not found in "+pathTestdata);
			e.printStackTrace();
		} catch (IOException e) {
			Log.warn("IOException  <font color='red'>Failed</font></br>");
			e.printStackTrace();
		}
       
		
       
		ExcelWSheet = ExcelWBook.getSheet(testdata_sheet);
      
		
        Row row = ExcelWSheet.getRow(0);
        int Rowcount =ExcelWSheet.getLastRowNum()+1;
        int col=row.getLastCellNum();
       
        //System.out.println("testsheet name..:"+ExcelWSheet.getSheetName());
        String Test_Data = null;
		if(ExcelWSheet!=null)
        {
              for(int j =1;j<Rowcount;j++)
              {
              for(int i =0;i<=col-1;i++)
              {
            	  
                    Celldata=getCellData(0,i, testdata_sheet);
                  
                  //   System.out.println("Celldata::"+Celldata+",sdata::"+sdata);
                     if(((String) Celldata).equalsIgnoreCase(sdata))
                     {                                   
                            Test_Data = getCellData(j,i, testdata_sheet);
                           
                         //   System.out.println(Test_Data+"*********"+Celldata+"*********"+sdata);
                     }
               }
            }
        }
		return Test_Data;

	}

	public static String getCellData(int rownum, int Colnum, String Sheetname) {
		
		
		ExcelWSheet = ExcelWBook.getSheet(Sheetname);
		Cell = ExcelWSheet.getRow(rownum).getCell(Colnum);
		String result = null;
		try{
         result = Cell.getStringCellValue();
           // System.out.println("==============================================================="+result);
		}catch (IllegalStateException e) {
			System.out.println("change the cell types in excelsheet in the address ("+rownum+","+Colnum+")");
			Log.warn("change the cell types in excelsheet in the address ("+rownum+","+Colnum+") <font color='red'>Failed</font></br>");
		}
        
        return result;
		
		
	}
	
}
