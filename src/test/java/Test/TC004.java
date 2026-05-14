package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import pages.loginValidation;

public class TC004 extends BaseTest {
	@Test(groups = {"regression"},retryAnalyzer = utils.retry.class)
	void multiProductCartLogic() {
		ProductPage product = new ProductPage(driver);
		CartPage cartproduct = new CartPage(driver);
		
		//search product and validate there is more than one product is visible
		product.searchProducts("T-shirt"); 
		int productCount = product.productCountValidation();
		Assert.assertTrue(productCount > 1);
		
		//Add to different product and validate
		
		product.addProductsToCart(2);
		
		int  justIt = cartproduct.getTotal();
		System.out.println(justIt);
		//How Many product presenet in cart
		Assert.assertEquals(cartproduct.cartProductCount(), 2);
		driver.navigate().back();
		product.getToIncreaseQuantity();
		cartproduct.increaseProductQuantity("1");
		Assert.assertEquals(cartproduct.getTotal(), 3598);
		
	}
	@Test(groups = {"sanity","regression"},retryAnalyzer = utils.retry.class)
	void checkoutFlow() {
		loginValidation login = new loginValidation(driver);
		ProductPage product = new ProductPage(driver);
		CartPage cartproduct = new CartPage(driver);
		CheckoutPage checkOut = new CheckoutPage(driver);
		//search product and validate there is more than one product is visible
		String validUserName=login.signIn( login.randomName() + "9@yahoo.com");
		login.userDetails();
		product.searchProducts("T-shirt"); 
		int productCount = product.productCountValidation();
		Assert.assertTrue(productCount > 1);
		
		//Add to different product and validate
		product.addProductsToCart(2);
		int total =cartproduct.getTotal();
		//Checking Amount Of Products
		Assert.assertEquals(checkOut.checkOut(),total);
		Assert.assertTrue(checkOut.fillPaymentForm().contains("Congratulations! Your order has been confirmed!"));
		
	}
	

}
