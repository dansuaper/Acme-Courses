package acme.features.administrator.dashboard;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequestStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result;
		
		final Integer totalNumberOfTheoryTutorials;
		final Map<String,Double> averageCostOfTheoryTutorialsByCurrency = new HashMap<>();
		final Map<String,Double> deviationCostOfTheoryTutorialsByCurrency = new HashMap<>();
		final Map<String,Double> minimumCostOfTheoryTutorialsByCurrency = new HashMap<>();
		final Map<String,Double> maximumCostOfTheoryTutorialsByCurrency = new HashMap<>();

		final int totalNumberOfLabTutorials;
		final Map<String,Double> averageCostOfLabTutorialsByCurrency = new HashMap<>();
		final Map<String,Double> deviationCostOfLabTutorialsByCurrency  = new HashMap<>();
		final Map<String,Double> minimumCostOfLabTutorialsByCurrency  = new HashMap<>();
		final Map<String,Double> maximumCostOfLabTutorialsByCurrency  = new HashMap<>();

		final EnumMap<HelpRequestStatus, Integer> totalNumberOfHelpRequestsByStatus = new EnumMap<>(HelpRequestStatus.class);
		final Map<Pair<HelpRequestStatus,String>, Double> averageBudgetOfHelpRequestsByStatus = new HashMap<>();
		final Map<Pair<HelpRequestStatus,String>, Double> deviationBudgetOfHelpRequestsByStatus = new HashMap<>();
		final Map<Pair<HelpRequestStatus,String>, Double> minimumBudgetOfHelpRequestsByStatus= new HashMap<>();
		final Map<Pair<HelpRequestStatus,String>, Double> maximumBudgetOfHelpRequestsByStatus = new HashMap<>();

		final String acceptedCurrencies = this.repository.findSystemCurrencies();
		final String[] split = acceptedCurrencies.split(","); 
		final List<String> currencies = Arrays.asList(split);
		
		totalNumberOfTheoryTutorials = this.repository.totalNumberOfTheoryTutorials();
		for(final String currency: currencies) {
			this.repository.averageCostOfTheoryTutorialsByCurrency().stream()
			.forEach(x-> averageCostOfTheoryTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.deviationCostOfTheoryTutorialsByCurrency().stream()
			.forEach(x-> deviationCostOfTheoryTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.minimumCostOfTheoryTutorialsByCurrency().stream()
			.forEach(x-> minimumCostOfTheoryTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.maximumCostOfTheoryTutorialsByCurrency().stream()
			.forEach(x-> maximumCostOfTheoryTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		
		totalNumberOfLabTutorials = this.repository.totalNumberOfLabTutorials();
		for(final String currency: currencies) {
			this.repository.averageCostOfLabTutorialsByCurrency().stream()
			.forEach(x-> averageCostOfLabTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.deviationCostOfLabTutorialsByCurrency().stream()
			.forEach(x-> deviationCostOfLabTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.minimumCostOfLabTutorialsByCurrency().stream()
			.forEach(x-> minimumCostOfLabTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.maximumCostOfLabTutorialsByCurrency().stream()
			.forEach(x-> maximumCostOfLabTutorialsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(int i = 0; i < this.repository.totalNumberOfHelpRequestsByStatus().size(); i++) {
			this.repository.totalNumberOfHelpRequestsByStatus().stream()
			.forEach(x -> totalNumberOfHelpRequestsByStatus.put((HelpRequestStatus) x.get(0), Integer.parseInt(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.averageBudgetOfHelpRequestsByStatus().stream()
			.forEach(x-> averageBudgetOfHelpRequestsByStatus
				.put(Pair.of((HelpRequestStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		for(final String currency: currencies) {
			this.repository.deviationBudgetOfHelpRequestsByStatus()
			.forEach(x-> deviationBudgetOfHelpRequestsByStatus
				.put(Pair.of((HelpRequestStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		for(final String currency: currencies) {
			this.repository.minimumBudgetOfHelpRequestsByStatus().stream()
			.forEach(x-> minimumBudgetOfHelpRequestsByStatus
				.put(Pair.of((HelpRequestStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		for(final String currency: currencies) {
			this.repository.maximumBudgetOfHelpRequestsByStatus().stream()
			.forEach(x-> maximumBudgetOfHelpRequestsByStatus
				.put(Pair.of((HelpRequestStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		
		result = new AdministratorDashboard();
		
		result.setTotalNumberOfTheoryTutorials(totalNumberOfTheoryTutorials);
		result.setAverageCostOfTheoryTutorialsByCurrency(averageCostOfTheoryTutorialsByCurrency);
		result.setDeviationCostOfTheoryTutorialsByCurrency(deviationCostOfTheoryTutorialsByCurrency);
		result.setMinimumCostOfTheoryTutorialsByCurrency(minimumCostOfTheoryTutorialsByCurrency);
		result.setMaximumCostOfTheoryTutorialsByCurrency(maximumCostOfTheoryTutorialsByCurrency);
		
		result.setTotalNumberOfLabTutorials(totalNumberOfLabTutorials);
		result.setAverageCostOfLabTutorialsByCurrency(averageCostOfLabTutorialsByCurrency);
		result.setDeviationCostOfLabTutorialsByCurrency(deviationCostOfLabTutorialsByCurrency);
		result.setMinimumCostOfLabTutorialsByCurrency(minimumCostOfLabTutorialsByCurrency);
		result.setMaximumCostOfLabTutorialsByCurrency(maximumCostOfLabTutorialsByCurrency);
		
		result.setTotalNumberOfHelpRequestsByStatus(totalNumberOfHelpRequestsByStatus);
		result.setAverageBudgetOfHelpRequestsByStatus(averageBudgetOfHelpRequestsByStatus);
		result.setDeviationBudgetOfHelpRequestsByStatus(deviationBudgetOfHelpRequestsByStatus);
		result.setMinimumBudgetOfHelpRequestsByStatus(minimumBudgetOfHelpRequestsByStatus);
		result.setMaximumBudgetOfHelpRequestsByStatus(maximumBudgetOfHelpRequestsByStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfHelpRequestsByStatus", 
			"totalNumberOfTheoryTutorials", "averageCostOfTheoryTutorialsByCurrency", "deviationCostOfTheoryTutorialsByCurrency", "maximumCostOfTheoryTutorialsByCurrency","minimumCostOfTheoryTutorialsByCurrency",
			"totalNumberOfLabTutorials","averageCostOfLabTutorialsByCurrency","deviationCostOfLabTutorialsByCurrency","maximumCostOfLabTutorialsByCurrency","minimumCostOfLabTutorialsByCurrency",
			"averageBudgetOfHelpRequestsByStatus","deviationBudgetOfHelpRequestsByStatus","maximumBudgetOfHelpRequestsByStatus","minimumBudgetOfHelpRequestsByStatus");
	}
}