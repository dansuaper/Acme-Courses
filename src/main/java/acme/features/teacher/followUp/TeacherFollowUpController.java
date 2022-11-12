package acme.features.teacher.followUp;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.followUps.FollowUp;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher; 
 
@Controller 
public class TeacherFollowUpController extends AbstractController<Teacher, FollowUp> { 
	 
	@Autowired 
	protected TeacherFollowUpListService listRecentService; 
	
	@Autowired 
	protected TeacherFollowUpShowService showService; 
	
	@Autowired
	protected TeacherHelpRequestFollowUpListService followUpListService;
	 
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService); 
		super.addCommand("listFollowUp", "list", this.followUpListService); 
		
	} 
 
} 
