package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.scrollClass;
import utils.waitClass;

public class HomePage {
	WebDriver driver;
	waitClass wait;
	scrollClass scroll;
	
	By dismissBtn = By.xpath("//div[@class='close-button']"); 
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new waitClass(driver);
		this.scroll = new scrollClass(driver);
	}
	
	
	public void handleAdIfPresent() {
	    try {
	        List<WebElement> ads = driver.findElements(dismissBtn);

	        if (ads.size() > 0 && ads.get(0).isDisplayed()) {
	            wait.clickForElementWait(dismissBtn).click();
	            System.out.println("Ad closed");
	        }

	    } catch (Exception e) {
	        System.out.println("Ad not present");
	    }
	}

}
