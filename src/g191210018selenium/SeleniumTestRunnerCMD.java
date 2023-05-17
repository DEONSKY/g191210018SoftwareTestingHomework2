package g191210018selenium;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

public class SeleniumTestRunnerCMD {
	private final WebDriver driver;
	
	private final String website = "https://www.saucedemo.com/";
	
	private final LoginSeleniumTest loginSeleniumTest;
	
	public SeleniumTestRunnerCMD(WebDriver driver) {
		this.driver = driver;
		this.loginSeleniumTest = new LoginSeleniumTest(driver);
	}
	
	public void cmd() {
		Scanner ins = new Scanner(System.in);
		String name = "";
		
		printMenu();
		
		while (name.equals("")) {
			 System.out.print("Execute command (For Avaible commands type help):");

			switch(ins.next()) {
				case "1":
					loginSeleniumTest.usernameIsRequired();
				    break;
				case "2":
					loginSeleniumTest.passwordIsRequired();
				    break;
				case "3":
					loginSeleniumTest.usernameAndPasswordNotMatch();
				    break;
				case "4":
					loginSeleniumTest.lockedOutUserTest();
				    break;
				case "menu":
					System.out.println("Program is closing");
					return;
				case "exit":
					System.out.println("Program is closing");
					return;
			}
		}
			
	}
	
	public void printMenu() {
		System.out.println("Menu:");
		System.out.println("menu: show commands");
		System.out.println("exit: exits from app");
		System.out.println("1: Login username required test");
	}
	
	private void data() {
		driver.get("https://www.n11.com/uye-ol");
	}
}
