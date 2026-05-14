package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SmartActions;
import utils.scrollClass;
import utils.waitClass;

public class CartPage {
	
	WebDriver driver;
	waitClass wait;
	scrollClass scroll;
	ProductPage product ;
	
	By cartProduct = By.xpath("//tr[@id='product-28']");
	By cartProductPrice = By.xpath(".//td[@class='cart_price']/p");
	By cartProductQuantity = By.xpath(".//td[@class='cart_quantity']/button");
	By cartProductTotal = By.xpath(".//td[@class='cart_total']/p");
	By cotainTshirtsText = By.xpath("//div[@class='productinfo text-center']/p");
	By viewCart = By.xpath("//p[@class='text-center']/a");
	By increaseBtn = By.xpath("//input[@id='quantity']");
	By addToCartBtn = By.xpath("//button[contains(@class,'cart')]");
	By allCartProduct = By.xpath("//td[@class='cart_description']/h4/a");
	By viewProduct = By.xpath(".//div[@class='choose']/ul/li/a");
	By checkOutBtn = By.xpath("//a[contains(@class,'check_out')]");
	By adsPopUP = By.xpath("//div[@class='continue-prompt-text']");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new waitClass(driver);
		this.scroll = new scrollClass(driver);
		this.product = new ProductPage(driver);
	}
	
	public List<String> cartProductVarification() {
		List<String> productDetails = new ArrayList<>();

		List<WebElement> fullProduct = wait.clickWaitForMultipleElements(cartProduct);
		for(int i=0;i<fullProduct.size();i++) {
			productDetails.add(fullProduct.get(i).findElement(cartProductPrice).getText());
			productDetails.add(fullProduct.get(i).findElement(cartProductQuantity).getText());
			productDetails.add(fullProduct.get(i).findElement(cartProductTotal).getText());
			
		}
		driver.navigate().back();
		return productDetails;
	}
	
	public int increaseProductQuantity(String quantity) {
		WebElement quantityBtn =wait.clickForElementWait(increaseBtn);
		quantityBtn.click();
		quantityBtn.clear();
		quantityBtn.sendKeys(quantity);
		wait.clickForElementWait(addToCartBtn).click();
		wait.clickForElementWait(viewCart).click();
		int intQuantity = Integer.parseInt(quantity);
		return intQuantity + 1;
	}
	public int getQuantity() {
		//wait.clickForElementWait(viewCart).click();
		String quantityOfProduct = wait.visibilityOfElementWait(cartProductQuantity).getText();
		int productQuantity = Integer.parseInt(quantityOfProduct);
		return productQuantity;
		
	}
	public int getTotal() {
		int total = 0;
		List<WebElement> getFullTotal = wait.clickWaitForMultipleElements(cartProductTotal);
		for(int i=0;i<getFullTotal.size();i++) {
			String productTotal = getFullTotal.get(i).getText();
			String realPrice=productTotal.replace("Rs. ","").trim(); 
			
			int Total = Integer.parseInt(realPrice);
			total+=Total;
		}
		System.out.println(total);
		
		return total;
	} 
	public int cartProductCount() {
		int count = 0;
		List<WebElement> productInfo = wait.clickWaitForMultipleElements(allCartProduct);
		
		for(int i=0;i<productInfo.size();i++) {
			count+=1;
		}
		System.out.println(count);
		return count;
		
	}
	
}
