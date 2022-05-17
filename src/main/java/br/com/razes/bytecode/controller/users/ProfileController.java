package br.com.razes.bytecode.controller.users;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.EnumUtils;

import br.com.razes.bytecode.model.users.Profile;
import br.com.razes.bytecode.model.users.TypeProfile;
import br.com.razes.bytecode.service.users.UserService;

@RestController
@RequestMapping("/users-profile")
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getProfiles(@RequestParam(required = false) String profileName){
		
		if(profileName != null) {
			Optional<Profile> profile =  userService.getProfileBy(profileName);
			
			if(profile.isPresent())
				return ResponseEntity.ok(profile);
			
			return ResponseEntity.notFound().build();
		}else {
			
			List<Profile> profiles = userService.getProfiles();
			
			return ResponseEntity.ok(profiles);
		}
	}
	// This EndPoint just use for developers
	// Obs:Pay attention for user this endpoint, because is necessary update TypeProfille
	@PostMapping
	public ResponseEntity<?> register(@RequestParam String profileName){
		
		try {
			
			EnumUtils.findEnumInsensitiveCase(TypeProfile.class, profileName);
			
		} catch (Exception e) {
			
			userService.saveProfile(profileName);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/add")
	@Transactional
	public ResponseEntity<?> addProfileForUser(@RequestParam String nameProfile,@RequestParam String username){
		
		boolean successAddProfile = userService.addProfileForUser(username, nameProfile);
		
		if(successAddProfile)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}
	@PutMapping("/remove")
	@Transactional
	public ResponseEntity<?> removeProfileForUser(@RequestParam String nameProfile,@RequestParam String username){
		
		boolean successAddProfile = userService.removeProfileForUser(username, nameProfile);
		
		if(successAddProfile)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}
}
