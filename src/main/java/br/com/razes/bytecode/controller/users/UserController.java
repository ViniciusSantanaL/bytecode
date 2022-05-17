package br.com.razes.bytecode.controller.users;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.razes.bytecode.controller.users.form.UserForm;
import br.com.razes.bytecode.controller.users.form.UserUpdateForm;
import br.com.razes.bytecode.model.users.User;
import br.com.razes.bytecode.model.users.dto.UserDTO;
import br.com.razes.bytecode.service.users.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {
		Optional<User> userExist = userService.getUserBy(userForm.getUserName());
		
		if(userExist.isPresent())
			return ResponseEntity.badRequest().build();
		
		User newUser = userForm.converter(userForm,userService);
		userService.saveUser(newUser);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UserDTO(newUser));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserBy(@PathVariable Long id){
		
		Optional<User> user = userService.getUserBy(id);
		
		if(user.isPresent())
			return ResponseEntity.ok(new UserDTO(user.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserUpdateForm userUpdateForm){
		Optional<User> user = userUpdateForm.updateUserById(id,userService);
		
		if(user.isPresent())
			return ResponseEntity.ok(new UserDTO(user.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		
		boolean successDelete = userService.deleteById(id);
		
		if(successDelete)
			return ResponseEntity.ok().build();
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public Page<UserDTO> getUsersByProfileNameOrAll(@RequestParam(required = false) String profileName,
			@PageableDefault(sort = "username", direction = Direction.ASC, page = 0, size = 10) Pageable pageable){
		
		if(profileName != null) {
			Page<User> users = userService.getUsersByProfileName(profileName, pageable);
			return UserDTO.converter(users);
		}else {
			Page<User> users = userService.getUsers(pageable);
			return UserDTO.converter(users);
		}
	}
	
}
