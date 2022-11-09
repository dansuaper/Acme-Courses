package acme.features.any.blink;
 
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.blinks.Blink;
import acme.framework.repositories.AbstractRepository; 
 
@Repository 
public interface AnyBlinkRepository extends AbstractRepository{ 
	 
	@Query("Select b from Blink b where b.instantationMoment > :fechaLimite") 
	Collection<Blink> findRecentBlink(Date fechaLimite); 
	 
	@Query("Select b from Blink b where b.id = :id") 
	Blink findBlinkById(int id); 
	
	
} 