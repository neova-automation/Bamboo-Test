package homepage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class NewTest1 {
  @Test
  public void f() {	  
	  
	  try{
	  
		  System.setProperty("webdriver.firefox.bin", "/home/bamboo/bamboo-agent-home/xml-data/build-dir/SDXQA-FIR-TEST/firefox/firefox-bin");
	  FirefoxBinary binary = new FirefoxBinary(new File("/home/bamboo/bamboo-agent-home/xml-data/build-dir/SDXQA-FIR-TEST/firefox/firefox-bin"));
	  FirefoxProfile profile = new FirefoxProfile();
	  WebDriver driver = new FirefoxDriver(binary, profile);
	  
	  System.out.println("Driver Initiazed");
	  //driver.manage().window().maximize(); 
	  driver.get("https://www.sdxcentral.com/"); // Navigate to SDXCentral 
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait for Elements to show up
	  System.out.println("Executed & Verified URL - " +driver.getTitle());
	  driver.close();	  
	  
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	  }
	  
	  
	  
  } 
}
