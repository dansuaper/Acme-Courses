package acme.features.authenticated.post;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.posts.Post;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedPostShowService implements AbstractShowService<Authenticated, Post>{
	
	@Autowired
	protected AuthenticatedPostRepository repository;
	// Interfaz
	@Override
	public boolean authorise(final Request<Post> request) {
		assert request != null;
		boolean res=false;
		Date fecha;
		Calendar calendar;
		Date fechaLimite;
		
		calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		fechaLimite=calendar.getTime();
		final int id=request.getModel().getInteger("id");
		fecha=this.repository.findPostMomentById(id);
		if (fecha.after(fechaLimite)) {
			res=true;
		}
		
		return res;
	}

	@Override
	public Post findOne(final Request<Post> request) {
		assert request != null;
		
		Post result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findPostById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Post> request, final Post entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "instantationMoment","caption", "message", "info", "flag");	
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}

}
