package br.com.razes.bytecode.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.razes.bytecode.model.users.User;
import br.com.razes.bytecode.service.users.UserService;

public class AuthWithTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private UserService userService;
	
	// Spring Boot Don't administration This Class Type, 
	// then Dependency Injection necessary make manually
	public AuthWithTokenFilter(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recoverToken(request);
		boolean tokenIsValid = tokenService.validateToken(token);
		if(tokenIsValid)
			authenticateUserWith(token);
		
		filterChain.doFilter(request, response);
	}

	private void authenticateUserWith(String token) {
		Long userIdAuth = tokenService.getIdUser(token);
		User user = userService.getUserBy(userIdAuth).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recoverToken(HttpServletRequest request) {
		Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"))
				.filter(e -> e.startsWith("Bearer "));
		return token.map(s -> s.substring(7)).orElse("");
	}

}
