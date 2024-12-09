package in.deepak.jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		System.out.print("jwtAuthenticationEntryPoint    start--------------------");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
		
		System.out.print("jwtAuthenticationEntryPoint  end --------------------");
	}
}
