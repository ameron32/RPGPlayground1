package com.rpgplayground.character.item;

import java.io.Serializable;

import com.rpgplayground.character.equipment.ArmorSlot;

public class Armor extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3782545721645964384L;

	String armorType;
	ArmorSlot armorSlot;
	// armorDefenseValue determines "deflected off of armor" and damage
	// reduction amount
	int armorDefenseValue;
	// armorPercentMitigated determines amount damage taken is reduced
	// by in %
	long armorPercentMitigated;

	/**
	 * 
	 * @param name
	 * @param baseIdCode
	 * @param itemType
	 * @param itemDamageBoost
	 * @param itemResistanceBoost
	 * @param armorType
	 * @param armorSlot
	 * @param armorDefenseValue
	 */
	public Armor(String name, int baseIdCode, int goldValue, String itemType,
			int itemDamageBoost, int itemResistanceBoost, String armorType,
			ArmorSlot armorSlot, int armorDefenseValue) {
		super(name, baseIdCode, goldValue, itemType, itemDamageBoost, itemResistanceBoost);
		this.armorType = armorType;
		this.armorSlot = armorSlot;
		this.armorDefenseValue = armorDefenseValue;
		armorPercentMitigated = armorDefenseValue / 1000;
	}
	
	public Armor() {
		super();
		// TODO do I need to initialize the other values?
	}

	public void overrideArmor(String armorType, int armorDefenseValue) {
		this.armorType = armorType;
		this.armorDefenseValue = armorDefenseValue;
		armorPercentMitigated = armorDefenseValue / 1000;
	}

	@Override
	public String toString() {
		return super.toString() + " [armorType=" + armorType + ", armorSlot="
				+ armorSlot + ", armorDefenseValue=" + armorDefenseValue
				+ ", armorPercentMitigated=" + armorPercentMitigated + "]" + "\n";
	}

	/**
	 * Getters
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getArmorType() {
		return armorType;
	}

	public ArmorSlot getArmorSlot() {
		return armorSlot;
	}

	public int getArmorDefenseValue() {
		return armorDefenseValue;
	}

	public long getArmorPercentMitigated() {
		return armorPercentMitigated;
	}

}
