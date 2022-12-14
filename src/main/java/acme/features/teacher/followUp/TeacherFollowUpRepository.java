package acme.features.teacher.followUp;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.followUps.FollowUp;
import acme.entities.helpRequests.HelpRequest;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;
 
@Repository 
public interface TeacherFollowUpRepository extends AbstractRepository { 
	 
	@Query("Select f from FollowUp f where f.helpRequest.id = :id") 
	Collection<FollowUp> findFollowUpsByHelpRequestId(int id); 
	 
	@Query("Select t from Teacher t where t.userAccount.id = :id")
	Teacher findTeacherByUserAccountId(int id); 
	
	@Query("Select f from FollowUp f where f.id = :id") 
	FollowUp findFollowUpById(int id); 

	@Query("Select f from FollowUp f where f.helpRequest.teacher.id = :id")
	Collection<FollowUp> findManyFollowUpsByTeacherId(int id); 
	
	@Query("select h from HelpRequest h where h.id = :id")
	HelpRequest  findOneHelpRequestById(int id);
	
	@Query("select f from FollowUp f where f.sequenceNumber = :sequenceNumber")
	FollowUp  findOneFollowUpBySequenceNumber(String sequenceNumber);
		
	@Query("select count(f) from FollowUp f where f.helpRequest.ticker = :ticker")
	Integer  numOfFollowUpsByHelpRequest(String ticker);
} 
