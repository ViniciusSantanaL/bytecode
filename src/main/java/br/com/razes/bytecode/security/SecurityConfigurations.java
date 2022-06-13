package br.com.razes.bytecode.security;

import br.com.razes.bytecode.service.users.UserService;
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
import org.springframework.web.bind.annotation.RequestMapping;


@Configuration
@EnableWebSecurity
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
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/users/**").permitAll()
		.antMatchers(HttpMethod.POST,"/api/auth").permitAll()
		.antMatchers(HttpMethod.GET,"/actuator/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/rate/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/trade/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/coin/**").permitAll()
		.anyRequest().authenticated()
		.and().cors()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AuthWithTokenFilter(tokenService, userService), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/**.html",
						"/v3/api-docs/**",
						"/webjars/**",
						"/configuration/**",
						"/swagger-resources/**",
						"/swagger-ui/**");
	}

}
