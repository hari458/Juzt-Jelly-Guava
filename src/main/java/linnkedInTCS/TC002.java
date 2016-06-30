package linnkedInTCS;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import linkedInPages.LoginPage;
import wrappers.LinkedInWrappers;

public class TC002 extends LinkedInWrappers{
	 
	@Test(dataProvider = "fetchData")
	public void advancedSearchTC2(String userName, String passWord, String expectedValue ) throws InterruptedException{
		new LoginPage()
		.loginToLinkedIn(userName, passWord, expectedValue)
		.clickOnAdvanceSearch()
		.closeSmallWindow()
		.closeSecondConnections()
		.closeGroupConnections()
		.printSearchResuslts()
		.compareStrings();
	
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="LinkedIn_TC02";
		browserName="chrome";
		testCaseName="Login to LinkedIn";
		testDescription="Login and finding the search results using advance search";
	}

}
