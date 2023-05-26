/**
*
* @author Mert Can Yılmaz mert.yilmaz13@ogr.sakarya.edu.tr
* @since 26/05/2023
* <p>
* Login ekranında yapılan testler.
* </p>
*/
package g191210018selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.AssertionFailedError;

public class LoginSeleniumTest {
	private final WebDriver driver;

	
	public LoginSeleniumTest(WebDriver driver) {
		this.driver = driver;

	}
	
	public void standartUserLogin () {
		driver.get("https://www.saucedemo.com/");
		fillAndSubmitLogin("standard_user","secret_sauce");
		assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl()); 
	}
	
	public void locketOutUserLogin () {
		driver.get("https://www.saucedemo.com/");
		fillAndSubmitLogin("locked_out_user","secret_sauce");
		assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl()); 
	}
	
	public void problemUser () {
		driver.get("https://www.saucedemo.com/");
		fillAndSubmitLogin("problem_user","secret_sauce");
		assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl()); 
	}
	
	public void performanceGlitchUser () {
		driver.get("https://www.saucedemo.com/");
		fillAndSubmitLogin("performance_glitch_user","secret_sauce");
		assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl()); 
	}
	
	
	public void fillAndSubmitLogin(String username,String password) {
		WebElement usernameInput = driver.findElement(By.id("user-name"));
		usernameInput.click();
		usernameInput.sendKeys("standard_user");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.click();
		passwordInput.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
	}
}
