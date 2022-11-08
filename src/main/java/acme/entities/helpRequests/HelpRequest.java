package acme.entities.helpRequests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Learner;
import acme.roles.Teacher;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class HelpRequest extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected HelpRequestStatus status;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(:[A-Z]{1,10})?$") 
	protected String ticker;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String statement;
	
	@Valid
	@NotNull
	protected Money budget;
	
	protected Date startDate;
	
	protected Date endDate;
	
	@URL
	protected String info;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Teacher teacher;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Learner learner;
	
}
