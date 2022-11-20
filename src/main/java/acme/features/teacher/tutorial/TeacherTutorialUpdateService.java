package acme.features.teacher.tutorial;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.tutorials.Tutorial;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherTutorialUpdateService implements AbstractUpdateService<Teacher, Tutorial>{
	
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
	@Autowired
	protected TeacherTutorialRepository repository;
	 
	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;
		
		final boolean result;
		int tutorialId;
		final Tutorial tutorial;
		final Teacher teacher;
		
		tutorialId = request.getModel().getInteger("id");
		tutorial = this.repository.findTutorialById(tutorialId);
		teacher = tutorial.getTeacher();
		
		result= !tutorial.isPublished() && request.isPrincipal(teacher);
		
		return result;
	}

	@Override
	public void bind(final Request<Tutorial> request, final Tutorial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "type", "title", "ticker", "abstractText", "cost", "info"); 
		
	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "title", "ticker", "abstractText", "cost", "info", "teacher.userAccount.username", "published");
		
	}

	@Override
	public Tutorial findOne(final Request<Tutorial> request) {
		assert request != null;
		
		Tutorial result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findTutorialById(id);
		
		return result;
	}

	public boolean validateCostCurrency(final Money cost) {
		final boolean acceptedCurrency;
		
		final String currencies = this.repository.findAcceptedCurrencies().replace(" ", "");
		final List<Object> listCurrencies = Arrays.asList(currencies.split(","));
		acceptedCurrency = listCurrencies.contains(cost.getCurrency());
		
		return acceptedCurrency;	
	}
 
	@Override 
	public void validate(final Request<Tutorial> request, final Tutorial entity, final Errors errors) { 
		assert request != null; 
		assert entity != null; 
		assert errors != null; 
		
		final Money cost = entity.getCost();
		
		if (!errors.hasErrors("title")) {
			errors.state(request, !SpamDetector.isSpam(entity.getTitle(), this.scRepository.findSystemConfiguration()), "title", "teacher.tutorial.form.error.spam");
	    }
		if (!errors.hasErrors("abstractText")) {
	        errors.state(request, !SpamDetector.isSpam(entity.getAbstractText(), this.scRepository.findSystemConfiguration()), "abstractText", "teacher.tutorial.form.error.spam");
	    }
		
		if (!errors.hasErrors("ticker")) {
			final Tutorial t = this.repository.findOneTutorialByTicker(entity.getTicker());
			errors.state(request, t == null || t.getId() == entity.getId(), "ticker", "teacher.tutorial.form.error.duplicated");
		}
		
		if(!errors.hasErrors("cost")){
			final boolean acceptedCurrency = this.validateCostCurrency(cost);
			errors.state(request, acceptedCurrency, "cost", "teacher.tutorial.form.error.cost-currency-not-accepted");
			errors.state(request, cost.getAmount() > 0., "cost", "teacher.tutorial.form.error.invalid-cost");
		}
	} 

	@Override
	public void update(final Request<Tutorial> request, final Tutorial entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
}
