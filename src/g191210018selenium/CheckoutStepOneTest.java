package g191210018selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepOneTest {
	private final WebDriver driver;
	private final CartSeleniumTest cartSeleniumTest;
	
	public CheckoutStepOneTest(WebDriver driver) {
		this.driver = driver;
		this.cartSeleniumTest = new CartSeleniumTest(driver);
	}
	
	public void formFirstNameRequired() {
		cartSeleniumTest.checkoutButtonTest();
		fillAndCheckout(null,null,null);
		
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));

		CmdUtils.assertEquals("Error: First Name is required",errorMessage.getText(),"Checkout Firstname Required Form Error Test");
	}
	
	public void formLastNameRequired() {
		cartSeleniumTest.checkoutButtonTest();
		fillAndCheckout("Mert Can",null,null);
		
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));

		CmdUtils.assertEquals("Error: Last Name is required",errorMessage.getText(),"Checkout Lastname Required Form Error Test");
	}
	
	public void formPostalCodeRequired() {
		cartSeleniumTest.checkoutButtonTest();
		fillAndCheckout("Mert Can","Yılmaz",null);
		
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));
		
		CmdUtils.assertEquals("Error: Postal Code is required",errorMessage.getText(),"Checkout Postal Code Required Form Error Test");
	}
	
	public List<String> checkoutStepTwo() {
		List<String> selectedItems = cartSeleniumTest.readyStepOneScreen();
		fillAndCheckout("Mert Can","Yılmaz","10300");
		
		CmdUtils.assertEquals("https://www.saucedemo.com/checkout-step-two.html",driver.getCurrentUrl(),"Navigate to Step Two Test");
		return selectedItems;
	}
	
	public void runAll() {
		formFirstNameRequired();
		resetSession();
		formLastNameRequired();
		resetSession();
		formPostalCodeRequired();
		resetSession();
		checkoutStepTwo();
		resetSession();
	}
	
	public void resetSession() {
		WebElement drawerbtn = driver.findElement(By.id("react-burger-menu-btn"));
		drawerbtn.click();
		
		WebElement resetStateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
		resetStateButton.click();
	}
	
	public void fillAndCheckout(String firstName,String lastName,String postalCode) {
		WebElement firstNameInput = driver.findElement(By.id("first-name"));
		firstNameInput.click();
		if(firstName!=null)
		firstNameInput.sendKeys(firstName);
		WebElement lastnameInput = driver.findElement(By.id("last-name"));
		lastnameInput.click();
		if(lastName!=null)
		lastnameInput.sendKeys(lastName);
		WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
		postalCodeInput.click();
		if(postalCode!=null)
		postalCodeInput.sendKeys(postalCode);
		WebElement continueButton = driver.findElement(By.id("continue"));
		continueButton.click();
	}
}
