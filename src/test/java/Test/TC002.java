package Test;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import pages.ProductPage;

public class TC002 extends BaseTest {
	@Test(dataProvider="Items",retryAnalyzer = utils.retry.class)
	void productTest(String items,String names,String price) {
		//Search product 
		ProductPage product = new ProductPage(driver);
		product.searchProducts(items);
		
		//Validte numbers of items are more than 1
		int productCount = product.productCountValidation();
		Assert.assertTrue(productCount > 1);
		
		//Product Name contains "T-shirt"
		//Assert.assertEquals(productCount,product.getTshirtsText(items));
		
		//validte all Products are visible
		List<String> priceAndProductList = Arrays.asList("View Product");
		Assert.assertTrue(product.getPriceImageViewproduct().containsAll(priceAndProductList));
		
		//item Which is added to cart Validation with Name and Price
		List<String> priceAndNameList = Arrays.asList(names,price);
		Assert.assertTrue(product.getFirstProductDetails().containsAll(priceAndNameList));
		
	}
	
	 @DataProvider(name = "Items")
	    public Object[][] getData() {

	        return new Object[][]{
	                {"T-shirt","Pure Cotton V-Neck T-Shirt","Rs. 1299"},
	                {"Jeans","Soft Stretch Jeans","Rs. 799"},
	                {"Dress","Sleeveless Dress","Rs. 1000"}
	        };
	    }

}
