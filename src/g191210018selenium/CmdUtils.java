package g191210018selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CmdUtils {
	
	public static void assertEquals(String expected,String current) {
		try {
			org.junit.jupiter.api.Assertions.assertEquals(expected,current);
			System.out.println("\u001B[32m"+"Success"+"\u001B[0m");
		}catch(AssertionError err) {
			System.out.println("\u001B[31m" + err.getMessage() + "\u001B[0m");
		}
	}
}
