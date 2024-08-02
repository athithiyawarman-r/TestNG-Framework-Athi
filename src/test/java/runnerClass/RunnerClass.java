package runnerClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import stepDefinitions.SwagLabs;
import extentReport.ExtentReportManager;
import org.openqa.selenium.support.FindBy;



public class RunnerClass {
	WebDriver driver;
	Duration timeoutSeconds; //Wait Timer
	private SwagLabs SLabs = this.SLabs;
	
	@FindBy(id = "user-name")
    private WebElement userNameField;
	
	@FindBy(id = "password")
    private WebElement passwordField;
	
	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(className ="inventory_item_name")
	private List<WebElement> inventoryItems;
	
	@FindBy(className ="inventory_item_price")
	private List<WebElement> inventoryItemPrice;
	
	@FindBy(xpath ="//button[text()='ADD TO CART']")
	private List<WebElement> addToCartButton;
	
	@FindBy(xpath ="//button[text()='REMOVE']")
	private List<WebElement> removeButton;
	
	@FindBy(id ="shopping_cart_container")
	private WebElement shoppingCart;
	
	@FindBy(className ="btn_secondary")
	private WebElement ContinueShoppingButton;
	
	@FindBy(linkText ="CHECKOUT")
	private WebElement CheckoutButton;
	
	@FindBy(id ="first-name")
	private WebElement FirstName;
	
	@FindBy(id ="last-name")
	private WebElement LastName;
	
	@FindBy(id ="postal-code")
	private WebElement ZipCode;
	
	@FindBy(xpath ="//input[@value='CONTINUE']")
	private WebElement continueButton;
	
	@FindBy(linkText ="FINISH")
	private WebElement finishButton;
	
	@FindBy(className ="complete-header")
	private WebElement CompleteOrder;
	
	@FindBy(className ="summary_subtotal_label")
	private WebElement CartTotalValue;
	
	
	
	@BeforeSuite
	public void browserProperties() throws FileNotFoundException, IOException {
		SLabs = new SwagLabs();
		SLabs.LoadProperties();
		timeoutSeconds=Duration.ofSeconds(Integer.parseInt(SLabs.Timeout)); //Wait Timer
		String BrowserName=SLabs.Browser;
		driver = SLabs.browserLaunch(BrowserName);
		PageFactory.initElements(driver, this);
	}
	
    @BeforeClass
    public void setupClass() {
        ExtentReportManager.createParentTest("Swag Labs - Athi", "Class Level Reporting");
    }

    @BeforeMethod
    public void setupMethod(Method method) {
        ExtentReportManager.createTestNode(method);
    }

    @AfterMethod
    public void reportTestResult(ITestResult result) {
        ExtentReportManager.logTestResult(result);
    }
	
	
		@Test (priority=1)
	    public void LaunchUrl() throws InterruptedException
	    {
		driver.get(SLabs.URLs);
	    }
		
		@Test (priority=2)
		@Parameters({"UserName","Password"})  
	    public void LoginPage(String UserName, String Password)
	    {
			userNameField.sendKeys(UserName);
			passwordField.sendKeys(Password);
			loginButton.click();
			SLabs.WaitUntilLoadState(driver,timeoutSeconds);
	    }
		
	
		@Test (priority=3)
	    public void AddingAllItemsToCart()
	    {
			int sizeOfInventory = inventoryItems.size();
			for(int i=sizeOfInventory-1; i>=0;i--) {
				addToCartButton.get(i).click();
			}
			shoppingCart.click();
			Assert.assertTrue(removeButton.size()==sizeOfInventory);
	    }
		
		@Test (priority=4)
	    public void removingAllItemsFromCart()
	    {
			int sizeOfCart = removeButton.size();
			System.out.println(sizeOfCart);
			for(int i=sizeOfCart-1; i>=0;i--) {
				removeButton.get(i).click();
			}
			Assert.assertTrue(removeButton.size()==0);
			ContinueShoppingButton.click();
			Assert.assertTrue(addToCartButton.size()==sizeOfCart);
	    }
		
		@Test (priority=5)
		@Parameters({"FirstName","LastName","Zipcode"}) 
	    public void checkOut(String First_Name,String Last_Name,String Zip_code)
	    {
			AddingAllItemsToCart();
			
			//Get the value of cart items
			float sum=0;
			for(int i =inventoryItemPrice.size()-1;i>=0;i--) {
				String currentProductPrice = inventoryItemPrice.get(i).getText().replace("$", "");
				sum=sum+Float.parseFloat(currentProductPrice);
			}
			System.out.println(sum);
			
			//Validating all the items were added to the cart
			int sizeOfInventory = inventoryItems.size();
			shoppingCart.click();
			Assert.assertTrue(removeButton.size()==sizeOfInventory);
			CheckoutButton.click();
			
			//Filling out the form
			FirstName.sendKeys(First_Name);
			LastName.sendKeys(Last_Name);
			ZipCode.sendKeys(Zip_code);
			continueButton.click();
			
			//Validating with the actual rate
			float ActualRate = Float.parseFloat(CartTotalValue.getText().replace("Item total: $", ""));
			Assert.assertTrue(ActualRate==sum);
			
			finishButton.click();
			
			//Validating the order is placed
			Assert.assertTrue(CompleteOrder.getText().equals("THANK YOU FOR YOUR ORDER"));
			
	    }

	
	@AfterSuite
	public void close() throws IOException
    {
	 System.out.println(SLabs.close());
	 driver.quit();
	 File htmlFile = new File("TestReport/extent.html");
	 Desktop.getDesktop().browse(htmlFile.toURI());
    }
}
