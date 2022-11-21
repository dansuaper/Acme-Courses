package acme.features.any.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import acme.entities.MoneyExchange;
import acme.entities.tutorials.Tutorial;
//import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyTutorialShowService implements AbstractShowService<Any, Tutorial>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyTutorialRepository repository;

	@Override
	public boolean authorise(final Request<Tutorial> request) {
		assert request != null;

		boolean result;
		int id;
		final Tutorial tutorial;

		id = request.getModel().getInteger("id");
		tutorial = this.repository.findOneTutorialById(id);
		result = tutorial.isPublished();

		return result;
	}

	@Override
	public Tutorial findOne(final Request<Tutorial> request) {
		assert request != null;

		Tutorial result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTutorialById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Tutorial> request, final Tutorial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

//		final boolean differentCurrency = !entity.getRetailPrice().getCurrency().equals(this.repository.findSystemCurrency());
//		model.setAttribute("differentCurrency", differentCurrency);
		//Quizas alla q poner "cost" en vez de retailPrice
//		model.setAttribute("conversion", this.conversion(entity.getRetailPrice()).getTarget());

		request.unbind(entity, model, "published", "title", "ticker", "abstractText",
			"cost","info", "type", "teacher.userAccount.username");

	}

}
