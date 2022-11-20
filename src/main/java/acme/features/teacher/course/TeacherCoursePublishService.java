package acme.features.teacher.course;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherCoursePublishService implements AbstractUpdateService<Teacher, Course>{

	@Autowired
	protected TeacherCourseRepository repository;
	
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
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

		request.unbind(entity, model,"ticker", "caption", "abstractText", "published", "link", "teacher.userAccount.username", "cost");
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
		
		int courseId;
		courseId = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findQuantityByCourseId(courseId);
		final Collection<Tutorial> tutorials = new HashSet<Tutorial>();
		Boolean publishedTutorial = true;
		
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
		
		for(final Quantity quantity: quantities) {
			final int id = quantity.getId();
			final Collection<Tutorial> tutorial = this.repository.findManyTutorialByQuantityId(id);
			tutorials.addAll(tutorial);
		}
		errors.state(request, !tutorials.isEmpty(), "*", "teacher.course.form.error.no-tutorials");
		
		for (final Tutorial tutorial: tutorials) {
			publishedTutorial = publishedTutorial && tutorial.isPublished();
		}
		errors.state(request, publishedTutorial, "*", "teacher.course.form.error.no-tutorials-published");
	}

	@Override
	public void update(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		
		this.repository.save(entity);
	}

}