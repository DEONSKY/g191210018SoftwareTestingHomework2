package g191210018selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) throws MalformedURLException {
		
		if(args.length<1) {
			System.out.println("No driver parameter \n Run as: Program remote or Program local");
			return;
		}
		
		WebDriver driver = null;
		System.out.print(args[0]);
		
		switch(args[0]) {
		  case "remote":
			driver = createInstanceWithRemoteDriver();
		    break;
		  case "local":
			driver = createInstanceWithChromeDriver();
		    break;
		}

		SeleniumTestRunnerCMD seleniumTestCmd= new SeleniumTestRunnerCMD(driver);
		
		seleniumTestCmd.cmd();

	}

	static public WebDriver createInstanceWithChromeDriver() {
		System.setProperty("webdriver.chrome.driver","lib/SeleniumDriver/ChromeDriver/chromedriver");
		return new ChromeDriver();
	}
	
	static public WebDriver createInstanceWithRemoteDriver() throws MalformedURLException {
		
		ChromeOptions opt = new ChromeOptions();
		final String HOST_URL = "http://localhost:4444/";
		return new RemoteWebDriver(new URL(HOST_URL),opt);
	}
}
