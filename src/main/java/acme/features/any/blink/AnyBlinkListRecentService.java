package acme.features.any.blink;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blinks.Blink;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService; 
 
@Service 
public class AnyBlinkListRecentService implements AbstractListService<Any, Blink> { 
	 
	@Autowired 
	protected AnyBlinkRepository repository; 
 
	@Override 
	public boolean authorise(final Request<Blink> request) { 
		assert request != null; 
		return true; 
	} 
 
	@Override 
	public Collection<Blink> findMany(final Request<Blink> request) { 
		assert request != null; 
		 
		final Collection<Blink> result; 
		Calendar calendar; 
		Date fechaLimite; 
		 
		calendar=Calendar.getInstance(); 
		calendar.add(Calendar.MONTH, -1); 
		fechaLimite=calendar.getTime(); 
		 
		result=this.repository.findRecentBlink(fechaLimite); 
		 
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<Blink> request, final Blink entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		request.unbind(entity, model, "instantationMoment","caption", "authorAlias", "message", "email");		 
	} 

} 
