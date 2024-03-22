package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import pomPages.ContactsPage;
import pomPages.CreateNewContactPage;
import pomPages.NewContactDetailsPage;

public class CreateContactWithExistingOrgTest extends BaseClass {

	@Test
	public void createContactWithOrgTest() throws InterruptedException {
		ContactsPage contact = pageObjects.getContactsPage();
		CreateNewContactPage createContact = pageObjects.getCreateContactPage();
		NewContactDetailsPage newContact = pageObjects.getNewContactDetailsPage();
		
		home.clickContacts();
		soft.assertEquals(contact.getPageHeader(), "Contacts");
		contact.clickPlusButton();
		soft.assertEquals(createContact.getPageHeader(), "Creating New Contact");

		Map<String, String> map = excel.readFromExcel("Create Contact With Organization", "ContactsTestData");
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createContact.setLastName(lastName);
		createContact.selectExistingOrg(web, map.get("Organization Name"));
		createContact.clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(newContact.getPageHeader().contains(lastName));
		newContact.clickContactsLink();
		
		if(contact.getNewContactName().equals(lastName)) {
			System.out.println("Test Pass");
			excel.updateTestStatus("Create Contact With Organization", "Pass", IConstantPath.EXCEL_PATH, "ContactsTestData");
		}
		else {
			System.out.println("Test Fail");
			excel.updateTestStatus("Create Contact With Organization", "Fail", IConstantPath.EXCEL_PATH, "ContactsTestData");
		}
		soft.assertEquals(contact.getNewContactName(), lastName);
		soft.assertAll();
	}
}
