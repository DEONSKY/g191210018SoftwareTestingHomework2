package g191210018selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CmdUtils {
	
	public static void assertEquals(String expected,String current,String assertionTitle) {
		try {
			org.junit.jupiter.api.Assertions.assertEquals(expected,current);
			System.out.println("\u001B[32m"+" "+assertionTitle+": Success :"+expected+"="+current+"\u001B[0m");
		}catch(AssertionError err) {
			System.out.println("\u001B[31m" + err.getMessage() + "\u001B[0m");
		}
	}
	
	public static void assertNotEquals(String expected,String current,String assertionTitle) {
		try {
			org.junit.jupiter.api.Assertions.assertNotEquals(expected,current);
			System.out.println("\u001B[32m"+" "+assertionTitle+": Success :"+expected+"!="+current+"\u001B[0m");
		}catch(AssertionError err) {
			System.out.println("\u001B[31m" + err.getMessage() + "\u001B[0m");
		}
	}
}
