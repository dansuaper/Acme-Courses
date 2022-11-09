package acme.features.authenticated.post;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.posts.Post;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedPostListRecentService implements AbstractListService<Authenticated, Post> {
	
	@Autowired
	protected AuthenticatedPostRepository repository;

	@Override
	public boolean authorise(final Request<Post> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Post> findMany(final Request<Post> request) {
		assert request != null;
		
		final Collection<Post> result;
		Calendar calendar;
		Date fechaLimite;
		
		calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		fechaLimite=calendar.getTime();
		
		result=this.repository.findRecentPost(fechaLimite);
		
		return result;
	}

	@Override
	public void unbind(final Request<Post> request, final Post entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "instantationMoment","caption", "message", "info", "flag");		
	}

}
