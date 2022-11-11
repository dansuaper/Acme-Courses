package acme.features.teacher.helpRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.helpRequests.HelpRequest;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher; 

@Controller
public class TeacherHelpRequestController extends AbstractController<Teacher, HelpRequest> {

	@Autowired
	protected TeacherHelpRequestListService	listPublishedAcceptedAndDeniedService;
	
	@Autowired
	protected TeacherHelpRequestShowService	showService;
	//		@Autowired 
	//		protected ChefFineDishListProposedService listPublishedProposedService; 

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listPublishedAcceptedAndDeniedService);
	}

}
