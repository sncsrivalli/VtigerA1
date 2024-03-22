package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import pomPages.ContactsPage;
import pomPages.CreateNewContactPage;
import pomPages.NewContactDetailsPage;

public class CreateContactTest extends BaseClass {

	@Test
	public void createNewContactTest() throws InterruptedException {
		ContactsPage contact = pageObjects.getContactsPage();
		CreateNewContactPage createContact = pageObjects.getCreateContactPage();
		NewContactDetailsPage newContact = pageObjects.getNewContactDetailsPage();
		
		home.clickContacts();
		soft.assertEquals(contact.getPageHeader(), "Contacts");
		contact.clickPlusButton();
		soft.assertEquals(createContact.getPageHeader(), "Creating New Contact");

		Map<String, String> map = excel.readFromExcel("Create Contact", "ContactsTestData");
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createContact.setLastName(lastName);
		createContact.clickSave();

		Thread.sleep(3000);
		soft.assertTrue(newContact.getPageHeader().contains(lastName));
		newContact.clickContactsLink();

		if (contact.getNewContactName().equals(lastName)) {
			System.out.println("Test pass");
			excel.updateTestStatus("Create Contact", "Pass", IConstantPath.EXCEL_PATH, "ContactsTestData");
		} else {
			System.out.println("Test fail");
			excel.updateTestStatus("Create Contact", "Fail", IConstantPath.EXCEL_PATH, "ContactsTestData");
		}

		soft.assertEquals(contact.getNewContactName(), lastName);
		soft.assertAll();
	}
}
