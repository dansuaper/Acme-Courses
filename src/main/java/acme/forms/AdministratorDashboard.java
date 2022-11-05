package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.helpRequests.HelpRequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;
	
	
//	THEORY TUTORIALS: Total number, average, deviation, minimum and maximum retail.
	
	int totalNumberOfTheoryTutorials;
	Map<String,Double> averageCostOfTheoryTutorialsByCurrency;
	Map<String,Double> deviationCostOfTheoryTutorialsByCurrency;
	Map<String,Double> minimumCostOfTheoryTutorialsByCurrency;
	Map<String,Double> maximumCostOfTheoryTutorialsByCurrency;

//	LAB TUTORIALS: Total number, average, deviation, minimum and maximum retail.

	int totalNumberOfLabTutorials;
	Map<String,Double> retailPriceOfLabTutorialsByCurrency;
	Map<String,Double> deviationRetailPriceOfLabTutorialsByCurrency;
	Map<String,Double> minimumRetailPriceOfLabTutorialsByCurrency;
	Map<String,Double> maximumRetailPriceOfLabTutorialsByCurrency;

//	HELP REQUESTS: Total number, average, deviation, minimum and maximum retail (Proposed, Accepted, Denied).
	
	Map<HelpRequestStatus, Integer> totalNumberOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>, Double> averageBudgetOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>, Double> deviationBudgetOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>, Double> minimumBudgetOfHelpRequestsByStatus;
	Map<Pair<HelpRequestStatus,String>, Double> maximumBudgetOfHelpRequestsByStatus;

}

