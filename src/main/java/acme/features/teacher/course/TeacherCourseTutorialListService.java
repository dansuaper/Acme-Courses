package acme.features.teacher.course;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service 
public class TeacherCourseTutorialListService implements AbstractListService<Teacher, Tutorial>{
	
	@Autowired
	protected TeacherCourseRepository repository;
	
	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;
		boolean res = false;
		int id;
		int teacherId;
		int userId;
		
		id = request.getModel().getInteger("id");
		final Course course= this.repository.findOneCourseById(id);
		teacherId = course.getTeacher().getId();
		userId = request.getPrincipal().getAccountId();
		final Teacher teacher = this.repository.findTeacherByUserAccountId(userId);
		final int teacherIdUser = teacher.getId();

		if(teacherId == teacherIdUser) {
			res = true;
		}

		return res;
	}

	@Override
	public Collection<Tutorial> findMany(final Request<Tutorial> request) {
		final Collection<Tutorial> result = new HashSet<Tutorial>();
		int courseId;
		courseId = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findQuantityByCourseId(courseId);
		
		for(final Quantity quantity: quantities) {
			final int id = quantity.getId();
			final Collection<Tutorial> tutorials = this.repository.findManyTutorialByQuantityId(id);
			result.addAll(tutorials);
		}
		
		return result;
	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		request.unbind(entity, model, "type", "title", "ticker", "teacher.userAccount.username", "cost"); 
		 
	}

}
