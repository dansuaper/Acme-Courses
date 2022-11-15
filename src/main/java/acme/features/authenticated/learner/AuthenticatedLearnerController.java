package acme.features.authenticated.learner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Learner;

@Controller
@RequestMapping("/authenticated/learner/")
public class AuthenticatedLearnerController extends AbstractController<Authenticated, Learner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedLearnerCreateService	createService;

	@Autowired
	protected AuthenticatedLearnerUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}

}
