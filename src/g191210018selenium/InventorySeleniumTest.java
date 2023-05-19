package g191210018selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.*;

public class InventorySeleniumTest {
	private final WebDriver driver;
	private final LoginSeleniumTest loginSeleniumTest;
	
	public InventorySeleniumTest(WebDriver driver) {
		this.driver = driver;
		this.loginSeleniumTest = new LoginSeleniumTest(driver);
	}
	//*[@id="inventory_container"]/div/div[1]/div[2]
	public void firstElementOfListTest() {
		loginSeleniumTest.standartUserLogin();
		WebElement firstElementOfList= driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/a/div"));
		CmdUtils.assertEquals("Sauce Labs Backpack", firstElementOfList.getText());	
	}
	
	public void orderAtoZTest() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		changeListOrder("az");
		
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div"));
		System.out.println(elements.size());
		List<String> titles = new ArrayList<String>(); 
		for(WebElement el:elements) {
			WebElement title =  el.findElement(By.xpath(".//div[2]/div[1]/a/div"));
			System.out.println(title.getText());
			titles.add(title.getText());
		}
		List<String> preOrdered = titles;
		Collections.sort(titles,Collections.reverseOrder());  
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString());	
	}
	
	public void orderZtoATest() {
	
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		changeListOrder("za");
		
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div"));
		System.out.println(elements.size());
		List<String> titles = new ArrayList<String>(); 
		for(WebElement el:elements) {
			WebElement title =  el.findElement(By.xpath(".//div[2]/div[1]/a/div"));
			System.out.println(title.getText());
			titles.add(title.getText());
		}
		List<String> preOrdered = titles;
		Collections.sort(titles,Collections.reverseOrder());  
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString());	
	}
	
	void changeListOrder(String order) {
		WebElement product_sort_select = driver.findElement(By.className("product_sort_container"));
		Select product_sort_selection = new Select(product_sort_select);
		product_sort_selection.selectByValue(order);
	}
	
	public void orderByPriceLowtoHighTest() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		changeListOrder("lohi");
		
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div"));
		System.out.println(elements.size());
		List<String> titles = new ArrayList<String>(); 
		for(WebElement el:elements) {
			WebElement title =  el.findElement(By.className("inventory_item_price"));
			System.out.println(title.getText());
			titles.add(title.getText());
		}
		List<String> preOrdered = titles;
		Collections.sort(titles,Collections.reverseOrder());  
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString());	
	}
	public void orderByPriceHightoLowTest() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		changeListOrder("hilo");
		
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div"));
		System.out.println(elements.size());
		List<String> titles = new ArrayList<String>(); 
		for(WebElement el:elements) {
			WebElement title =  el.findElement(By.className("inventory_item_price"));
			System.out.println(title.getText());
			titles.add(title.getText());
		}
		List<String> preOrdered = titles;
		Collections.sort(titles,Collections.reverseOrder());  
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString());	
	}
	
	public void itemTitleHoverTest() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/a"));
		
		
		CmdUtils.assertEquals("rgba(24, 88, 58, 1)", element.findElement(By.tagName("div")).getCssValue("color"));	
		
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		
		CmdUtils.assertEquals("rgba(74, 74, 74, 1)", element.getCssValue("color"));	
	}
	
	public void addToCardButtonChange() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]"));
		WebElement button = element.findElement(By.tagName("button"));
		
		CmdUtils.assertEquals("rgba(19, 35, 34, 1)", button.getCssValue("color"));	
		CmdUtils.assertEquals("Add to cart", button.getText());	
		
		button.click();	

		button = element.findElement(By.tagName("button"));
		
		CmdUtils.assertEquals("rgba(226, 35, 26, 1)", button.getCssValue("color"));	
		CmdUtils.assertEquals("Remove", button.getText());	
		
		button.click();	

		button = element.findElement(By.tagName("button"));
		
		CmdUtils.assertEquals("rgba(19, 35, 34, 1)", button.getCssValue("color"));	
		CmdUtils.assertEquals("Add to cart", button.getText());	
	}
	
	public void headerOrderListIconCount() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]"));
		WebElement button = element.findElement(By.tagName("button"));
		
		button.click();	
		
		WebElement shoppingCardBadge = driver.findElement(By.className("shopping_cart_badge"));
		
		CmdUtils.assertEquals("1", shoppingCardBadge.getText());
		
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]"));
		WebElement button2 = element2.findElement(By.tagName("button"));
		
		button2.click();	
		
		shoppingCardBadge = driver.findElement(By.className("shopping_cart_badge"));
		
		CmdUtils.assertEquals("2", shoppingCardBadge.getText());
		
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
