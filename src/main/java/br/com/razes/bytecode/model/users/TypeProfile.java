package br.com.razes.bytecode.model.users;

public enum TypeProfile {
	
	SUPER("SUPER"),
	ADMIN("ADMIN"),
	TEST("TEST"),
	CLIENT("CLIENT");
	
	public String name;
	TypeProfile(String nameProfile) {
		this.name = nameProfile;
	}
	
	public String getName() {
		return name;
	}

}
