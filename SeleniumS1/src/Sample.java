import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class Sample {
	
	static WebDriver wd;
	
	public static WebDriver initDvr(String dvr) {
		
		switch(dvr) {
		case "headless-chrome":
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Balamanikandan B\\OneDrive\\Desktop\\chromedriver.exe");
			ChromeOptions ch=new ChromeOptions();
			ch.addArguments("headless");
			wd=new ChromeDriver(ch);
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Balamanikandan B\\OneDrive\\Desktop\\chromedriver.exe");
			wd=new ChromeDriver();
			break;	
		case "firefox":
			System.setProperty("webdriver.gecko.driver","C:\\Users\\Balamanikandan B\\OneDrive\\Desktop\\geckodriver.exe");
			wd=new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver","C:\\Users\\Balamanikandan B\\OneDrive\\Desktop\\msedgedriver.exe");
			wd=new EdgeDriver();
			break;
		default:
			System.err.println("Out of scope!!!");
			break;
		}
			
		return wd;
	}

	
	@SuppressWarnings("resource")
	public static String inputData() {
		return new Scanner(System.in).nextLine();
	}
	
	public static void main(String[] args) {
		System.out.println("browser option pls...\n[headless-chrome,chrome,firefox,edge]");
		String bwr=inputData();
		System.out.println("Number1: ");
		String n1=inputData();
		System.out.println("Number2: ");
		String n2=inputData();
		System.out.println("math operation pls...\n[+,-,*,/]");
		String op=inputData();
		
		wd=initDvr(bwr);
	if(wd!=null) {
		try {
		wd.get("https://www.calculator.net/");
		wd.manage().window().maximize();
		Thread.sleep(5000);
		Actions act=(Actions)wd;
		act.sendKeys(wd.findElement(By.id("sciInput")),"6").build().perform();
		
		wd.findElement(By.xpath("//span[text()='"+n1+"']")).click();
		Thread.sleep(1000);
		wd.findElement(By.xpath("//span[text()='"+op+"']")).click();
		Thread.sleep(1000);
		wd.findElement(By.xpath("//span[text()='"+n2+"']")).click();
		Thread.sleep(1000);
		wd.findElement(By.xpath("//span[text()='=']")).click();
		Thread.sleep(1000);
		int runVal =Integer.parseInt(wd.findElement(By.id("sciOutPut")).getText().trim());
		System.out.println(runVal);
		int res=0;			
		if(op.equals("+")?(res=Integer.parseInt(n1)+Integer.parseInt(n2))==runVal:(op.equals("-")?(res=Integer.parseInt(n1)-Integer.parseInt(n2))==runVal:(op.equals("x")?(res=Integer.parseInt(n1)*Integer.parseInt(n2))==runVal:(op.equals("/")?(res=Integer.parseInt(n1)/Integer.parseInt(n2))==runVal:(op.equals("%")?(res=Integer.parseInt(n1)%Integer.parseInt(n2))==runVal:false))))) {
			System.out.println("Math operation validated successfully!");
		}else {
			System.err.println("Math operation validation failed: \nExpected: "+res+"\nBut in actual, "+runVal);
		}
		wd.close();
		System.out.println("Test successful using "+bwr);
		}catch(Exception e) {
			System.err.println("Execution failed at runtime due to below exception:\n"+e.getMessage());
			wd.quit();
		}
	}else {
		System.err.println("Webdriver cannot be instantiate!!!");
	}
	}
}
