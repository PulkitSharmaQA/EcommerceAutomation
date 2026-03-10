package utils;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.DriverManager;

public class SmartActions {
	
	 public static void smartClick(WebElement element) {

	        int attempts = 0;

	        while (attempts < 3) {

	            try {

	                WebDriverWait wait = new WebDriverWait(
	                        DriverManager.getDriver(),
	                        Duration.ofSeconds(10)
	                );

	                wait.until(ExpectedConditions.elementToBeClickable(element));

	                scrollToElement(element);

	                element.click();

	                return;

	            }

	            catch (ElementClickInterceptedException e) {

	                System.out.println("Click intercepted, trying JS click");

	                jsClick(element);

	                return;

	            }

	            catch (StaleElementReferenceException e) {

	                System.out.println("Stale element, retrying...");

	            }

	            catch (Exception e) {

	                jsClick(element);

	                return;

	            }

	            attempts++;

	        }

	    }

	    public static void scrollToElement(WebElement element) {

	        JavascriptExecutor js =
	                (JavascriptExecutor) DriverManager.getDriver();

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                element
	        );

	    }

	    public static void jsClick(WebElement element) {

	        JavascriptExecutor js =
	                (JavascriptExecutor) DriverManager.getDriver();

	        js.executeScript("arguments[0].click();", element);

	    }

	}


