package linkedInPages;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class ProfilePage extends LinkedInWrappers{
	String skillAdded = "";
	public ProfilePage() {
		if(!verifyTitle("Edit Profile | LinkedIn"))
			Reporter.reportStep("This is not a Profile Page", "FAIL");
			//System.out.println("This is not a Profile Page");
		}
		public ProfilePage getCurrentOrganization(String currectORG) {
			
		String companyName = getTextByXpath(prop.getProperty("Profile.CurrentOrganization.Name.Xpath"));
		compareTwoStrings(companyName, currectORG);
		//getTextByXpath("//*[@id='overview-summary-current']/td/span/ol/li/span");
		return this;
		}
		
		public ProfilePage verifySkillsExist (String skillToAdd) throws InterruptedException {
			
			boolean flag = verifySkillByXpath(prop.getProperty("Profile.Existed.Skills.Xpath"),(prop.getProperty("Profile.skillToCheck")));
			if(!flag){
				skillAdded = skillToAdd;
				clickAddSkill()
				.enterSkill(skillToAdd)
				.clickAdd()
				.clickSave()
				.verifyAddedSkill();
			}
			else 
				System.out.println("Duplicate skill: Skill already exist");
			//skillsExist("//*[@id='yui-gen33']/fieldset/span[2]/span[1]", "PHP");
			return this;
			}
		
		public ProfilePage clickAddSkill () {
			clickByXpath(prop.getProperty("Profile.click.AddSkill.Xpath"));
			//clickByXpath("//*[@id='background-skills']/button");
			return this;
		}
		
		public ProfilePage enterSkill (String skillToAdd) throws InterruptedException {
			enterByXpath(prop.getProperty("Profile.EnterSkill.Xpath"), skillToAdd);
			Thread.sleep(2000);
			return this;
		}
		
		public ProfilePage clickAdd () {
			clickByXpath(prop.getProperty("Profile.click.Add.Xpath"));
			//clickByLink("Add");
			return this;
		}
		
		public ProfilePage clickSave () {
		clickByXpath(prop.getProperty("Profile.click.Save.Xpath"));
		//clickByXpath("//*[@id='skills-editor-form']/p/input");
		return this;
		}
		public ProfilePage verifyAddedSkill() throws InterruptedException{
			driver.navigate().refresh();
			Thread.sleep(4000);
			handleAlert();
			boolean flag = verifySkillByXpath(prop.getProperty("Profile.Existed.Skills.Xpath"),(prop.getProperty("Profile.skillToCheck")));
			if(flag){
				System.out.println("Skill added successfully");
			}
			else
				System.out.println("Skill not added");
			return this;
		}

}
