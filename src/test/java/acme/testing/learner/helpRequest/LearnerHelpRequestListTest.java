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
	public void positivePatronage(final int recordIndex, final String ticker, final String budget,
		final String start_date, final String end_date, final String status) {
		
		super.signIn("learner3", "learner3");
		super.clickOnMenu("Learner", "Help Requests");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, ticker);
		super.checkColumnHasValue(recordIndex, 1, budget);
		super.checkColumnHasValue(recordIndex, 2, start_date);
		super.checkColumnHasValue(recordIndex, 3, end_date);
		super.checkColumnHasValue(recordIndex, 4, status);

		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/learner/help-request/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/learner/help-request/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("teacher2", "teacher2");
		super.navigate("/learner/help-request/list");
		super.checkPanicExists();
		super.signOut();
	}

}