package pe.com.amazon.util;

import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author Nilton Pacheco
 */
public class DriverConnection
{
	private static final Logger logger = Logger.getLogger(DriverConnection.class.getName());
	public static WebDriver myDriver = null;

	public  static WebDriver getDriverConnection() 
	{
		
		try
		{
			ReadConfiguration readConfiguration = new ReadConfiguration();
			Properties properties = readConfiguration.getConfigurationProperties();
			
			String browser = properties.getProperty("driver.browser");
			String configuration = properties.getProperty("driver.configuration");
			
			if("local".equalsIgnoreCase(configuration))
			{
				myDriver = getBrowserLocal(browser);
			}
			else
			{
				myDriver = getBrowserRemote(browser);
			}
			
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myDriver;
	}

	private static WebDriver getBrowserRemote(String browser)
	{
		logger.info("Obteniendo browser :"+ browser + " en getBrowserRemote()" );
		
		WebDriver driver = null;
		
		try
		{
			ReadConfiguration readConfiguration = new ReadConfiguration();
			Properties properties = readConfiguration.getConfigurationProperties();
			String ipRemota = properties.getProperty("driver.ipremota");
			
			switch (browser.toLowerCase())	
			{
			case "safari":
				DesiredCapabilities dcs = DesiredCapabilities.safari();
				driver = new RemoteWebDriver(new URL(ipRemota), dcs);			              
				Thread.sleep(3000);  
				break;
			
			case "chrome":
				DesiredCapabilities dcc = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL(ipRemota), dcc);	
				Thread.sleep(2000);  
				break;
				
			case "firefox":
				DesiredCapabilities dcf = DesiredCapabilities.firefox();
				driver = new RemoteWebDriver(new URL(ipRemota), dcf);
				Thread.sleep(3000);
				break;
				
			case "iexplorer":
				DesiredCapabilities dci = DesiredCapabilities.internetExplorer();
				driver = new RemoteWebDriver(new URL(ipRemota), dci);
				Thread.sleep(3000);
				break;					
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver;
	}

	private static WebDriver getBrowserLocal(String browser)
	{
		logger.info("Obteniendo browser :"+ browser + " en getBrowserLocal()" );
		
		WebDriver driver = null;
		
		try
		{
			ReadConfiguration readConfiguration = new ReadConfiguration();
			Properties properties = readConfiguration.getConfigurationProperties();
			DesiredCapabilities capabilities = null;
			
			switch (browser.toLowerCase())	
			{
			case "safari":
		    	driver = new SafariDriver();			              
				Thread.sleep(3000);  
				break;
			
			case "chrome":
				System.setProperty("webdriver.chrome.driver", properties.getProperty("driver.chrome"));							  
				ChromeOptions options= new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				options.addArguments("--js-flags=--expose-gc");  
				options.addArguments("--enable-precise-memory-info"); 
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-default-apps");
				options.addArguments("test-type=browser");
				options.addArguments("disable-infobars");
				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);					  
				break;	
				
			case "firefox":
				System.setProperty("webdriver.gecko.driver", properties.getProperty("driver.firefox"));							  
				capabilities = DesiredCapabilities.firefox();
				FirefoxOptions options2 = new FirefoxOptions(); 
				options2.addArguments("--start-maximized"); 
				capabilities.setCapability(ChromeOptions.CAPABILITY, options2);						   
				driver = new FirefoxDriver(capabilities);						   
				break;
				
			case "iexplorer":
				System.setProperty("webdriver.ie.driver", properties.getProperty("driver.ie"));
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				Thread.sleep(3000);
				break;					
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver;
	}
	
}

