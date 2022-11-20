package acme.features.learner.helpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.followUps.FollowUp;
import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestDeleteService implements AbstractDeleteService<Learner, HelpRequest> {

	@Autowired
	protected LearnerHelpRequestRepository repository;
	
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		
		boolean result;
		int helpRequestId;
		HelpRequest helpRequest;
		
		helpRequestId = request.getModel().getInteger("id");
		helpRequest = this.repository.findHelpRequestById(helpRequestId);

		result = request.isPrincipal(helpRequest.getLearner()) && !helpRequest.isPublished();
		
		return result; 
	}

	@Override
	public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "ticker", "startDate", "endDate", "statement", "budget", "info");
	}

	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "ticker", "startDate", "endDate", "statement", "budget", "info", "published");
	}

	@Override
	public HelpRequest findOne(final Request<HelpRequest> request) {
		assert request != null; 
		 
		HelpRequest result; 
		int id; 
		 
		id = request.getModel().getInteger("id"); 
		result = this.repository.findHelpRequestById(id);
		
		return result; 
	}

	@Override
	public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;
		for (final FollowUp f : this.repository.findFollowUpsByHelpRequestId(entity.getId())) {
			this.repository.delete(f);
		}

		this.repository.delete(entity);
	}

}
