package acme.features.teacher.helpRequest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.helpRequests.HelpRequest;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherHelpRequestShowService implements AbstractShowService<Teacher, HelpRequest> {

	@Autowired 
	protected TeacherHelpRequestRepository repository; 
	
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		boolean result;
		final int HelpRequestId;
		final Teacher teacher;
		final HelpRequest helpRequest;

		HelpRequestId = request.getModel().getInteger("id");
		helpRequest = this.repository.findHelpRequestById(HelpRequestId);
		teacher = this.repository.findTeacherByUserAccountId(request.getPrincipal().getAccountId());
		result = helpRequest.getTeacher().equals(teacher);

		return result;
	}
 
	@Override
	public HelpRequest findOne(final Request<HelpRequest> request) {
		assert request != null;

		HelpRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findHelpRequestById(id);

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
 
	@Override 
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		final boolean dif = !entity.getBudget().getCurrency().equals(this.repository.findSystemCurrency());
		model.setAttribute("dif", dif);
		model.setAttribute("convertir", this.convertir(entity.getBudget()).getTarget());
		 
		request.unbind(entity, model, "ticker", "budget", "info", "statement", "startDate", "endDate","status", "teacher.userAccount.username", "teacher.school", "teacher.info", "teacher.statement");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
}
