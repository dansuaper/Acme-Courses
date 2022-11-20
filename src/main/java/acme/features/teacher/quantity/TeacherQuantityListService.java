package acme.features.teacher.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.features.teacher.course.TeacherCourseRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityListService implements AbstractListService<Teacher, Quantity> {

	@Autowired
	protected TeacherCourseRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		int courseId;
		boolean result;
		
		courseId = request.getModel().getInteger("id");
		final Course course = this.repository.findOneCourseById(courseId);
		result = course != null && (course.isPublished() || request.isPrincipal(course.getTeacher()));
		
		return result;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		final int courseId;
		courseId = request.getModel().getInteger("id");
		
		return this.repository.findQuantityByCourseId(courseId);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null; 
		assert model != null; 
		
		int courseId;
		courseId = request.getModel().getInteger("id");
		model.setAttribute("courseId", courseId);
		
		Course course;
		course = this.repository.findOneCourseById(courseId);
		model.setAttribute("isPublished", course.isPublished());
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
 
		request.unbind(entity, model, "amount", "timeUnit", "tutorial.type", "tutorial.ticker", "tutorial.cost"); 
	}

}
