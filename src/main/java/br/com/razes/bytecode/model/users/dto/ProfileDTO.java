package br.com.razes.bytecode.model.users.dto;

import br.com.razes.bytecode.model.users.Profile;

public class ProfileDTO {

    private Long id;
    private String name;

    public ProfileDTO(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
