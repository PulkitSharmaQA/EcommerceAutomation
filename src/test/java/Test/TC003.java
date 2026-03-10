package Test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import pages.CartPage;
import pages.ProductPage;

public class TC003 extends BaseTest{
	@Test(retryAnalyzer = utils.retry.class)
	void addProductToCart() {
		ProductPage product = new ProductPage(driver);
		CartPage cartproduct = new CartPage(driver);
		product.searchProducts("T-shirt");
		
		//add first product to cart
		product.getFirstProductDetails();
		
		//Get in cart and validte product quantity price and total
		List<String> containCartDetalis = Arrays.asList("Rs. 1299","1","Rs. 1299");
		Assert.assertTrue(cartproduct.cartProductVarification().containsAll(containCartDetalis));	
	}
	@Test
	void increaseQuantity() {
		ProductPage product = new ProductPage(driver);
		CartPage cartproduct = new CartPage(driver);
		//Search product
		product.searchProducts("T-shirt");
		//add first product to cart
		product.getFirstProductDetails();
		cartproduct.cartProductVarification();
		//Increase quantity
		product.getToIncreaseQuantity();
		//Here amount of product to increase
		
		Assert.assertEquals(cartproduct.increaseProductQuantity("2"),cartproduct.getQuantity());
		Assert.assertEquals(cartproduct.getTotal(),3897);
	}
	@Test
	void addTwoDifferentProduct() {
		ProductPage product = new ProductPage(driver);
		CartPage cartproduct = new CartPage(driver);
		//Search product
		product.searchProducts("T-shirt"); 
		//Item in cart are more than 1
		Assert.assertTrue(product.productCountValidation()>1);
		//adding number of items want to add
		product.addProductsToCart(2);
		Assert.assertEquals(cartproduct.getTotal(), 2299);

	}

}
