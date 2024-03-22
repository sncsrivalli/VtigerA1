package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import pomPages.CreateNewLeadPage;
import pomPages.LeadsPage;
import pomPages.NewLeadDetailsPage;

public class CreateLeadTest extends BaseClass {

	@Test
	public void createNewLeadTest() throws InterruptedException {
		LeadsPage lead = pageObjects.getLeadsPage();
		CreateNewLeadPage createLead = pageObjects.getCreateNewLeadPage();
		NewLeadDetailsPage newLead = pageObjects.getNewLeadDetailsPage();
		
		home.clickLeads();
		soft.assertEquals(lead.getPageHeader(), "Leads");
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Create lead", "LeadsTestData");
		String lastName = map.get("Last Name")+jutil.generateRandomNum(100);
		createLead.setLastName(lastName);
		createLead.setCompany(map.get("Company"));
		createLead.clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(lastName));
		newLead.clickLeadsLink();
		
		if(lead.getNewLeadName().equals(lastName)) {
			System.out.println("Test Pass");
			excel.updateTestStatus("Create lead", "Pass", IConstantPath.EXCEL_PATH, "LeadsTestData");
		}
		else {
			System.out.println("Test Fail");
			excel.updateTestStatus("Create lead", "Fail", IConstantPath.EXCEL_PATH, "LeadsTestData");
		}		
		soft.assertEquals(lead.getNewLeadName(), lastName);
		soft.assertAll();
	}
}
