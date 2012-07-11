package com.rpgplayground.character.ability;

import java.util.ArrayList;
import java.util.List;

public class CharacterAbilities {

	Ability primaryAttack = null;

	Ability first = null;
	Ability second = null;
	Ability third = null;
	Ability fourth = null;
	Ability fifth = null;
	Ability sixth = null;

	public CharacterAbilities() {
		
	}

	/**
	 * Returns the Ability at the given position. 0 is primaryAttack, 1-6 are
	 * first through sixth
	 * @param position
	 * @return
	 */
	public Ability getAbility(int position) {
		List<Ability> allAbilities = getAllAbilities();
		Ability returnedAbility = allAbilities.get(position);
		return returnedAbility;
	}

	public List<Ability> getAllAbilities() {
		List<Ability> allAbilities = new ArrayList<Ability>();
		allAbilities.add(primaryAttack);
		allAbilities.add(first);
		allAbilities.add(second);
		allAbilities.add(third);
		allAbilities.add(fourth);
		allAbilities.add(fifth);
		allAbilities.add(sixth);
		return allAbilities;
	}

	public void setAbility(int position, Ability ability) {
		switch (position) {
		case 0:
			primaryAttack = ability;
			break;
		case 1:
			first = ability;
			break;
		case 2:
			second = ability;
			break;
		case 3:
			third = ability;
			break;
		case 4:
			fourth = ability;
			break;
		case 5:
			fifth = ability;
			break;
		case 6:
			sixth = ability;
			break;
		}
	}
	
	public void setNextAbility(Ability ability) {
		List<Ability> allAbilities = getAllAbilities();
		int count = 0;
		for (Ability a : allAbilities) {
			if (a == null) {
				setAbility(count, ability);
				break;
			}
			count++;
		}
		// if none are null, it never gets added
	}

	public void setAllAbilities(List<Ability> sevenAbilities) {
		for (int x = 0; x < sevenAbilities.size(); x++) {
			setAbility(x, sevenAbilities.get(x));
		}
	}

	public void clearAllAbilities() {
		for (int x = 0; x < 7; x++) {
			setAbility(x, null);
		}
	}
}
