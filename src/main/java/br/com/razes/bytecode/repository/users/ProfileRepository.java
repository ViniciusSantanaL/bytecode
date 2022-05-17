package br.com.razes.bytecode.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.razes.bytecode.model.users.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

	Profile findByName(String nameProfile);

}
