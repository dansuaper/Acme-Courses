package acme.features.administrator.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("Select s from SystemConfiguration s")
	SystemConfiguration findSystemConfiguration();

	@Query("select sc.spamRecordsEn from SystemConfiguration sc")
	String findSpamRecordsEn();
	
	@Query("select sc.spamRecordsEs from SystemConfiguration sc")
	String findSpamRecordsEs();
	
	@Query("select sc.spamThreshold from SystemConfiguration sc")
	double findSpamThreshold();
	
	@Query("select sc.spamBooster from SystemConfiguration sc")
	double findSpamBooster();
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
}
