package acme.features.teacher.course;

import java.util.Collection;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface TeacherCourseRepository extends AbstractRepository{
	
	@Query("select c from Course c where c.teacher.id= :id")
	Collection<Course> findManyCoursesByTeacherId(int id);
	
	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);
	
	@Query("Select t from Teacher t where t.userAccount.id = :id")
	Teacher findTeacherByUserAccountId(int id);
	
	@Query("select q from Quantity q where q.course.id = :id")
	Collection<Quantity> findQuantityByCourseId(int id);
	
	@Query("Select q.tutorial from Quantity q where q.id = :id")
	Collection<Tutorial> findManyTutorialByQuantityId(int id);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
	
	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchange(String currency, Double amount, String systemCurrency);
	
//	@Query("select count (q.element) from Quantity q where q.element.type = acme.entities.elements.ElementType.INGREDIENT and q.recipe.id = :recipeId")
//	Integer findNumIngredientsOfRecipe(int courseId);
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);
	
	@Query("select c from Course c where c.ticker = :ticker")
	Course findOneCourseByTicker(String ticker);
	
	@Query("select q.course from Quantity q where q.id = :id")
	Course findCourseByQuantityId(int id);

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(int id);
	
	@Query("select t from Tutorial t where t.published = true and t not in (select q.tutorial from Quantity q where q.course.id = :courseId)")
	Collection<Item> findAssignableTutorials(int courseId);
}
