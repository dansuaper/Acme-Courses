package acme.features.learner.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface LearnerDashboardRepository extends AbstractRepository{
	
	//Help requests
	
	@Query("SELECT hr.status, count(hr) FROM HelpRequest hr GROUP BY hr.status")
	Collection<Tuple> totalNumberOfProposedHelpRequestsByStatus();
	@Query("SELECT hr.status, hr.budget.currency, avg(hr.budget.amount) FROM HelpRequest hr GROUP BY hr.status")
	Collection<Tuple> averageBudgetOfHelpRequestsByStatus();
	@Query("SELECT hr.status, hr.budget.currency, stddev(hr.budget.amount) FROM HelpRequest hr GROUP BY hr.status")
	Collection<Tuple> deviationBudgetOfHelpRequestsByStatus();
	@Query("SELECT hr.status, hr.budget.currency, min(hr.budget.amount) FROM HelpRequest hr GROUP BY hr.status")
	Collection<Tuple> minimumBudgetOfHelpRequestsByStatus();
	@Query("SELECT hr.status, hr.budget.currency, max(hr.budget.amount) FROM HelpRequest hr GROUP BY hr.status")
	Collection<Tuple> maximumBudgetOfHelpRequestsByStatus();

	//System configuration

	@Query("SELECT s.acceptedCurrencies FROM SystemConfiguration s")
	String findSystemCurrencies();
}
