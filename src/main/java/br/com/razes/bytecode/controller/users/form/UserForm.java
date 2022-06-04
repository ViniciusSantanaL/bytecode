package br.com.razes.bytecode.controller.users.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.razes.bytecode.model.users.User;
import br.com.razes.bytecode.service.users.UserService;

public class UserForm {
	
	@NotNull @NotEmpty @Length(min = 5,max = 30)
	private String username;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String email;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String password;
	
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User converter(UserForm userForm) {
		return new User(username,email,new BCryptPasswordEncoder().encode(password));
	}
}
