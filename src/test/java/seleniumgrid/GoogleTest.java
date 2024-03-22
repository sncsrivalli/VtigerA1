package seleniumgrid;

import org.testng.annotations.Test;

public class GoogleTest extends BaseClass {

	@Test
	public void googleTest() throws InterruptedException {
		driver.get("https://www.google.com/");
		Thread.sleep(3000);
	}
}
