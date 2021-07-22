package config;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.sun.jna.platform.FileUtils;
import com.sun.jna.platform.FileUtils.*;

import utility.Log;


public class Actionkeywords {
	
		public static WebDriver driver;
		private static String browser;
		
		
		
	public static void openbrowser(String Object, String data, String Snap){	
		try{
		browser=data;
		 if(data.equals("InternetExplorer"))
		 {
			File f = new File("E:/selenium/jar files/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", f.getAbsolutePath());
			driver=new InternetExplorerDriver();
			Log.info(data+" Started </br>");
			}
		else if(data.equals("Chrome"))
		{
			File f = new File("E:/selenium/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
			driver=new ChromeDriver();
			Log.info(data+" Started</br>");
			}
		else if(data.equalsIgnoreCase("Firefox"))
		{
			
			System.out.println("opening");

			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			 driver = new FirefoxDriver(capabilities);
	        
			 Log.info(data+" Started</br>");
		   }
		driver.manage().window().maximize();
		getScreenshot(Snap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
 
	public static void Navigate(String object, String data, String Snap){	
		Log.info("Navigating to URL</br>");
		try{
			driver.get(data);
			Log.info("Navigating to URL : "+data+" <font color='green'>PASSED</font> </br>");
			
		}catch (Exception e) 
		{
			Log.error("Navigating to URL <font color='red'>Failed</font></br>");
			
		}		
		}
 
	public static void Click(String Object, String data, String snap) {
		
		String s1=Object;
		String[] words=s1.split(":");
		WebElement ele = null;
		try{
			 ele=(WebElement) element(words[0],words[1]);
			 getScreenshot(snap);
			 ele.click();
			 String Title = driver.getTitle();
				if(Title.equals("Certificate Error: Navigation Blocked")){
					driver.findElement(By.id("overridelink")).click();
				}
			 Log.info("Clicking on Webelement "+ Object+"<font color='green'>PASSED</font></br>");
			 
		}catch (Exception e) {
			Log.error("Clicking on the "+Object+" <font color='red'>Failed</font></br>");
			driver.close();
		}
		}
 
	public static void Enter(String Object, String data, String snap)
	{
		String s1=Object;
		String[] words=s1.split(":");
		WebElement ele = null;
		
		try{
		 ele=(WebElement) element(words[0],words[1]);
		 ele.sendKeys(data);
		 getScreenshot(snap);
		 Log.info("Entering the text in " + Object+" <font color='green'>PASSED</font></br>");
		}catch (Exception e) {
			
			Log.error("Entering the text in " + Object+" <font color='red'>Failed</font>"+"</br>");
			driver.close();
		}
		}
	
	
	public static void menuselect(String Object, String data, String snap){
		String s1=Object;
		String[] words=s1.split(":");
		try{
		 Actions action= new Actions(driver);
		 WebElement element = (WebElement) element(words[0],words[1]);
		action.moveToElement(element).build().perform();
		getScreenshot(snap);
		
		}catch (Exception e) {
			Log.error("menuselection " + Object+" <font color='red'>Failed</font> "+"</br>");
			driver.close();
			
		}
		
		//java.util.List<WebElement> links = driver.findElements(By.tagName("a"));

		//System.out.println(links.size());

		//for (int i = 0; i<=links.size()-1; i=i+1)

		//{

		//	System.out.println("link "+links.get(i).getText());
		//}
	 }
	public static void dropdownselect(String Object, Object data, String snap){
		String s1=Object;
		String[] words=s1.split(":");
		try{
		 WebElement element = (WebElement) element(words[0],words[1]);
		Select dpdown= new Select(element);
		
		dpdown.selectByValue((String) data);
		getScreenshot(snap);
		Log.info("dropdown selection"+Object+ " <font color='green'>PASSED</font></br>");
	} catch (Exception e) {
		Log.error("dropdown selection :" + data+"<font color='red'>Failed</font> "+"</br>");
		driver.close();
		
	}
}
	public static void verifycolor(String Object, String data, String snap) throws InterruptedException, IOException{     

        String s1=Object;
        String[] words=s1.split(":");
        WebElement ele=(WebElement) element(words[0],words[1]);
        
        String colorString = ele.getCssValue("color");
        getScreenshot(snap);
        System.out.println(colorString);


        if (browser.equalsIgnoreCase("chrome")||browser.equalsIgnoreCase("InternetExplorer")){
                                   if (colorString.equals(data))
                                          {
                                	             Log.info(colorString+" color matches : "+data+" <font color='green'>PASSED</font> </br>");
                                                // System.out.println("Color Matches");
                                          }
               else
                                          {
            	                                Log.info("color not matching : "+data+" <font color='red'>Failed</font> </br>");
                                                // System.out.println("Color is not Matched");
                                          }}                           
        else if(browser.equalsIgnoreCase("firefox")){
                                   String t1 = colorString.replace("rgb(", "rgba(");
                                   String t2 = t1.replace(")", ", 1)");
                                System.out.println("input"+" "+colorString+" "+t2);
                                  if (t2.equals(data))
                                          {
                                	             Log.info("color matches : "+data+" <font color='green'>PASSED</font> </br>");
                                               //  System.out.println("Color Matches");
                                          }
             else
                                          {
            	                         Log.info("color not matching : "+data+" <font color='red'>Failed</font> </br>");
                                        // System.out.println("Color is not Matched");
                                          }}

               }
	public void verifyimage(String Object, String data, String snap) {
		String s1=Object;
		String[] words=s1.split(":");
		 WebElement imagelist =(WebElement) element(words[0],words[1]);
		//imagelist.size();
		//imagelist.contains(Object);
		 //System.out.println(driver.findElements(By.tagName("img")));
		//(Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof"
 		//+ " arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imagelist);
		//System.out.println(imagelist.isDisplayed());
	    Boolean ImagePresent = imagelist.isDisplayed();
        if (!ImagePresent)
        {
        	 Log.info("image is displayed"+"  <font color='red'>Failed</font> </br>");
             System.out.println("Image not displayed.");
        }
        else
        {
        	 Log.info("image is displayed"+"<font color='green'>PASSED</font> </br>");
            System.out.println("Image displayed.");
        }		
	}
	
private static Object element(String word1, String word2) {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 WebElement elem = null;
try{	  
	if (word1.equalsIgnoreCase("id")) 
		{
		  
			elem = driver.findElement(By.id(word2));
			
		} 
		else if (word1.equalsIgnoreCase("name"))
		{
			
			elem =driver.findElement(By.name(word2));
			
		} 
		else if (word1.equalsIgnoreCase("xpath"))
		{
			
			elem = driver.findElement(By.xpath(word2));
			
		} 
		else if (word1.equalsIgnoreCase("link")) 
		{  
			elem = driver.findElement(By.linkText(word2));
			
		}
		else if (word1.equalsIgnoreCase("partialLink")) 
		{   
			elem = driver.findElement(By.partialLinkText(word2));
			
		}
		else if (word1.equalsIgnoreCase("css"))
		{ 
			elem = driver.findElement(By.cssSelector(word2));
			
		}
}catch (NoSuchElementException e) {
	
	Log.error("finding element :" + word2+" <font color='red'>Failed</font>"+"</br>");
	driver.close();
	// TODO: handle exception
}
	return elem;
}

 void verifylinks(String Object, String data, String Snap) {
	java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
	String s1=Object;
	String[] words=s1.split(":");
	 WebElement link =(WebElement) element(words[0],words[1]);
	 boolean linkpresent = link.isDisplayed();
	 if (!linkpresent)
     {
     	 Log.info("link is not displayed"+"  <font color='red'>Failed</font> </br>");
          System.out.println("link not displayed.");
     }
     else
     {
     	 Log.info("link is displayed"+"<font color='green'>PASSED</font> </br>");
         System.out.println("link displayed.");
     }
	for (int i = 0; i<=links.size(); i=i+1)

	{

		System.out.println("link "+links.get(i).getText());
	}
}
	

	private static void getScreenshot(String snap) throws IOException, InterruptedException {
		String imagename = driver.getTitle();
		System.out.println("taking screenshot "+ snap);
		if((snap.trim()).equalsIgnoreCase("yes")){
		Thread.sleep(1000);
		@SuppressWarnings("unused")
		File image=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("MM-dd-yyyy-hh");
		SimpleDateFormat formater1 = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss");
		String imagefolder=Constant.ScreenShotpath+"/"+formater.format(calendar.getTime());
		
		File destFile= new File(imagefolder);
		destFile.mkdirs();
	
		String ActualImagepath=imagefolder+"/"+imagename+formater1.format(calendar.getTime())+" "+".JPEG";
		@SuppressWarnings("unused")
		File destFilesub= new File(ActualImagepath);
	//	FileUtils.copyFile(image, destFilesub);
		}
			
	}

	}
