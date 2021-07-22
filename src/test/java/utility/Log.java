package utility;
 
import org.apache.log4j.Logger;
import org.apache.xpath.operations.String;
 
 public class Log {
 

 
	 private static Logger Log = Logger.getLogger(Log.class.getName());//
 

 
 public static void startTestCase(java.lang.String sTestCaseID){
 
	
 
	Log.info("<html><body><h1  style='background-color:powderblue;'>"
			
			+ "<TR><TD BGCOLOR=#66699 WIDTH=27%><B>Test Case Name:"
					+"<head>"+ sTestCaseID + "</head></B></TD></TR></h1></p> ");
 
	
 
	
 
	}
 
	
 
 public static void endTestCase(java.lang.String sTestCaseID){
 
	Log.info("XX"+"-E---N---D-"+"X</br></body></html>");
 

	}
 
	
 
 public static void info(java.lang.String string) {
 
		Log.info(string);
 
		}
 
 public static void warn(java.lang.String string) {
 
    Log.warn(string);
 
	}
 
 public static void error(java.lang.String string) {
 
    Log.error(string);
 
	}
 
 public static void fatal(String message) {
 
    Log.fatal(message);
 
	}
 
 public static void debug(String message) {
 
    Log.debug(message);
 
	}
 
}
