package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class scrollClass {
	
	public WebDriver driver;
	public JavascriptExecutor js;
	
	public scrollClass(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
		
	}
	
	public void scrollToElement(WebElement elemnt) {
		js.executeScript("arguments[0].scrollIntoView(true);", elemnt);
		
	}
	public void clickOnElement(WebElement elemnt) {
		js.executeScript("arguments[0].click();", elemnt);
	}

}
