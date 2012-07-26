package com.rpgplayground.character.ability;

import java.util.ArrayList;
import java.util.List;

import com.rpgplayground.R;

public class AbilitySelector {

	List<Ability> allAbilities = new ArrayList<Ability>();

	public AbilitySelector() {
		createAbilities();
	}

	private void createAbilities() {
		allAbilities.add(new Ability("Primary Attack",
				"Attack with the weapon(s) in your hands.", 10101, R.drawable.attack, 1, 100, 0));
		allAbilities.add(new Ability("Whirlwind",
				"Attack all opponents in 1 yard for 75% damage.", 10201, R.drawable.ammo,1, 75,
				0, true, 3));
		
		allAbilities.add(new Ability("Run Away",
				"Run Away from Combat.", 11101, R.drawable.ic_launcher, 1, 100, 0));
		allAbilities.add(new Ability("Heal Target",
				"Heals this Target.", 12101, R.drawable.heal, 1, 100, 0));
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
