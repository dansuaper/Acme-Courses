package acme.features.learner.followUp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.followUps.FollowUp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Learner; 
 
@Service 
public class LearnerFollowUpListService implements AbstractListService<Learner, FollowUp> { 
	 
	@Autowired 
	protected LearnerFollowUpRepository repository; 
 
	@Override 
	public boolean authorise(final Request<FollowUp> request) { 
		assert request != null; 
		
		return true; 
	} 
 
	@Override 
	public Collection<FollowUp> findMany(final Request<FollowUp> request) { 
		assert request != null; 
		 
		final Collection<FollowUp> result; 
		final int UAId = request.getPrincipal().getAccountId();
		final int learnerId = this.repository.findLearnerByUserAccountId(UAId).getId();
		
		result = this.repository.findManyFollowUpsByLearnerId(learnerId);
		
		return result;
	}
 
	@Override 
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		request.unbind(entity, model, "instantiationMoment", "sequenceNumber", "info");		 
	} 
 
} 
