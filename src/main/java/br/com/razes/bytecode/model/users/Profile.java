package br.com.razes.bytecode.model.users;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Profile implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	//Mapped on Class TypeProfile
	private String name;
	
	public Profile() {
		
	}
	public Profile(ProfileType profileName) {
		setId(profileName);
		setName(profileName);
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(ProfileType profileType) {
		if(profileType != null)
			this.id = (long) profileType.getCode();
	}

	public String getName() {
		return ProfileType.valueOf(name).toString();
	}

	public void setName(ProfileType profileType) {
		if(profileType != null)
			this.name = profileType.toString();

	}
	
}
