package acme.features.learner.helpRequest;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Learner; 
 
@Service 
public class LearnerHelpRequestListService implements AbstractListService<Learner, HelpRequest> { 
	 
	@Autowired 
	protected LearnerHelpRequestRepository repository; 
 
	@Override 
	public boolean authorise(final Request<HelpRequest> request) { 
		assert request != null; 
		return true; 
	} 
 
	@Override 
	public Collection<HelpRequest> findMany(final Request<HelpRequest> request) { 
		assert request != null; 
		 
		final Collection<HelpRequest> result; 
		final int UAId = request.getPrincipal().getAccountId();
		final int HelpRequestId = this.repository.findLearnerByUserAccountId(UAId).getId();
		result=this.repository.findHelpRequestsByLearnerId(HelpRequestId); 
		
		return result;
	} 
 
	@Override 
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		request.unbind(entity, model, "ticker","budget","statement", "info", "startDate","endDate","status");		 
	}
 
} 
