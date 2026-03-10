package pages;

import java.util.List;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.SmartActions;
import utils.scrollClass;
import utils.waitClass;

public class loginValidation {
	
	WebDriver driver;
	waitClass wait;
	scrollClass scroll;
	
	
	By loginBtn = By.xpath("//a[@href ='/login']");
	By inputNameFeild = By.xpath("//input[@Name = 'name']");
	By inputEmailFeild = By.xpath("//form[@action= '/signup']/input[@type='email']");
	By submitBtn = By.xpath("//form[@action='/signup']/button[@type='submit']");
	By mrRadio = By.xpath("//input[@id='id_gender1']");
	By password = By.xpath("//input[@id='password']");
	By dropdowndays = By.xpath("//select[@id='days']");
	By dropdownmonths = By.xpath("//select[@id='months']");
	By dropdownyear = By.xpath("//select[@id='years']");
	By firstName = By.xpath("//input[@id='first_name']");
	By lastName = By.xpath("//input[@id='last_name']");
	By address = By.xpath("//input[@id='address1']");
	By country  = By.xpath("//select[@id='country']");
	By state  = By.xpath("//input[@id='state']");
	By city  = By.xpath("//input[@id='city']");
	By zipCode  = By.xpath("//input[@id='zipcode']");
	By number  = By.xpath("//input[@id='mobile_number']");
	By creatAccount  = By.xpath("//button[text()='Create Account']");
	By accountConfirm = By.xpath("//div[contains(@class,'col-sm-9')]/p");
	By continueBtn = By.xpath("//a[@data-qa='continue-button']");
	By confirmName = By.xpath("//i[@class='fa fa-user']/following::b");
	
	

	public loginValidation(WebDriver driver) {
		this.driver = driver;
		this.wait = new waitClass(driver);
		this.scroll = new scrollClass(driver);
	}
	
	public String signIn(String email) {
		WebElement login = wait.visibilityOfElementWait(loginBtn);
		SmartActions.smartClick(login);
		String nameMaking = "pukki"+randomName();
		wait.visibilityOfElementWait(inputNameFeild).sendKeys(nameMaking);
		wait.visibilityOfElementWait(inputEmailFeild).sendKeys(email);
		wait.clickForElementWait(submitBtn).click();
		return nameMaking;
		
	}
	public void getValueInDropDown(By dropDown ,String value) {
		List<WebElement> items =wait.clickWaitForMultipleElements(dropDown);
		//WebElement product =wait.clickForElementWait(dropdown);
		for(WebElement item :items) {
			if(item.getText().contains(value)) {
				item.click();
				break;
			}
		}
		/*Select select = new Select(product);
		select.selectByVisibleText(value);*/
	}
	
	public void userDetails() {
		wait.clickForElementWait(mrRadio).click();
		wait.visibilityOfElementWait(password).sendKeys(randomName());
		getValueInDropDown(dropdowndays,"23");
		getValueInDropDown(dropdownmonths,"January");
		getValueInDropDown(dropdownyear,"2000");
		
		WebElement Fname = driver.findElement(firstName);
		scroll.scrollToElement(Fname);
		
		
		wait.clickForElementWait(firstName).sendKeys("hello");
		wait.clickForElementWait(lastName).sendKeys("hiii");
		wait.clickForElementWait(address).sendKeys("C-1222 Vaidhnath dham");
		
		getValueInDropDown(country,"India");
		
		wait.visibilityOfElementWait(state).sendKeys("Madhya Pradesh");
		wait.visibilityOfElementWait(city).sendKeys("Bhopal");
		wait.visibilityOfElementWait(zipCode).sendKeys("8327934");
		wait.visibilityOfElementWait(number).sendKeys("9876543210");
		wait.clickForElementWait(creatAccount).click();
		
	}
	
	public String accountCreationDetail() throws InterruptedException {
		
		String displayInfo = wait.visibilityOfElementWait(accountConfirm).getText();
		wait.clickForElementWait(continueBtn).click();
		return displayInfo;

	}
	
	public String validateUserName()  {
		String username = wait.clickForElementWait(confirmName).getText();
		System.out.println(username);
		return username;
		
	}
	
	
	public String randomName() {
		String randomString =  RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}
	

}
