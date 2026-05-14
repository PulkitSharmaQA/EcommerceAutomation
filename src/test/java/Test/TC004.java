package Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import pages.loginValidation;

public class TC004 extends BaseTest {
	@Test(groups = {"regression"},retryAnalyzer = utils.retry.class)
	void multiProductCartLogic() {
		SoftAssert softAssert = new SoftAssert();
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
		softAssert.assertEquals(cartproduct.getTotal(), 3598);
		softAssert.assertAll();
		
	}
	
	
	@Test(groups = {"sanity","regression"},retryAnalyzer = utils.retry.class)
	void checkoutFlow() {
		SoftAssert softAssert = new SoftAssert();
		loginValidation login = new loginValidation(driver);
		ProductPage product = new ProductPage(driver);
		CartPage cartproduct = new CartPage(driver);
		CheckoutPage checkOut = new CheckoutPage(driver);
		//search product and validate there is more than one product is visible
		String validUserName=login.signIn( login.randomName() + "9@yahoo.com");
		login.userDetails();
		product.searchProducts("T-shirt"); 
		int productCount = product.productCountValidation();
		softAssert.assertTrue(productCount > 1);
		
		//Add to different product and validate
		product.addProductsToCart(2);
		int total =cartproduct.getTotal();
		//Checking Amount Of Products
		softAssert.assertEquals(checkOut.checkOut(),total);
		Assert.assertTrue(checkOut.fillPaymentForm().contains("Congratulations! Your order has been confirmed!"));
		softAssert.assertAll();
	}
	

}
