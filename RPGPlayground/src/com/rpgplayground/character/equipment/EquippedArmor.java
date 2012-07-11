package com.rpgplayground.character.equipment;

import com.rpgplayground.character.item.Armor;

public class EquippedArmor {

	Armor armor;
	ArmorSlot slot;
	
	public EquippedArmor(Armor armor, ArmorSlot slot) {
		this.armor = armor;
		this.slot = slot;
	}
	
	public void setArmor(Armor armor, ArmorSlot slot) {
		this.armor = armor;
		this.slot = slot;
	}
	
	public Armor getArmor() {
		return armor;
	}
	
	public ArmorSlot getSlot() {
		return slot;
	}
}
