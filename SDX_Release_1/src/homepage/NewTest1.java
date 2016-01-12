package homepage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class NewTest1 {
	@Test
	public void f() {

		try {

			String Xport = System.getProperty("lmportal.xvfb.id", ":1");
			final File firefoxPath = new File(System.getProperty("lmportal.deploy.firefox.path", "/usr/bin/firefox"));
			FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
			firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
			// Start Firefox driver
			WebDriver driver = new FirefoxDriver(firefoxBinary, null);
			System.out.println("Driver Initiazed");
			// driver.manage().window().maximize();
			driver.get("https://www.sdxcentral.com/"); // Navigate to SDXCentral
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
			System.out.println("Executed & Verified URL - " + driver.getTitle());
			
			
			String sMenuName = "RESOURCES";
			String sSubMenuName = "Cloud";
			WebElement objMenu = driver.findElement(By.xpath("//*[@id='menu-main-123']/li[a//text()='" +sMenuName +"']"));
			WebElement objSubMenu = driver.findElement(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']" +"//li[a//text()='" +sSubMenuName +"']"));
			Actions build = new Actions(driver);
			build.moveToElement(objMenu).build().perform();
			//build.moveToElement(objSubMenu).build().perform();
			build.moveToElement(objSubMenu).click(objSubMenu).build().perform();
				
			System.out.println("Current URL : "+driver.getCurrentUrl());
			
			driver.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
			
			
		
	}
}
