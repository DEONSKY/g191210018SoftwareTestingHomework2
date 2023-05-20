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
		CmdUtils.assertEquals("Sauce Labs Backpack", firstElementOfList.getText(),"First Element Of List Tes");	
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
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString(),"A to Z order Test");	
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
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString(),"Z to A order Test");	
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
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString(),"Low to High order Test");	
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
		
		CmdUtils.assertEquals(preOrdered.toString(), titles.toString(),"High to Low order Test");	
	}
	
	public void itemTitleHoverTest() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/a"));
		
		
		CmdUtils.assertEquals("rgba(24, 88, 58, 1)", element.findElement(By.tagName("div")).getCssValue("color"),"Unhovered Color Test");	
		
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		
		CmdUtils.assertEquals("rgba(74, 74, 74, 1)", element.getCssValue("color"),"Hovered Color Test");	
	}
	
	public void addToCardButtonChange() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]"));
		WebElement button = element.findElement(By.tagName("button"));
		
		CmdUtils.assertEquals("rgba(19, 35, 34, 1)", button.getCssValue("color"),"Item Add to Cart Button Color Test");	
		CmdUtils.assertEquals("Add to cart", button.getText(),"Item Add to Cart Button Text Test");	
		
		button.click();	

		button = element.findElement(By.tagName("button"));
		
		CmdUtils.assertEquals("rgba(226, 35, 26, 1)", button.getCssValue("color"),"Item Remove Button Color Test");	
		CmdUtils.assertEquals("Remove", button.getText(),"Item Remove Button Text Test");	
		
		button.click();	

		button = element.findElement(By.tagName("button"));
		
		CmdUtils.assertEquals("rgba(19, 35, 34, 1)", button.getCssValue("color"),"Item Add to Cart Button Color After Remove Test");	
		CmdUtils.assertEquals("Add to cart", button.getText(),"Item Add to Cart Button Text After Remove Test");	
	}
	
	public List<String> headerOrderListIconCount() {
		
		//*[@id="inventory_container"]/div
		loginSeleniumTest.standartUserLogin();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]"));
		WebElement button = element.findElement(By.tagName("button"));
		
		List<String> selecteds = new ArrayList();
		selecteds.add(element.findElement(By.className("inventory_item_name")).getText());
		
		button.click();	
		
		WebElement shoppingCardBadge = driver.findElement(By.className("shopping_cart_badge"));
		
		CmdUtils.assertEquals("1", shoppingCardBadge.getText(),"Shopping Badge Increase Test");
		
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]"));
		WebElement button2 = element2.findElement(By.tagName("button"));
		selecteds.add(element2.findElement(By.className("inventory_item_name")).getText());
		
		button2.click();	
		
		shoppingCardBadge = driver.findElement(By.className("shopping_cart_badge"));
		
		CmdUtils.assertEquals("2", shoppingCardBadge.getText(),"Shopping Badge Increase Test");
		
		return selecteds;
	}
	
	public void OpenItemsDetailsPage() {
		loginSeleniumTest.standartUserLogin();
		
		WebElement link = driver.findElement(By.id("item_4_img_link"));
		
		link.click();
		
		
		CmdUtils.assertEquals("https://www.saucedemo.com/inventory-item.html?id=4",driver.getCurrentUrl(),"Item Details Page Open");

	}
	
	public void ResponsiveLayout() {
		loginSeleniumTest.standartUserLogin();
		
		driver.manage().window().setSize(new Dimension(1366,768));

		WebElement element = driver.findElement(By.className("inventory_item"));
		
		CmdUtils.assertEquals("505",""+element.getSize().width,"Expected Item Box Width Test On Big Screen");
		
		driver.manage().window().setSize(new Dimension(800+69,400));
		
		WebElement element2 = driver.findElement(By.className("inventory_item"));
		
		CmdUtils.assertEquals("770",""+element.getSize().width,"Expected Item Box Width Test On Smaller Screen");

	}
	
	public void ResponsiveLayout2() {
		loginSeleniumTest.standartUserLogin();
		
		driver.manage().window().setSize(new Dimension(1366,768));

		List<WebElement> elements = driver.findElements(By.className("inventory_item"));
		
		CmdUtils.assertNotEquals(""+elements.get(0).getLocation().getX(),""+elements.get(1).getLocation().getX() ,"Two items in one row");
		
		driver.manage().window().setSize(new Dimension(800+69,400));
		
		List<WebElement> elements2 = driver.findElements(By.className("inventory_item"));
		
		CmdUtils.assertEquals(""+elements2.get(0).getLocation().getX(),""+elements2.get(1).getLocation().getX(),"One item per row");
	}
	
	
	public void resetSession() {
		WebElement drawerbtn = driver.findElement(By.id("react-burger-menu-btn"));
		drawerbtn.click();
		
		WebElement resetStateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
		resetStateButton.click();
	}
}
