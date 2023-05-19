package g191210018selenium;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

public class SeleniumTestRunnerCMD {
	private final WebDriver driver;
	
	private final String website = "https://www.saucedemo.com/";
	
	private final LoginErrorsSeleniumTest loginErrorsSeleniumTest;
	private final LoginSeleniumTest loginSeleniumTest;
	private final InventorySeleniumTest inventorySeleniumTest;
	
	public SeleniumTestRunnerCMD(WebDriver driver) {
		this.driver = driver;
		this.loginErrorsSeleniumTest = new LoginErrorsSeleniumTest(driver);
		this.loginSeleniumTest = new LoginSeleniumTest(driver);
		this.inventorySeleniumTest = new InventorySeleniumTest(driver);
	}
	
	public void cmd() {
		Scanner ins = new Scanner(System.in);
		String name = "";
		
		printMenu();
		
		while (name.equals("")) {
			 System.out.print("Execute command (For Avaible commands type help):");

			switch(ins.next()) {
				case "1":
					loginErrorsSeleniumTest.usernameIsRequired();
				    break;
				case "2":
					loginErrorsSeleniumTest.passwordIsRequired();
				    break;
				case "3":
					loginErrorsSeleniumTest.usernameAndPasswordNotMatch();
				    break;
				case "4":
					loginErrorsSeleniumTest.lockedOutUserTest();
				    break;
				case "5":
					loginSeleniumTest.standartUserLogin();
				    break;
				case "6":
					loginSeleniumTest.locketOutUserLogin();
				    break;
				case "7":
					loginSeleniumTest.problemUser();
				    break;
				case "8":
					loginSeleniumTest.performanceGlitchUser();
				    break;
				case "9":
					inventorySeleniumTest.firstElementOfListTest();
				    break;
				case "10":
					inventorySeleniumTest.orderAtoZTest();
				    break;
				case "11":
					inventorySeleniumTest.orderZtoATest();
				    break;
				case "12":
					inventorySeleniumTest.orderByPriceLowtoHighTest();;
				    break;
				case "13":
					inventorySeleniumTest.orderByPriceHightoLowTest();;
				    break;
				case "14":
					inventorySeleniumTest.itemTitleHoverTest();
				    break;
				case "15":
					inventorySeleniumTest.addToCardButtonChange();
				    break;
				case "16":
					inventorySeleniumTest.headerOrderListIconCount();
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
