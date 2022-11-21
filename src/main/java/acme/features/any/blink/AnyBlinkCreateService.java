package acme.features.any.blink;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.blinks.Blink;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyBlinkCreateService implements AbstractCreateService<Any, Blink> {
	
	// Internal state ---------------------------------------------------------
		@Autowired
		protected AdministratorSystemConfigurationRepository scRepository;
		@Autowired
		protected AnyBlinkRepository repository;

	// AbstractCreateService<Administrator, Chirp> interface --------------


	@Override
	public boolean authorise(final Request<Blink> request) {
		
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Blink> request, final Blink entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "caption", "message", "authorAlias", "email");
		
	}

	@Override
	public void unbind(final Request<Blink> request, final Blink entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "caption", "message", "authorAlias", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		
	}

	@Override
	public Blink instantiate(final Request<Blink> request) {
		assert request != null;

		final Blink result;
		final Date instantationMoment;

		result = new Blink();
		instantationMoment = new Date(System.currentTimeMillis() - 1);
		result.setInstantationMoment(instantationMoment);

		return result;
	}
	
	@Override
	public void validate(final Request<Blink> request, final Blink entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		if (!errors.hasErrors("caption")) { 
            errors.state(request, !SpamDetector.isSpam(entity.getCaption(), this.scRepository.findSystemConfiguration()), "caption", "any.blink.form.error.spam");
        }
		if (!errors.hasErrors("message")) {
            errors.state(request, !SpamDetector.isSpam(entity.getMessage(), this.scRepository.findSystemConfiguration()),"message", "any.blink.form.error.spam");
        }
		if (!errors.hasErrors("authorAlias")) {
            errors.state(request, !SpamDetector.isSpam(entity.getAuthorAlias(), this.scRepository.findSystemConfiguration()), "authorAlias", "any.blink.form.error.spam");
        }
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "any.blink.confirmation.error");
		
	}

	@Override
	public void create(final Request<Blink> request, final Blink entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setInstantationMoment(moment);
		this.repository.save(entity);
		
	}
}
