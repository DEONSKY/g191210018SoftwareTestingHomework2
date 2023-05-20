package g191210018selenium;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class CheckoutStepTwoTest {
	private final WebDriver driver;
	private final CheckoutStepOneTest checkoutStepOneTest;
	
	public CheckoutStepTwoTest(WebDriver driver) {
		this.driver = driver;
		this.checkoutStepOneTest = new CheckoutStepOneTest(driver);
	}
	
	public void validateItemsBeforePayment() {
		List<String> selectedItems = checkoutStepOneTest.checkoutStepTwo();
		List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
		
		CmdUtils.assertEquals("2",""+ cartItems.size(),"Expected Item Count In Cart Test");

		for(WebElement cartItem:cartItems) {

			WebElement cartItemName = cartItem.findElement(By.className("inventory_item_name"));
			CmdUtils.assertEquals("true",""+selectedItems.contains(cartItemName.getText()),"Expected Items Inside Cart Test");
		}
	}
	
	public void validateItemTotalPrice() {
		checkoutStepOneTest.checkoutStepTwo();
		List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

		List<Double> cartItemPriceList = new ArrayList();
		for(WebElement cartItem:cartItems) {

			WebElement cartItemPrice = cartItem.findElement(By.className("inventory_item_price"));
			cartItemPriceList.add(Double.parseDouble(cartItemPrice.getText().split(Pattern.quote("$"))[1]));
			
		}
		
		Double subtotalexpected = cartItemPriceList.stream().mapToDouble(Double::doubleValue).sum();
		WebElement subtotal = driver.findElement(By.className("summary_subtotal_label"));
		
		CmdUtils.assertEquals(""+subtotalexpected,""+subtotal.getText().split(Pattern.quote("$"))[1],"Sub Total Test");
	}
	
	public void validateTotalPrice() {
		checkoutStepOneTest.checkoutStepTwo();
		
		WebElement subtotal = driver.findElement(By.className("summary_subtotal_label"));
		Double subTotalValue=Double.parseDouble(subtotal.getText().split(Pattern.quote("$"))[1]);
		WebElement taxesTotal = driver.findElement(By.className("summary_tax_label"));
		Double taxesTotalValue=Double.parseDouble(taxesTotal.getText().split(Pattern.quote("$"))[1]);
		
		WebElement total = driver.findElement(By.className("summary_total_label"));
		String totalStr=total.getText().split(Pattern.quote("$"))[1];
		
		CmdUtils.assertEquals(""+(subTotalValue+taxesTotalValue),totalStr,"Sub Total Test");
	}
	
	public void cancelButtonTest() {
		checkoutStepOneTest.checkoutStepTwo();
		WebElement cancelButton = driver.findElement(By.id("cancel"));
		cancelButton.click();
		CmdUtils.assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl(),"Total Test");
	}
	
	public void finishButtonTest() {
		checkoutStepOneTest.checkoutStepTwo();
		WebElement cancelButton = driver.findElement(By.id("finish"));
		cancelButton.click();
		CmdUtils.assertEquals("https://www.saucedemo.com/checkout-complete.html",driver.getCurrentUrl(),"Finish");
	}
	
	public void runAll() {
		validateItemsBeforePayment();
		resetSession();
		validateItemTotalPrice();
		resetSession();
		validateTotalPrice();
		resetSession();
		cancelButtonTest();
		resetSession();
		finishButtonTest();
	}
	
	public void resetSession() {
		WebElement drawerbtn = driver.findElement(By.id("react-burger-menu-btn"));
		drawerbtn.click();
		
		WebElement resetStateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
		resetStateButton.click();
	}
}
