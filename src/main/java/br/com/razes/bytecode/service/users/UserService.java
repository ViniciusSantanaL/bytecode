package br.com.razes.bytecode.service.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.razes.bytecode.model.users.Profile;
import br.com.razes.bytecode.model.users.User;

public interface UserService {
	
	User saveUser(User user);
	
	Optional<User> getUserBy(Long userId);
	
	Page<User> getUsers(Pageable pageable);
	
	Page<User> getUsersByProfileName(String profileName, Pageable pageable);

	Optional<User> getUserBy(String username);
	
	Profile saveProfile(String profile);

	Optional<Profile> getProfileBy(String profileName);
	
	List<Profile> getProfiles();
	
	boolean addProfileForUser(String username, String nameProfile);
	
	boolean removeProfileForUser(String username, String nameProfile);

	boolean deleteById(Long id);

	
	
	
}
