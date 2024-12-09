package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.dto.JWTAuthResponse;
import in.deepak.dto.SigninRequest;
import in.deepak.dto.SignupRequest;
import in.deepak.entity.User;
import in.deepak.exception.UserException;

@Service
public interface UserService {

	
	public JWTAuthResponse signinRequest(SigninRequest signin);
	
	
	public User signupRequest(SignupRequest signup );
	
	
	public User findById(Integer id) throws UserException;
	public User findByUsername(String username) throws UserException;
	public User findByProfile(String token) throws UserException;
	

	
	public String followUser(Integer reqUserId, Integer followerUserId) throws UserException;
	
	public String unfollowUser(Integer reqUserId, Integer followerUserId) throws UserException;
	
	public List<User> findUserByIds(List<Integer> userIds) throws UserException;
	
	public User findUserById(Integer userIds) throws UserException;
	
	public List<User> searchUser(String query) throws UserException;;
	
	public User updateUserDetails(User updateUser,User existingUser) throws UserException;;
}
