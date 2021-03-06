package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginStepDefinition{

	
	WebDriver wd;
	FileWriter fw;
	 // your code here
	
	@Given("user is already on Login Page")
	public void user_is_already_on_Login_Page() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Balamanikandan B\\OneDrive\\Desktop\\chromedriver.exe");
		wd=new ChromeDriver();
		wd.get("https://qa-fake.herokuapp.com/");
		wd.manage().window().maximize();
		
	}
	@When("title of login page is QA Fake")
	public void title_of_login_page_is_QA_Fake() {
		assertTrue(wd.getTitle().equals("QA Fake"));
	}
	@Then("user enters (.*) and (.*)")
	public void user_enters_username_and_password(String una,String pwd){
		wd.findElement(By.id("username")).sendKeys(una.replace("\"",""));
		wd.findElement(By.id("password")).sendKeys(pwd.replace("\"",""));
	}
	@Then("user checked rember me")
	public void user_checked_remember_me() {
		wd.findElement(By.id("rememberme")).click();
	}
	
	@Then("user clicks on login button")
	public void user_clicks_on_login_button(){
		wd.findElement(By.xpath("//button[text()='Login']")).click();
	}
	@Then("user get the secret string on home screen")
	public void user_get_the_secret_string_on_home_screen(){
		wd.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebElement ele=wd.findElement(By.xpath("//div[@id='result']"));
		String op=ele.getText();
		try {
			fw=new FileWriter("/FreeCrmBDDFramework/result.txt");
			fw.write(op);
			fw.close();
			System.out.println("Secret string retrieved and stored in results.txt under root folder of the project");
		} catch (IOException e) {
			System.err.println("Below Exception occured in output file writer!!!\n"+e.getMessage());
		}
	}
	@Then("Close the browser")
	public void Close_the_browser() {
		wd.close();
	}
	 
}
