package com.rpgplayground.character.item;

import java.io.Serializable;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429923176139520625L;

	String name;
	int id;
	String itemType;
	int damageBoost;
	int resistanceBoost;
	int goldValue;

	public Item(String itemName, int baseIdCode, int goldValue, String itemType,
			int damageBoostAmount, int resistanceBoostAmount) {
		name = itemName;
		id = baseIdCode;
		this.itemType = itemType;
		damageBoost = damageBoostAmount;
		resistanceBoost = resistanceBoostAmount;
		this.goldValue = goldValue;
	}

	public Item() {
		name = "default empty item";
		id = 0;
		itemType = "none";
		damageBoost = 0;
		goldValue = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int baseId) {
		id = baseId;
	}

	public String getType() {
		return itemType;
	}

	public void setType(String type) {
		this.itemType = type;
	}

	public int getDamage() {
		return damageBoost;
	}

	public void setDamage(int damage) {
		this.damageBoost = damage;
	}

	public int getResistance() {
		return resistanceBoost;
	}

	public void setResistance(int resistanceBoost) {
		this.resistanceBoost = resistanceBoost;
	}
	
	public int getGoldValue() {
		return goldValue;
	}
	
	public void setGoldValue(int goldValue) {
		this.goldValue = goldValue;
	}

	@Override
	public String toString() {
		return "    " + itemType.toString() + " [name=" + name + ", id=" + id
				+ ", itemType=" + itemType + ", damageBoost=" + damageBoost
				+ ", resistanceBoost=" + resistanceBoost + "]" + "\n";
	}

}
