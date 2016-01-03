package homepage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class NewTest2 {
  @Test
  public void f() {
	  
	  
	  
	  
	  
	  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	  //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	  //capabilities.setCapability("ignoreProtectedModeSettings", true);
	  
	    
	  //capabilities.setCapability("acceptSslCerts", true);
	  
	  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	  capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	  
	  
	  
	  
	  
	  System.out.println("JavaScript - " +DesiredCapabilities.internetExplorer().isJavascriptEnabled());
	  
	  //capabilities.setCapability("unexpectedAlertBehaviour","accept");
	  
	  
	  File file = new File("IEDriverServer.exe");
	  
	  System.out.println(file.getPath());
	  
	  System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	  
	  
	  WebDriver myTestDriver = new InternetExplorerDriver(capabilities);
	  myTestDriver.get("https://www.google.com/");
	  myTestDriver.manage().window().maximize();
	  myTestDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  
	  myTestDriver.get("https://www.sdxcentral.com/"); // Navigate to SDXCentral 
	  myTestDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait for Elements to show up
	  System.out.println("Executed & Verified URL - " +myTestDriver.getTitle());
	  myTestDriver.close();	  
  }
}
