package br.com.razes.bytecode.service.users.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import br.com.razes.bytecode.model.users.Profile;
import br.com.razes.bytecode.model.users.TypeProfile;
import br.com.razes.bytecode.model.users.User;
import br.com.razes.bytecode.repository.users.ProfileRepository;
import br.com.razes.bytecode.repository.users.UserRepository;
import br.com.razes.bytecode.service.users.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserBy(Long userId){
		return userRepository.findById(userId);
	}
	
	@Override
	public Optional<User> getUserBy(String username){
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
		return  user;
	}

	@Override
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	@Override
	public Page<User> getUsersByProfileName(String profileName, Pageable pageable) {
		Page<User> users =  userRepository.findByProfilesName(profileName,pageable);
		return users;
	}
	
	@Override
	public Profile saveProfile(String profileName) {
		Profile profile = new Profile(profileName);
		return profileRepository.save(profile);
	}
	
	@Override
	public Optional<Profile> getProfileBy(String profileName) {
		Optional<Profile> userProfile =  Optional.ofNullable(profileRepository.findByName(profileName));
		return  userProfile;
	}
	@Override
	public List<Profile> getProfiles() {
		List<Profile> profiles = profileRepository.findAll();
		return profiles;
	}

	@Override
	public boolean addProfileForUser(String username, String nameProfile) {
		
		try {
			EnumUtils.findEnumInsensitiveCase(TypeProfile.class, nameProfile);
		} catch (Exception e) {
			
			return false;
		}
		Optional<User> user = getUserBy(username);
		
		if(user.isEmpty()) {
			return false;
		}
			
		Optional<Profile> userProfile = getProfileBy(nameProfile);
		
		if(!(user.get().getProfiles().contains(userProfile.get()))) {
			user.get().getProfiles().add(userProfile.get());
			return true;
		}else {
			return false;
		}
					
	}
	@Override
	public boolean removeProfileForUser(String username, String nameProfile) {
		try {
			EnumUtils.findEnumInsensitiveCase(TypeProfile.class, nameProfile);
		} catch (Exception e) {
			
			return false;
		}
		Optional<User> user = getUserBy(username);
		
		if(user.isEmpty()) {
			return false;
		}
			
		Optional<Profile> userProfile = getProfileBy(nameProfile);
		
		if(user.get().getProfiles().contains(userProfile.get())) {
			user.get().getProfiles().remove(userProfile.get());
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		boolean successDelete;
		
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			successDelete = false;
			return successDelete;
		}

		successDelete = true;
		
		return successDelete;
	}

	

	
	
}
