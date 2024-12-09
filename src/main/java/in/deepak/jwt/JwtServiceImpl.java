package in.deepak.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl {

	

	public String generatToken(UserDetails userDetails) {
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24) )
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
public String generatRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails) {
		
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+604800000) )
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	private <T> T extractCliam(String token,Function<Claims,T> claimsResolver) {
		final Claims claims =extractAllCliams(token);
		
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllCliams(String token) {
		
		return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
	
	public String extractUserName(String token) {
		return extractCliam(token,Claims::getSubject);
	}

	private Key getSignKey() {
		
		byte[] key = Decoders.BASE64.decode("skjdagjfq7r394124dbsgfiwe7287129740324tncjfeyfgysukfu2784612740174ujsft7836");
		
		return Keys.hmacShaKeyFor(key);
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUserName(token);
		return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		
		
		return extractCliam(token, Claims::getExpiration).before(new Date());
	}
}
