package acme.features.learner.helpRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner; 
 
@Controller 
public class LearnerHelpRequestController extends AbstractController<Learner, HelpRequest> { 

	@Autowired 
	protected LearnerHelpRequestListService listRecentService; 
	
	@Autowired 
	protected LearnerHelpRequestShowService showService; 
	 
	@Autowired 
	protected LearnerHelpRequestCreateService createService; 
	
	@Autowired 
	protected LearnerHelpRequestDeleteService deleteService; 
	
	@Autowired 
	protected LearnerHelpRequestPublishService publishService; 
	
	@Autowired 
	protected LearnerHelpRequestUpdateService updateService; 
	
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService); 
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService); 
		super.addCommand("publish", "update", this.publishService); 
		super.addCommand("update", this.updateService);
	} 
 
} 