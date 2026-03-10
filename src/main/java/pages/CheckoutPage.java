package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.scrollClass;
import utils.waitClass;

public class CheckoutPage {
	
	WebDriver driver;
	waitClass wait;
	scrollClass scroll;
	CartPage cart;
	
	By checkOutBtn = By.xpath("//div[@class='container']//a[text()='Proceed To Checkout']");
	By totalAmount = By.xpath("//b[text()='Total Amount']/parent::h4/parent::td/following::td/p");
	By placeBtn = By.xpath("//a[@href='/payment']");
	
	By nameField = By.xpath("//input[@name='name_on_card']");
	By cardField= By.xpath("//input[@name='card_number']");
	By cvcField= By.xpath("//input[@name='cvc']");
	By expiryMonthField= By.xpath("//input[@name='expiry_month']");
	By expiryYearField= By.xpath("//input[@name='expiry_year']");
	By submitBtn = By.xpath("//button[@id='submit']");
	By successMsg = By.xpath("//p[starts-with(@style,'font-size')]");
	By registerDisplay = By.xpath("//div[@class='modal-footer']/button");
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new waitClass(driver);
		this.scroll = new scrollClass(driver);
		this.cart = new CartPage(driver);

	}

	public int checkOut() {
	 
		wait.clickForElementWait(checkOutBtn).click();
		
		WebElement Amount = wait.clickForElementWait(totalAmount);
		
			String productTotal = Amount.getText();
			String realPrice=productTotal.replace("Rs. ","").trim(); 
			
			int Total = Integer.parseInt(realPrice);
			WebElement placeOrderbtn = wait.clickForElementWait(placeBtn);
			scroll.scrollToElement(placeOrderbtn);
			placeOrderbtn.click();
			return Total;

	}
	
	public String fillPaymentForm() {
		wait.clickForElementWait(nameField).sendKeys("ritik");
		wait.clickForElementWait(cardField).sendKeys("32478927384");
		wait.clickForElementWait(cvcField).sendKeys("454");
		wait.clickForElementWait(expiryMonthField).sendKeys("05");
		wait.clickForElementWait(expiryYearField).sendKeys("2031");
		wait.clickForElementWait(submitBtn).click();
		String successPopUp = wait.visibilityOfElementWait(successMsg).getText();
		return successPopUp;
	}

}
