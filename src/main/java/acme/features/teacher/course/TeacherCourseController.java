package acme.features.teacher.course;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.courses.Course;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherCourseController extends AbstractController<Teacher, Course> {

	@Autowired
	protected TeacherCourseListMineService	courseListMineService;

	@Autowired
	protected TeacherCourseShowService		showService;

	@Autowired
	protected TeacherCourseCreateService	createService;

	@Autowired
	protected TeacherCourseUpdateService	updateService;

	@Autowired
	protected TeacherCourseDeleteService	deleteService;

	@Autowired
	protected TeacherCoursePublishService	publishService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.courseListMineService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
	}
}
