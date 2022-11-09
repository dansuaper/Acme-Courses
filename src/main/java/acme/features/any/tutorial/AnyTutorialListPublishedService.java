package acme.features.any.tutorial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyTutorialListPublishedService implements AbstractListService<Any, Tutorial> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected AnyTutorialRepository repository;
			
			@Override
			public boolean authorise(final Request<Tutorial> request) {
				assert request != null;

				return true;
			}

			@Override
			public Collection<Tutorial> findMany(final Request<Tutorial> request) {
				assert request != null;

				Collection<Tutorial> result;

				result = this.repository.findManyPublishedTutorials(true);

				return result;
			}

			@Override
			public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model, "type", "title", "teacher.userAccount.username", "ticker", "cost");
				
			}	

}
