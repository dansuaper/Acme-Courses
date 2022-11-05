package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	// Theory Tutorials
	
	@Query("SELECT count(t) FROM Tutorial t WHERE t.type = 'THEORY'")
	int totalNumberOfTheoryTutorials();
	
	@Query("SELECT avg(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'THEORY' GROUP BY t.cost.currency")
	Collection<Tuple> averageCostOfTheoryTutorialsByCurrency();
	
	@Query("SELECT stddev(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'THEORY' GROUP BY t.cost.currency")
	Collection<Tuple> deviationCostOfTheoryTutorialsByCurrency();

	@Query("SELECT min(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'THEORY' GROUP BY t.cost.currency")
	Collection<Tuple> minimumCostOfTheoryTutorialsByCurrency();

	@Query("SELECT max(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'THEORY' GROUP BY t.cost.currency")
	Collection<Tuple> maximumCostOfTheoryTutorialsByCurrency();
	
	// Lab Tutorials
	
	@Query("SELECT count(t) FROM Tutorial t WHERE t.type = 'LAB'")
	int totalNumberOfLabTutorials();
	
	@Query("SELECT avg(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'LAB' GROUP BY t.cost.currency")
	Collection<Tuple> averageCostOfLabTutorialsByCurrency();
	
	@Query("SELECT stddev(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'LAB' GROUP BY t.cost.currency")
	Collection<Tuple> deviationCostOfLabTutorialsByCurrency();

	@Query("SELECT min(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'LAB' GROUP BY t.cost.currency")
	Collection<Tuple> minimumCostOfLabTutorialsByCurrency();

	@Query("SELECT max(t.cost.amount), t.cost.currency FROM Tutorial t WHERE t.type = 'LAB' GROUP BY t.cost.currency")
	Collection<Tuple> maximumCostOfLabTutorialsByCurrency();
	
	// Help Requests
	
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
