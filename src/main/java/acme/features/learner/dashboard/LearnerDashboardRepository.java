package acme.features.learner.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface LearnerDashboardRepository extends AbstractRepository{
	
	@Query("select count(hr) from HelpRequest hr where hr.status = 'PROPOSED'")
	int totalNumberOfProposedHelpRequests();
	@Query("select count(hr) from HelpRequest hr where hr.status = 'ACCEPTED'")
	int totalNumberOfAcceptedHelpRequests();
	@Query("select count(hr) from HelpRequest hr where hr.status = 'DENIED'")
	int totalNumberOfDeniedHelpRequests();
	@Query("select hr.budget.currency, avg(hr.budget.amount), hr.status from HelpRequest hr group by hr.budget.currency, hr.status")
	List<String> averageBudgetByCurrency();
	@Query("select hr.budget.currency, stddev(hr.budget.amount), hr.status from HelpRequest hr group by hr.budget.currency, hr.status")
	List<String> deviationBudgetByCurrency();
	@Query("select hr.budget.currency, min(hr.budget.amount), hr.status from HelpRequest hr group by hr.budget.currency, hr.status")
	List<String> minBudgetByCurrency();
	@Query("select hr.budget.currency, max(hr.budget.amount), hr.status from HelpRequest hr group by hr.budget.currency, hr.status")
	List<String> maxBudgetByCurrency();
	@Query("select hr.budget.currency from HelpRequest hr")
	List<String> getAllCurrencies();	
	@Query("select hr.status from HelpRequest hr")
	List<Integer> getAllStatus();

}
