package linnkedInTCS;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import linkedInPages.LoginPage;
import wrappers.LinkedInWrappers;

public class TC001 extends LinkedInWrappers{
	@Test(dataProvider = "fetchData")
	public void advancedSearchTC1(String userName, String passWord, String expectedValue, String searchKeyword) throws InterruptedException{
		new LoginPage()
		.loginToLinkedIn(userName, passWord, expectedValue)
		.clickOnAdvanceSearch()
		.enterSearchKeyWord(searchKeyword)
		.moveWebPage("down")
		.clickOnSearch()
		.printSearchResuslts()
		.getConnectionTypeForFirstUser();
		
	}

	@BeforeClass
	public void beforeClass() {
		dataSheetName="LinkedIn_TC01";
		browserName="chrome";
		testCaseName="Advance Search";
		testDescription="login to linkedIn, search with a keyword and pring the first connection details";
	}

}
