package in.deepak.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.deepak.dto.JWTAuthResponse;
import in.deepak.dto.SigninRequest;
import in.deepak.dto.SignupRequest;
import in.deepak.entity.User;
import in.deepak.entity.UserDto;
import in.deepak.exception.UserException;
import in.deepak.jwt.JwtServiceImpl;
import in.deepak.repository.UserRepository;
import in.deepak.security.JwtTokenClaims;
import in.deepak.security.JwtTokenProvider;
import in.deepak.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public User signupRequest(SignupRequest signup ) {
		
		System.out.println("UserServiceImpl registerUser ----------begin");
		Optional<User> isEmailExist=userRepo.findByEmail(signup.getEmail());
		
		if(isEmailExist.isPresent()) {
			System.out.println("THis email is already exists");
		}
		System.out.println("this user is new userEmail comform so next check for username ");
		
		Optional<User> isUsernameExist=userRepo.findByUsername(signup.getUsername());
		if(isUsernameExist.isPresent()) {

			System.out.println("This Username Aleardy Exist");
		}
		
		System.out.println("this username is not present comform so create a new user");
		if(signup.getEmail()==null ||signup.getPassword()==null||signup.getUsername()==null|signup.getName()==null) {
			
			System.out.print("All Filed are Requried");
		}
		
		User newUser = new User();
		newUser.setEmail(signup.getEmail());
		newUser.setPassword(passwordEncoder.encode(signup.getPassword()));
		newUser.setUsername(signup.getUsername());
		newUser.setName(signup.getName());

		
		
		
		System.out.println("New User Create ----"+newUser);
		System.out.println("UserServiceImpl registerUser ----------end");
		
		return userRepo.save(newUser);
		
	}
	
	@Override
	public JWTAuthResponse signinRequest(SigninRequest signin) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getUsernameOrEmail(),signin.getPassword()));

		 var user=userRepo.findByEmail(signin.getUsernameOrEmail()).orElseThrow(()->new IllegalArgumentException("Invalid Username or password"));
		 
		 var jwtToken=jwtService.generatToken(user);
		 var refreshToken=jwtService.generatRefreshToken(new HashMap<>(),user);
		 
		 JWTAuthResponse jwtResponse=new JWTAuthResponse();
		 
		 jwtResponse.setToken(jwtToken);
		 jwtResponse.setRefreshToken(refreshToken);
		 
		 return jwtResponse;
		
	}
	
	

	@Override
	public User findById(Integer id) throws UserException {
		
		
		Optional<User> opt=userRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("User Not Found By this Id");
	}

	@Override
	public User findByUsername(String username) throws UserException {
		
		Optional<User> opt=userRepo.findByUsername(username);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("User not exist with username");
	}

	@Override
	public User findByProfile(String token) throws UserException {
		
		token=token.substring(0,7);
		JwtTokenClaims jwtTokenClaims=jwtTokenProvider.getCliamsFromToken(token);
		String username=jwtTokenClaims.getUsername();
		Optional<User> opt=userRepo.findByUsername(username);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
       throw new UserException("Invalid token ");	
	}

	@Override
	public String followUser(Integer reqUserId, Integer followerUserId) throws UserException {
		
		User reqUser=findById(reqUserId);
		User followUser=findById(followerUserId);
		
		UserDto follower=new UserDto();
		
		follower.setId(reqUser.getId());
		follower.setEmail(reqUser.getEmail());
		follower.setUsername(reqUser.getUsername());
		follower.setName(reqUser.getName());
//	follower.setImage(reqUser.getImage());

	
	in.deepak.entity.UserDto following=new UserDto();
	
	following.setId(followUser.getId());
	following.setEmail(followUser.getEmail());
	following.setUsername(followUser.getUsername());
	following.setName(followUser.getName());
//    following.setImage(followUser.getImage());
    
    reqUser.getFollowing().add(following);
    followUser.getFollowers().add(follower);
    
    
     userRepo.save(followUser);
     userRepo.save(reqUser);
     
		return "you are following "+followUser.getUsername();
	}

	@Override
	public String unfollowUser(Integer reqUserId, Integer followerUserId) throws UserException {
	
		User reqUser=findById(reqUserId);
		User followUser=findById(followerUserId);
		
		UserDto follower=new UserDto();
		
		follower.setId(reqUser.getId());
		follower.setEmail(reqUser.getEmail());
		follower.setUsername(reqUser.getUsername());
		follower.setName(reqUser.getName());
//	follower.setImage(reqUser.getImage());

	
	UserDto following=new UserDto();
	
	following.setId(followUser.getId());
	following.setEmail(followUser.getEmail());
	following.setUsername(followUser.getUsername());
	following.setName(followUser.getName());
//    following.setImage(followUser.getImage());
    
    reqUser.getFollowing().remove(following);
    followUser.getFollowers().remove(follower);
    
    
     userRepo.save(followUser);
     userRepo.save(reqUser);
     
		return "you are unfollowUser"+followUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserException {
		
		List<User> users=userRepo.findAllUserByUserIds(userIds);
		
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		
		List<User> users=userRepo.findByQuery(query);
		if(users.size()==0) {
			throw new UserException("User not Found");
		}
		return users;
	}

	@Override
	public User updateUserDetails(User updateUser, User existingUser) throws UserException {
		
		if(updateUser.getEmail()!=null) {
			existingUser.setEmail(updateUser.getEmail());
		}
		if(updateUser.getImage()!=null) {
			existingUser.setImage(updateUser.getImage());
		}
		if(updateUser.getId().equals(existingUser.getId())) {
			return userRepo.save(existingUser);
		}
		throw new UserException("You can't update this User");
	}

	@Override
	public User findUserById(Integer userIds) throws UserException {
		
		Optional<User> user=userRepo.findById(userIds);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("This userId is invalid");
		
	}

	
	
}
