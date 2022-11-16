package acme.features.learner.followUp;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.followUps.FollowUp;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner; 
 
@Controller 
public class LearnerFollowUpController extends AbstractController<Learner, FollowUp> { 
	 
	@Autowired 
	protected LearnerFollowUpListService listRecentService; 
	
	@Autowired 
	protected LearnerFollowUpShowService showService; 
	
	@Autowired 
	protected LearnerHelpRequestFollowUpListService followUpListService; 
	 
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService);
		super.addCommand("listFollowUp", "list", this.followUpListService);
	} 
 
} 
