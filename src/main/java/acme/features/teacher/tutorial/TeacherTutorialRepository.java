package acme.features.teacher.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;
 
@Repository 
public interface TeacherTutorialRepository extends AbstractRepository{ 
	 
	@Query("select tut from Tutorial tut where tut.teacher.id = :id")
	Collection<Tutorial> findManyTutorialsByTeacherId(int id);
	 
	@Query("Select tch from Teacher tch where tch.userAccount.id = :id")
	Teacher findTeacherByUserAccountId(int id);
	
	@Query("select tut from Tutorial tut where tut.id = :id")
	Tutorial findTutorialById(int id);
} 
