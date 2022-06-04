package br.com.razes.bytecode.service.users;

import br.com.razes.bytecode.model.users.Profile;
import br.com.razes.bytecode.model.users.ProfileType;
import br.com.razes.bytecode.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
	
	User saveUser(User user);
	
	Optional<User> getUserBy(Long userId);
	
	Page<User> getUsers(Pageable pageable);
	
	Page<User> getUsersByProfileName(String profileName, Pageable pageable);

	Optional<User> getUserBy(String username);
	
	Profile saveProfile(ProfileType profile);

	Optional<Profile> getProfileBy(String profileName);
	
	List<Profile> getProfiles();
	
	boolean addProfileForUser(String username, ProfileType profileType);
	
	boolean removeProfileForUser(String username, ProfileType profileType);

	boolean deleteById(Long id);

	
	
	
}
