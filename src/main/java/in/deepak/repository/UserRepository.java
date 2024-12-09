package in.deepak.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.deepak.entity.User;
import in.deepak.exception.UserException;


@Repository
public interface  UserRepository extends JpaRepository<User, Integer>{
	
	 Optional<User> findByUsername(String username);
	 Optional<User> findByEmail(String email);
	 Optional<User>  findByUsernameOrEmail(String usernam,String email);
	
//	 Optional<User> findUserById(Integer userId) throws UserException;
	
	@Query("SELECT u FROM User u WHERE u.id IN:users")
	public List<User> findAllUserByUserIds(@Param("users") List<Integer> userId);
	
	@Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.email LIKE %:query%  ")
	public List<User> findByQuery(@Param("query") String query);
	

}