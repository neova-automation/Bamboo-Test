import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Action extends Driver_Script{	
	
	public Action(){
		// Constructor For Actions.
	}	
	
	// This Method verifies if the Web Element Matches the Text Label
	// Returns True If WebElement Found and Match Is Positive; Else False
	public boolean VerifyText(String sElementToVerify, String sWebElementXPath, WebDriver objDriver){
		WebElement objWebElement = objDriver.findElement(By.xpath(sWebElementXPath));
		if(objWebElement.isEnabled()&& objWebElement.getText().equals(sElementToVerify) ){
			return true;
		}else{
			return false;
		}
	}
	
	// This Method verifies if the Web Element have similar CSS Properties.
	// Returns True If All List items are Found To have similar CSS properties; Else False
	public boolean VerifySimilarityOfListItems(String sWebElementXPath, WebDriver objDriver){		
		
		boolean pass = false;
		List<WebElement> list = objDriver.findElements(By.xpath(sWebElementXPath));
						
		// Create Reference to compare each element.
		WebElement ReferenceElement = objDriver.findElement(By.xpath(sWebElementXPath));	
		String sFontSize = ReferenceElement.getCssValue("font-size");
		String sColor =ReferenceElement.getCssValue("color");
		String sFamily =ReferenceElement.getCssValue("font-family");
		String sBackColor = ReferenceElement.getCssValue("background-color");
		String sTextAlign = ReferenceElement.getCssValue("text-align");
		
		for (WebElement we: list) { 
			if(we.getCssValue("font-size").equals(sFontSize)&& we.getCssValue("color").equals(sColor)&& we.getCssValue("font-family").equals(sFamily)&& we.getCssValue("background-color").equals(sBackColor)&& we.getCssValue("text-align").equals(sTextAlign)){
				pass = true;
			}
			else{
				pass = false;
				break;
			}	   	    
		}
		return pass;
	}

	// This Method verifies if the Web Element have similar CSS Properties.
		// Returns True If All List items are Found To have similar CSS properties; Else False
		public boolean VerifyTextOfListItems(String sListItemNames, String sWebElementXPath, WebDriver objDriver){		
		int i = 0;
		List<WebElement> list = objDriver.findElements(By.xpath(sWebElementXPath));	
		String lines[] = sListItemNames.split("\\r?\\n");
		
		if (list.size() != lines.length){
			return false;			
		}

		boolean pass = false;
		for (WebElement we: list) { 
			//System.out.println(we.getText()+"---"+lines[i]);
			if(we.getText().matches(lines[i] +"(\n)?(.*)")){
				pass = true;
			}
			else{
				pass = false;
				System.out.println(we.getText() +" " +lines[i]);
				break;
			}
				i++;
		}
		return pass;
	}		
		
	//Launch sdxcentral.com in Firefox
		
	public WebDriver LaunchApp(){		
		
		WebDriver driver;	
		driver = new FirefoxDriver(); 
		//driver.manage().window().maximize(); 
		driver.get("https://www.sdxcentral.com/"); // Navigate to SDXCentral 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait for Elements to show up
		return driver;
	}

	// This Method verifies if the Web Element have similar CSS Properties.
			// Returns True If All List items are Found To have similar CSS properties; Else False
			public boolean VerifyLinkTextOfListItems(String sListItemNames, String sWebElementXPath, WebDriver objDriver){		
			int i = 0;
			List<WebElement> list = objDriver.findElements(By.xpath(sWebElementXPath));	
			String lines[] = sListItemNames.split("\\r?\\n");
			
			if (list.size() != lines.length){
				return false;			
			}

			boolean pass = false;
			for (WebElement we: list) { 
				//System.out.println(we.getAttribute("href")+"---"+lines[i]);
				if(we.getAttribute("href").contains(lines[i])){
					pass = true;
				}
				else{
					pass = false;
					System.out.println(we.getAttribute("href") +" " +lines[i]);
					break;
				}
					i++;
			}
			return pass;
		}
				
			// This Method verifies the links on a page should have 200 Response.
			// Returns String of Links & their Response Code.
			public String VerifyBrokenLinksOfPage(String sPageUrl, WebDriver objDriver) throws IOException{
				String sReturnValue = "";
				int ncount = 0;
				if (!objDriver.getCurrentUrl().equalsIgnoreCase(sPageUrl)){
					return "Wrong URL Opened\n";
				}
				List<WebElement> links = objDriver.findElements(By.tagName("a"));				
				for (WebElement we:links){	
					HttpClient client = HttpClientBuilder.create().build();
					HttpGet request = new HttpGet(we.getAttribute("href"));
					HttpResponse response = client.execute(request);
					// verifying response code and The HttpStatus should be 200,
					if (response.getStatusLine().getStatusCode() != 200){
						sReturnValue = sReturnValue +we.getAttribute("href") +" " +response.getStatusLine().getStatusCode() +"\n";
						ncount = ncount + 1;
					}
				}				
				if(ncount > 0){
					return sReturnValue;
				}else{
					return "No Broken Link Found";
				}
		}
			
			// This Method verifies the links on a page for 404 & 403 Response.
			public boolean LoginToApp(String sUserName, String sPassword, WebDriver objDriver){
				
				WebElement SdxPageLoginButton = objDriver.findElement(By.linkText("LOG IN"));
				SdxPageLoginButton.click();				
				WebElement Username = objDriver.findElement(By.xpath("//*[@class='login-username']//*[@class='input']"));
				WebElement Password = objDriver.findElement(By.xpath("//*[@class='login-password']//*[@class='input']"));
				WebElement LoginButton = objDriver.findElement(By.xpath("//*[@value='Log In' or @type='submit']"));
				Username.sendKeys(sUserName);
				Password.sendKeys(sPassword);
				LoginButton.click();
				WebElement CheckUserLogin = objDriver.findElement(By.xpath("//ul[@class='menu-member'][contains(.,'Hi,')]"));
				return CheckUserLogin.isEnabled();
				
			}		
			
			// This Method verifies the Functionality Of Demo Friday Webinar Section.						
			@SuppressWarnings("finally")
			public boolean VerifyDemoFridaysWebinars(WebDriver objDriver){
							
				boolean  pass = false;
				objDriver.get("https://www.sdxcentral.com/");
				objDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				String sSectionName = "DemoFridays and Webinars";
				List<WebElement> listitems = objDriver.findElements(By
						.xpath("//*[text()='" + sSectionName
								+ "']/parent::*//parent::*//ul/li"));
				
				WebElement objDate;
				WebElement objAuthor;
				WebElement objHeader;
				WebElement obj_Watch_Register;
				WebElement ObjRegister;
				WebElement ObjWatch ;
				String text="";
				String textWatch = "";
				String textRegister = "";
				
				try{

				for (int i = 1; i <= listitems.size(); i++) {
					
					objDate = objDriver.findElement(By.xpath("//*[text()='" + sSectionName
							+ "']/parent::*//parent::*//parent::*/li[" + i
							+ "]//*[@class='date']"));
					objAuthor = objDriver.findElement(By.xpath("//*[text()='"
							+ sSectionName + "']/parent::*//parent::*//parent::*/li["
							+ i + "]//*[@class='author']"));
					objHeader = objDriver.findElement(By.xpath("//*[text()='"
							+ sSectionName + "']/parent::*//parent::*//parent::*/li["
							+ i + "]//*[@itemprop='headline']"));
					
						try{
					obj_Watch_Register = objDriver.findElement(By.xpath("//*[text()='" + sSectionName + "']/parent::*//parent::*//parent::*/li["+i+"]//*[contains(.,'Watch Now') or contains(.,'Register')]"));
					text = obj_Watch_Register.getText();
					((JavascriptExecutor) objDriver).executeScript("arguments[0].scrollIntoView(true);", obj_Watch_Register);
						}catch(Exception e){
							System.out.println("Watch Now or Register element not found and exception occured "+e.getMessage());
						}
					ObjWatch = objDriver
							 .findElement(By
							 .xpath("//*[text()='" + sSectionName
							 + "']/parent::*//parent::*//parent::*/li//*[text()='Watch Now']"));	
					
					 textWatch = ObjWatch.getText();
					
					ObjRegister = objDriver
							 .findElement(By
							 .xpath("//*[text()='"
							 + sSectionName
							 + "']/parent::*//parent::*//parent::*/li//*[text()='Register']"));
					
					 textRegister = ObjRegister.getText();
					 
					 if(ObjWatch.isEnabled()||ObjRegister.isEnabled()){
							if(text.equals(textWatch)){
								if(objDate.isEnabled() && objAuthor.isEnabled()
								&& objHeader.isEnabled()&&ObjWatch.isEnabled()){
									pass = true;
								}
							}else if(text.equals(textRegister)) {
								if((objDate.isEnabled() && objAuthor.isEnabled()
										&& objHeader.isEnabled()&&ObjRegister.isEnabled())){
									pass = true;
								}
							}
						}
					} 
			}catch(Exception e){
				System.out.println("Exception occured : In VerifyDemoFridaysWebinars");
			}finally{
					return pass;
				}				
			}
			
			// This Method verifies the Functionality Of Demo Friday Webinar Section.						
			@SuppressWarnings("finally")
			public boolean VerifyVideosInDemoFridayMenu(WebDriver objDriver){
							
			boolean  pass = false;
			objDriver.get("https://www.sdxcentral.com/");
			objDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			try{
				String sMenuName = "RESOURCES";
				String sSubMenuName = "SDN & NFV DemoFriday";
				WebElement objMenu = objDriver.findElement(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']"));
				WebElement objSubMenu = objDriver.findElement(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']" +"//li[a//text()='" +sSubMenuName +"']"));
				List<WebElement> list = objDriver.findElements(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']" +"//li[a//text()='" +sSubMenuName +"']" +"/ul/li[*][not(ul)]"));
				WebElement we;
				
				int nCounter = 0;
				for (int i=1;i<=list.size();i++){			
					Actions build = new Actions(objDriver);
					objMenu = objDriver.findElement(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']"));
					objSubMenu = objDriver.findElement(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']" +"//li[a//text()='" +sSubMenuName +"']"));
					we = objDriver.findElement(By.xpath("//*[@id='menu-main-1']/li[a//text()='" +sMenuName +"']"+"//li[a//text()='" +sSubMenuName +"']" +"/ul/li[*][not(ul)][" +i +"]"));
					build.moveToElement(objMenu).build().perform();
					build.moveToElement(objSubMenu).build().perform();
					build.moveToElement(we).click(we).build().perform();
					if(objDriver.findElement(By.xpath("//iframe[starts-with(@id,'v')]")).isEnabled()){
						nCounter = nCounter + 1;
					}else{
						break;
					}			
				}		
				if(nCounter == list.size()){
					pass = true;
				}else{
					pass = false;
				}
									 
			}catch(Exception e){
				System.out.println("Exception occured : VerifyVideosInDemoFridayMenu");
			}finally{
					return pass;
				}
				
			}
			
			// This Method verifies the Functionality Of Demo Friday Webinar Section.						
			@SuppressWarnings("finally")
			public boolean VerifySDxCentralOpEdAndSummaries(WebDriver driver){
							
			boolean  pass = false;
			driver.get("https://www.sdxcentral.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			try{
						
			String sSectionName = "SDxCentral OpEd and Summaries";

			List<WebElement> listitems = driver.findElements(By
					.xpath("//*[text()='" + sSectionName
							+ "']/parent::*//parent::*//ul/li"));

			int nCounter = 0;
			WebElement objDate;
			WebElement objAuthor;
			WebElement objHeader;
			WebElement objImageContainer;

			for (int i = 1; i <= listitems.size(); i++) {

				objDate = driver.findElement(By.xpath("//*[text()='" + sSectionName
						+ "']/parent::*//parent::*//parent::*/li[" + i
						+ "]//*[@class='date']"));
				objAuthor = driver.findElement(By.xpath("//*[text()='"
						+ sSectionName + "']/parent::*//parent::*//parent::*/li["
						+ i + "]//*[@class='author']"));
				objHeader = driver.findElement(By.xpath("//*[text()='"
						+ sSectionName + "']/parent::*//parent::*//parent::*/li["
						+ i + "]//*[@itemprop='headline']"));
				objImageContainer = driver.findElement(By.xpath("//*[text()='"
						+ sSectionName + "']/parent::*//parent::*//parent::*/img"));

				if (objDate.isEnabled() && objAuthor.isEnabled()
						&& objHeader.isEnabled() && objImageContainer.isEnabled()) {
					nCounter = nCounter + 1;
				} else {
					break;
				}
			}
			if (nCounter == listitems.size()) {
				pass = true;
			} else {
				pass = false;
			}
			
			if(pass){
				// SDxCentral OpEd and Summaries Part 2
				int nCounter1 = 0;
	
				List<WebElement> listitems1 = driver
						.findElements(By
								.xpath("//*[@class='section-inner clearfix column-reverse']//li"));
	
				for (int i = 1; i <= listitems1.size(); i++) {
	
					objDate = driver
							.findElement(By
									.xpath("//*[@class='section-inner clearfix column-reverse']//li["
											+ i + "]//*[@class='date']"));
					objAuthor = driver
							.findElement(By
									.xpath("//*[@class='section-inner clearfix column-reverse']//li["
											+ i + "]//*[@class='author']"));
					objHeader = driver
							.findElement(By
									.xpath("//*[@class='section-inner clearfix column-reverse']//li["
											+ i + "]//*[@itemprop='headline']"));
					objImageContainer = driver
							.findElement(By
									.xpath("//*[@class='section-inner clearfix column-reverse']//img"));
	
					if (objDate.isEnabled() && objAuthor.isEnabled()
							&& objHeader.isEnabled() && objImageContainer.isEnabled()) {
						nCounter1 = nCounter1 + 1;
					} else {
						break;
					}
				}
				if (nCounter1 == listitems1.size()) {
					pass = true;
				} else {
					pass = false;
				}
			}	
			}catch(Exception e){
				System.out.println("Exception occured : VerifySDxCentralOpEdAndSummaries");
			}finally{
					return pass;
				}				
			}
			
			// This Method verifies the Functionality Of Demo Friday Webinar Section.						
			@SuppressWarnings("finally")
			public boolean VerifyNews(WebDriver driver){							
			boolean  pass = false;
			driver.get("https://www.sdxcentral.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			try{
				
				String sSectionName = "News";
				List<WebElement> listitems = driver
						.findElements(By
								.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='"
										+ sSectionName
										+ "']/parent::*//parent::*//ul/li"));
				int nCounter = 0;
				WebElement objDate;
				WebElement objAuthor;
				WebElement objHeader;
				WebElement objImageContainer;

				for (int i = 1; i <= listitems.size(); i++) {

					objDate = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[@class='date']"));
					objAuthor = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[@class='author']"));
					objHeader = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[@itemprop='headline']"));
					objImageContainer = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[img]"));

					if (objDate.isEnabled() && objAuthor.isEnabled()
							&& objHeader.isEnabled() && objImageContainer.isEnabled()) {
						nCounter = nCounter + 1;
					} else {
						break;
					}
				}
				if (nCounter == listitems.size()) {
					pass = true;
				} else {
					pass = false;
				}
									 
			}catch(Exception e){
				System.out.println("Exception occured : VerifyNews");
			}finally{
					return pass;
				}				
			}
			
			// This Method verifies the Functionality Of Demo Friday Webinar Section.						
			@SuppressWarnings("finally")
			public boolean VerifyHighlights(WebDriver driver){
							
			boolean  pass = false;
			driver.get("https://www.sdxcentral.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			int j=0;
			String src[] = new String[4];
			src[0] = "https://www.sdxcentral.com/wp-content/uploads/2015/12/oracle-cloud-saas-paas-iaas-amazon-web-services-google-cloud-platform-460x230.jpg";
			src[1] = "https://www.sdxcentral.com/wp-content/uploads/2015/12/Screen-Shot-2015-12-16-at-1.19.33-PM-460x230.png";
			src[2] = "https://www.sdxcentral.com/wp-content/uploads/2015/12/container-survey-announcemnet-460x230.png";
			src[3] = "https://www.sdxcentral.com/wp-content/uploads/2015/11/network-virtualization-report-download-available-2015-460x230.png";
		
			String alt[] = new String[4];
			alt[0] = "attachment-slide size-slide wp-post-image";
			alt[1] = "Alcatel-Lucent Virtualizes the RAN";
			alt[2] = "container-survey-announcemnet";
			alt[3] = "attachment-slide size-slide wp-post-image";
			
			List<WebElement> lists = driver.findElements(By.xpath("//*[starts-with(@*,'home-slide')]/a/img"));
			String sliderSrc;
			String sliderAlt;
			WebElement Currentitem;
			WebElement slideItem;
			try{
				
			for(int i=1;i<=lists.size();i++){
				try{
				slideItem = driver.findElement(By.xpath("//*[@*='title']/parent::*/parent::*/parent::*/parent::*/ul[@class='slides-pagination']/li["+i+"]/a"));
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[starts-with(@*,'home-slide')]["+i+"]/a/img")));
				Currentitem = driver.findElement(By.xpath("//*[starts-with(@*,'home-slide')]["+i+"]/a/img"));
				sliderSrc= Currentitem.getAttribute("src");
				sliderAlt= Currentitem.getAttribute("alt");
				
				if(slideItem.isEnabled()&& Currentitem.isEnabled()){
					//System.out.println("Yes");
					pass= true;
				}else{
					//System.out.println("No");
					pass = false;
					break;
				}
				
//				if(sliderSrc.equals(src[j])&& sliderAlt.equals(alt[j])){
//					pass = true;
//				}else{
//					pass = false;
//					break;
//				}
				j++;
				}catch(Exception e){
					System.out.println("Element not found exception occured"+e.getMessage());
				}
				
				//System.out.println("completed : "+i);
				}
			}catch(Exception e){
				System.out.println("Exception occured VerifyHighlights");
			}finally{
				return pass;
			}
				
			}
			
			// This Method verifies the Functionality Of Demo Friday Webinar Section.						
			@SuppressWarnings("finally")
			public boolean VerifyFeaturedContent(WebDriver driver){
							
			boolean  pass = false;
			driver.get("https://www.sdxcentral.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			try{
						
			String sSectionName = "Featured Content";
			List<WebElement> listitems = driver.findElements(By.xpath("//*[@class='home-section clearfix home-one-column-list'][//text()='" +sSectionName +"']/ul/li"));
			int nCounter = 0;
			WebElement objDate;
			WebElement objAuthor;
			WebElement objHeader;
			WebElement objImageContainer;
			
			for (int i=1;i<=listitems.size();i++){
				
				objDate = driver.findElement(By.xpath("//*[@class='home-section clearfix home-one-column-list'][//text()='" +sSectionName +"']/ul/li[" +i +"]//*[@class='date']"));
				objAuthor = driver.findElement(By.xpath("//*[@class='home-section clearfix home-one-column-list'][//text()='" +sSectionName +"']/ul/li[" +i +"]//*[@class='author']"));
				objHeader = driver.findElement(By.xpath("//*[@class='home-section clearfix home-one-column-list'][//text()='" +sSectionName +"']/ul/li[" +i +"]//*[@itemprop='headline']"));
				objImageContainer = driver.findElement(By.xpath("//*[@class='home-section clearfix home-one-column-list'][//text()='" +sSectionName +"']/ul/li[" +i +"]//*[img]"));
				
				if (objDate.isEnabled()&& objAuthor.isEnabled()&& objHeader.isEnabled()&& objImageContainer.isEnabled()){
					nCounter = nCounter + 1;
				}else{
					break;
				}			
			}
			if(nCounter == listitems.size()){
				pass = true;
			}else{
				pass = false;
			}	
				
			}catch(Exception e){
				System.out.println("Exception occured : VerifyFeaturedContent");
			}finally{
					return pass;
				}				
			}
			
			
			// This Method verifies the Functionality Of Contributed Articles.						
			@SuppressWarnings("finally")
			public boolean VerifyContributedArticles(WebDriver driver){
							
			boolean  pass = false;
			driver.get("https://www.sdxcentral.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			try{
				String sSectionName = "Contributed Articles";
				List<WebElement> listitems = driver
						.findElements(By
								.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='"
										+ sSectionName
										+ "']/parent::*//parent::*//ul/li"));

				int nCounter = 0;
				WebElement objDate;
				WebElement objAuthor;
				WebElement objHeader;
				WebElement objImageContainer;

				for (int i = 1; i <= listitems.size(); i++) {

					objDate = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[@class='date']"));
					objAuthor = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[@class='author']"));
					objHeader = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[@itemprop='headline']"));
					objImageContainer = driver
							.findElement(By
									.xpath("//*[@class='home-section clearfix home-two-column-list']//*[text()='" +sSectionName +"']/parent::*//parent::*//parent::*/li["
											+ i + "]//*[img]"));

					if (objDate.isEnabled() && objAuthor.isEnabled()
							&& objHeader.isEnabled() && objImageContainer.isEnabled()) {
						nCounter = nCounter + 1;
					} else {
						break;
					}
				}
				if (nCounter == listitems.size()) {
					pass = true;
				} else {
					pass = false;
				}		 
			}catch(Exception e){
				System.out.println("Exception occured : VerifyContributedArticles");
			}finally{
					return pass;
				}				
			}
			
}
