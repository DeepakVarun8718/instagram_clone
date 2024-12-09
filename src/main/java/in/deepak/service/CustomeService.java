package in.deepak.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CustomeService {

	public UserDetailsService userDetailsService();
}
