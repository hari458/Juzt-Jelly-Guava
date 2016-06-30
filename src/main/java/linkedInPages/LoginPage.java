package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class LoginPage extends LinkedInWrappers{

	public LoginPage(){
		if(!verifyTitle("World’s Largest Professional Network | LinkedIn"))
			Reporter.reportStep("This is not LinkedIn Login Page", "FAIL");
	}	
	
	public LoginPage enterUserName(String userName){
		enterById(prop.getProperty("Login.UserName.Id"), userName);
		return this;
	}
	
	public LoginPage enterPassWord(String passWord){
		enterById(prop.getProperty("Login.Password.Id"), passWord);
		return this;
	}
	public HomePage clickLogin(){
		clickByXpath(prop.getProperty("Login.LoginButton.Xpath"));
		return new HomePage();
	}
	public HomePage loginToLinkedIn(String userName, String passWord, String expectedValue ){
		
		return new LoginPage().enterUserName(userName).enterPassWord(passWord).clickLogin();
	}
}
