package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class JobsPage extends LinkedInWrappers {
	
	public JobsPage(){
		if(!verifyTitle("Jobs Home | LinkedIn")){
			Reporter.reportStep("This is not LinkedIn Jobs Page", "FAIL");
		}
	}
	public JobsPage enterJobSearchKeyWord(String keyword) {
		enterById(prop.getProperty("JobsPage.Enter.SearchKeyWord.Id"), keyword);
		return this;
	}

	public jobSearchResults clickOnJobSearch() {
		clickByXpath(prop.getProperty("JobsPage.CLick.Search.Xpath"));
		return new jobSearchResults();
	}
}
