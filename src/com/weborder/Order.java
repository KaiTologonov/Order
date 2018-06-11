package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}
	
	public static int randomCC() {
		Random r = new Random();
		int i = r.nextInt(3)+1;
		return i;
	}
	
	public static String randomQuantity() {
		Random r = new Random();
		int i = r.nextInt(100)+1;
		String str = "";
		String result = str + i;
		return result;
	}
	
	public static String randomZip() {
		Random r = new Random();
		int i = r.nextInt(99999)+1;
		String str = "";
		String result = str + i;
		return result;
	}
	
	public static String randomVisa() {
		Random r = new Random();
		long i = r.nextLong()+1;
		String str = "4";
		String result = str + i;
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", 
				"/Users/kairattologonov/Documents/selenium dependencies/drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		
		String expected = "Web Orders";
		String actual = driver.getTitle();
		
		if (actual.contains(expected)) {
			System.out.println("Login Passed");
		}else {
			System.out.println("Login Failed");
			System.out.println("Expected:\t" + expected);
			System.out.println("Actual:\t" + actual);
		}
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(randomQuantity());
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John ");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(randomString(5));
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(" Smith");
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(randomZip());
		
		switch (randomCC()) {
		case 1:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("4001435676546891");
			break;
		case 2:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("5001435676546891");
			break;
		case 3:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("400143567654689");
			break;
		}
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("07/22");
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		String expectedText = "New order has been successfully added.";
		String actualText = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong"))
                .getText();
		
		if(expectedText.equals(actualText)) {
			System.out.println("Forms has been succeffully submitted");
		}else {
			System.out.println("Form Submission failed");
		}
			
		
		
		
	}
}
