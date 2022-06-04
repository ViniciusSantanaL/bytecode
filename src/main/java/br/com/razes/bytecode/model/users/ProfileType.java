package br.com.razes.bytecode.model.users;

public enum ProfileType {
	
	SUPER(1),
	ADMIN(2),
	TEST(3),
	CLIENT(4);
	
	private final int code;

	private ProfileType(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}

	public static ProfileType valueOf(int codeProfileType) {
		for(ProfileType value: ProfileType.values()){
			if(codeProfileType == value.getCode())
				return value;

		}
		throw new IllegalArgumentException("Invalid ProfileType code");
	}
//	public static ProfileType valueOf(String nameProfileType) {
//
//		for(ProfileType value: ProfileType.values()){
//			if(nameProfileType.equals(value.toString()))
//				return value;
//		throw new IllegalArgumentException("Invalid name ProfileType ");
//	}

}
