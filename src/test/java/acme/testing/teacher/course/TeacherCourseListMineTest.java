package acme.testing.teacher.course;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TeacherCourseListMineTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/course/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String ticker, final String caption, final String link, final String cost) {
		super.signIn("teacher1", "teacher1");
		
		super.clickOnMenu("Teacher", "Courses");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, ticker);
		super.checkColumnHasValue(recordIndex, 1, caption);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, cost);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/teacher/course/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/teacher/course/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("learner1", "learner1");
		super.navigate("/teacher/course/list");
		super.checkPanicExists();
		super.signOut();
	}
}
