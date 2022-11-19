package acme.features.administrator.post;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.posts.Post;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorPostCreateService implements AbstractCreateService<Administrator, Post> {
	
	// Internal state ---------------------------------------------------------
		@Autowired
		protected AdministratorSystemConfigurationRepository scRepository;
		@Autowired
		protected AdministratorPostRepository repository;

	// AbstractCreateService<Administrator, Post> interface --------------


	@Override
	public boolean authorise(final Request<Post> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Post> request, final Post entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "caption", "message", "flag", "info");
		
	}

	@Override
	public void unbind(final Request<Post> request, final Post entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "caption", "message", "flag", "info");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		
	}

	@Override
	public Post instantiate(final Request<Post> request) {
		assert request != null;

		final Post result;
		final Date instantationMoment;

		result = new Post();
		instantationMoment = new Date(System.currentTimeMillis() - 1);
		result.setInstantationMoment(instantationMoment);

		return result;
	}

	@Override
	public void validate(final Request<Post> request, final Post entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		
		if (!errors.hasErrors("caption")) {
            errors.state(request, SpamDetector.isSpam(entity.getCaption(), this.scRepository.findSystemConfiguration()), "caption", "form.error.spam");
        }
		if (!errors.hasErrors("message")) {
            errors.state(request, SpamDetector.isSpam(entity.getMessage(), this.scRepository.findSystemConfiguration()), "message", "form.error.spam");
        }
	
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "administrator.post.confirmation.error");
		
	}

	@Override
	public void create(final Request<Post> request, final Post entity) {
		assert request != null;
		assert entity != null;

		final Date instantationMoment;

		instantationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setInstantationMoment(instantationMoment);
		this.repository.save(entity);
		
	}

}
