package acme.testing.learner.helpRequest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LearnerHelpRequestShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/learner/help-request/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String ticker, final String start_date, final String end_date,
		final String statement, final String budget, final String info, final String status, final String teacher_username,
		final String teacher_school, final String teacher_info, final String teacher_statement) {
		
		super.signIn("learner3", "learner3");
		super.clickOnMenu("Learner", "Help Requests");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("ticker", ticker);
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("teacher.userAccount.username", teacher_username);
		super.checkInputBoxHasValue("teacher.school", teacher_school);
		super.checkInputBoxHasValue("teacher.info", teacher_info);
		super.checkInputBoxHasValue("teacher.statement", teacher_statement);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/learner/helpRequest/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/learner/helpRequest/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("learner3", "learner3");
		super.navigate("/learner/helpRequest/list");
		super.checkPanicExists();
		super.signOut();
	}
}
