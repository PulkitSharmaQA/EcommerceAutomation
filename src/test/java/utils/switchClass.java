package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class switchClass {
	WebDriver driver;
	
	public void switchIntoPages() {
		String parentWindow = driver.getWindowHandle();
		
		Set<String> chilsWindow  = driver.getWindowHandles();
		for(String window : chilsWindow ) {
			if(!window.equals(parentWindow)) {
				driver.switchTo().window(window);
			}
		}
	
	}
}
