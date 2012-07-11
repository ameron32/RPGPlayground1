package com.rpgplayground.character.equipment;

import java.util.ArrayList;
import java.util.List;

import com.rpgplayground.character.item.Armor;
import com.rpgplayground.character.item.Item;
import com.rpgplayground.character.item.Weapon;

public class CharacterEquipment {

	EquippedArmor head;
	EquippedArmor chest;
	EquippedArmor arms;
	EquippedArmor legs;
	List<EquippedArmor> allEquippedArmors = new ArrayList<EquippedArmor>();

	EquippedWeapon mainHand;
	EquippedWeapon offHand;
	EquippedWeapon twoHand;
	EquippedWeapon back;
	List<EquippedWeapon> allEquippedWeapons = new ArrayList<EquippedWeapon>();
	
	List<Item> allEquippedItems = new ArrayList<Item>();

	public CharacterEquipment() {
		loadNulls();
	}
	
	private void loadNulls() {
		head = null;
		chest = null;
		arms = null;
		legs = null;
		mainHand = null;
		offHand = null;
		twoHand = null;
		back = null;
		allEquippedArmors = new ArrayList<EquippedArmor>();
		allEquippedWeapons = new ArrayList<EquippedWeapon>();
		allEquippedItems = new ArrayList<Item>();
	}

	public CharacterEquipment(Armor head, Armor chest, Armor arms, Armor legs,
			Weapon mainHand, Weapon offHand, Weapon twoHand, Weapon back) {
		if (head != null) setArmor(head);
		if (chest != null) setArmor(chest);
		if (arms != null) setArmor(arms);
		if (legs != null) setArmor(legs);
		if (mainHand != null) setWeapon(mainHand);
		if (offHand != null) setWeapon(offHand);
		if (twoHand != null) setWeapon(twoHand);
		if (back != null) setWeapon(back);
	}
	
	public void setArmors(List<Armor> armors){
		for (Armor a : armors) {
			if (a != null) setArmor(a);
		}
	}
	
	public void setWeapons(List<Weapon> weapons){
		for (Weapon w : weapons) {
			if (w != null) setWeapon(w);
		}
	}
	
	public void setArmor(Armor armor) {
		ArmorSlot slot = armor.getArmorSlot();
		EquippedArmor ea = new EquippedArmor(armor, slot);
		if (slot == ArmorSlot.Head) {
			head = new EquippedArmor(armor, slot);
		} else if (slot == ArmorSlot.Chest) {
			chest = new EquippedArmor(armor, slot);
		} else if (slot == ArmorSlot.Arms) {
			arms = new EquippedArmor(armor, slot);
		} else if (slot == ArmorSlot.Legs) {
			legs = new EquippedArmor(armor, slot);
		}
		allEquippedArmors.add(ea);
		allEquippedItems.add(ea.getArmor());
	}

	public void setWeapon(Weapon weapon) {
		WeaponSlot slot = weapon.getWeaponSlot();
		EquippedWeapon ew = new EquippedWeapon(weapon, slot);
		if (slot == WeaponSlot.MainHand) {
			mainHand = new EquippedWeapon(weapon, slot);
			twoHand = null;
		} else if (slot == WeaponSlot.OffHand) {
			// TODO or set to MainHand? need a prompt of some sort
			offHand = new EquippedWeapon(weapon, slot);
			twoHand = null;
		} else if (slot == WeaponSlot.TwoHand) {
			twoHand = new EquippedWeapon(weapon, slot);
			mainHand = null;
			offHand = null;
		} else if (slot == WeaponSlot.Back) {
			back = new EquippedWeapon(weapon, slot);
		}
		allEquippedWeapons.add(ew);
		allEquippedItems.add(ew.getWeapon());
	}
	
	public Armor getArmor(ArmorSlot slot) {
		Armor armor = null;
		for (EquippedArmor ea : allEquippedArmors) {
			if (ea.getArmor().getArmorSlot() == slot){
				armor = ea.getArmor();
			}
		}
		return armor;
	}
	
	public List<Armor> getAllArmors() {
		List<Armor> armors = new ArrayList<Armor>();
		for (EquippedArmor a : allEquippedArmors) {
			armors.add(a.getArmor());
		}
		return armors;
	}
	
	public Weapon getWeapon(WeaponSlot slot) {
		Weapon weapon = null;
		for (EquippedWeapon ew : allEquippedWeapons) {
			if (ew.getWeapon().getWeaponSlot() == slot) {
				weapon = ew.getWeapon();
			}
		}
		return weapon;
	}
	
	public List<Weapon> getAllWeapons() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		for (EquippedWeapon w : allEquippedWeapons) {
			weapons.add(w.getWeapon());
		}
		return weapons;
	}
	
	public void clearEquipment(Boolean confirm){
		if (confirm) {
			loadNulls();
		}
	}
}
