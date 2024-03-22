package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import pomPages.CreateNewOrganizationPage;
import pomPages.NewOrgDetailsPage;
import pomPages.OrganizationsPage;

public class CreateOrgWithTypeAndIndustryTest extends BaseClass {

	@Test
	public void createOrgWithTypeAndIndustryTest() throws InterruptedException {
		OrganizationsPage org = pageObjects.getOrganizationsPage();
		CreateNewOrganizationPage createOrg = pageObjects.getCreateNewOrganizationPage();
		NewOrgDetailsPage newOrg = pageObjects.getNewOrgDetailsPage();
		
		home.clickOrganizations();
		soft.assertEquals(org.getPageHeader(), "Organizations");
		org.clickPlusButton();
		soft.assertEquals(createOrg.getPageHeader(), "Creating New Organization");

		Map<String, String> map = excel.readFromExcel("Create Organization With Industry And Type",
				"OrganizationsTestData");
		String orgName = map.get("Organization Name") + jutil.generateRandomNum(100);
		createOrg.setOrgName(orgName);
		createOrg.selectIndustry(web, map.get("Industry"));
		createOrg.selectType(web, map.get("Type"));
		createOrg.clickSave();

		Thread.sleep(3000);
		soft.assertTrue(newOrg.getPageHeader().contains(orgName));
		newOrg.clickOrgLink();
		if (org.getNewOrgName().equals(orgName)) {
			System.out.println("test pass");
			excel.updateTestStatus("Create Organization With Industry And Type", "Pass", IConstantPath.EXCEL_PATH,
					"OrganizationsTestData");
		} else {
			System.out.println("test fail");
			excel.updateTestStatus("Create Organization With Industry And Type", "Fail", IConstantPath.EXCEL_PATH,
					"OrganizationsTestData");
		}
		soft.assertEquals(org.getNewOrgName(), orgName);
		soft.assertAll();
	}
}
