package acme.features.teacher.followUp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.followUps.FollowUp;
import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher; 
 
@Service 
public class TeacherHelpRequestFollowUpListService implements AbstractListService<Teacher, FollowUp> { 
	 
	@Autowired 
	protected TeacherFollowUpRepository repository; 
 
	@Override 
	public boolean authorise(final Request<FollowUp> request) { 
		assert request != null;

		boolean result;
		int masterId;
		HelpRequest helpRequest;

		masterId = request.getModel().getInteger("id");
		helpRequest = this.repository.findOneHelpRequestById(masterId);
		result = (helpRequest != null && helpRequest.isPublished() && request.isPrincipal(helpRequest.getTeacher()));

		return result;
	} 
 
	@Override 
	public Collection<FollowUp> findMany(final Request<FollowUp> request) { 
		assert request != null; 
		 
		final Collection<FollowUp> result; 
		final int helpRequestId = request.getModel().getInteger("id");
		result = this.repository.findFollowUpsByHelpRequestId(helpRequestId); 
		
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
