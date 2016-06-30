package wrappers;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.Reporter;

public class GenericWrappers {

	protected static RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,primaryWindowHandle,sHubUrl,sHubPort;

	public GenericWrappers() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Babu - TestLeaf
	 * @param url - The url with http or https
	 * 
	 */
	public boolean invokeApp(String browser) {
		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);
			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}else
				driver = new FirefoxDriver();
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterById(String idValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS");
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS");

		}catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
		}


		return bReturn;
	}
	
	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextContainsByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().contains(text)){
			Reporter.reportStep("The text: "+sText+" contains the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not contain the value :"+text, "FAIL");
		}


		return bReturn;
	}

	/**
	 * This method will close all the browsers
	 * @author Babu - TestLeaf
	 */
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Reporter.reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL");
		}

	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickById(String id) {
		boolean bReturn = false;
		try{
			driver.findElement(By.id(id)).click();
			Reporter.reportStep("The element with id: "+id+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByClassName(String classVal) {
		boolean bReturn = false;
		try{
			Thread.sleep(4000);
			driver.findElement(By.className(classVal)).click();
			Reporter.reportStep("The element with class Name: "+classVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The element with class Name: "+classVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByName(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.name(name)).click();
			Reporter.reportStep("The element with name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using link name as locator
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByLink(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.linkText(name)).click();
			Reporter.reportStep("The element with link name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with link name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.xpath(xpathVal))).build().perform();
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using link name as locator
	 * @param xpathVal  The link name (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByLinkText(String linkName) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.linkText(linkName))).build().perform();
			Reporter.reportStep("The mouse over by link : "+linkName+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	public String getTextByXpath(String xpathVal){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
		} catch (Exception e) {
			//e.printStackTrace();
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}

	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Babu - TestLeaf
	 */
	public boolean selectById(String id, String value) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(id))).selectByVisibleText(value);;
			Reporter.reportStep("The element with id: "+id+" is selected with value :"+value, "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: "+value+" could not be selected.", "FAIL");
		}
		return bReturn;
	}
	
	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));
	
	}
	/**
	 * This method will remove all the Alpha Numaric values from the given string
	 */
	public String removeNonAlphaNumarics(String value) {
		try {
			value.replace("[^a-zA-Z0-9]", "");
			Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(value);
			while (m.find()) {
				value = m.group(1);
			}
			Reporter.reportStep("Non AlphaNumaric characters from String :" + value + " are removed", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("No AlphaNumaric characters from the String :" + value + " can't be removed", "FAIL");
		}
		return value;

	}
	/**
	 * This method will compare two string values
	 * using equalsIngnorecase string method
	 */
	public void compareTwoStrings(String actualValue, String expectedValue){
		try{
			if(actualValue.equalsIgnoreCase(expectedValue)){
				System.out.println("Both the strings are matching");
			}
			Reporter.reportStep("Given string values are matching", "PASS");
		}catch(NullPointerException e){
			Reporter.reportStep("Given String values can't be compared", "FAIL");
		}
	}
	
	/**
	 * This method will scroll the webpage up or down as per the given input "UP" or "DOWN"
	 * using javascriptExecuter
	 */
	public void scrollUpAndDown(String position){
		try{
			if(position.equalsIgnoreCase("UP")){
				((JavascriptExecutor)driver).executeScript("scroll(0,0)");
			}
			else
				((JavascriptExecutor)driver).executeScript("scroll(0,200)");
			Reporter.reportStep("The webpage is moved to the required position", "PASS");
		}catch(Exception e){
			Reporter.reportStep("The webpage can't move to the required position", "FAIL");
		}
		
	}
	
	/**
	 * This method will verify the color of the webelement
	 * By taking xpath of the webelement, color and the attribute to which the color should find
	 * @param Xpatth - xpath of the webelement for which the color should found
	 * @param attributeProperty -CSS property of the webelement
	 */
	
	public void verifyColorByXpath(String xpath, String attributeOroperty) {
		String attributevalue = null, bluecolor = "rgba(0, 140, 201, 1)";
		try {
			attributevalue = driver.findElement(By.xpath(xpath)).getCssValue(attributeOroperty);
			if(attributevalue.equalsIgnoreCase(bluecolor)){
				System.out.println("The color is matching");
				Reporter.reportStep("Blue Color of the webElement is matching with " + attributevalue+" value", "PASS");
			}
			else
				Reporter.reportStep("Blue Color of the webElement is matching with " + attributevalue+" value", "PASS");
		} catch (Exception e) {
			System.out.println("No Such Element Exception : " + e);
		}
	}
	
	/**
	 * This method will confirm the provided skill is present or not
	 * By comparing the given skill with existing skills
	 * @param xpath - xpath of the list of existing skills
	 * @param skillToCheck - skill to be added if not duplicate
	 */
	public boolean verifySkillByXpath(String xpath, String skillToCheck) {
		boolean flag = false;
		try {
			List<WebElement> skill = driver.findElementsByXPath(xpath);
			for (WebElement skills : skill) {
				if (skills.getText().equalsIgnoreCase(skillToCheck)) {
					Reporter.reportStep("Duplicate Skill : Skill already exists" + skillToCheck, "PASS");
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No Such Xpath" + e);
		}return flag;
	}
	
	/**
	 * This method will select an option using "Index" from the list of drop down options
	 * @param xpath - xpath of the drop down
	 * @param index - index of the required value 
	 */
	public boolean selectDropDownByXpathIndex(String xpath, int index) {
		boolean flag = false;
		try {
			WebElement element = driver.findElementByXPath(xpath);
			Select se = new Select(element);
			se.selectByIndex(index);
			flag = true;
			Reporter.reportStep("One value selected from the drop down using index: "+index, "PASS");
		} catch (NoSuchElementException e) {
			Reporter.reportStep("value can't be selected from the drop down using index: "+index, "FAIL");
			e.printStackTrace();
		} catch (Exception e) {
			Reporter.reportStep("value can't be selected from the drop down using index: "+index, "FAIL");
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * This method will select an option using "value" from the list of drop down options
	 * @param xpath - xpath of the drop down
	 * @param value - value of the required option 
	 */
	public boolean selectDropDownByXpathValue(String xpath, String value) {
		boolean flag = false;
		try {
			WebElement element = driver.findElementByXPath(xpath);
			Select se = new Select(element);
			se.selectByValue(value);
			flag = true;
			Reporter.reportStep("One value selected from the drop down using 'value': "+value, "PASS");
		} catch (NoSuchElementException e) {
			Reporter.reportStep("One value can't be selected from the drop down using 'value': "+value, "FAIL");
			e.printStackTrace();

		} catch (Exception e) {
			Reporter.reportStep("One value can't be selected from the drop down using 'value': "+value, "FAIL");
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * This method will select an option using "visible text" from the list of drop down options
	 * @param xpath - xpath of the drop down
	 * @param visibleText - visible text of the required option
	 */
	public boolean selectDropDownByXpathVisibleText(String xpath, String visibleText) {
		boolean flag = false;
		try {
			WebElement element = driver.findElementByXPath(xpath);
			Select se = new Select(element);
			se.selectByVisibleText(visibleText);
			flag = true;
			Reporter.reportStep("One value selected from the drop down using 'Visible text': "+visibleText, "PASS");
		} catch (NoSuchElementException e) {
			Reporter.reportStep("One value can't be selected from the drop down using 'Visible text': "+visibleText, "FAIL");
			e.printStackTrace();
		} catch (Exception e) {
			Reporter.reportStep("One value can't be selected from the drop down using 'Visible text': "+visibleText, "FAIL");
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param xpath - xpath of the webelement
	 * @param skillToAdd - The data to be added to the existing skill set
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	
	public boolean enterByXpath(String xpath, String skillToAdd){
		boolean flag = false;
		try{
			driver.findElementByXPath(xpath).sendKeys(skillToAdd);
			Reporter.reportStep("The skill :"+skillToAdd +" is added to the list of skills", "PASS");
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			Reporter.reportStep("The skill :"+skillToAdd +" can't be added to the list of skills", "FAIL");
		}
		return flag;
	}

	/**
	 * This method will handle alert when ever this method is called
	 * 
	 * @param No parameters required
	 * @throws NoalertPresent Exception if alert is not present in the webpage
	 */
	public boolean handleAlert(){
		boolean flag = false;
		try{
			driver.switchTo().alert().accept();
			Reporter.reportStep("Alert is handled successfully", "PASS");
			flag = true;
		}catch(NoAlertPresentException e){
			Reporter.reportStep("Alert can't be handled", "FAIL");
		}
		return flag;
	}
	
}

