package pe.com.amazon.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import pe.com.amazon.util.ReadConfiguration;

public class HomePage {
	public WebDriver drive;
	ReadConfiguration readConfiguration = new ReadConfiguration();
	
	public HomePage(WebDriver webDriver)
	{
		
	}
	
	
	@FindBy(xpath = "//*[@id=\\'nav-search\\']/form/div[2]/div/input")
	WebElement Find;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	public Wait<WebDriver> esperaControl(WebDriver driver){
		 Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
	            .pollingEvery(2, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);	
		 return wait;
	}
	
	
	public void clickByXpath(String objXpath){
		Wait<WebDriver> wait = esperaControl(drive);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objXpath)));
		new Actions(drive).moveToElement(element).perform();
		element.click();	
	}
	
	public void clickFind(){
		Wait<WebDriver> wait = esperaControl(drive);
		new Actions(drive).moveToElement(Find).perform();
		Find.click();	
	}
	
	
}