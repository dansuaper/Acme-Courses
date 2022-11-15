package acme.features.authenticated.learner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Learner;

@Service
public class AuthenticatedLearnerUpdateService implements AbstractUpdateService<Authenticated, Learner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedLearnerRepository repository;

	// AbstractUpdateService<Authenticated, Patron> interface -----------------


	@Override
	public boolean authorise(final Request<Learner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Learner> request, final Learner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Learner> request, final Learner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "school", "statement", "info");
	}

	@Override
	public void unbind(final Request<Learner> request, final Learner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "school", "statement", "info");
	}

	@Override
	public Learner findOne(final Request<Learner> request) {
		assert request != null;

		Learner result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneLearnerByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Learner> request, final Learner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Learner> request, final Response<Learner> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
