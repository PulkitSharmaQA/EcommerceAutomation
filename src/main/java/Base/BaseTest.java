package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import config.configReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;

public class BaseTest {

	public WebDriver driver;
	public configReader configData;

	
	
	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String br) throws IOException {
		configData = new configReader();
		configData.readFile();
		String url = configData.getUrl();
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		}

		// driver.manage().deleteAllCookies();
		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().get(url);

	}

	@AfterMethod
	public void tearDown() {
		DriverManager.getDriver().quit();
		DriverManager.removeDriver();
	}

}
