package automationTestCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utility.*;
import appModule.*;
import pageObjectRepositories.Objects_Guidance_Page;
import pageObjectRepositories.Objects_Navigation_Bar;

public class Guidance_Page_View_Item_Firefox_Administrator {

	//Declare our test variables
	public WebDriver driver;	
	private String sTestCaseName;
	private int iTestCaseRow;
	
	//Get data and open the browser
	@BeforeMethod
	public void beforeMethod() throws Exception {
	
		DOMConfigurator.configure("log4j.xml");
	
	    sTestCaseName = this.toString();
	
	    sTestCaseName = Utils.getTestCaseName(this.toString());
	
	    Log.startTestCase(sTestCaseName);
	
	    ExcelUtils.setExcelFile(Constant.Path_TestData + sTestCaseName + ".xlsx","Sheet1");
	
	    iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	
	    driver = Utils.openBrowser(iTestCaseRow);
	    
		//Verify that we are on the correct page
		Objects_Navigation_Bar.lnk_Stan_Home(driver).isDisplayed();
		Log.info("Home button displayed");
	
	}
	
	//Run the test
	@Test
	public void main() throws Exception {
	
		Objects_Navigation_Bar.lnk_Quick_Find(driver).click();
		Objects_Navigation_Bar.lnk_Guidance(driver).click();
		
		//Verify that we are on the correct page
		Utils.checkSearchFilter("ContentTags:Guidance", Objects_Guidance_Page.Guidance.txt_Guidance_Search(driver));
		
		//Navigate to the Investment News guidance item
		Objects_Guidance_Page.Guidance.lnk_Investment_News(driver).click();
		
		//Call the method to check the Policies Item page
		Methods_Guidance.viewItem(driver, sTestCaseName);
					
	}
	
	//Log out
	@AfterMethod
	public void afterMethod() {
	
	    driver.quit();
	    
	    Log.endTestCase(sTestCaseName);        
	
	}
}

