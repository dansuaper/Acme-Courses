package acme.features.any.course;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyCourseRepository extends AbstractRepository{
		
		@Query("select c from Course c where c.published = :published")
		Collection<Course> findManyPublishedCourses(boolean published);

		@Query("select q.tutorial from Quantity q where q.course.id = :courseId")
		Collection<Tutorial> findManyTutorialsByCourseId(int courseId);

		@Query("select c from Course c where c.id = :id")
		Course findOneCourseById(int id);
		
		@Query("select q from Quantity q where q.course.id = :id")
		Collection<Quantity> findQuantityByCourseId(int id);
		
		@Query("Select q.tutorial from Quantity q where q.id = :id")
		Collection<Tutorial> findManyTutorialByQuantityId(int id);
					
		@Query("select sc.systemCurrency from SystemConfiguration sc")
		String findSystemCurrency();		
		
//		@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
//		MoneyExchange findMoneyExchange(@Param("currency")String currency, @Param("amount")Double amount,
//			@Param("systemCurrency")String systemCurrency);
}