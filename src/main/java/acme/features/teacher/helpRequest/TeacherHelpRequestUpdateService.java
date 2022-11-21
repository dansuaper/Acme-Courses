package acme.features.teacher.helpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.entities.helpRequests.HelpRequestStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher; 
 
@Service 
public class TeacherHelpRequestUpdateService implements AbstractUpdateService<Teacher, HelpRequest> { 
 
	@Autowired 
	protected TeacherHelpRequestRepository repository; 
	  
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;

		final boolean result;
		int helpRequestId;
		final HelpRequest helpRequest;
		final Teacher teacher;

		helpRequestId = request.getModel().getInteger("id");
		helpRequest = this.repository.findHelpRequestById(helpRequestId);
		teacher = helpRequest.getTeacher();

		result = request.isPrincipal(teacher) && helpRequest.isPublished() && helpRequest.getStatus().equals(HelpRequestStatus.PROPOSED);

		return result;
	}
 
	@Override 
	public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) { 
		assert request != null; 
		assert entity != null; 
		assert errors != null; 
		
		request.bind(entity, errors,"ticker", "budget", "info", "statement", "startDate", "endDate", "status", "learner.userAccount.username", "learner.school", "learner.info", "learner.statement"); 
	} 
 
	@Override 
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		request.unbind(entity, model,"ticker", "budget", "info", "statement", "startDate", "endDate","status", "learner.userAccount.username", "learner.school", "learner.info", "learner.statement"); 
		model.setAttribute("confirmation", false); 
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
	public void update(final Request<HelpRequest> request, final HelpRequest entity) { 
		assert request != null; 
		assert entity != null;
		
		this.repository.save(entity); 
	} 
 
} 
