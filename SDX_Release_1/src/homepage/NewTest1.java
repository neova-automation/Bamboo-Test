package homepage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class NewTest1 {
  @Test
  public void f() {	  
	  	WebDriver driver = new FirefoxDriver(); 
		//driver.manage().window().maximize(); 
		driver.get("https://www.sdxcentral.com/"); // Navigate to SDXCentral 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait for Elements to show up
		System.out.println(driver.getTitle());
		driver.close();
  }
}
