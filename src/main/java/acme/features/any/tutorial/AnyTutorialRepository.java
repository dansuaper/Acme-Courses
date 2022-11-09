package acme.features.any.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyTutorialRepository extends AbstractRepository{
	

	@Query("select t from Tutorial t where t.published = :published")
	Collection<Tutorial> findManyPublishedTutorials(boolean published);

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findOneTutorialById(int id);
	
//	@Query("select sc.systemCurrency from SystemConfiguration sc")
//	String findSystemCurrency();
//	
//	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount and me.target.currency = :systemCurrency")
//	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount, String systemCurrency);
}
