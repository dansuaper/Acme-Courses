package acme.features.learner.helpRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner; 
 
@Repository 
public interface LearnerHelpRequestRepository extends AbstractRepository{ 
	 
	@Query("Select h from HelpRequest h where h.learner.id = :id") 
	Collection<HelpRequest> findHelpRequestsByLearnerId(int id); 
	@Query("Select l from Learner l where l.userAccount.id = :id")
	Learner findLearnerByUserAccountId(int id);
	@Query("Select h from HelpRequest h where h.id = :id") 
	HelpRequest findHelpRequestById(int id); 
} 
