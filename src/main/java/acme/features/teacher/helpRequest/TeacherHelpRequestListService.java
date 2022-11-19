package acme.features.teacher.helpRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherHelpRequestListService implements AbstractListService<Teacher, HelpRequest> {
	
	@Autowired 
	protected TeacherHelpRequestRepository repository; 
 
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
		final int teacherId = this.repository.findTeacherByUserAccountId(UAId).getId();
		result = this.repository.findPublishedAcceptedOrDeniedHelpRequestsByTeacherId(teacherId, true);
		return result;
	}
 
	@Override 
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		request.unbind(entity, model, "status", "ticker", "statement", "budget", "startDate", "endDate", "info", "learner");		 
	}

}
