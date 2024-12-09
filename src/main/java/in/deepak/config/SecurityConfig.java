package in.deepak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.deepak.jwt.JWTAuthenticationEntryPoint;
import in.deepak.jwt.JwtAuthenticationFilter;
import in.deepak.service.CustomeService;
import in.deepak.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private JWTAuthenticationEntryPoint point;
	
	@Autowired
	private CustomeService userServie;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
			request-> request.requestMatchers("/auth/api/**").permitAll()
			           .anyRequest().authenticated()
				)
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling(ex->ex.authenticationEntryPoint(point))
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(
				jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
//	 @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	    }
	
	@Bean 
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(userServie.userDetailsService());
		dao.setPasswordEncoder(passwordEncoder());
		
		return dao;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
		
	
			return config.getAuthenticationManager();
	}

}
