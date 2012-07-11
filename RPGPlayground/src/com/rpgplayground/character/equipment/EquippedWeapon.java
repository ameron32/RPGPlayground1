package com.rpgplayground.character.equipment;

import com.rpgplayground.character.item.Weapon;

public class EquippedWeapon {

	Weapon weapon;
	WeaponSlot slot;
	
	public EquippedWeapon(Weapon weapon, WeaponSlot slot) {
		this.weapon = weapon;
		this.slot = slot;
	}
	
	public void setWeapon(Weapon weapon, WeaponSlot slot) {
		this.weapon = weapon;
		this.slot = slot;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public WeaponSlot getSlot() {
		return slot;
	}
}
