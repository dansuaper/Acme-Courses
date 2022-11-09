package acme.features.learner.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.LearnerDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner;

@Controller
public class LearnerDashboardController extends AbstractController<Learner, LearnerDashboard> {

	@Autowired
	protected LearnerDashboardShowService learnerDashboardShowService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.learnerDashboardShowService);
	}

}
