package com.rpgplayground.character.item;

import java.io.Serializable;

import com.rpgplayground.character.equipment.WeaponSlot;

public class Weapon extends Item implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 7485612548896352458L;

	String weaponType;
	WeaponSlot weaponSlot;
	// weaponDamageValue determines "deflected off of armor" and damage
	// reduction amount
	int weaponDamageValue;

	// weaponChanceToHitPercent determines amount damage taken is reduced
	// by in %
	int weaponChanceToHitValue;
	long weaponChanceToHitPercent;
	
	/**
	 * 
	 * @param name
	 * @param baseIdCode
	 * @param itemType
	 * @param itemDamageBoost
	 * @param itemResistanceBoost
	 * @param weaponType
	 * @param weaponSlot
	 * @param weaponDamageValue
	 * @param weaponChanceToHitValue
	 */
	public Weapon(String name, int baseIdCode, int goldValue, String itemType,
			int itemDamageBoost, int itemResistanceBoost, String weaponType,
			WeaponSlot weaponSlot, int weaponDamageValue,
			int weaponChanceToHitValue) {
		super(name, baseIdCode, goldValue, itemType, itemDamageBoost, itemResistanceBoost);
		this.weaponType = weaponType;
		this.weaponSlot = weaponSlot;
		this.weaponDamageValue = weaponDamageValue;
		this.weaponChanceToHitValue = weaponChanceToHitValue;
		weaponChanceToHitPercent = weaponChanceToHitValue / 1000;
	}

	public void overrideWeapon(String weaponType, int weaponDamageValue,
			int weaponChanceToHitValue) {
		this.weaponType = weaponType;
		this.weaponDamageValue = weaponDamageValue;
		this.weaponChanceToHitValue = weaponChanceToHitValue;
		weaponChanceToHitPercent = weaponDamageValue / 1000;
	}

	public void setDamageValue(int DamageValue) {
		weaponDamageValue = DamageValue;
	}

	public void setChanceToHit(String weaponType, int weaponChanceToHitValue) {
		this.weaponType = weaponType;
		this.weaponChanceToHitValue = weaponChanceToHitValue;
		weaponChanceToHitPercent = weaponChanceToHitValue / 1000;
	}

	@Override
	public String toString() {
		return super.toString() + "[weaponType=" + weaponType + ", weaponSlot="
				+ weaponSlot + ", weaponDamageValue=" + weaponDamageValue
				+ ", weaponChanceToHitValue=" + weaponChanceToHitValue
				+ ", weaponChanceToHitPercent=" + weaponChanceToHitPercent
				+ "]" + "\n";
	}

	
	/**
	 * Getters
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public WeaponSlot getWeaponSlot() {
		return weaponSlot;
	}

	public int getWeaponDamageValue() {
		return weaponDamageValue;
	}

	public int getWeaponChanceToHitValue() {
		return weaponChanceToHitValue;
	}

	public long getWeaponChanceToHitPercent() {
		return weaponChanceToHitPercent;
	}

}