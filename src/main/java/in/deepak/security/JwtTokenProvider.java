package in.deepak.security;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import in.deepak.security.JwtTokenClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {

   public JwtTokenClaims getCliamsFromToken(String token) {
	   
	   SecretKey secretKey=Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
	   
	   Claims claims=Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody();
	   
	   String username=String.valueOf(claims.get("username"));
	   
	   JwtTokenClaims jwtClaims=new JwtTokenClaims();
	   jwtClaims.setUsername(username);
		
		return jwtClaims;
	}
}
