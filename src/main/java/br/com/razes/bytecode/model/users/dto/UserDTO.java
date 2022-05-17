package br.com.razes.bytecode.model.users.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.razes.bytecode.model.users.Profile;
import br.com.razes.bytecode.model.users.User;

public class UserDTO {
	
	private Long id;
	private String username;
	private String email;
	private List<String> profiles = new ArrayList<String>();
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.profiles.addAll(user.getProfiles().stream().map(Profile::getName).collect(Collectors.toList()));

	}
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}

	public static Page<UserDTO> converter(Page<User> users) {
		return users.map(UserDTO::new);
	}
	
}
