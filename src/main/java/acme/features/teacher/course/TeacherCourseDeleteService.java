package acme.features.teacher.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Teacher;

@Service
public class TeacherCourseDeleteService implements AbstractDeleteService<Teacher, Course>{

	@Autowired
	protected TeacherCourseRepository repository;
	
	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		
		final boolean result;
		final int courseId;
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

		request.bind(entity, errors, "ticker", "caption", "abstractText", "link", "teacher.userAccount.username","cost");
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption", "abstractText", "link", "published", "link", "teacher.userAccount.username", "cost");
		
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
	}

	@Override
	public void delete(final Request<Course> request, final Course entity) {
		assert request != null;
		assert entity != null;
		final Collection<Quantity> quantities;
		
		quantities= this.repository.findQuantityByCourseId(entity.getId());
		
		for(final Quantity quantity:quantities) {
			this.repository.delete(quantity);
		}
		
		this.repository.delete(entity);
	}

}