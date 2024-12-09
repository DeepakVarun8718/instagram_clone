package in.deepak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.dto.JWTAuthResponse;
import in.deepak.dto.SigninRequest;
import in.deepak.dto.SignupRequest;
import in.deepak.entity.User;
import in.deepak.service.UserService;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> signin(@RequestBody SigninRequest signin){
		
		
		JWTAuthResponse jwtAuthResponse = userService.signinRequest(signin);
		
		return new  ResponseEntity<JWTAuthResponse>(jwtAuthResponse,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignupRequest signup){
		
		User response=userService.signupRequest(signup);
		return new  ResponseEntity<User>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/welcome")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<String>("Welcome user your a instagram user",HttpStatus.OK);
	}
	
	
}
