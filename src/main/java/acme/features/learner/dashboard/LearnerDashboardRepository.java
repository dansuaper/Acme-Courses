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
	Collection<Tuple> totalNumberOfHelpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, avg(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> averageBudgetOfHelpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, stddev(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> deviationBudgetOfHelpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, min(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> minimumBudgetOfHelpRequestsByStatus();

	@Query("SELECT h.status, h.budget.currency, max(h.budget.amount) FROM HelpRequest h GROUP BY h.status")
	Collection<Tuple> maximumBudgetOfHelpRequestsByStatus();

}
