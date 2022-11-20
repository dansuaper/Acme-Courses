package acme.features.learner.helpRequest;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestPublishService implements AbstractUpdateService<Learner, HelpRequest> {

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
		
		request.bind(entity, errors, "ticker", "startDate", "endDate","statement", "budget", "info");
	}

	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "ticker", "statement", "budget", "startDate", "endDate", "info", "published",
			"status", "teacher.userAccount.username", "teacher.school", "teacher.info", "teacher.statement");
		model.setAttribute("inventors", this.repository.findManyTeachers());
		model.setAttribute("inventorId", entity.getTeacher().getId());
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
		
		if (!errors.hasErrors("ticker")) {
			final HelpRequest h = this.repository.findOneHelpRequestByTicker(entity.getTicker());
			errors.state(request, h == null || h.getId() == entity.getId(), "ticker", "teacher.help-request.form.error.duplicated");
		}
		
		if(entity.getTeacher() == null) {
            errors.state(request, entity.getTeacher() != null, "teacherId", "teacher.help-request.form.error.no-teacher");
        }
		
		if(!errors.hasErrors("startDate")) {
			final Date startDate = DateUtils.addMonths(new Date(System.currentTimeMillis() - 1), 1);
			errors.state(request, entity.getStartDate().after(startDate), "startDate", "teacher.help-request.form.error.start-date");
		}
		
		if(!errors.hasErrors("endDate") && !errors.hasErrors("startDate")) {
			final Date endDate = DateUtils.addMonths(entity.getStartDate(), 1);
			errors.state(request,entity.getEndDate().after(endDate), "endDate", "teacher.help-request.form.error.one-month");
		}
		
		if (!errors.hasErrors("budget")) {
			final String[] acceptedCurrencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
            boolean accepted = false;
            
            for(int i = 0; i < acceptedCurrencies.length; i++) {
                if(entity.getBudget().getCurrency().equals(acceptedCurrencies[i].trim())) {
                    accepted = true;
                }
            }
			errors.state(request, accepted, "budget", "teacher.help-request.form.error.currency");
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "teacher.help-request.form.error.amount");
		}
	}

	@Override
	public void update(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);

		this.repository.save(entity);
	}
	
}
