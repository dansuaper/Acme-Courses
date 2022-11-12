package acme.features.teacher.followUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.followUps.FollowUp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher; 
 
@Service 
public class TeacherFollowUpShowService implements AbstractShowService<Teacher, FollowUp>{ 
	 
	 
	@Autowired 
	protected TeacherFollowUpRepository repository; 
	
	@Override 
	public boolean authorise(final Request<FollowUp> request) { 
		assert request != null;
		boolean result;
		int followUpId;
		final int userId;
		
		final FollowUp f;
		
		userId = request.getPrincipal().getActiveRoleId();
		followUpId = request.getModel().getInteger("id");
		f = this.repository.findFollowUpById(followUpId);
		result = f.getHelpRequest().getTeacher().getId() == userId;
		
		return result; 
	} 
 
	@Override
	public FollowUp findOne(final Request<FollowUp> request) {
		assert request != null;

		FollowUp result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findFollowUpById(id);

		return result;
	}
 
	@Override 
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		 
		request.unbind(entity, model, "instantiationMoment","sequenceNumber", "message", "info");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
 
} 
