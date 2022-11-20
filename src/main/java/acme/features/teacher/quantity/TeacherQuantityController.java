package acme.features.teacher.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantities.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherQuantityController extends AbstractController<Teacher, Quantity> {
	
	@Autowired
	protected TeacherQuantityListService listService;
	
	@Autowired
	protected TeacherQuantityCreateService createService;
	
	@Autowired
	protected TeacherQuantityShowService showService;
	
	@Autowired
	protected TeacherQuantityUpdateService updateService;
				
	@Autowired
	protected TeacherQuantityDeleteService deleteService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}
