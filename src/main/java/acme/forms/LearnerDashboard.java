
package acme.forms;

import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.helpRequests.HelpRequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearnerDashboard {

	// Serialisation identifier ----------------------------------------------

	protected static final long						serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------
	
	Map<HelpRequestStatus, Integer> totalNumberOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>,Double> averageBudgetOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>,Double> deviationBudgetOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>,Double> minimumBudgetOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>,Double> maximumBudgetOfHelpRequestsByStatus;

}
