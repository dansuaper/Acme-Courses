package acme.features.teacher.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.courses.Course;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;

@Service
public class TeacherCourseCreateService implements AbstractCreateService<Teacher, Course> {
	
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
	@Autowired
	protected TeacherCourseRepository repository;
	
	@Override
	public boolean authorise(final Request<Course> request) {
		assert request !=null;
		
		return true;
	}

	@Override
	public void bind(final Request<Course> request, final Course entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "caption", "abstractText", "link");
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption","abstractText", "published", "link", 
			"teacher.userAccount.username", "cost");
	}

	@Override
	public Course instantiate(final Request<Course> request) {
		assert request != null;
		Course result;
		final Teacher teacher;
		
		teacher = this.repository.findTeacherByUserAccountId(request.getPrincipal().getAccountId());
		result = new Course();
		result.setPublished(false);
		result.setTeacher(teacher);
		
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
	public void create(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;
		
		entity.setTeacher(this.repository.findTeacherByUserAccountId(request.getPrincipal().getAccountId()));
		entity.setPublished(false);
		
		this.repository.save(entity);
	}
}