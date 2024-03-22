package seleniumgrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	protected WebDriver driver;
	
	@Parameters("Browser")
	@BeforeClass
	public void classSetup(String browser) throws MalformedURLException {
		URL url = new URL("http://192.168.0.69:5555/wd/hub");
		DesiredCapabilities cap = new DesiredCapabilities();
			
		switch(browser) {
		case "chrome":
			cap.setBrowserName("chrome");
			break;
		case "firefox":
			cap.setBrowserName("firefox");
			break;
		default:
			System.out.println("Invalid");
		}
		cap.setPlatform(Platform.WINDOWS);
		driver = new RemoteWebDriver(url, cap);
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void classTeardown() {
		driver.close();
	}
}
