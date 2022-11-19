package acme.features.administrator.post;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.posts.Post;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdminitstratorPostController extends AbstractController<Administrator,Post> {
	
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorPostCreateService		createService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("create", this.createService);
		}

}
