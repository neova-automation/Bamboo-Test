package homepage;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.*;

public class NewTest1 {
  @Test
  public void f() {	  
	  
	  	System.out.println("In Process IE Script");
	  	
	  	File file = new File("IEDriverServer.exe");
	  	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	  	WebDriver driver = new InternetExplorerDriver();
	  	System.out.println("Driver Initiazed");
		//driver.manage().window().maximize(); 
		driver.get("https://www.sdxcentral.com/"); // Navigate to SDXCentral 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait for Elements to show up
		System.out.println("Executed & Verified URL - " +driver.getTitle());
		driver.close();	  	
  }
}
