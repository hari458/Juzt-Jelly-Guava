package pages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class CreateLeadPage extends LinkedInWrappers{

	public CreateLeadPage() {
		if(!verifyTitle("My Home | opentaps CRM"))
			Reporter.reportStep("This is not CRM Home Page", "FAIL");
	}	
















}
