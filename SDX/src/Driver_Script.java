
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Driver_Script {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Launch Homepage
		
		Action CurrentElement = new Action();
		WebDriver driver = CurrentElement.LaunchApp();
		CurrentElement.LoginToApp("neova.automation@gmail.com", "sdx@neova", driver);
		
		// -------- Read Execute Column, If y then apply strategy using switch case.
		
		InputStream ExcelFileToRead = new FileInputStream("Test.xls");
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row;
		HSSFCell cell;
		Iterator<Row> rows = sheet.rowIterator();
		String sResult = "";

		while (rows.hasNext())
		{
			row = (HSSFRow) rows.next();
			if (row.getCell(7).toString().equalsIgnoreCase("y")){				
				sResult = "";
				String arrStrategyColumn[] = row.getCell(6).toString().split("\\r?\\n");
				for (String sStrategy:arrStrategyColumn){
			    	switch (sStrategy) {
			         case "VerifyTextOfListItems":
			        	 if (CurrentElement.VerifyTextOfListItems(row.getCell(5).toString(),row.getCell(3).toString(), driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
						}
			             break;
			         case "VerifyLinkTextOfListItems":
			        	 if (CurrentElement.VerifyLinkTextOfListItems(row.getCell(5).toString(),row.getCell(3).toString(), driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
						}
			             break;
			         case "VerifySimilarityOfListItems":
			        	 if (CurrentElement.VerifySimilarityOfListItems(row.getCell(3).toString(), driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break;
			         case "VerifyText":
			        	 if (CurrentElement.VerifyText(row.getCell(5).toString(),row.getCell(3).toString(), driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break;   
			         case "VerifyContributedArticles":
			        	 if (CurrentElement.VerifyContributedArticles(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			         case "VerifyHighlights":
			        	 if (CurrentElement.VerifyHighlights(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			         case "VerifyFeaturedContent":
			        	 if (CurrentElement.VerifyFeaturedContent(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			             
			         case "VerifyNews":
			        	 if (CurrentElement.VerifyNews(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			         case "VerifySDxCentralOpEdAndSummaries":
			        	 if (CurrentElement.VerifySDxCentralOpEdAndSummaries(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			         case "VerifyVideosInDemoFridayMenu":
			        	 if (CurrentElement.VerifyVideosInDemoFridayMenu(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			         case "VerifyDemoFridaysWebinars":
			        	 if (CurrentElement.VerifyDemoFridaysWebinars(driver)){
			        		 	sResult = sResult +"Pass\n";
							}else{
								sResult = sResult +"Fail\n";
							}
			             break; 
			         case "VerifyBrokenLinksOfPage":
			        	 	System.out.print(CurrentElement.VerifyBrokenLinksOfPage(row.getCell(5).toString(), driver));
			        	 	break;			             
			         default:
			        	 sResult = sResult +"Strategy Not Implemented\n";
			    	}
			    	cell = row.createCell(8);
					cell.setCellValue(sResult);
				}
				FileOutputStream fileOut = new FileOutputStream("Test.xls");
				//write this workbook to an Output Stream.
				wb.write(fileOut);
				fileOut.flush();
			}
		}		
		driver.close(); // Close Browser   	
		System.out.println("Execution Completed");
	}
}
