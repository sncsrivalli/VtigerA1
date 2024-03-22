package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import pomPages.CreateNewEventPage;
import pomPages.NewEventDetailsPage;

public class CreateEventTest extends BaseClass {

	@Test
	public void createNewEventTest() {
		CreateNewEventPage createEvent = pageObjects.getCreateNewEventPage();
		NewEventDetailsPage newEvent = pageObjects.getNewEventDetailsPage();
		
		Map<String, String> map = excel.readFromExcel("Create New Event", "EventsTestData");
		
		home.selectFromQuickCreate(web, map.get("Quick Create"));
		String subject = map.get("Subject")+jutil.generateRandomNum(100);
		createEvent.setSubject(subject);
		createEvent.setStartDate(map.get("Start Date"));
		createEvent.setEndDate(map.get("Due Date"));
		createEvent.clickSave();
		
		if(newEvent.getPageHeader().contains(subject)) {
			System.out.println("Test Pass");
			excel.updateTestStatus("Create New Event", "Pass", IConstantPath.EXCEL_PATH, "EventsTestData");
		}
		else {
			System.out.println("Test Fail");
			excel.updateTestStatus("Create New Event", "Fail", IConstantPath.EXCEL_PATH, "EventsTestData");
		}
	}
}
