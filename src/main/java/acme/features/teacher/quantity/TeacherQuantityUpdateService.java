package acme.features.teacher.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.features.teacher.course.TeacherCourseRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityUpdateService implements AbstractUpdateService<Teacher, Quantity> {
	
	@Autowired
	protected TeacherCourseRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		int quantityId;
		boolean result;
		
		quantityId = request.getModel().getInteger("id");
		final Course course = this.repository.findCourseByQuantityId(quantityId);
		result = course != null && (course.isPublished() || request.isPrincipal(course.getTeacher()));
		
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "amount", "timeUnit"); 
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("coursePublished", entity.getCourse().isPublished());
		
		request.unbind(entity, model, "amount", "timeUnit", "tutorial.type", "tutorial.title", "tutorial.ticker", "tutorial.abstractText",
			"tutorial.cost", "tutorial.info", "tutorial.teacher.userAccount.username", "tutorial.published");
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		int quantityId;
		Quantity result;
		quantityId = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(quantityId);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null; 
		assert entity != null; 
		assert errors != null; 
		
		if(entity.getTutorial() == null) {
			errors.state(request, entity.getTutorial() != null, "tutorialId", "teacher.quantity.form.error.null");
		}
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
}
