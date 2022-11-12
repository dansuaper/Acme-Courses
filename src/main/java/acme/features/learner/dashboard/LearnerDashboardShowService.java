package acme.features.learner.dashboard;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequestStatus;
import acme.forms.LearnerDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Learner;

@Service
public class LearnerDashboardShowService implements AbstractShowService<Learner, LearnerDashboard> {

	@Autowired
	protected LearnerDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<LearnerDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public LearnerDashboard findOne(final Request<LearnerDashboard> request) {
		assert request != null;

		final LearnerDashboard result;
		
		final EnumMap<HelpRequestStatus, Integer> totalNumberOfHelpRequestsByStatus = new EnumMap<>(HelpRequestStatus.class);
		final Map<Pair<HelpRequestStatus,String>, Double> averageBudgetOfHelpRequestsByStatus = new HashMap<>();
		final Map<Pair<HelpRequestStatus,String>, Double> deviationBudgetOfHelpRequestsByStatus = new HashMap<>();
		final Map<Pair<HelpRequestStatus,String>, Double> minimumBudgetOfHelpRequestsByStatus = new HashMap<>();
		final Map<Pair<HelpRequestStatus,String>, Double> maximumBudgetOfHelpRequestsByStatus = new HashMap<>();

		final String acceptedCurrencies = this.repository.findSystemCurrencies();
		final String[] split = acceptedCurrencies.split(","); 
		final List<String> currencies = Arrays.asList(split);
		
		
		for(int i = 0; i < this.repository.totalNumberOfProposedHelpRequestsByStatus().size(); i++) {
			this.repository.totalNumberOfProposedHelpRequestsByStatus().stream()
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
		
		result = new LearnerDashboard();
		
		result.setTotalNumberOfHelpRequestsByStatus(totalNumberOfHelpRequestsByStatus);
		result.setAverageBudgetOfHelpRequestsByStatus(averageBudgetOfHelpRequestsByStatus);
		result.setDeviationBudgetOfHelpRequestsByStatus(deviationBudgetOfHelpRequestsByStatus);
		result.setMinimumBudgetOfHelpRequestsByStatus(minimumBudgetOfHelpRequestsByStatus);
		result.setMaximumBudgetOfHelpRequestsByStatus(maximumBudgetOfHelpRequestsByStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<LearnerDashboard> request, final LearnerDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfHelpRequestsByStatus", 
			"averageBudgetOfHelpRequestsByStatus", "deviationBudgetOfHelpRequestsByStatus",
			"minimumBudgetOfHelpRequestsByStatus", "maximumBudgetOfHelpRequestsByStatus");
	}

}