package acme.features.authenticated.post;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.posts.Post;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedPostController extends AbstractController<Authenticated, Post> {
	//Internal State
	
	@Autowired
	protected AuthenticatedPostListRecentService listRecentService;
	@Autowired
	protected AuthenticatedPostShowService showService;
	
	
	//Constructors
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listRecentService);
	}

}