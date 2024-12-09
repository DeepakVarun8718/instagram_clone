package in.deepak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.deepak.entity.Post;
import in.deepak.service.PostService;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query("SELECT p FROM Post p WHERE p.user.id = userId")
	List<Post> findPostsByUserId(@Param("userId") Integer userId);
}
