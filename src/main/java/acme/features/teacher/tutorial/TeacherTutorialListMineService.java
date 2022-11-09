package acme.features.teacher.tutorial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Teacher; 
 
@Service 
public class TeacherTutorialListMineService implements AbstractListService<Teacher, Tutorial> { 
	// Internal state --------------------------------------------------------- 
 
	@Autowired 
	protected TeacherTutorialRepository repository; 
	 
	 
	@Override 
	public boolean authorise(final Request<Tutorial> request) { 
		assert request != null; 
 
		return true; 
	} 
 
	@Override 
	public Collection<Tutorial> findMany(final Request<Tutorial> request) { 
		assert request != null; 
 
		Collection<Tutorial> result; 
		final int UAId = request.getPrincipal().getAccountId();
		final int TeacherId = this.repository.findTeacherByUserAccountId(UAId).getId();
		result = this.repository.findManyTutorialsByTeacherId(TeacherId); 
 
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