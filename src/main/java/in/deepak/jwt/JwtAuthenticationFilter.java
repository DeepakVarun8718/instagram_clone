package in.deepak.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.deepak.entity.User;
import in.deepak.repository.UserRepository;
import in.deepak.service.CustomeService;
import in.deepak.service.UserService;
import in.deepak.serviceImpl.CustomUserService;
import in.deepak.serviceImpl.UserServiceImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	@Autowired 
	private CustomUserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
   	final String  authHeader=request.getHeader("Authorization");
   	final String jwtToken;
   	final String userEmail;
   	
   System.out.println("authHeader -----"+authHeader);
   	
   	if(org.springframework.util.StringUtils.isEmpty(authHeader)&& !org.apache.commons.lang3.StringUtils.startsWith(authHeader,"Bearer")) {
   		filterChain.doFilter(request, response);
   	}
 	else{
   		jwtToken=authHeader.substring(7);
		String username=jwtService.extractUserName(jwtToken);
		User user=userRepo.findByUsername(username).orElseThrow();
		 userEmail=user.getEmail();
		
		
		if(StringUtils.isNotEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication()==null) {
			
		
			UserDetails userDetails =userService.userDetailsService().loadUserByUsername(userEmail);
			
			System.out.println(userDetails);
			if(jwtService.isTokenValid(jwtToken,userDetails)) {
				
				
//				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				
				UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
						userDetails, null,userDetails.getAuthorities());
				
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
//				securityContext.setAuthentication(token);
				
				SecurityContextHolder.getContext().setAuthentication(token);;
			}		
						
			}
	}
		
		filterChain.doFilter(request, response);
	}
		
	
	
}
