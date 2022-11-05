package acme.features.learner.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface LearnerDashboardRepository extends AbstractRepository{
	
	//Help Requests
	@Query("SELECT h.status, count(h) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> totalNumberOfHeLpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, avg(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> averageBudgetOfHeLpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, stddev(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> deviationBudgetOfHeLpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, min(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> minimumBudgetOfHeLpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, max(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> maximumBudgetOfHeLpRequestsByStatus();
	
	//SystemConfiguration
//	@Query("SELECT s.acceptedCurrencies FROM SystemConfiguration s")
//	String findSystemCurrencies();
}
