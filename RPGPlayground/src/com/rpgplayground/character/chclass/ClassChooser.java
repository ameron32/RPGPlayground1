package com.rpgplayground.character.chclass;

import java.util.ArrayList;
import java.util.List;

public class ClassChooser {

	List<CharacterClass> allClasses = new ArrayList<CharacterClass>();
	CharacterClass nullClass = new CharacterClass(0, CharacterClassChoice.noClass, "noClass");
	
	public ClassChooser () {
		createClasses();
	}

	private void createClasses() {
		allClasses.add(new CharacterClass(100103, CharacterClassChoice.Fighter, "desc. pending"));
		allClasses.add(new CharacterClass(100102, CharacterClassChoice.Barbarian, "desc. pending"));
		allClasses.add(new CharacterClass(100101, CharacterClassChoice.Commoner, "desc. pending"));
	}
	
	public CharacterClass getCharacterClass(CharacterClassChoice type) {
		CharacterClass foundClass = nullClass;
		
		for (CharacterClass c : allClasses) {
			if (c != null) {
				if (c.getMyClassType() == type) {
					foundClass = c;
				}
			}
		}
		
		return foundClass;
	}

}
