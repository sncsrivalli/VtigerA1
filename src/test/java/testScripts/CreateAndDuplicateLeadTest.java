package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateAndDuplicateLeadTest extends BaseClass {

	@Test
	public void createAndDuplicateLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(lead.getPageHeader(), "Leads");
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Create and Duplicate Lead", "LeadsTestData");
		String lastName = map.get("Last Name")+jutil.generateRandomNum(100);
		createLead.setLastName(lastName);
		createLead.setCompany(map.get("Company"));
		createLead.clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(lastName));
		newLead.clickDuplicate();
		soft.assertTrue(duplicateLead.getPageHeader().contains("Duplicating"));
		String newLastName = map.get("New Last Name")+jutil.generateRandomNum(100);
		duplicateLead.setNewLastName(newLastName);
		duplicateLead.clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(newLastName));
		newLead.clickLeadsLink();
		
		if(lead.getNewLeadName().equals(newLastName)) {
			System.out.println("Test Pass");
			excel.updateTestStatus("Create and Duplicate Lead", "Pass", IConstantPath.EXCEL_PATH, "LeadsTestData");
		}
		else {
			System.out.println("Test Fail");
			excel.updateTestStatus("Create and Duplicate Lead", "Fail", IConstantPath.EXCEL_PATH, "LeadsTestData");
		}	
		soft.assertEquals(lead.getNewLeadName(), newLastName);
		soft.assertAll();
	}
}
