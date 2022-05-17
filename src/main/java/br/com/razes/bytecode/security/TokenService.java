package br.com.razes.bytecode.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.razes.bytecode.model.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${razes.jwt.expiration}")
	private String expiration;
	
	@Value("${razes.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		User loggedUser = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
			.setIssuer("Bytecode API")
			.setSubject(loggedUser.getId().toString())
			.setIssuedAt(today)
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}
	
	public Long getIdUser(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(secret)
				.parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}
}
