package acme.entities.quantities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.courses.Course;
import acme.entities.tutorials.Tutorial;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@Min(value = 1)
	protected Double quantity;
	
	protected String timeUnit;
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Course course;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Tutorial tutorial;
}
