package acme.features.learner.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface LearnerDashboardRepository extends AbstractRepository{
	
	@Query("SELECT count(hr) FROM HelpRequest hr WHERE hr.status = 'PROPOSED'")
	int totalNumberOfProposedHelpRequests();
	@Query("SELECT count(hr) FROM HelpRequest hr WHERE hr.status = 'ACCEPTED'")
	int totalNumberOfAcceptedHelpRequests();
	@Query("SELECT count(hr) FROM HelpRequest hr WHERE hr.status = 'DENIED'")
	int totalNumberOfDeniedHelpRequests();
	@Query("SELECT hr.budget.currency, avg(hr.budget.amount), hr.status FROM HelpRequest hr GROUP BY hr.budget.currency, hr.status")
	List<String> averageBudgetByCurrency();
	@Query("SELECT hr.budget.currency, stddev(hr.budget.amount), hr.status FROM HelpRequest hr GROUP BY hr.budget.currency, hr.status")
	List<String> deviationBudgetByCurrency();
	@Query("SELECT hr.budget.currency, min(hr.budget.amount), hr.status FROM HelpRequest hr GROUP BY hr.budget.currency, hr.status")
	List<String> minBudgetByCurrency();
	@Query("SELECT hr.budget.currency, max(hr.budget.amount), hr.status FROM HelpRequest hr GROUP BY hr.budget.currency, hr.status")
	List<String> maxBudgetByCurrency();
	@Query("SELECT hr.budget.currency FROM HelpRequest hr")
	List<String> getAllCurrencies();	
	@Query("SELECT hr.status FROM HelpRequest hr")
	List<Integer> getAllStatus();

}
