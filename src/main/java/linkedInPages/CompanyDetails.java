package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class CompanyDetails extends LinkedInWrappers{
	
	public CompanyDetails() {
		if(!verifyTitle("IBM: Careers & Employment | LinkedIn"))
			Reporter.reportStep("This is not Company Page", "FAIL");
	}
	
	public CompanyDetails printEmployeeCount() {
		System.out.println("No of Employees are: "+getTextByXpath(prop.getProperty("JobsPage.print.NoOfEmployees.Xpath")));
		return this;
	}

}
