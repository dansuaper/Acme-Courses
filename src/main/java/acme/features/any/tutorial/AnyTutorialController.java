package acme.features.any.tutorial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.tutorials.Tutorial;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyTutorialController extends AbstractController<Any, Tutorial>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyTutorialListPublishedService	tutorialListPublishedService;
	

	@Autowired
	protected AnyTutorialShowService	showService;
	
	
//	@Autowired
//	protected AnyCourseTutorialListService	courseTutorialListService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.tutorialListPublishedService);
//		super.addCommand("listCourseTutorial", "list", this.courseTutorialListService);
		super.addCommand("show", this.showService);
	}

}