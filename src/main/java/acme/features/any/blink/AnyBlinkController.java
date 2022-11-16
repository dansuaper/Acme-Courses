package acme.features.any.blink;
 
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.blinks.Blink;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;
 
@Controller 
public class AnyBlinkController extends AbstractController<Any, Blink> { 
	
	//Internal State 
	 
	@Autowired 
	protected AnyBlinkListRecentService listRecentService; 
	
	@Autowired 
	protected AnyBlinkCreateService createService;
	
	//Constructors 
	
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("list", this.listRecentService);
		super.addCommand("create", this.createService);
	} 
 
} 