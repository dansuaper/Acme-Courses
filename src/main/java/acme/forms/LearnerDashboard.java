
package acme.forms;

import java.util.Map;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearnerDashboard {

	// Serialisation identifier ----------------------------------------------

	protected static final long						serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------
	
	int totalNumberOfProposedHelpRequests;
	int totalNumberOfAcceptedHelpRequests;
	int totalNumberOfDeniedHelpRequests;
	Map<Pair<String,String>,Double> averageBudgetByCurrency;
	Map<Pair<String,String>,Double> deviationBudgetByCurrency;
	Map<Pair<String,String>,Double> minBudgetByCurrency;
	Map<Pair<String,String>,Double> maxBudgetByCurrency;

}
