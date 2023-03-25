package classes;

public enum Gender {
Male,
Female,
Other;
	
	public static Gender toGender(String v) {
		Gender gender=null;
		if(v.equals("Male")) {
			gender=Gender.Male;
		}
		else if(v.equals("Female")) {
			gender=Gender.Female;
		}
		else {
			gender=Gender.Other;
		}
		return gender;
	}
}
