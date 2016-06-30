package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class ViewCompanyResults extends LinkedInWrappers{
	public ViewCompanyResults(){
		if(!verifyTitle("Adtech-QA Engineer Job at IBM in HYDERABAD | LinkedIn"))
			Reporter.reportStep("This is not Job details page", "FAIL");
	}
	
	public ViewCompanyResults printCompanyName() {
		System.out.println("The Company Name is :" + getTextByXpath(prop.getProperty("JobsPage.print.CompanyName.Xpath")));
		return this;
	}
	
	public CompanyDetails clickOnCompanyName() {
		clickByXpath(prop.getProperty("JobsPage.Click.CompanyName.Xpath"));
		return new CompanyDetails();
	}

}
