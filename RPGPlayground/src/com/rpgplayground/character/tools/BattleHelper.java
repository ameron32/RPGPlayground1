package com.rpgplayground.character.tools;

import java.util.ArrayList;
import java.util.List;

import com.rpgplayground.character.Character;
import com.rpgplayground.character.ability.Ability;

public class BattleHelper {

	int attackerTotalDamage;
	int defenderTotalResistance;
	int damageToDefender;
	int characterTotalHeal;

	Ability abilityUsed = null;
	int abilityTotalDamage;
	int abilityTotalHeal;

	public enum AbilityType {
		Attack, Heal
	}

	public BattleHelper() {

	}

	/**
	 * Very basic attack.
	 * 
	 * @param attacker
	 * @param defender
	 * @return
	 */
	// public int attack(Character attacker, Character defender) {
	// int damageToDefender = 0;
	//
	// if (isSuccessful(attacker, defender, 1)) {
	// damageToDefender = attacker.getTotalCalculatedDamage()
	// - defender.getTotalCalculatedResistance();
	// } else {
	// damageToDefender = 0;
	// }
	//
	// return damageToDefender;
	//
	// // first, we need the attackers total damage
	// // and the defender's total defense
	//
	// // 1. gather attacker stats and attacker item stats
	// // 2. extract needed damageValue information to determine total
	// // 3. run formula: stats + item stats = total
	// // 4. apply multiplicative bonuses next: total * bonus = finalTotal
	//
	// // 1. gather defender stats and defender item stats
	// // 2. extract needed armorValue information to determine total
	// // 3. run formula: stats + item stats = total
	// // 4. apply multiplicative bonuses next: total * bonus = finalTotal
	//
	// // next, we compare Attack vs. Defend numbers
	// // if less than 0, attack fails
	// // else, we reduce the successful attacks damage by mitigation %
	//
	// // 1. gather defender stats and defender item stats for % damage reduced
	// // 2. extract needed % damage reduction information to determine total
	// // 3. run formula: (Attack - Defend) * (1 - %dmgReduction) =
	// // damageToDefender
	// }

	/**
	 * Attack with manual amplifier (write as 95.55 for 95.55%)
	 * 
	 * @param attacker
	 * @param defender
	 * @param percent
	 * @return
	 */
	// public int attack(Character attacker, CharacthealToCasterer defender,
	// long percent) {
	// int damageToDefender = 0;
	//
	//
	//
	//
	// if (isSuccessful(attacker, defender, percent/100)) {
	// damageToDefender = Math.round(attacker.getTotalCalculatedDamage()
	// * (percent / 100))
	// - defender.getTotalCalculatedResistance();
	// } else {
	// damageToDefender = 0;
	// }
	//
	// return damageToDefender;
	// }

	public int useAbilityAttack(Character attacker, Character defender,
			int abilityId) {
		int damageToDefender = 0;
		List<Ability> allAbilities = attacker.getMyAbilities();

		for (Ability ability : allAbilities) {
			if (ability != null) {
				if (ability.getAbilityId() == abilityId) {
					abilityUsed = ability;
					break;
				}
			}
		}

		double damageMultiplier = 1;
		if (abilityUsed != null) {
			damageMultiplier = abilityUsed.getDamageMultiplier();
		}

		int abilityDamage = (int) Math.round(attacker
				.getTotalCalculatedDamage() * damageMultiplier);
		storeAbilityTotals(AbilityType.Attack, abilityDamage);
		if (isSuccessful(attacker, defender, damageMultiplier)) {
			abilityDamage = (int) Math.round(attacker
					.getTotalCalculatedDamage() * damageMultiplier);
			defenderTotalResistance = defender.getTotalCalculatedResistance();
			damageToDefender = abilityDamage - defenderTotalResistance;
		} else {
			damageToDefender = 0;
		}

		this.damageToDefender = damageToDefender;
		return damageToDefender;
	}

	public int useAbilityHeal(Character c, int abilityId) {
		int healToCaster = 0;

		for (Ability ability : c.getMyAbilities()) {
			if (ability.getAbilityId() == abilityId) {
				abilityUsed = ability;
			}
		}

		double healMultiplier = 1;
		if (abilityUsed != null) {
			healMultiplier = abilityUsed.getHealMultiplier();
		}

		healToCaster = (int) Math.round(c.getTotalCalculatedHealing()
				* healMultiplier);

		this.characterTotalHeal = healToCaster;
		storeAbilityTotals(AbilityType.Heal, healToCaster);
		return healToCaster;
	}

	private void storeAbilityTotals(AbilityType type, int abilityTotal) {
		switch (type) {
		case Attack:
			this.abilityTotalDamage = abilityTotal;
			break;
		case Heal:
			this.abilityTotalHeal = abilityTotal;
			break;
		}
	}

	public int getAbilityTotalDamage(int abilityId) {
		return abilityTotalDamage;
	}

	public int getAbilityTotalHeal(int abilityId) {
		return abilityTotalHeal;
	}

	public Ability getAbilityUsed() {
		return abilityUsed;
	}

	public int getAttackerTotalDamage() {
		return attackerTotalDamage;
	}

	public void setAttackerTotalDamage(int attackerTotalDamage) {
		this.attackerTotalDamage = attackerTotalDamage;
	}

	public int getDefenderTotalResistance() {
		return defenderTotalResistance;
	}

	public void setDefenderTotalResistance(int defenderTotalResistance) {
		this.defenderTotalResistance = defenderTotalResistance;
	}

	public int getDamageToDefender() {
		return damageToDefender;
	}

	public void setDamageToDefender(int damageToDefender) {
		this.damageToDefender = damageToDefender;
	}

	public int getCharacterTotalHeal() {
		return characterTotalHeal;
	}

	public void setCharacterTotalHeal(int characterTotalHeal) {
		this.characterTotalHeal = characterTotalHeal;
	}

	private boolean isSuccessful(Character attacker, Character defender,
			double multiplier) {
		boolean isSuccessful = false;
		attackerTotalDamage = attacker.getTotalCalculatedDamage();
		defenderTotalResistance = defender.getTotalCalculatedResistance();
		if (attacker.getTotalCalculatedDamage() * (multiplier) > defender
				.getTotalCalculatedResistance()) {
			return true;
		}
		return isSuccessful;
	}

}
