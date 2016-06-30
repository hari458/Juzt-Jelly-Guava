package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class jobSearchResults extends LinkedInWrappers {
	
	public jobSearchResults(){
		if(!verifyTitle("Search | LinkedIn"))
			Reporter.reportStep("This is not Search Results Page", "FAIL");
	}

	public jobSearchResults getJobSearchResults() {
		System.out.println(getTextByXpath(prop.getProperty("JobsPage.Print.SearchResults.Xpath")));
		return this;
	}
	
	public jobSearchResults verifyColorOfViewButton() {
		verifyColorByXpath(prop.getProperty("JobsPage.FirstViewButton.Xpath"), 
				prop.getProperty("JobsPage.ViewButton.AttributeValue"));
		return this;
	}
	
	public ViewCompanyResults clickOnSecondView() {
		clickByXpath(prop.getProperty("JobsPage.SecondViewButton.Xpath"));
		return new ViewCompanyResults();
		
	}

}
