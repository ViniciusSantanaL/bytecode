package br.com.razes.bytecode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.razes.bytecode.model.users.TypeProfile;
import br.com.razes.bytecode.service.users.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	// necessary for user in AuthController
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST,"/users/**").permitAll()
		.antMatchers(HttpMethod.POST,"/auth").permitAll()
		.antMatchers(HttpMethod.DELETE,"/users/**").hasRole(TypeProfile.SUPER.toString())
		.antMatchers(HttpMethod.GET,"/users").hasAnyRole(TypeProfile.SUPER.toString(),TypeProfile.ADMIN.toString())
		.antMatchers(HttpMethod.PUT,"/users-profile/add").hasAnyRole(TypeProfile.SUPER.toString(),TypeProfile.ADMIN.toString())
		.antMatchers(HttpMethod.PUT,"/users-profile/remove").hasAnyRole(TypeProfile.SUPER.toString(),TypeProfile.ADMIN.toString())
		.antMatchers(HttpMethod.GET,"/users-profile").hasAnyRole(TypeProfile.SUPER.toString(),TypeProfile.ADMIN.toString())
		.antMatchers(HttpMethod.POST,"/users-profile").hasRole(TypeProfile.SUPER.toString())
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AuthWithTokenFilter(tokenService, userService), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

}
