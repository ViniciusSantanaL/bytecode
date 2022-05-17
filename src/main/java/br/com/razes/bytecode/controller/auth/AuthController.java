package br.com.razes.bytecode.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.razes.bytecode.controller.auth.form.AuthForm;
import br.com.razes.bytecode.model.auth.dto.TokenDTO;
import br.com.razes.bytecode.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody @Valid AuthForm authForm) {
		UsernamePasswordAuthenticationToken userData = authForm.convert();
		try {
			//This line catch Exception case userData is incorrect
			Authentication authenticate = authManager.authenticate(userData);
			
			String token = tokenService.generateToken(authenticate);
			return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
			
		}catch(AuthenticationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}
