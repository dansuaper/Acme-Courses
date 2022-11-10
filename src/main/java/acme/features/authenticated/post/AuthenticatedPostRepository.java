package acme.features.authenticated.post;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.posts.Post;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedPostRepository extends AbstractRepository{
	
	@Query("Select p from Post p where p.instantationMoment > :fechaLimite")
	Collection<Post> findRecentPost(Date fechaLimite);
	
	@Query("Select p from Post p where p.id = :id")
	Post findPostById(int id);
	
	@Query("Select p.instantationMoment from Post p where p.id = :id")
	Date findPostMomentById(int id);
}
