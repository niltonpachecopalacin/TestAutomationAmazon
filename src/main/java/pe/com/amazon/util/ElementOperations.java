package pe.com.amazon.util;


import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.acl.Owner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.*;
import java.util.List;


import com.gargoylesoftware.htmlunit.javascript.host.file.File;

/**
 * @author Nilton Pacheco
 */
public class ElementOperations {
	
	public WebDriver drive;

    ReadConfiguration readConfiguration = new ReadConfiguration();

	public void setText(String objId, String value, Logger logger){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		element.clear();
		element.sendKeys(value);	
		logger.info("Ingresar el texto: " +value+ " en el objeto: " +objId);
	}
	public void setFocus(String objId,Logger logger){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		logger.info("Pone focus en Objeto: " +objId);
	}
	public void setTextbyPath(String objXpath, String value, Logger logger){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objXpath)));
		new Actions(drive).moveToElement(element).perform();
		element.sendKeys(value);		
		logger.info("Ingresar el texto: " +value+ " en el objeto: " +objXpath);
	}
	
	public void clearText(String objId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		element.clear();
	}
	
	public void selectDialogModal(){
		drive.findElement(By.xpath("//button[contains(text(),'Acepto')]")).click();
	}
	
	public void selectDialogModal(String objOpcion){
		drive.findElement(By.xpath("//button[contains(text(),'"+objOpcion+"')]")).click();
	}
	
	public void selectText(String objId, String valor, Logger logger){
		Select select = new Select(drive.findElement(By.id(objId)));
		select.selectByVisibleText(valor);	
		logger.info("Seleccionar el texto: " +valor+ " en el objeto: " +objId);
	}
	
	public void selectCheck(String objId, String option, Logger logger){
		String Value;
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		  List<WebElement> Checkbox = drive.findElements(By.id(objId));
		 
		for(int i=0;i<=Checkbox.size();i++){
		    Value = Checkbox.get(i).getAttribute(option);
		    if(Value.equalsIgnoreCase(option))
		      Checkbox.get(i).click();
		  }
		logger.info("Se hace click en el objeto: " +objId);
	}
	
	
	
	
	  
	public void selectText2(String objId, String valor, Logger logger){
		Select select = new Select(drive.findElement(By.id(objId)));
		select.selectByValue(valor);	
		logger.info("Seleccionar el texto: " +valor+ " en el objeto: " +objId);
	}
	
	public String getSelectedText(String objId, Logger logger){
		Select select = new Select(drive.findElement(By.id(objId)));
		logger.info("Se obtuvo el valor del campo con ID " + objId);
		return select.getFirstSelectedOption().getText();
	}
	
	public void selectOption(String objId, Logger logger){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		element.click();
		logger.info("Se hace click en el objeto: " +objId);
	}
	
	public void clickByXpath(String objXpath){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objXpath)));
		new Actions(drive).moveToElement(element).perform();
		element.click();	
	}

	public void clickById(String objId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		element.click();
	}
	
	public boolean Isenabled(String objId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		return element.isEnabled();
	}
	
	public boolean IsSelect(String objId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
		new Actions(drive).moveToElement(element).perform();
		return element.isSelected();
	}

	public String obtValorByXpath(String objectXpath){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectXpath)));
		new Actions(drive).moveToElement(element).perform();
		return element.getText();
	}
	
	public String obtValorById(String objectId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectId)));
		new Actions(drive).moveToElement(element).perform();
		return element.getText();
	}
	
	public String obtValueControlX(String objectXpath){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectXpath)));
		new Actions(drive).moveToElement(element).perform();
		return element.getAttribute("value");
	}
	
	public String obtValueControlI(String objectId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectId)));
		new Actions(drive).moveToElement(element).perform();
		return element.getAttribute("value");
	}
	
	public String obtenerNameControl(String objectXpath){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectXpath)));
		new Actions(drive).moveToElement(element).perform();
		return element.getAttribute("name");
	}
	
	public boolean estadoControl(String objXpath){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objXpath)));
		new Actions(drive).moveToElement(element).perform();
		return element.isEnabled();	
	}
	
	public boolean obtenerControlActivado(String objXpath){
		try {
			Wait<WebDriver> wait = esperaControl(drive);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objXpath)));
			new Actions(drive).moveToElement(element).perform();
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		} 	
	}
	
	public boolean obtenerControlActivadoId(String objId){
		try {
			Wait<WebDriver> wait = esperaControl(drive);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objId)));
			new Actions(drive).moveToElement(element).perform();
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		} 	
	}
	
	 public static String obtenerFecha(){
			DateFormat df = new SimpleDateFormat("yyMMdd_HHmmss");
			Date today = Calendar.getInstance().getTime();
			return df.format(today);  
	 }

	public void logonRed(String objusr, String objpwd, Logger logger)	{
		Properties properties;
		try {
			properties = readConfiguration.getConfigurationProperties();
			setText(objusr,properties.getProperty("driver.userlogin"),logger);
			setText(objpwd,properties.getProperty("driver.pwdlogin"),logger);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public String nombreExtTipoDoc(String codigo){
		switch (codigo) {
		case "LIBRETA ELECTORAL O DNI": return "DNI" ; 
		case "PART. DE NACIMIENTO-IDENTIDAD": return "PNI" ; 
		case "CARNET DE EXTRANJERIA": return "CARNET EXT." ; 
		case "Pasaporte": return "PASAPORTE" ;
		case "REG. UNICO DE CONTRIBUYENTES": return "RUC" ; 
		case "OTROS": return "OTROS" ; 
		default: return "NA"; 
		}
	}

	public String nombreCorTipoDoc2(String codigo){
		switch (codigo) {
		case "DNI": return "Libreta Electoral o DNI"; 
		case "PNI": return "PART. DE NACIMIENTO-IDENTIDAD"; 
		case "CEX": return "CARNET DE EXTRANJERIA"; 
		case "PASAPORTE": return "Pasaporte"; 
		case "RUC": return "REG. UNICO DE CONTRIBUYENTES"; 
		case "OTROS": return "OTROS" ; 
		default: return "NA"; 
		}
	}
	
	public Wait<WebDriver> esperaControl(WebDriver driver){
		 Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
	            .pollingEvery(2, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);	
		 return wait;
	}
	
	public Integer upfile(String Path, Logger logger)	throws Exception
	{

		try{
			Robot robot = new Robot();		
			Wait<WebDriver> wait = esperaControl(drive);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Path)));
			new Actions(drive).moveToElement(element).perform();
			element.click();
			robot.setAutoDelay(2000);
			
			StringSelection StriSe = new StringSelection("C:\\Zum\\COTIZACION\\PDF_PRUEBA_GPS.pdf");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StriSe, null);
			
			robot.setAutoDelay(1000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			
			robot.setAutoDelay(1000);
			
			robot.keyPress(KeyEvent.VK_ENTER);		
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			robot.setAutoDelay(1500);
		
			logger.info("Se adjunto archivo en el objeto/path: " + Path);
			return 1;
		}catch(Exception error){
			logger.info("<<Error>> No se adjunto archivo en el objeto/path: " + Path);
			return 0;
		}
		

	}
	
	public String selectComboValue(String elementId) 
	{
	    Select selectBox = new Select(drive.findElement(By.id(elementId)));
	    return selectBox.getFirstSelectedOption().getText();
	}
	
	public String obtAtribuById(String objectId){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectId)));
		new Actions(drive).moveToElement(element).perform();
		return element.getAttribute("value");
	}
	
	public Integer obtValorClass(String objectClass){
		
		Integer valor=1;		
		try
		{	Wait<WebDriver> wait = esperaControl(drive);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(objectClass)));
			new Actions(drive).moveToElement(element).perform();			
			return valor;
		}catch (Exception e)
		{			
			valor=0;
			return valor;
		}
	}
	
public Boolean clickGrilla(String objXpath, Logger logger){
		
		
		Wait<WebDriver> wait = esperaControl(drive);
		
		try
		{
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objXpath)));			
			new Actions(drive).moveToElement(element).perform();		
			
			if(element.isEnabled())
			{
				
				element.click();	
				logger.info("Se hace click en el Objeto/path: | " + objXpath + " | de la grilla");
				return true;
			}else
				{
					logger.info("<<Error>> No se puede hacer clic en el Objeto/path: | " + objXpath + " |" + " se encuentra inhabilitado");
					return false;
				}
		}
		catch (Exception e)
			{
				logger.info("<<Error>> No se encontr� el Objeto/path: | " + objXpath + " |");
				return false;
			}
		
	}	


public void clickByClass(String objClass){
Wait<WebDriver> wait = esperaControl(drive);
WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(objClass)));
new Actions(drive).moveToElement(element).perform();
element.click();
}
public boolean obtenerControlActivadoClass(String objClass){
	try {
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objClass)));
		new Actions(drive).moveToElement(element).perform();
		return element.isDisplayed();
	} catch (Exception e) {
		return false;
	} 	
}

public void Clicklinktext(String objlink){
	Wait<WebDriver> wait = esperaControl(drive);
	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objlink)));
	new Actions(drive).moveToElement(element).perform();
	element.click();	
}
	

public String linktext(String objlink, Logger logger){
	Wait<WebDriver> wait = esperaControl(drive);
	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objlink)));
	new Actions(drive).moveToElement(element).perform();
	logger.info("link " +element.getAttribute("href")+ " en el objeto: " +objlink);
	return element.getAttribute("href");	
}

public boolean validaExisteObjeto(String objXpath, Logger logger){
try {
	drive.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
	drive.findElement(By.xpath(objXpath));
	drive.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	logger.info("Objeto " +objXpath + "encontrado");
	return true;
	} catch (NoSuchElementException e) {
	logger.info("Objeto " +objXpath + "NO encontrado");	
	return false;
	}
}

}
