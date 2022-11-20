package acme.features.teacher.quantity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.teacher.course.TeacherCourseRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherQuantityShowService implements AbstractShowService<Teacher, Quantity> {

	@Autowired
	protected TeacherCourseRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		int quantityId;
		boolean result;
		
		quantityId = request.getModel().getInteger("id");
		final Course course = this.repository.findCourseByQuantityId(quantityId);
		result = course != null && (course.isPublished() || request.isPrincipal(course.getTeacher()));
		
		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		int quantityId;
		Quantity result;
		quantityId = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(quantityId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("published", entity.getTutorial().isPublished());
		final boolean differentCurrency = !entity.getTutorial().getCost().getCurrency().equals(this.repository.findSystemCurrency());
		model.setAttribute("differentCurrency", differentCurrency);
		model.setAttribute("conversion", this.convertir(entity.getTutorial().getCost()).getTarget());

		request.unbind(entity, model, "amount", "timeUnit", "course.ticker", "tutorial.ticker", "tutorial.type", "tutorial.title", "tutorial.ticker", "tutorial.cost");
	}
	
	public MoneyExchange convertir(final Money money) {
		
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		MoneyExchange m = new MoneyExchange();
		final String systemCurrency = this.repository.findSystemCurrency();

		if(!money.getCurrency().equals(systemCurrency)) {
			m = this.repository.findMoneyExchange(money.getCurrency(), money.getAmount(),systemCurrency);
			if(m == null) {
				m = moneyExchange.computeMoneyExchange(money, systemCurrency);
				this.repository.save(m);
			}
		}else {
			m.setSource(money);
			m.setTarget(money);
			m.setCurrency(systemCurrency);
			m.setDate(new Date(System.currentTimeMillis()));
		}
		return m;
	}
	
//	public Money price(final int courseId) {
//		final Money result = new Money();
//		result.setAmount(0.);
//		result.setCurrency(this.repository.findSystemCurrency());
//		final Collection<Quantity> quantities = this.repository.findQuantityByCourseId(courseId);
//				
//		for(final Quantity q: quantities) {
//				final Double c;
//				final Money money = q.getTutorial().getCost();
//				final double number = q.getAmount();
//				
//				c = this.convertir(money).getTarget().getAmount();
//				
//				final Double newAmount = Math.round((result.getAmount() + c * number) * 100)/100.0;
//				result.setAmount(newAmount);
//		}
//		
//		return result;
//	}

}
