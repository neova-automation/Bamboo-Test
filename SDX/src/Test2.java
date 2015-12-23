import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;




import org.testng.annotations.Test;

import bsh.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Test2 {


//public static void main(String[] args) {
	@Test
	public void open(){
	WebDriver driver = new FirefoxDriver();
	// driver.manage().window().maximize();
	driver.get("https://www.sdxcentral.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String sSectionName = "DemoFridays and Webinars";
	
	List<WebElement> list = driver.findElements(By.xpath("//*[text()='" + sSectionName
			+ "']/parent::*//parent::*//parent::*/li//*[@class='author']"));
	for(int i =1;i<=list.size();i++){
		
		WebElement authorEle =  driver.findElement(By.xpath("//*[text()='" + sSectionName
				+ "']/parent::*//parent::*//parent::*/li[" + i
				+ "]//*[@class='author']"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", authorEle);
	String authorText =authorEle.getText();

	String text = String.format("%s",  authorText);
	System.out.println(text);
	}
	driver.close();
	
	}
}

