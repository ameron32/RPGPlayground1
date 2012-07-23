package com.rpgplayground.character.chclass;

public class CharacterClass {

	String name = "noclass";
	CharacterClassChoice myClassType = CharacterClassChoice.noClass; 
	String description = "none";
	int id;
	
	public CharacterClass() {
		this.id = 0;
	}
	
	public CharacterClass(int id, CharacterClassChoice type, String description) {
		if (description != null) {
			this.description = description;
		}
		this.id = id;
		if (type != null) {
			this.myClassType = type;
			this.name = type.toString();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CharacterClassChoice getMyClassType() {
		return myClassType;
	}

	public void setMyClassType(CharacterClassChoice myClassType) {
		this.myClassType = myClassType;
		this.name = myClassType.toString();
	}
	
	
	
}
