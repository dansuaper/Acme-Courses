
package acme.features.any.course;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.courses.Course;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyCourseController extends AbstractController<Any, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyCourseListPublishedService	courseListPublishedService;

	@Autowired
	protected AnyCourseShowService			showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", "list", this.courseListPublishedService);
		super.addCommand("show", this.showService);
	}

}
