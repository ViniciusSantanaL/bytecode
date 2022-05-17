package br.com.razes.bytecode.controller.users.form;

import java.util.Optional;

import org.hibernate.validator.constraints.Length;

import br.com.razes.bytecode.model.users.User;
import br.com.razes.bytecode.service.users.UserService;

public class UserUpdateForm {
	
	@Length(min = 5,max = 30)
	private String userName;
	@Length(min = 5)
	private String email;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<User> updateUserById(Long idUser,UserService userService) {
		
		Optional<User> user = userService.getUserBy(idUser);
		
		user.ifPresent(e -> {
			if(userName != null && !userName.isEmpty() && !userName.isBlank())
				e.setUserName(this.userName);
			if(email != null && !email.isEmpty() && !email.isBlank())
				e.setEmail(this.email);
		});
		
		return user;
	}

}
