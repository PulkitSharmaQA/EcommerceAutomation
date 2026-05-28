package hooks;

import Base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    @Before
    public void setUp() {

        WebDriver driver = new ChromeDriver();

        DriverManager.setDriver(driver);

        DriverManager.getDriver()
                .get("https://automationexercise.com/");
    }

    @After
    public void tearDown() {

        DriverManager.getDriver().quit();

        DriverManager.removeDriver();
    }
}