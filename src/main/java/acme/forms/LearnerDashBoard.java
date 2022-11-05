package acme.forms;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearnerDashBoard {
	
		// Serialisation identifier ----------------------------------------------
	
				protected static final long		serialVersionUID 		= 1L;
				
				// Attributes -----------------------------------------------------
				
				int totalNumberOfProposedHelpRequests;
				int totalNumberOfAcceptedHelpRequests;
				int totalNumberofDeniedHelpRequests;
				
				Map<String, Double> averageBudgetOfProposedHelpRequestsByCurrency;
				Map<String, Double> deviationBudgetOfProposedHelpRequestsByCurrency;
				Map<String, Double> minimumBudgetOfProposedHelpRequestsByCurrency;
				Map<String, Double> maximumBudgetOfProposedHelpRequestsByCurrency;
				
				Map<String, Double> averageBudgetOfAcceptedHelpRequestsByCurrency;
				Map<String, Double> deviationBudgetOfAcceptedHelpRequestsByCurrency;
				Map<String, Double> minimumBudgetOfAcceptedHelpRequestsByCurrency;
				Map<String, Double> maximumBudgetOfAceeptedHelpRequestsByCurrency;
				
				Map<String, Double> averageBudgetOfDeniedHelpRequestsByCurrency;
				Map<String, Double> deviationBudgetOfDeniedHelpRequestsByCurrency;
				Map<String, Double> minimumBudgetOfDeniedHelpRequestsByCurrency;
				Map<String, Double> maximumBudgetOfDeniedHelpRequestsByCurrency;
				
}
