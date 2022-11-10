package acme.features.learner.helpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Learner; 
 
@Service 
public class LearnerHelpRequestShowService implements AbstractShowService<Learner, HelpRequest>{ 
	 
	 
	@Autowired 
	protected LearnerHelpRequestRepository repository; 
	
	@Override 
	public boolean authorise(final Request<HelpRequest> request) { 
		assert request != null;
		boolean result;
		int helpRequestID;
		Learner learner;
		HelpRequest hreq;
		
		helpRequestID = request.getModel().getInteger("id");
		hreq = this.repository.findHelpRequestById(helpRequestID);
		learner =this.repository.findLearnerByUserAccountId(request.getPrincipal().getAccountId());
		result = hreq.getLearner().equals(learner);
		return result; 
	} 
 
	@Override 
	public  HelpRequest findOne(final Request<HelpRequest> request) { 
		assert request != null; 
		 
		HelpRequest result; 
		int id; 
		 
		id=request.getModel().getInteger("id"); 
		result=this.repository.findHelpRequestById(id);
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		 
		request.unbind(entity, model, "ticker", "budget", "statement", "info", "startDate","endDate","status","teacher.userAccount.username","teacher.school","teacher.info","teacher.statement");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
	
} 
