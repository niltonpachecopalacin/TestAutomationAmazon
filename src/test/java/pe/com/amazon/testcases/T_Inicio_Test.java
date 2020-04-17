package pe.com.amazon.testcases;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * @author Nilton Pacheco
 */
public class T_Inicio_Test {
	
  public static String rutaDirTest = "";
  private static Logger logger = Logger.getLogger(T_Inicio_Test.class.getName());
  
  @Test
  public static void Inicio () {
	    logger = Logger.getLogger(T_Inicio_Test.class.getName());
		DateFormat df = new SimpleDateFormat("yyMMdd_HHmm");
		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);
		logger.info("=============================================================================================================================================================");
		logger.info("============================================================== EJECUCION "+reportDate+" =============================================================");
		logger.info("=============================================================================================================================================================");
		rutaDirTest  = "D:\\tmp\\SIAC_Test_"+reportDate;
	    File theDir = new File(rutaDirTest);      
		if (!theDir.exists()) {theDir.mkdir();}
	}
}