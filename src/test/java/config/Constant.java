package config;

import java.io.FileInputStream;
import java.lang.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xpath.operations.String;



public class Constant
{
	public static final java.lang.String Path_Filepath = "./Filepath.xls";
	
	public static java.lang.String Path_Testcase;
	public static java.lang.String Path_Testdata;
	public static java.lang.String ieDriverPath;
	public static java.lang.String chromeDriverPath;
	private static java.lang.String outputpath;
	private static java.lang.String firefoxdriverpath;

	public static java.lang.String ScreenShotpath;
	
	
	public static void ReadUtilFile() throws Exception
	{
		
		
		FileInputStream ExcelFile = new FileInputStream(Path_Filepath);
			 Workbook ExcelWBook = null;
             Sheet Sheet =null;
			if(Path_Filepath.endsWith(".xlsx")){
				
		         ExcelWBook = new XSSFWorkbook(ExcelFile);
		         
				 }else if(Path_Filepath.endsWith(".xls")){
					 ExcelWBook = new HSSFWorkbook(ExcelFile);
					
			        
				 }
	
			 Sheet = ExcelWBook.getSheetAt(0);
			 
			outputpath = Sheet.getRow(2).getCell(1).getStringCellValue();
			Path_Testcase = Sheet.getRow(3).getCell(1).getStringCellValue();
			Path_Testdata = Sheet.getRow(4).getCell(1).getStringCellValue();
			ieDriverPath=Sheet.getRow(5).getCell(1).getStringCellValue();
			chromeDriverPath=Sheet.getRow(6).getCell(1).getStringCellValue();
			ScreenShotpath=Sheet.getRow(7).getCell(1).getStringCellValue();
			firefoxdriverpath = Sheet.getRow(8).getCell(1).getStringCellValue();

		
}
}
