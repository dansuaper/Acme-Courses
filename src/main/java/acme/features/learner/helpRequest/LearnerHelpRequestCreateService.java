package acme.features.learner.helpRequest;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.entities.helpRequests.HelpRequestStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestCreateService implements AbstractCreateService<Learner, HelpRequest> {

	@Autowired
	protected LearnerHelpRequestRepository repository;
	
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		entity.setStatus(HelpRequestStatus.PROPOSED);
		entity.setPublished(false);
		if(this.repository.findManyTeachers().isEmpty()) {			
			request.bind(entity, errors, "ticker", "startDate", "endDate","statement", "budget", "info");
		} else {
			entity.setTeacher(this.repository.findTeacherById(Integer.valueOf(request.getModel().getAttribute("teacherId").toString())));
			request.bind(entity, errors, "ticker", "startDate", "endDate","statement", "budget", "info", "teacherId");
		}
	}

	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 

		request.unbind(entity, model, "ticker", "startDate", "endDate", "statement", "info", "budget","published");
		model.setAttribute("teachers", this.repository.findManyTeachers());
	}

	@Override
	public HelpRequest instantiate(final Request<HelpRequest> request) {
		assert request != null;
		
		final HelpRequest result = new HelpRequest();
		
		result.setLearner(this.repository.findLearnerByUserAccountId(request.getPrincipal().getAccountId()));
		
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
	public void create(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
	

}
