package acme.features.teacher.tutorial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.tutorials.Tutorial;
import acme.features.teacher.course.TeacherCourseTutorialListService;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherTutorialController extends AbstractController<Teacher, Tutorial> {

	@Autowired
	protected TeacherTutorialListMineService	tutorialListMineService;

	@Autowired
	protected TeacherTutorialShowService		tutorialShowService;

	@Autowired
	protected TeacherCourseTutorialListService	courseTutorialListService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.tutorialListMineService);
		super.addCommand("show", this.tutorialShowService);
		super.addCommand("listCourseTutorials", "list", this.courseTutorialListService);
	}

}
