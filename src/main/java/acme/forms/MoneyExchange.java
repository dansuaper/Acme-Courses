package acme.forms;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MoneyExchange extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	// Query attributes -------------------------------------------------------

	@NotNull
	@Valid
	public Money	source;

	@NotBlank
	public String	currency;

	// Response attributes ----------------------------------------------------

	@Valid
	public Money	target;

	public Date		date;

}
