package esaucompany.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import esauacademy.AbstractComponents.OrderPage;
import esaucompany.TestComponents.BaseTest;
import esaucompany.pageobjects.CartPage;
import esaucompany.pageobjects.CheckOutPage;
import esaucompany.pageobjects.ConfirmationPage;
import esaucompany.pageobjects.ProductCatalog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException{
		// TODO Auto-generated method stub

		ProductCatalog productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		
		checkOutPage.selectCountry("Mexico");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	//To verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalog productCatalogue = landingPage.loginApplication("EsauTesting@gmail.com", "Patito10");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	} 
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "EsauTesting@gmail.com");
		map.put("password","Patito10");
		map.put("productName", "ZARA COAT 3");
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "EsauTest@gmail.com");
		map1.put("password","Patito10");
		map1.put("productName", "ADIDAS ORIGINAL");
	*/
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\esaucompany\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}
