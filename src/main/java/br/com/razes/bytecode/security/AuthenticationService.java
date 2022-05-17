package br.com.razes.bytecode.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.razes.bytecode.model.users.User;
import br.com.razes.bytecode.service.users.UserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userForAuthentication = userService.getUserBy(username);
		
		if(userForAuthentication.isPresent())
			return userForAuthentication.get();
		
		throw new UsernameNotFoundException("Invalid User data!!");
		
	}
}
