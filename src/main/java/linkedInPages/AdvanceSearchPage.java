package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class AdvanceSearchPage extends LinkedInWrappers{
	String searchResults,actualResults;
	
	public AdvanceSearchPage(){
		if(!verifyTitle("Search | LinkedIn")){
			Reporter.reportStep("This is not LinkedIn Advance Search Page", "FAIL");
		}
	}
	
	public AdvanceSearchPage enterSearchKeyWord(String searhKeyWord){
		enterById(prop.getProperty("ADSearch.enterKeyWord.Id"), searhKeyWord);
		return this;
	}
	
	public AdvanceSearchPage closeSmallWindow(){
		clickByXpath(prop.getProperty("ADSearch.closeSmallWindow.Xpath"));
		return this;
	}
	
	public AdvanceSearchPage closeSecondConnections() throws InterruptedException{
		clickByXpath(prop.getProperty("ADSearch.closeSecondConnection.Xpath"));
		Thread.sleep(2000);
		return this;
	}
	
	public AdvanceSearchPage closeGroupConnections() throws InterruptedException{
		clickByXpath(prop.getProperty("ADSearch.closeGroupConnection.Xpath"));
		Thread.sleep(2000);
		return this;
	}

	public AdvanceSearchPage printSearchResuslts() throws InterruptedException{
		searchResults = getTextByXpath(prop.getProperty("ADSearch.SearchResultCount.Xpath"));
		searchResults = searchResults.replace(",","");
		searchResults = removeNonAlphaNumarics(searchResults);
		System.out.println("No of search results for the entered search keyWord are: "+searchResults);
		return this;
	}
	public AdvanceSearchPage compareStrings(){
		actualResults = getTextByXpath(prop.getProperty("ADSearch.ActualResultsCount.Xpath"));
		actualResults = actualResults.replace(",","");
		actualResults = removeNonAlphaNumarics(actualResults);
		compareTwoStrings(actualResults, searchResults);
		return this;
	}
	public AdvanceSearchPage clickOnSearch() throws InterruptedException{
		clickByClassName(prop.getProperty("ADSearch.ClickOnSearch.Class"));
		Thread.sleep(4000);
		return this;
	}
		public AdvanceSearchPage printFirstConnectionName(){
		System.out.println("Name of the first connection is: "+getTextByXpath(prop.getProperty("ADSearch.getFirstConnectionName.Xpath")));
		return this;
	}
	
	public AdvanceSearchPage getConnectionTypeForFirstUser(){
		
		String connectionType = getTextByXpath(prop.getProperty("ADSearch.getFirstUser.ConnectionType.Xpath"));
		//System.out.println(connectionType);
		if(connectionType.equalsIgnoreCase("1")){
			System.out.println("The first user belongs to FIRST connection");
		}
		else if(connectionType.equalsIgnoreCase("2")){
			System.out.println("The first user belongs to SECOND connection");
		}else
			System.out.println("The first user belongs to THIRD connection");
		
		return this;
	}
	public AdvanceSearchPage moveWebPage(String position) throws InterruptedException{
		scrollUpAndDown(position);
		Thread.sleep(2000);
		return this;
	}
	public AdvanceSearchPage enterCompanyName(String companyName){
		enterById(prop.getProperty("ADSearch.EnterCompanyName.Xpath"), companyName);
		return this;
	}
	public AdvanceSearchPage selectLocation(String 	selectByValue){
		selectDropDownByXpathValue(prop.getProperty("ADSearch.SelectLocation.Xpath"), selectByValue);
		return this;
	}
	public AdvanceSearchPage selectCountry(String country){
		selectDropDownByXpathVisibleText(prop.getProperty("ADSearch.SelectCountry.VisibleText.Xpath"), country);
		return this;
	}
	
	public AdvanceSearchPage clickOnReset(){
		clickByClassName(prop.getProperty("ADSearch.Click.Reset.Class"));
		return this;
	}
	public AdvanceSearchPage checkFirstConnections(){
		clickById(prop.getProperty("ADSearch.checkFirstConnections.Id"));
		return this;
	}
	
}
	

