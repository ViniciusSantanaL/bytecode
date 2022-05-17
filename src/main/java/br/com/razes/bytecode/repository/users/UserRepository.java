package br.com.razes.bytecode.repository.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.razes.bytecode.model.users.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

	Page<User> findByProfilesName(String profileName, Pageable pageable);
}
