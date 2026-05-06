package pages;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SmartActions;
import utils.scrollClass;
import utils.waitClass;

public class ProductPage {
	WebDriver driver;
	waitClass wait;
	scrollClass scroll;

	By productLink = By.xpath("//a[@href='/products']");
	By searchProduct = By.xpath("//input[@id='search_product']");
	By searchBtn = By.xpath("//button[@id='submit_search']");
	By allProducts = By.xpath("//div[@class='product-image-wrapper']");
	By productWithNames = By.xpath("//div[starts-with(@class,'productinfo ')]/p");
	By cotainTshirtsText = By.xpath(".//div[@class='productinfo text-center']/p");
	By productPrice = By.xpath(".//div[@class='productinfo text-center']/h2");
	By viewProduct = By.xpath(".//div[@class='choose']/ul/li/a");
	By cartBtn = By.xpath(".//a[contains(@class,'add-to-cart')]");
	By viewCart = By.xpath("//p[@class='text-center']/a");
	By continueShopping = By.xpath("//div[@class='modal-footer']/button");
	By dismissBtn = By.xpath("//div[@class='close-button']");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new waitClass(driver);
		this.scroll = new scrollClass(driver);

	}
	
	public  void searchProducts(String productName) {
		
		WebElement productBtn=wait.clickForElementWait(productLink);
		SmartActions.scrollToElement(productBtn);
		SmartActions.jsClick(productBtn);
		List<WebElement> closeBtnPro = driver.findElements(dismissBtn);
		wait.visibilityOfElementWait(searchProduct).sendKeys(productName);
		wait.clickForElementWait(searchBtn).click();
		
		
	}
	//Product with names
		public int countProductWithName(String productname) {
			int count=0;
			List<WebElement> productNames = wait.clickWaitForMultipleElements(productWithNames);
			for(int i =0;i<productNames.size();i++) {
				if(productNames.get(i).getText().contains(productname)) {
					
					count+=1;
				}
			}
			return count;
		}
	public int productCountValidation() {
		int count = 0;
		List<WebElement> productInfo = wait.clickWaitForMultipleElements(productWithNames);
		
		for(int i=0;i<productInfo.size();i++) {
			count+=1;
		}
		return count;
		
	}
	public int getTshirtsText(String name) {
		
		int countContainName = 0;
		List<WebElement> productText = wait.clickWaitForMultipleElements(cotainTshirtsText);
		for(int i=0;i<productText.size();i++) {
			System.out.println(productText.get(i).getText());
			if(productText.get(i).getText().contains(name)) {
				
				countContainName += 1;
			}
			
		}
		System.out.println(countContainName);
		return countContainName;
		
		
	}
	public List<String>  getPriceImageViewproduct() {
		List<String> list = new ArrayList<>();
		
		List<WebElement> allProductsDeatils = wait.clickWaitForMultipleElements(allProducts);
		for(int i =0;i<allProductsDeatils.size();i++) {
			
			 list.add( allProductsDeatils.get(i).findElement((productPrice)).getText());
			 list.add( allProductsDeatils.get(i).findElement((viewProduct)).getText());

		}
		return list;

	}
	
	public List<String>  getFirstProductDetails() {
		
		List<String> list = new ArrayList<>();
		
		List<WebElement> nameAndPrice = wait.clickWaitForMultipleElements(allProducts);
		for(int i =0;i<nameAndPrice.size();i++) {
			
			list.add(nameAndPrice.get(i).findElement((cotainTshirtsText)).getText());
			list.add(nameAndPrice.get(i).findElement((productPrice)).getText());
			WebElement cartLinkBtn=wait.clickForElementWait(cartBtn);
			SmartActions.scrollToElement(cartLinkBtn);
			SmartActions.jsClick(cartLinkBtn);
			wait.clickForElementWait(viewCart).click();
			break;
		}
		
		return list;

	}
	public void getOutSideOfCart() {
		if(driver.getCurrentUrl()=="https://automationexercise.com/view_cart") {
			driver.navigate().back();
		}
	}
	public void getToIncreaseQuantity() {
		getOutSideOfCart();
		List<WebElement> allProductsDeatils = wait.clickWaitForMultipleElements(allProducts);
		for(int i =0;i<allProductsDeatils.size();i++) {
			WebElement shoppingBtn = allProductsDeatils.get(i).findElement(continueShopping);
			
			if(shoppingBtn.isDisplayed() == true) {
				SmartActions.scrollToElement(shoppingBtn);
				SmartActions.jsClick(shoppingBtn);
			}
			
			allProductsDeatils.get(i).findElement(viewProduct).click();
			break;
		}
		
	}
	public void addProductsToCart(int number) {
		List<WebElement> allProductsDeatils = wait.clickWaitForMultipleElements(allProducts);
		for(int i =0;i<=number - 1;i++) {
			WebElement cartLinkBtn=allProductsDeatils.get(i).findElement(cartBtn);
			SmartActions.scrollToElement(cartLinkBtn);
			SmartActions.jsClick(cartLinkBtn);
			if(i == number - 1) {
				wait.clickForElementWait(viewCart).click();
				break;
			}
			wait.clickForElementWait(continueShopping).click();
			
		}
		
	}

}
