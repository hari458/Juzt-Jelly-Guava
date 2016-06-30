package linnkedInTCS;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import linkedInPages.LoginPage;
import wrappers.LinkedInWrappers;

public class TC003 extends LinkedInWrappers{
	
	@Test(dataProvider = "fetchData")
	public void advancedSearchTC2(String userName, String passWord, String expectedValue, String skillTOAdd, String currentOrg ) throws InterruptedException{
		new LoginPage()
		.loginToLinkedIn(userName, passWord, expectedValue)
		.clickOnProfile()
		.getCurrentOrganization(currentOrg)
		.verifySkillsExist(skillTOAdd);
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC003_Profile";
		browserName="chrome";
		testCaseName="Adding skill to profile";
		testDescription="Login and add a skill if it is not available in the list of skills";
	}

}
