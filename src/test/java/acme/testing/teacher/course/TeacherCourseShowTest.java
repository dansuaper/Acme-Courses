package acme.testing.teacher.course;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TeacherCourseShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/course/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String ticker, final String caption,
		final String abstractText, final String link, final String published, final String cost, final String username) {
		
		super.signIn("teacher1", "teacher1");
		super.clickOnMenu("Teacher", "Courses");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("ticker", ticker);
		super.checkInputBoxHasValue("caption", caption);
		super.checkInputBoxHasValue("abstractText", abstractText);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		super.checkInputBoxHasValue("cost", cost);
		super.checkInputBoxHasValue("teacher.userAccount.username", username);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/teacher/course/show");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/teacher/course/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("learner1", "learner1");
		super.navigate("/teacher/course/show");
		super.checkPanicExists();
		super.signOut();
	}
}
