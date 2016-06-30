package linkedInPages;

import utils.Reporter;

public class HomePage extends MenuPage{
	
	public HomePage(){
		if(!verifyTitle("Welcome! | LinkedIn")){
			Reporter.reportStep("This is not LinkedIn Home Page", "FAIL");
		}
	}
	
	public HomePage validateUser(String expectedName){
		verifyTextByXpath(prop.getProperty("Home.Verify.UserName.Xpath"), expectedName);
		return this;
	}
//	public HomePage printName(){
//		System.out.println(getTextByXpath(prop.getProperty("Home.Verify.UserName.Xpath")));
//		return this;
//	}
	
}
