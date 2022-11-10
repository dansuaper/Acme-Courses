package acme.features.teacher.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherTutorialShowService implements AbstractShowService<Teacher, Tutorial> {

	@Autowired
	protected TeacherTutorialRepository repository;


	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;

		boolean result;
		int tutorialId;
		Tutorial tutorial;

		tutorialId = request.getModel().getInteger("id");
		tutorial = this.repository.findTutorialById(tutorialId);
		result = tutorial != null && tutorial.getTeacher().getId() == request.getPrincipal().getActiveRoleId();

		return result;

	}

	@Override
	public Tutorial findOne(final Request<Tutorial> request) {
		assert request != null;

		Tutorial result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findTutorialById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "published", "title", "ticker", "abstractText", "cost", "info", "type", "teacher.userAccount.username");

	}
}
