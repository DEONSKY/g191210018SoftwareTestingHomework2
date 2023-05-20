package g191210018selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class CartSeleniumTest {
	private final WebDriver driver;
	private final LoginSeleniumTest loginSeleniumTest;
	private final InventorySeleniumTest inventorySeleniumTest;
	
	public CartSeleniumTest(WebDriver driver) {
		this.driver = driver;
		this.loginSeleniumTest = new LoginSeleniumTest(driver);
		this.inventorySeleniumTest = new InventorySeleniumTest(driver);
	}
	
	public void showCartItems(){
		List<String> selecteds = inventorySeleniumTest.headerOrderListIconCount();
		WebElement shoppingCartButton = driver.findElement(By.className("shopping_cart_link"));
		
		shoppingCartButton.click();
		
		List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
		CmdUtils.assertEquals("2",""+ cartItems.size(),"Expected Item Count In Cart Test");
		System.out.print(selecteds.toString());
		for(WebElement cartItem:cartItems) {

			WebElement cartItemName = cartItem.findElement(By.className("inventory_item_name"));
			CmdUtils.assertEquals("true",""+selecteds.contains(cartItemName.getText()),"Expected Items Inside Cart Test");
		}
	}
	
	public void removeFromCartList(){
		List<String> selecteds = inventorySeleniumTest.headerOrderListIconCount();
		WebElement shoppingCartButton = driver.findElement(By.className("shopping_cart_link"));
		
		shoppingCartButton.click();
		
		List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
		CmdUtils.assertEquals("2",""+ cartItems.size(),"Expected Item Count In Cart Test");
		System.out.print(selecteds.toString());
		for(WebElement cartItem:cartItems) {
			WebElement cartItemName = cartItem.findElement(By.className("inventory_item_name"));
			WebElement cartItemButton = cartItem.findElement(By.tagName("button"));
			cartItemButton.click();
		}
		
		List<WebElement> cartItemsRemoved = driver.findElements(By.className("cart_item"));
		CmdUtils.assertEquals("0",""+ cartItemsRemoved.size(),"Expected Item Count After Items Removed From Cart Test");
	}
	
	public void continueToShopping(){
		List<String> selecteds = inventorySeleniumTest.headerOrderListIconCount();
		WebElement shoppingCartButton = driver.findElement(By.className("shopping_cart_link"));
		
		shoppingCartButton.click();
		
		WebElement continueShoppingButton = driver.findElement(By.id("continue-shopping"));
		
		continueShoppingButton.click();
		driver.getCurrentUrl();
		CmdUtils.assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl(),"Contuniue Shopping Button Test");
	}
	
	public void checkoutButtonTest() {
		List<String> selecteds = inventorySeleniumTest.headerOrderListIconCount();
		WebElement shoppingCartButton = driver.findElement(By.className("shopping_cart_link"));
		
		shoppingCartButton.click();
		
		WebElement checkoutButton = driver.findElement(By.id("checkout"));
		
		checkoutButton.click();
		

		
		CmdUtils.assertEquals("https://www.saucedemo.com/checkout-step-one.html",driver.getCurrentUrl(),"Checkout Button Test");
	}
	
	public List<String> readyStepOneScreen() {
		inventorySeleniumTest.headerOrderListIconCount();
		WebElement shoppingCartButton = driver.findElement(By.className("shopping_cart_link"));
		
		shoppingCartButton.click();
		
		List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
		CmdUtils.assertEquals("2",""+ cartItems.size(),"Expected Item Count In Cart Test");
		List<String> cartItemNames = new ArrayList();
		for(WebElement cartItem:cartItems) {
			WebElement cartItemName = cartItem.findElement(By.className("inventory_item_name"));
			cartItemNames.add(cartItemName.getText());
		}
		
		WebElement checkoutButton = driver.findElement(By.id("checkout"));
		
		checkoutButton.click();
		
		return cartItemNames;
	}
	
	public void runAll() {
		showCartItems();
		resetSession();
		removeFromCartList();
		resetSession();
		continueToShopping();
		resetSession();
		checkoutButtonTest();
		resetSession();
		readyStepOneScreen();
		resetSession();
	}
	
	public void resetSession() {
		WebElement drawerbtn = driver.findElement(By.id("react-burger-menu-btn"));
		drawerbtn.click();
		
		WebElement resetStateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
		resetStateButton.click();
	}
}
