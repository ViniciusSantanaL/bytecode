package br.com.razes.bytecode.controller.users;

import br.com.razes.bytecode.model.users.Profile;
import br.com.razes.bytecode.model.users.ProfileType;
import br.com.razes.bytecode.model.users.dto.ProfileDTO;
import br.com.razes.bytecode.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users-profile")
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
	public ResponseEntity<?> register(@RequestParam String profileName, UriComponentsBuilder uriBuilder){

		try {
			ProfileType profile = ProfileType.valueOf(profileName);
			Profile newProfile = userService.saveProfile(profile);
			URI uri = uriBuilder.path("/api/users-profile/{id}").buildAndExpand(newProfile.getId()).toUri();

			return ResponseEntity.created(uri).body(new ProfileDTO(newProfile));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping("/add")
	@Transactional
	public ResponseEntity<?> addProfileForUser(@RequestParam String profileName,@RequestParam String username){

		ProfileType profile = ProfileType.valueOf(profileName);
		boolean successAddProfile = userService.addProfileForUser(username, profile);

		if(successAddProfile)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}
	@PutMapping("/remove")
	@Transactional
	public ResponseEntity<?> removeProfileForUser(@RequestParam String profileName,@RequestParam String username){

		ProfileType profile = ProfileType.valueOf(profileName);
		boolean successRemoveProfile = userService.removeProfileForUser(username, profile);
		
		if(successRemoveProfile)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}
}
