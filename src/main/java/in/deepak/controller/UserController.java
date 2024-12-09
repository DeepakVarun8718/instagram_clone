package in.deepak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.exception.UserException;
import in.deepak.dto.MessageReponse;
import in.deepak.entity.User;
import in.deepak.entity.UserDto;
import in.deepak.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService ;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id ) throws UserException{
		
		User user=userService.findById(id);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/username/{username}")
	public  ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException{
	
		User user=userService.findByUsername(username);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@PutMapping("follow/{followUserId}")
	public ResponseEntity<MessageReponse> followUserHandler(@PathVariable Integer followUserId,@RequestHeader("Authorization") String token) throws UserException{
		
	    
		User user=userService.findByProfile(token);
		String message=userService.followUser(user.getId(), followUserId);
		
		MessageReponse msg=new MessageReponse(message);
		
		return new ResponseEntity<MessageReponse>(msg,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("unfollow/{userId}")
	public ResponseEntity<MessageReponse> unfollowUserHandler(@PathVariable Integer userId,@RequestHeader("Authorization") String token) throws UserException{
		User user=userService.findByProfile(token);
		String message=userService.unfollowUser(user.getId(), userId);
		
		MessageReponse msg=new MessageReponse(message);
		
		return new ResponseEntity<MessageReponse>(msg,HttpStatus.ACCEPTED);	
	
	}
	
	
	@PutMapping("/req")
	public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException{
		
		User user=userService.findByProfile(token);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/m/{userIds}")
	public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException{
		
		List<User> users=userService.findUserByIds(userIds);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	//api/uses/serach?q="query"
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException{
		
		List<User> users=userService.searchUser(query);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@PutMapping("/account/edit")
	public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token,@RequestBody User user ) throws UserException{
		
		User reqUser=userService.findByProfile(token);	
//		User updatedUser=userService.updateUserDetails(user, user);
		
		User updatedUser=userService.updateUserDetails(user, reqUser);
		
		
		return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
	}
	

}
