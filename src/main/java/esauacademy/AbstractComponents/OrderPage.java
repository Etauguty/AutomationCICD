package esauacademy.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esaucompany.pageobjects.CartPage;

public class OrderPage extends CartPage {
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		//initialization 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productsNames;
	

	public boolean verifyOrderDisplay(String productName)
	{
		Boolean match = productsNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}

