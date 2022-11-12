package acme.testing.learner.helpRequest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LearnerHelpRequestListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/learner/help-request/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronage(final int recordIndex, final String status, final String ticker, final String statement, final String start_date, 
		final String end_date, final String info, final String teacher_username, final String teacher_info, final String teacher_statement) {
		
		super.signIn("learner5", "learner5");
		super.clickOnMenu("Learner", "List help requests");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, ticker);
		super.checkColumnHasValue(recordIndex, 2, statement);
		super.checkColumnHasValue(recordIndex, 3, start_date);
		super.checkColumnHasValue(recordIndex, 4, end_date);

		super.signOut();
	}
	
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Learner");
		super.navigate("/learner/helpRequest/list");
		super.checkPanicExists();
	}
	// Ancillary methods ------------------------------------------------------

}