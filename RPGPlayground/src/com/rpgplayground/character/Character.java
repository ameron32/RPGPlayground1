package com.rpgplayground.character;

import java.util.ArrayList;
import java.util.List;

import com.rpgplayground.character.ability.Ability;
import com.rpgplayground.character.ability.AbilitySelector;
import com.rpgplayground.character.ability.CharacterAbilities;
import com.rpgplayground.character.chclass.CharacterClass;
import com.rpgplayground.character.chclass.CharacterClassChoice;
import com.rpgplayground.character.chclass.ClassChooser;
import com.rpgplayground.character.equipment.CharacterEquipment;
import com.rpgplayground.character.inventory.CharacterInventory;
import com.rpgplayground.character.item.Armor;
import com.rpgplayground.character.item.Item;
import com.rpgplayground.character.item.Weapon;

public class Character {

	String name;
	int id;
	int currentHealth;
	int maxHealth;
	int currentEnergy;
	int maxEnergy;
	int baseDamage;
	int totalCalculatedDamage;
	int baseResistance;
	int totalCalculatedResistance;
	int baseHealing;
	int totalCalculatedHealing;
	CharacterClass myClass = new CharacterClass();

	CharacterAbilities myAbilities = new CharacterAbilities();
	CharacterEquipment myEquipment = new CharacterEquipment();
	CharacterInventory myInventory = new CharacterInventory();

	/**
	 * constructors
	 */
	public Character() {
		name = "None";
		currentHealth = maxHealth = currentEnergy = maxEnergy = baseDamage = 0;
		id = 0;
		updateTotals();
	}

	public Character(int setId) {
		name = "None";
		currentHealth = maxHealth = currentEnergy = maxEnergy = baseDamage = 0;
		id = setId;
		updateTotals();
	}

	public Character(String setName, int setId, CharacterClassChoice setType, int setMaxHealth,
			int setMaxEnergy, int setDamage, int setHealing) {
		name = setName;
		id = setId;
		currentHealth = maxHealth = setMaxHealth;
		currentEnergy = maxEnergy = setMaxEnergy;
		baseDamage = setDamage;
		baseHealing = setHealing;
		
		ClassChooser cc = new ClassChooser();
		myClass = cc.getCharacterClass(setType);
		updateTotals();
	}

	/**
	 * Items getters and setters
	 */
	public void overwriteAllCurrentAbilities(List<Ability> newAbilityList) {
		myAbilities.clearAllAbilities();
		addAllAbilities(newAbilityList);
	}

	public void addAllAbilities(List<Ability> abilitiesToAdd) {
		for (Ability ability : abilitiesToAdd) {
			myAbilities.setNextAbility(ability);
		}
	}

	public List<Ability> getMyAbilities() {
		return myAbilities.getAllAbilities();
	}

	public void setNewAbility(int abilityId) {
		AbilitySelector as = new AbilitySelector();
		Ability ability = as.getAbility(abilityId);
		myAbilities.setNextAbility(ability);
	}

	public void overwriteAllCurrentInventory(List<Item> newItemList) {
		myInventory.clearInventory(true);
		addAllToInventory(newItemList);
	}

	public void addAllToInventory(List<Item> itemsToAdd) {
		myInventory.addMore(itemsToAdd);
		updateTotals();
	}

	public List<Item> getMyItems() {
		return myInventory.getAllItemsInInventory();
	}

	/**
	 * EquippedItems getters and setters
	 */
	public void overwriteAllCurrentEquippedItems(List<Item> newItemList) {
		// erase the existing equipped items
		myEquipment.clearEquipment(true);
		addToEquippedItems(newItemList);
	}

	public void addToEquippedItems(List<Item> itemsToAdd) {

		for (Item item : itemsToAdd) {
			if (item.getType() == "armor") {
				Armor armor = (Armor) item;
				myEquipment.setArmor(armor);
			}
			if (item.getType() == "weapon") {
				Weapon weapon = (Weapon) item;
				myEquipment.setWeapon(weapon);
			}
		}
		updateTotals();
	}

	public List<Item> getMyEquippedItems() {
		List<Item> myEquippedItems = new ArrayList<Item>();
		for (Item item : myEquipment.getAllArmors()) {
			myEquippedItems.add(item);
		}
		for (Item item : myEquipment.getAllWeapons()) {
			myEquippedItems.add(item);
		}
		return myEquippedItems;
	}

	/**
	 * Call this method to update all at once.
	 */
	private void updateTotals() {
		updateTotalCalculatedDamage();
		updateTotalCalculatedResistance();
	}

	/**
	 * These methods are called from updateTotals();
	 */
	private void updateTotalCalculatedDamage() {
		int damage = baseDamage;
		for (Weapon w : myEquipment.getAllWeapons()) {
			damage += w.getDamage();
			damage += w.getWeaponDamageValue();
		}
		for (Armor a : myEquipment.getAllArmors()) {
			damage += a.getDamage();
		}
		setTotalCalculatedDamage(damage);
	}

	private void updateTotalCalculatedResistance() {
		int resistance = baseResistance;
		for (Weapon w : myEquipment.getAllWeapons()) {
			resistance += w.getResistance();
		}
		for (Armor a : myEquipment.getAllArmors()) {
			resistance += a.getResistance();
			resistance += a.getArmorDefenseValue();
		}
		setTotalCalculatedResistance(resistance);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String charString = this.getClass().getSimpleName() + " [name=" + name
				+ ", id=" + id + ", currentHealth=" + currentHealth
				+ ", maxHealth=" + maxHealth + ", currentEnergy="
				+ currentEnergy + ", maxEnergy=" + maxEnergy + ", damage="
				+ baseDamage + "]";

		sb.append(charString);
		sb.append("\n");

		List<Item> myEquippedItems = getMyEquippedItems();
		List<Item> myItems = getMyItems();
		List<Ability> myAbilities = getMyAbilities();
		if (!myEquippedItems.isEmpty()) {
			for (Item item : myEquippedItems) {
				sb.append(item.toString());
				sb.append("\n");
			}
		} else {
			sb.append("No equipped items.");
			sb.append("\n");
		}
		if (!myItems.isEmpty()) {
			for (Item item : myItems) {
				sb.append(item.toString());
				sb.append("\n");
			}
		} else {
			sb.append("No items in inventory.");
			sb.append("\n");
		}
		if (!myAbilities.isEmpty()) {
			for (Ability ability : myAbilities) {
				if (ability != null) {
					sb.append(ability.toString());
					sb.append("\n");
				}
			}
		} else {
			sb.append("No abilities.");
			sb.append("\n");
		}
		return sb.toString();
	}

	public void takeDamage(int damage) {
		if (damage > 0) {
			setCurrentHealth(getCurrentHealth() - damage);
		}
	}

	public void receiveHeal(int heal) {
		if (heal > 0) {
			setCurrentHealth(getCurrentHealth() + heal);
		}
	}

	public void spendEnergy(int energyCost) {
		if (energyCost > 0) {
			setCurrentEnergy(getCurrentEnergy() - energyCost);
		}
	}

	public void restoreEnergy(int energyRestored) {
		if (energyRestored > 0) {
			setCurrentEnergy(getCurrentEnergy() + energyRestored);
		}
	}

	/**
	 * Base Stats
	 */
	/**
	 * Getters and Setters for fields
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int health) {
		if (health < 0) {
			currentHealth = 0;
		} else if (health > maxHealth) {
			currentHealth = maxHealth;
		} else {
			currentHealth = health;
		}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setStartingHealth(int health) {
		currentHealth = health;
		maxHealth = health;
	}

	public int getCurrentEnergy() {
		return currentEnergy;
	}

	public void setCurrentEnergy(int energy) {
		if (energy < 0) {
			currentEnergy = 0;
		} else if (energy > maxEnergy) {
			currentEnergy = maxEnergy;
		} else {
			currentEnergy = energy;
		}
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setStartingEnergy(int energy) {
		currentEnergy = energy;
		maxEnergy = energy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBaseDamage() {

		return baseDamage;
	}

	public void setBaseDamage(int damage) {
		this.baseDamage = damage;
	}

	public int getTotalCalculatedDamage() {
		return totalCalculatedDamage;
	}

	private void setTotalCalculatedDamage(int totalCalculatedDamage) {
		this.totalCalculatedDamage = totalCalculatedDamage;
	}

	public int getBaseResistance() {
		return baseResistance;
	}

	public void setBaseResistance(int baseResistance) {
		this.baseResistance = baseResistance;
	}

	public int getTotalCalculatedResistance() {
		return totalCalculatedResistance;
	}

	private void setTotalCalculatedResistance(int totalCalculatedResistance) {
		this.totalCalculatedResistance = totalCalculatedResistance;
	}

	public int getBaseHealing() {
		return baseHealing;
	}

	public void setBaseHealing(int healing) {
		this.baseHealing = healing;
	}

	public int getTotalCalculatedHealing() {
		return totalCalculatedHealing;
	}

	private void setTotalCalculatedHealing(int totalCalculatedHealing) {
		this.totalCalculatedHealing = totalCalculatedHealing;
	}
	
	public void setCharacterClassChoice(CharacterClassChoice myClassType) {
		myClass.setMyClassType(myClassType);
	}
	
	public CharacterClassChoice getCharacterClassChoice() {
		return myClass.getMyClassType();
	}
}
