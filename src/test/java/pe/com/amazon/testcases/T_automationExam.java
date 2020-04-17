package pe.com.amazon.testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import pe.com.amazon.pageObjects.HomePage;
import pe.com.amazon.util.DriverConnection;
import pe.com.amazon.util.ElementOperations;
import pe.com.amazon.util.ExcelUtils;
import pe.com.amazon.util.ReadConfiguration;

/**
 * @author Nilton Pacheco
 */

public class T_automationExam{

	private WebDriver webDriver = null;
	private File scrFile = null;
	private static Logger logger = Logger.getLogger(T_automationExam.class.getName());
	private static String url = "";  
	private static ElementOperations eO = new ElementOperations();
	private ATUTestRecorder recorder;
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
    ReadConfiguration readConfiguration = new ReadConfiguration();
    
	@BeforeTest
    public void Configuracion() throws IOException, ATUTestRecorderException{
		//public void Configuracion() throws IOException{
			try {	   
			Properties properties = readConfiguration.getConfigurationProperties();	
			url = properties.getProperty("driver.mainurl");
			T_Inicio_Test.Inicio();	

	        String log4jConfigFile = System.getProperty("user.dir")
                        + "\\src\\main\\resources" + File.separator + "log4j.properties"; 	       
            PropertyConfigurator.configure(log4jConfigFile);
            logger.info("Cargar la configuracion...");
            webDriver = DriverConnection.getDriverConnection();
            eO.drive = webDriver;
						
			logger.info("Abrir la url: "+url);
			webDriver.get(url);
			} catch (SecurityException e) {logger.error(e.getMessage());}
     }
	
	@BeforeMethod
	public void iniciarTest(Method method) throws Exception  {
		logger.info("============ INICIO: " + method.getName()  + " ============");
	}

	@Test(priority=1)
	public void Ingreso() {
		  
		 try {	 
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
	     Date date = new Date();
		 recorder = new ATUTestRecorder("D:\\tmp\\","TestVideo-"+dateFormat.format(date),false);

		 recorder.start(); 

		} catch (Exception e) {
			logger.info(e.getMessage());
			Assert.fail();}
	    }
	@Test(priority=2,dataProvider = "dataCliente")
	public void cargaDataExcel( 
			String Producto,	
			String Idioma	
			) throws IOException, ATUTestRecorderException{ 
	 
		 try {
			 String cantResul,mensajeAlerta,mensaje;
			 double price[] = new double[5];
			 String price_s[] = new String[5];
			 Thread.sleep(1000);
			 HomePage h = new HomePage(webDriver);
			 
			 eO.setText("twotabsearchtextbox", Producto, logger);			 
			 //h.clickFind();			 
			 eO.clickByXpath("//*[@id=\'nav-search\']/form/div[2]/div/input");
			 
			 
			 eO.clickByXpath("//*[@id=\'icp-nav-flyout\']/span");
			 eO.clickByXpath("//*[@id=\'customer-preferences\']/div/div/form/div[1]/div[1]/div[2]/div/label/span");
			 eO.clickById("icp-btn-save");
			 Thread.sleep(7000);
			 eO.clickByXpath("//*[@id=\'search\']/div[1]/div[1]/div/span[4]/div[1]/div[1]/div/span/div/div/span/a/div");
			 Thread.sleep(1000);
			 eO.clickByXpath("//*[@id=\'dropdown_selected_size_name\']/span/span");
			 Thread.sleep(1000);
			 eO.clickByXpath("//*[@id=\'native_dropdown_selected_size_name_2\']");
			 Thread.sleep(2000);
			 eO.clickById("add-to-cart-button-ubb");
			 Thread.sleep(1000);
			 mensaje = eO.obtValorById("hlb-view-cart");
			 recorder.stop();
		 } catch (SecurityException | InterruptedException e) {logger.error(e.getMessage());}
		 
    }
	@AfterMethod
	public void printResult(ITestResult result) throws IOException, InterruptedException {	
		 String testMethod = result.getMethod().getMethodName();
		 if (result.getStatus()==result.SUCCESS){
				logger.info("============ FINAL: " + testMethod  + " ============" + " => PASS");
		 }else{
				logger.info("============ FINAL: " +testMethod  + " ============" + " => FAIL");
		 }
	 } 
	
	@AfterTest
	public void End()
	
	 {
	 if (webDriver != null) {webDriver.quit();}
	 
	 }
	

	@DataProvider 
	public Object[][] dataCliente() throws Exception{	  
		Properties properties = readConfiguration.getConfigurationProperties();
	    Object[][] testObjArray = ExcelUtils.getTableArray(properties.getProperty("driver.dataProviderDir"),"VALIDA-PRODUCTO",2); 
		    	return (testObjArray);	 
	}	
	
}
