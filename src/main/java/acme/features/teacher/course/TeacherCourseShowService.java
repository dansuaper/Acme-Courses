
package acme.features.teacher.course;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherCourseShowService implements AbstractShowService<Teacher, Course> {

	@Autowired
	protected TeacherCourseRepository repository;


	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		boolean res = false;
		int id;
		final int teacherId;
		int userId;
		
		id = request.getModel().getInteger("id");
		final Course course = this.repository.findOneCourseById(id);
		teacherId = course.getTeacher().getId();
		userId = request.getPrincipal().getAccountId();
		final Teacher teacher = this.repository.findTeacherByUserAccountId(userId);
		final int teacherIdUser = teacher.getId();

		if (teacherId == teacherIdUser) {
			res = true;
		}

		return res;
	}

	@Override
	public Course findOne(final Request<Course> request) {
		assert request != null;

		Course result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneCourseById(id);
		result.setCost(this.price(id));
		
		return result;
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
	
	public Money price(final int courseId) {
		final Money result = new Money();
		result.setAmount(0.);
		result.setCurrency(this.repository.findSystemCurrency());
		final Collection<Quantity> quantities = this.repository.findQuantityByCourseId(courseId);
				
		for(final Quantity q: quantities) {
				final Double c;
				final Money money = q.getTutorial().getCost();
				final double number = q.getAmount();
				
				c = this.convertir(money).getTarget().getAmount();
				
				final Double newAmount = Math.round((result.getAmount() + c * number) * 100)/100.0;
				result.setAmount(newAmount);
		}
		
		return result;
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("teacher", entity.getTeacher().getUserAccount().getUsername());
		model.setAttribute("cost",entity.getCost());

		request.unbind(entity, model, "ticker", "caption", "abstractText", "published", "link", "teacher.userAccount.username");
	}
}
