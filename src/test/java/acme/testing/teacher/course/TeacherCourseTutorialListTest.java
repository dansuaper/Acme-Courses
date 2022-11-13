package acme.testing.teacher.course;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TeacherCourseTutorialListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/course/list-tutorial.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int courseRecordIndex, final String ticker, final int tutorialRecordIndex, final String type, 
		final String title, final String username, final String tutorialTicker, final String cost) {
		super.signIn("teacher1", "teacher1");
		
		super.clickOnMenu("Teacher", "Courses");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(courseRecordIndex, 0, ticker);
		super.clickOnListingRecord(courseRecordIndex);
		super.checkInputBoxHasValue("ticker", ticker);
		super.clickOnButton("Tutorials");
		
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.checkColumnHasValue(tutorialRecordIndex, 0, type);
		super.checkColumnHasValue(tutorialRecordIndex, 1, title);
		super.checkColumnHasValue(tutorialRecordIndex, 2, username);
		super.checkColumnHasValue(tutorialRecordIndex, 3, tutorialTicker);
		super.checkColumnHasValue(tutorialRecordIndex, 4, cost);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/teacher/course/listCourseTutorials");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/teacher/course/listCourseTutorials");
		super.checkPanicExists();
		super.signOut();

		super.signIn("learner1", "learner1");
		super.navigate("/teacher/course/listCourseTutorials");
		super.checkPanicExists();
		super.signOut();
	}
}
