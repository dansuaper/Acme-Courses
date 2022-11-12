package acme.features.learner.followUp;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.followUps.FollowUp;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;
 
@Repository 
public interface LearnerFollowUpRepository extends AbstractRepository { 
	 
	@Query("Select f from FollowUp f where f.helpRequest.id = :id") 
	Collection<FollowUp> findFollowUpsByHelpRequestId(int id); 
	 
	@Query("Select l from Learner l where l.userAccount.id = :id")
	Teacher findTeacherByUserAccountId(int id); 
	
	@Query("Select f from FollowUp f where f.id = :id") 
	FollowUp findFollowUpById(int id); 
} 
