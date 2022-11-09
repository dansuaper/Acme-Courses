package acme.features.learner.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

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
		final LearnerDashboard result=new LearnerDashboard();
		final int totalNumberOfProposedHelpRequests=this.repository.totalNumberOfProposedHelpRequests();
		final int totalNumberOfAcceptedHelpRequests=this.repository.totalNumberOfAcceptedHelpRequests();
		final int totalNumberOfDeniedHelpRequests=this.repository.totalNumberOfDeniedHelpRequests();
		final Map<Pair<String, String>, Double> averageBudgetByCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> deviationBudgetByCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> minBudgetByCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> maxBudgetByCurrency = new HashMap<Pair<String, String>, Double>();

		int i=0;
	
		while(i<this.repository.averageBudgetByCurrency().size()) {
			final String linea= this.repository.averageBudgetByCurrency().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			final String estado= sub[2];
			final Pair<String, String> res=Pair.of(divisa, estado);
			averageBudgetByCurrency.put(res, key);
			i++;
		}
		i=0;
		while(i<this.repository.deviationBudgetByCurrency().size()) {
			final String linea= this.repository.deviationBudgetByCurrency().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			final String estado= sub[2];
			final Pair<String, String> res=Pair.of(divisa, estado);
			deviationBudgetByCurrency.put(res, key);
			i++;
		}
		i=0;
		while(i<this.repository.minBudgetByCurrency().size()) {
			final String linea= this.repository.minBudgetByCurrency().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			final String estado= sub[2];
			final Pair<String, String> res=Pair.of(divisa, estado);
			minBudgetByCurrency.put(res, key);
			i++;
		}
		i=0;
		while(i<this.repository.maxBudgetByCurrency().size()) {
			final String linea= this.repository.maxBudgetByCurrency().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			final String estado= sub[2];
			final Pair<String, String> res=Pair.of(divisa, estado);
			maxBudgetByCurrency.put(res, key);
			i++;
		}
		result.setTotalNumberOfAcceptedHelpRequests(totalNumberOfAcceptedHelpRequests);
		result.setTotalNumberOfProposedHelpRequests(totalNumberOfProposedHelpRequests);
		result.setTotalNumberOfDeniedHelpRequests(totalNumberOfDeniedHelpRequests);
		result.setAverageBudgetByCurrency(averageBudgetByCurrency);
		result.setDeviationBudgetByCurrency(deviationBudgetByCurrency);
		result.setMinBudgetByCurrency(minBudgetByCurrency);
		result.setMaxBudgetByCurrency(maxBudgetByCurrency);

		return result;
	}

	@Override
	public void unbind(final Request<LearnerDashboard> request, final LearnerDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfProposedHelpRequests", "totalNumberOfAcceptedHelpRequests", "totalNumberOfDeniedHelpRequests", "averageBudgetByCurrency", "deviationBudgetByCurrency", "minBudgetByCurrency", "maxBudgetByCurrency");
	}

}
