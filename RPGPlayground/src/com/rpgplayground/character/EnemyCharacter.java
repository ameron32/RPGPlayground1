package com.rpgplayground.character;

import com.rpgplayground.R;
import com.rpgplayground.character.chclass.CharacterClassChoice;



public class EnemyCharacter extends Character {

	int xpGain;
	int imageResource;

	public EnemyCharacter() {
		super();
		name = "None";
		currentHealth = maxHealth = currentEnergy = maxEnergy = baseDamage = 0;
		id = 0;
		xpGain = 1;
		imageResource = R.drawable.ic_launcher;
	}

	public EnemyCharacter(int setId) {
		super(setId);
		name = "None";
		currentHealth = maxHealth = currentEnergy = maxEnergy = baseDamage = 0;
		xpGain = 1;
		imageResource = R.drawable.ic_launcher;
	}

	/**
	 * String setName, int setId, int setMaxHealth,
	 * int setMaxEnergy, int setDamage, int setHealing, int setXPGain
	 */
	public EnemyCharacter(String setName, int setId, CharacterClassChoice setType, int setMaxHealth,
			int setMaxEnergy, int setDamage, int setHealing, int setXPGain, int imageResource) {
		super(setName, setId, setType, setMaxHealth, setMaxEnergy, setDamage, setHealing);
		xpGain = setXPGain;
		this.imageResource = imageResource;
	}

	public int getXPGain() {
		return xpGain;
	}
	
	public void setXPGain(int newXPGain) {
		xpGain = newXPGain;
	}
	
	public int getImageResource () {
		return imageResource;
	}
	
	public void setImageResource (int imageResource) {
		this.imageResource = imageResource;
	}
}
