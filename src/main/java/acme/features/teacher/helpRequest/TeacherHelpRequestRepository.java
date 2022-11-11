package acme.features.teacher.helpRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.helpRequests.HelpRequest;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface TeacherHelpRequestRepository extends AbstractRepository {

	@Query("Select h from HelpRequest h where h.teacher.id = :id") 
	Collection<HelpRequest> findHelpRequestByTeacherId(int id);
	
	@Query("Select t from Teacher t where t.userAccount.id = :id")
	Teacher findTeacherByUserAccountId(int id); 
	
	@Query("Select h from HelpRequest h where h.id = :id") 
	HelpRequest findHelpRequestById(int id); 
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
		
	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchange(String currency, Double amount, String systemCurrency);
}
