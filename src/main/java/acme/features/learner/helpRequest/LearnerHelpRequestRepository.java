package acme.features.learner.helpRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.followUps.FollowUp;
import acme.entities.helpRequests.HelpRequest;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner;
import acme.roles.Teacher; 
 
@Repository 
public interface LearnerHelpRequestRepository extends AbstractRepository{ 
	 
	@Query("Select h from HelpRequest h where h.learner.id = :id") 
	Collection<HelpRequest> findHelpRequestsByLearnerId(int id); 
	
	@Query("Select l from Learner l where l.userAccount.id = :id")
	Learner findLearnerByUserAccountId(int id);
	
	@Query("Select h from HelpRequest h where h.id = :id") 
	HelpRequest findHelpRequestById(int id); 
	
	@Query("Select f from FollowUp f where f.helpRequest.id = :id") 
	Collection<FollowUp> findFollowUpsByHelpRequestId(int id); 
	
	@Query("Select t from Teacher t where t.id = :id") 
	Teacher findTeacherById(int id); 	
	
	@Query ("Select t from Teacher t")
	Collection<Teacher> findManyTeachers();
	
	@Query("Select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("Select h from HelpRequest h where h.ticker = :ticker")
	HelpRequest findOneHelpRequestByTicker(String ticker);
	
	@Query("Select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
	
	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount, String systemCurrency);
} 
