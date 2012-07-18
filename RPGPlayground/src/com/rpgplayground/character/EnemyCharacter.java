package com.rpgplayground.character;

import com.rpgplayground.character.chclass.CharacterClassChoice;



public class EnemyCharacter extends Character {

	int xpGain;

	public EnemyCharacter() {
		super();
		name = "None";
		currentHealth = maxHealth = currentEnergy = maxEnergy = baseDamage = 0;
		id = 0;
		xpGain = 1;
	}

	public EnemyCharacter(int setId) {
		super(setId);
		name = "None";
		currentHealth = maxHealth = currentEnergy = maxEnergy = baseDamage = 0;
		xpGain = 1;
	}

	/**
	 * String setName, int setId, int setMaxHealth,
	 * int setMaxEnergy, int setDamage, int setHealing, int setXPGain
	 */
	public EnemyCharacter(String setName, int setId, CharacterClassChoice setType, int setMaxHealth,
			int setMaxEnergy, int setDamage, int setHealing, int setXPGain) {
		super(setName, setId, setType, setMaxHealth, setMaxEnergy, setDamage, setHealing);
		xpGain = setXPGain;
	}

	public int getXPGain() {
		return xpGain;
	}
	
	public void setXPGain(int newXPGain) {
		xpGain = newXPGain;
	}
}
