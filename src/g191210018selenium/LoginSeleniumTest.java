package g191210018selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSeleniumTest {
	private final WebDriver driver;
	
	public LoginSeleniumTest(WebDriver driver) {
		this.driver = driver;
	}
	
	public void usernameIsRequired () {
		driver.get("https://www.saucedemo.com/");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));
		System.out.println(errorMessage.getText());
		try {
		assertEquals("Epic sadface: Username is required",errorMessage.getText());
		}catch(AssertionError err) {
			System.out.println(err.getMessage());
		}
		/*
		WebElement usernameInput = driver.findElement(By.id("user-name"));
		usernameInput.click();
		usernameInput.sendKeys("asdsadda");
		*/
	}
	
	public void passwordIsRequired () {
		driver.get("https://www.saucedemo.com/");
		WebElement usernameInput = driver.findElement(By.id("user-name"));
		usernameInput.click();
		usernameInput.sendKeys("asdsadda");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));
		System.out.println(errorMessage.getText());
		try {
		assertEquals("Epic sadface: Password is required",errorMessage.getText());
		}catch(AssertionError err) {
			System.out.println(err.getMessage());
		}
	}
	
	public void usernameAndPasswordNotMatch () {
		driver.get("https://www.saucedemo.com/");
		WebElement usernameInput = driver.findElement(By.id("user-name"));
		usernameInput.click();
		usernameInput.sendKeys("asdsadda");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.click();
		passwordInput.sendKeys("asdsadda");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));
		System.out.println(errorMessage.getText());
		try {
		assertEquals("Epic sadface: Username and password do not match any user in this service",errorMessage.getText());
		}catch(AssertionError err) {
			System.out.println(err.getMessage());
		}
	}
	
	public void lockedOutUserTest () {
		driver.get("https://www.saucedemo.com/");
		WebElement usernameInput = driver.findElement(By.id("user-name"));
		usernameInput.click();
		usernameInput.sendKeys("locked_out_user");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.click();
		passwordInput.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		WebElement errorMessage = driver.findElement(By.className("error-message-container"));
		System.out.println(errorMessage.getText());
		try {
		assertEquals("Epic sadface: Sorry, this user has been locked out.",errorMessage.getText());
		}catch(AssertionError err) {
			System.out.println(err.getMessage());
		}
	}
}
