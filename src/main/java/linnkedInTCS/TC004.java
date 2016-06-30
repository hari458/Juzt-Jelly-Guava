package linnkedInTCS;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import linkedInPages.LoginPage;
import wrappers.LinkedInWrappers;

public class TC004 extends LinkedInWrappers{
	@Test(dataProvider = "fetchData")
	public void advancedSearchTC1(String userName, String passWord, String expectedValue, String searchKeyword, String color) throws InterruptedException{
		new LoginPage()
		.loginToLinkedIn(userName, passWord, expectedValue)
		.clickOnJobs()
		.enterJobSearchKeyWord(searchKeyword)
		.clickOnJobSearch()
		.getJobSearchResults()
		.verifyColorOfViewButton()
		.clickOnSecondView()
		.printCompanyName()
		.clickOnCompanyName()
		.printEmployeeCount();	
	}

	@BeforeClass
	public void beforeClass() {
		dataSheetName="LinkedIn_TC04";
		browserName="chrome";
		testCaseName="Job Search";
		testDescription="login to linkedIn, getting the first company details with the searched job";
	}
}
