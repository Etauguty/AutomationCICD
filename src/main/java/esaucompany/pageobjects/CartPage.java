package esaucompany.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		//initialization 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;

	
	public boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOutPage()
	{
		checkoutEle.click();
		return new CheckOutPage(driver);
	}

	
}
