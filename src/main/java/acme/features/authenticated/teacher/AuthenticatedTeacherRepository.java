package acme.features.authenticated.teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface AuthenticatedTeacherRepository extends AbstractRepository {

	@Query("select t from Teacher t where t.userAccount.id = :id")
	Teacher findOneTeacherByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

}