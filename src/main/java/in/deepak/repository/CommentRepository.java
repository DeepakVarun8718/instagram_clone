package in.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.deepak.entity.Comments;


@Repository
public interface CommentRepository extends JpaRepository<Comments,Integer> {
	

}
