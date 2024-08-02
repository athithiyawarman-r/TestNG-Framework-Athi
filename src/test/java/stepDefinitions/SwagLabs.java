package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SwagLabs {
	
	private static WebDriver driver;
	private static long startTime;
	public static String Timeout;
	private static Duration timeoutSeconds;//Wait Timer
	private static String BrowserType="";
	private static String driverExecutables="";
	public static String URLs;
	public static String Browser;
	public static String FirstName;
	public static String LastName;
	public static String Zipcode;	
	
	private static String basePath = System.getProperty("user.dir");
	
	public WebDriver browserLaunch(String BrowserName){
		startTime = System.currentTimeMillis();//StartTime of Test Execution
		
		switch(BrowserName) {
		case "Google":
			BrowserType=basePath+"/src/main/resources/chromedriver.exe";
			driverExecutables="webdriver.chromer.driver";
			driver= new ChromeDriver();
			break;
		case "Edge":
			BrowserType=basePath+"src/main/resources/msedgedriver.exe";
			driverExecutables="webdriver.edge.driver";
			driver= new EdgeDriver();
			break;
		case "Firefox":
			BrowserType=basePath+"/src/main/resources/geckodriver.exe";
			driverExecutables="webdriver.gecko.driver";
			driver= new FirefoxDriver();
			break;
		default:
			System.out.println("Enter proper driverBrowser");
			break;
		}
		
		System.setProperty(BrowserType, driverExecutables);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	public static void LoadProperties() throws IOException, FileNotFoundException {
		
		FileReader reader = new FileReader(basePath+"/config/config.properties");
		Properties properties = new Properties();
		properties.load(reader);
		
		URLs = properties.getProperty("URLs");
		Browser = properties.getProperty("Browser");
		Timeout=properties.getProperty("Timeout");
		
	}
	
	public static void WaitUntilElementIsVisible(WebDriver driver,Duration timeoutSeconds,String Element) {
		WebDriverWait wait=new WebDriverWait(driver,timeoutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(Element))));
	}
	
	public static void WaitUntilLoadState(WebDriver driver,Duration timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds); 
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));
	}
	
	public static long close() {
		long endTime= System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Total Time Taken: "+(totalTime/1000)+" Seconds");
		return totalTime;
		
	}
	
	
}