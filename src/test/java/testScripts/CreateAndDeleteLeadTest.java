package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;

public class CreateAndDeleteLeadTest extends BaseClass {

	@Test
	public void createAndDeleteLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(lead.getPageHeader(), "Leads");
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Delete lead", "LeadsTestData");
		String lastName = map.get("Last Name")+jutil.generateRandomNum(100);
		createLead.setLastName(lastName);
		createLead.setCompany(map.get("Company"));
		createLead.clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(lastName));
		newLead.clickLeadsLink();
		
		Thread.sleep(2000);
		lead.deleteLead(web, lastName);
		web.handleAlert("OK");
		
		Thread.sleep(2000);
		soft.assertFalse(lead.searchLead(lastName));
		soft.assertAll();		
	}
}
