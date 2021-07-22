package execution;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import config.Actionkeywords;
import config.Constant;
import utility.ExcelTestdata;
import utility.Excelutils;
import utility.Log;

public class Driver {

	public static Actionkeywords actionKeywords;
	public static String sActionKeyword;
	public static String sobject;
	public static Method method[];
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static String Testdata_sheet;
	public static final int Col_TestCaseID = 0;
	public static final int Col_testcasedescription = 1;
	public static final int Col_RunMode = 2;
	public static final int Col_datasheet = 3;
	public static final int Col_ScenarioID = 1;
	public static final int Col_description = 2;
	public static final int Col_Object = 3;
	public static final int Col_ActionKeyword = 4;
	public static final int Col_DataSet = 5;
	public static final int Col_Snap = 6;
	private static String Tdata;
	@SuppressWarnings("unused")
	private String Testcasedescription;
	private String snap;

	public Driver() {
		actionKeywords = new Actionkeywords();
		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception {

		Constant.ReadUtilFile();
		Excelutils.setExcelFile(Constant.Path_Testcase);
		DOMConfigurator.configure("log.xml");

		Driver startEngine = new Driver();
		startEngine.executeTestcase();
	}

	private void executeTestcase() throws Exception {
		String sheetname = Excelutils.getindexSheet();

		int iTotalTestCases = Excelutils.getRowCount(sheetname);

		for (int iTestcase = 1; iTestcase < iTotalTestCases; iTestcase++) {
			sTestCaseID = Excelutils.getCellData(iTestcase, Col_TestCaseID, sheetname);
			sRunMode = Excelutils.getCellData(iTestcase, Col_RunMode, sheetname);
			Testdata_sheet = Excelutils.getCellData(iTestcase, Col_datasheet, sheetname);
			Testcasedescription = Excelutils.getCellData(iTestcase, Col_testcasedescription, sheetname);
			// System.out.println("current test case name:"+Testcase);

			if (sRunMode.equals("yes")) {
				Log.startTestCase(sTestCaseID);

				String testsheet = sTestCaseID;
				iTestStep = Excelutils.getRowContains(sTestCaseID, Col_TestCaseID, testsheet);

				iTestLastStep = Excelutils.getTestStepsCount(testsheet, sTestCaseID, iTestStep);

				// System.out.println("iteststep :"+iTestStep+" "+"iTestLastStep
				// :"+iTestLastStep);

				for (; iTestStep < iTestLastStep; iTestStep++) {

					sActionKeyword = Excelutils.getCellData(iTestStep, Col_ActionKeyword, testsheet);
					sobject = Excelutils.getCellData(iTestStep, Col_Object, testsheet);

					sData = Excelutils.getCellData(iTestStep, Col_DataSet, testsheet);

					System.out.println("sData:" + sData);
					if (sData.isEmpty()) {
						Tdata = "";

					} else {

						Tdata = (String) ExcelTestdata.getcelldata(Constant.Path_Testdata, Testdata_sheet, sData);
					}

					System.out.println("keyword : " + sActionKeyword + " " + sobject + ", Tdata: " + Tdata);
					snap = Excelutils.getCellData(iTestStep, Col_Snap, testsheet);

					for (int i = 0; i < method.length; i++) {

						if (method[i].getName().equals(sActionKeyword)) {

							method[i].invoke(actionKeywords, sobject, Tdata, snap);

						} else {

						}
					}

				}
				Log.endTestCase(sTestCaseID);
			}
		}
	}

}
