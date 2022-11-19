package acme.features.teacher.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;
 
@Repository 
public interface TeacherTutorialRepository extends AbstractRepository{ 
	 
	@Query("select tut from Tutorial tut where tut.teacher.id = :id")
	Collection<Tutorial> findManyTutorialsByTeacherId(int id);
	 
	@Query("Select tch from Teacher tch where tch.userAccount.id = :id")
	Teacher findTeacherByUserAccountId(int id);
	
	@Query("select tut from Tutorial tut where tut.id = :id")
	Tutorial findTutorialById(int id);
	
	@Query("select t from Tutorial t where t.ticker = :ticker")
	Tutorial findOneTutorialByTicker(String ticker);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("select t from Tutorial t")
	Collection<Tutorial> findManyTutorial();

	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchange(String currency, Double amount, String systemCurrency);
	
	@Query("select t from Tutorial t where t.published = true and t not in (select q.tutorial from Quantity q where q.course.id = :courseId)")
	Collection<Tutorial> findPossibleTutorialsToCourse(int courseId);
	
	@Query("select q from Quantity q where q.tutorial.id = :tutorialId")
	Collection<Quantity> findQuantityByTutorialId(int tutorialId);
} 
