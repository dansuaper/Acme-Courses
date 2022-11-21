package acme.features.teacher.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.features.teacher.course.TeacherCourseRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityCreateService implements AbstractCreateService<Teacher, Quantity> {

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
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(this.repository.findAssignableTutorials(entity.getCourse().getId()).isEmpty()) {
			request.bind(entity, errors, "amount", "timeUnit");
		} else {
			entity.setTutorial(this.repository.findTutorialById(Integer.valueOf(request.getModel().getAttribute("tutorialId").toString())));
			request.bind(entity, errors, "amount", "timeUnit", "tutorialId");
		}
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		final int masterId = Integer.parseInt((String) request.getModel().getAttribute("id"));
		model.setAttribute("masterId", masterId);
		model.setAttribute("tutorials", this.repository.findAssignableTutorials(masterId));
		request.unbind(entity, model, "amount", "timeUnit");
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		result = new Quantity();
		
		final Course course = this.repository.findOneCourseById(request.getModel().getInteger("id"));
		
		result.setCourse(course);
		
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
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;	

		this.repository.save(entity);
	}
}
