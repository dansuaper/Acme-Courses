package acme.features.learner.helpRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner; 
 
@Controller 
public class LearnerHelpRequestController extends AbstractController<Learner, HelpRequest> { 
	//Internal State 
	 
	@Autowired 
	protected LearnerHelpRequestListService listRecentService; 
	@Autowired 
	protected LearnerHelpRequestShowService showService; 
	 
	 
	//Constructors 
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService); 
	} 
 
} 