
package acme.features.any.course;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyCourseTutorialListService implements AbstractListService<Any, Tutorial> {

	@Autowired
	protected AnyCourseRepository repository;

	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;
		int id;
		
		id = request.getModel().getInteger("id");
		final Course course = this.repository.findOneCourseById(id);
		
		return course.isPublished();
	}

	@Override
	public Collection<Tutorial> findMany(final Request<Tutorial> request) {
		final Collection<Tutorial> result = new HashSet<Tutorial>();
		int courseId;
		
		courseId = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findQuantityByCourseId(courseId);
		for (final Quantity quantity : quantities) {
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

		request.unbind(entity, model, "type", "caption", "ticker");

	}

}
