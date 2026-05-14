package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class frameClass {
	

	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public frameClass(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	
	public void swtichToFrame(By webElement) {
		WebElement frame = (WebElement) wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
	}
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

}
