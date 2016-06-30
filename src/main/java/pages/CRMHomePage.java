package pages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class CRMHomePage extends LinkedInWrappers{

	public CRMHomePage() {
		if(!verifyTitle("Create Lead | opentaps CRM"))
			Reporter.reportStep("This is not Create Lead Page", "FAIL");
	}	
















}
