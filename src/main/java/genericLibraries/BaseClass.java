package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.PageObjectManager;

//This is BaseClass
public class BaseClass {

	//@BeforeSuite
	//@BeforeTest
	
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected WebDriverUtility web;
	protected JavaUtility jutil;
	
	protected WebDriver driver;
	protected SoftAssert soft;
	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	protected LoginPage login;
	protected HomePage home;

	protected PageObjectManager pageObjects;
	
	@BeforeClass
	public void classSetup() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		web = new WebDriverUtility();
		jutil = new JavaUtility();
		
		sjutil = jutil;
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		driver = web.launchBrowserAndMaximize(property.readFromProperties("browser"));
		
		sdriver = driver;
		web.waitTillElementFound(Long.parseLong(property.readFromProperties("timeouts")));
	}
	
	@BeforeMethod
	public void methodSetup() {

		pageObjects = new PageObjectManager(driver);
		soft = new SoftAssert();
		
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		login = pageObjects.getLoginPage();
		home = pageObjects.getHomePage();
		
		web.navigateToApp(property.readFromProperties("url"));
		Assert.assertEquals(login.getPageHeader(), "vtiger");
		login.loginToVtiger(property.readFromProperties("username"),
							property.readFromProperties("password"));
		
		Assert.assertTrue(home.getPageHeader().contains("Home"));
	}
	
	@AfterMethod
	public void methodTeardown() {
		home.signOutOfApp(web);
		excel.closeExcel();
	}
	
	@AfterClass
	public void classTeardown() {
		web.quitAllWindows();
	}
	
	//@AfterTest
	//@AfterSuite
}
