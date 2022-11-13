package acme.features.teacher.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher;

@Service
public class TeacherCourseListMineService implements AbstractListService<Teacher, Course> {

	@Autowired
	protected TeacherCourseRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Course> findMany(final Request<Course> request) {
		assert request != null;

		Collection<Course> result;
		final int UAId = request.getPrincipal().getAccountId();
		final int teacherId = this.repository.findTeacherByUserAccountId(UAId).getId();

		result = this.repository.findManyCoursesByTeacherId(teacherId);

		return result;
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "caption", "link", "cost");
	}
}
