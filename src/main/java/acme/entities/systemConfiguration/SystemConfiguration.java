package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	public String acceptedCurrencies;
	
	@NotBlank
	public String systemCurrency;

	@NotBlank
	public String spamRecordsEn;
	
	@NotBlank
	public String spamRecordsEs;
	
	@NotNull
	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	public Double spamThreshold;
	
	@NotNull
	@Range(min = 1, max = 2)
	@Digits(integer = 1, fraction = 2)
	public Double spamBooster;
	
	@NotBlank
	public String moneyExchangeName;
	
	@URL
	public String moneyExchangeLink;
}

