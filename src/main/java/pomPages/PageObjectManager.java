package pomPages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	LoginPage login;
	HomePage home;
	OrganizationsPage org;
	ContactsPage contact;
	LeadsPage lead;
	CreateNewOrganizationPage createOrg;
	CreateNewContactPage createContact;
	CreateNewLeadPage createLead;
	CreateNewEventPage createEvent;
	DuplicatingLeadPage duplicateLead;
	NewOrgDetailsPage newOrg;
	NewContactDetailsPage newContact;
	NewLeadDetailsPage newLead;
	NewEventDetailsPage newEvent;
	
	WebDriver driver;	
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage getLoginPage() {
		login = new LoginPage(driver);
		return login;
	}
	
	public HomePage getHomePage() {
		home = new HomePage(driver);
		return home;
	}

	public OrganizationsPage getOrganizationsPage() {
		org = new OrganizationsPage(driver);
		return org;
	}
	
	public ContactsPage getContactsPage() {
		contact = new ContactsPage(driver);
		return contact;
	}
	
	public LeadsPage getLeadsPage() {
		lead = new LeadsPage(driver);
		return lead;
	}
	
	public CreateNewOrganizationPage getCreateNewOrganizationPage() {
		createOrg = new CreateNewOrganizationPage(driver);
		return createOrg;
	}
	
	public CreateNewContactPage getCreateContactPage() {
		createContact = new CreateNewContactPage(driver);
		return createContact;
	}
	
	public CreateNewLeadPage getCreateNewLeadPage() {
		createLead = new CreateNewLeadPage(driver);
		return createLead;
	}
	
	public CreateNewEventPage getCreateNewEventPage() {
		createEvent = new CreateNewEventPage(driver);
		return createEvent;
	}
	
	public DuplicatingLeadPage getDuplicatingLeadPage() {
		duplicateLead = new DuplicatingLeadPage(driver);
		return duplicateLead;
	}
	
	public NewOrgDetailsPage getNewOrgDetailsPage() {
		newOrg = new NewOrgDetailsPage(driver);
		return newOrg;
	}
	
	public NewContactDetailsPage getNewContactDetailsPage() {
		newContact = new NewContactDetailsPage(driver);
		return newContact;
	}
	
	public NewLeadDetailsPage getNewLeadDetailsPage() {
		newLead = new NewLeadDetailsPage(driver);
		return newLead;
	}
	
	public NewEventDetailsPage getNewEventDetailsPage() {
		newEvent = new NewEventDetailsPage(driver);
		return newEvent;
	}
}
