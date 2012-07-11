package com.rpgplayground.character.ability;

import java.util.ArrayList;
import java.util.List;

public class AbilitySelector {

	List<Ability> allAbilities = new ArrayList<Ability>();

	public AbilitySelector() {
		createAbilities();
	}

	private void createAbilities() {
		allAbilities.add(new Ability("Primary Attack",
				"Attack with the weapon(s) in your hands.", 10101, 1, 100, 0));
		allAbilities.add(new Ability("Whirlwind",
				"Attack all opponents in 1 yard for 75% damage.", 10201, 1, 75,
				0, true, 3));
	}
	
	public Ability getAbility(int abilityId) {
		Ability returned = null;
		for (Ability a : allAbilities) {
			if (a.getAbilityId() == abilityId) {
				returned = a;
			}
		}
		return returned;
	}

}
