package com.rpgplayground.character.ability;

public class Ability {

	String abilityName;
	String description;
	int abilityId;
	int resourceId;
	
	int range;
	double damageMultiplier;
	double healMultiplier;
	int numberOfTargetsPerAttack;
	boolean unlimitedTargets = false;
	int numberOfAttacksPerTurn;
	
	int energyCost;
	int healthCost;

	/**
	 * Single Target, single attack ability.
	 * @param range
	 * @param damagePercent
	 */
	public Ability(String name, String description, int id, int resourceId, int range, double damagePercent, int energyCost) {
		setCoreAbility(name, description, id, resourceId, range, damagePercent, energyCost);

		unlimitedTargets = false;
		numberOfTargetsPerAttack = 1;
	}

	/**
	 * Multi-target, single attack ability. Unlimited overrides numberOfTargets.
	 * @param range
	 * @param damagePercent
	 * @param numberOfTargetsPerAttack
	 * @param unlimitedTargets
	 */
	public Ability(String name, String description, int id, int resourceId, int range, double damagePercent, int numberOfTargetsPerAttack,
			boolean unlimitedTargets, int energyCost) {
		setCoreAbility(name, description, id, resourceId, range, damagePercent, energyCost);

		this.unlimitedTargets = unlimitedTargets;
		if (unlimitedTargets) {
			this.numberOfTargetsPerAttack = 99;
		} else {
			this.numberOfTargetsPerAttack = numberOfTargetsPerAttack;
		}
	}
	
	private void setCoreAbility(String name, String description, int id, int resourceId, int range, double damagePercent, int energyCost) {
		this.abilityName = name;
		this.description = description;
		this.abilityId = id;
		this.resourceId = resourceId;
		if (range > 0) {
			this.range = range;
		} else {
			range = 1;
		}
		damageMultiplier = damagePercent / 100;
		numberOfAttacksPerTurn = 1;
		this.energyCost = energyCost;
	}
	
	
	
	@Override
	public String toString() {
		return "   " + this.getClass().getSimpleName() +" [abilityName=" + abilityName + ", description="
				+ description + ", abilityId=" + abilityId + ", range=" + range
				+ ", damageMultiplier=" + damageMultiplier
				+ ", healMultiplier=" + healMultiplier
				+ ", numberOfTargetsPerAttack=" + numberOfTargetsPerAttack
				+ ", unlimitedTargets=" + unlimitedTargets
				+ ", numberOfAttacksPerTurn=" + numberOfAttacksPerTurn
				+ ", energyCost=" + energyCost + ", healthCost=" + healthCost
				+ "]";
	}

	public String getAbilityName() {
		return abilityName;
	}

	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}

	public int getAbilityId() {
		return abilityId;
	}

	public void setAbilityId(int abilityId) {
		this.abilityId = abilityId;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public double getDamageMultiplier() {
		return damageMultiplier;
	}

	public void setDamageMultiplier(double damageMultiplier) {
		this.damageMultiplier = damageMultiplier;
	}
	
	public double getHealMultiplier() {
		return healMultiplier;
	}

	public void setHealMultiplier(double healMultiplier) {
		this.healMultiplier = healMultiplier;
	}

	public int getNumberOfTargetsPerAttack() {
		return numberOfTargetsPerAttack;
	}

	public void setNumberOfTargetsPerAttack(int numberOfTargetsPerAttack) {
		this.numberOfTargetsPerAttack = numberOfTargetsPerAttack;
	}

	public boolean isUnlimitedTargets() {
		return unlimitedTargets;
	}

	public void setUnlimitedTargets(boolean unlimitedTargets) {
		this.unlimitedTargets = unlimitedTargets;
	}

	public int getNumberOfAttacksPerTurn() {
		return numberOfAttacksPerTurn;
	}

	public void setNumberOfAttacksPerTurn(int numberOfAttacksPerTurn) {
		this.numberOfAttacksPerTurn = numberOfAttacksPerTurn;
	}
	
	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public int getHealthCost() {
		return healthCost;
	}

	public void setHealthCost(int healthCost) {
		this.healthCost = healthCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}


}
