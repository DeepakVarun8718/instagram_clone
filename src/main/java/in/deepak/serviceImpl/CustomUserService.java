package in.deepak.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.deepak.repository.UserRepository;
import in.deepak.service.CustomeService;

@Service
public class CustomUserService implements  CustomeService{

	@Autowired
	private UserRepository userRepo;
	
	
			
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with this email"));
			}
			
	};
	
	}
			

	
	

	

}
