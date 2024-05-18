package esaucompany.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import esaucompany.TestComponents.BaseTest;
import esaucompany.TestComponents.Retry;
import esaucompany.pageobjects.CartPage;
import esaucompany.pageobjects.CheckOutPage;
import esaucompany.pageobjects.ConfirmationPage;
import esaucompany.pageobjects.ProductCatalog;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		
		landingPage.loginApplication("EsauTesting@gmail.com", "Patito1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	
	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalogue = landingPage.loginApplication("EsauTesting@gmail.com", "Patito10");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	
	}
}
