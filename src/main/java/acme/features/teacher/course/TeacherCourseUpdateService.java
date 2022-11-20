package acme.features.teacher.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.courses.Course;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherCourseUpdateService implements AbstractUpdateService<Teacher, Course> {
	
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	@Autowired
	protected TeacherCourseRepository repository;
	 
	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		
		final boolean result;
		int courseId;
		final Course course;
		final Teacher teacher;
		
		courseId = request.getModel().getInteger("id");
		course = this.repository.findOneCourseById(courseId);
		teacher = course.getTeacher();
		
		result= !course.isPublished() && request.isPrincipal(teacher);
		
		return result;
	}

	@Override
	public void bind(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "caption", "abstractText", "link", "teacher.userAccount.username", "cost");
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));

		request.unbind(entity, model, "ticker", "caption", "abstractText", "published", "link", "teacher.userAccount.username", "cost");
	}

	@Override
	public Course findOne(final Request<Course> request) {
		assert request != null;
		
		Course result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneCourseById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("caption")) {
			errors.state(request, !SpamDetector.isSpam(entity.getCaption(), this.scRepository.findSystemConfiguration()), "caption", "teacher.course.form.error.spam");
	    }
		if (!errors.hasErrors("abstractText")) {
	        errors.state(request, !SpamDetector.isSpam(entity.getAbstractText(), this.scRepository.findSystemConfiguration()), "abstractText", "teacher.course.form.error.spam");
	    }
		
		if (!errors.hasErrors("ticker")) {
			final Course c = this.repository.findOneCourseByTicker(entity.getTicker());
			errors.state(request, c == null || c.getId() == entity.getId(), "ticker", "teacher.course.form.error.duplicated");
		}
			
	}

	@Override
	public void update(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
