package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class MenuPage extends LinkedInWrappers{
	public MenuPage(){
		if(!verifyTitle("Welcome! | LinkedIn")){
			Reporter.reportStep("This is not a LinkedIn Home Page", "FAIL");
		}
	}
	public MenuPage clickOnHome(){
		clickByXpath(prop.getProperty("Menu.Click.Menu.Link"));
		return this;
	}
	public AdvanceSearchPage clickOnAdvanceSearch(){
		clickByXpath(prop.getProperty("Menu.Click.Advanced.Xpath"));
		return new AdvanceSearchPage();
	}
	public MessagingPage clickOnMessages() throws InterruptedException{
		mouseOverByXpath(prop.getProperty("Menu.MouseOver.Messaging"));
		Thread.sleep(1000);
		clickByName(prop.getProperty("Menu.Click.Messaging.Link"));
		return new MessagingPage();
	}
	public JobsPage clickOnJobs(){
		clickByXpath(prop.getProperty("Menu.Click.OnJobs.Xpath"));
		return new JobsPage();
	}
	public ProfilePage clickOnProfile(){
		clickByXpath(prop.getProperty("Menu.Click.Profile.Xpath"));
		return new ProfilePage();
	}
	public LogOut clickOnLogout(){
		mouseOverByXpath(prop.getProperty("Menu.MouseOver.Logout.Xpath"));
		clickByClassName(prop.getProperty("Menu.Click.Logout.Class"));
		return new LogOut();
	}
	
}
