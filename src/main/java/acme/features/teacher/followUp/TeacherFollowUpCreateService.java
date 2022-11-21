package acme.features.teacher.followUp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.followUps.FollowUp;
import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;

@Service
public class TeacherFollowUpCreateService implements AbstractCreateService<Teacher, FollowUp> {
	
	@Autowired 
	protected TeacherFollowUpRepository repository; 

	@Override
	public boolean authorise(final Request<FollowUp> request) {
		assert request != null;

		boolean result;
		int masterId;
		HelpRequest helpRequest;

		masterId = request.getModel().getInteger("helpRequestId");
		helpRequest = this.repository.findOneHelpRequestById(masterId);
		result = (helpRequest != null && helpRequest.isPublished() && request.isPrincipal(helpRequest.getTeacher()));

		return result;
	}

	@Override
	public void bind(final Request<FollowUp> request, final FollowUp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "message", "info");
	}

	@Override
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", false); 
		model.setAttribute("status", entity.getHelpRequest().getStatus());
		model.setAttribute("helpRequestId", entity.getHelpRequest().getId());
		
		request.unbind(entity, model, "instantiationMoment", "sequenceNumber", "message", "info");	
	}

	@Override
	public FollowUp instantiate(final Request<FollowUp> request) {
		assert request != null;
		
		int masterId;
		final FollowUp result = new FollowUp();
		HelpRequest helpRequest;
		String sequenceNumber = "";
		
		masterId = request.getModel().getInteger("helpRequestId");
		helpRequest = this.repository.findOneHelpRequestById(masterId);
		
		final Integer numHelpRequest = this.repository.numOfFollowUpsByHelpRequest(helpRequest.getTicker())+1;
		final String n = numHelpRequest.toString();
		if(n.length() == 1) {
			sequenceNumber = helpRequest.getTicker() + ":000" + n;
		}else if(n.length() == 2){
			sequenceNumber = helpRequest.getTicker() + ":" + "00" + n;
		}else if(n.length() == 3) {
			sequenceNumber = helpRequest.getTicker() + ":" + "0" + n;
		}else {
			sequenceNumber = helpRequest.getTicker() + ":" + n;
		}
		
		Date date;
		date = Calendar.getInstance().getTime();
		
		result.setHelpRequest(helpRequest);
		result.setInstantiationMoment(date);
		result.setSequenceNumber(sequenceNumber);
		return result;
	}

	@Override
	public void validate(final Request<FollowUp> request, final FollowUp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "teacher.follow-up.confirmation.error");
		
		if (!errors.hasErrors("sequenceNumber")) {
			final FollowUp f = this.repository.findOneFollowUpBySequenceNumber(entity.getSequenceNumber());
			errors.state(request, f == null || f.getId() == entity.getId(), "sequenceNumber", "teacher.follow-up.form.error.duplicated");
		}
	}

	@Override
	public void create(final Request<FollowUp> request, final FollowUp entity) {
		assert request != null;
		assert entity != null;
		
		Date instantiationMoment;
		instantiationMoment = new Date(System.currentTimeMillis()-1);
		entity.setInstantiationMoment(instantiationMoment);
		
		this.repository.save(entity);
	}
}
