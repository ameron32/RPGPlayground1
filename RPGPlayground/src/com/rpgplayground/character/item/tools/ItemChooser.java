package com.rpgplayground.character.item.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpgplayground.character.equipment.ArmorSlot;
import com.rpgplayground.character.equipment.WeaponSlot;
import com.rpgplayground.character.item.Armor;
import com.rpgplayground.character.item.Item;
import com.rpgplayground.character.item.Weapon;

public class ItemChooser {

	List<Item> allItems = new ArrayList<Item>();

	List<Weapon> allWeapons = new ArrayList<Weapon>();
	List<Armor> allArmor = new ArrayList<Armor>();
	List<Item> allOtherItems = new ArrayList<Item>();

	Random r = new Random();

	public enum ChooserOption {
		random, perSlot
	}

	public ItemChooser(ChooserOption option) {
		createItems();

		for (Item item : allWeapons) {
			allItems.add(item);
		}
		for (Item item : allArmor) {
			allItems.add(item);
		}
		for (Item item : allOtherItems) {
			allItems.add(item);
		}

	}

	public Item getNewItem(int Id) {
		Item itemToReturn = new Item();
		for (Item item : allItems) {
			if (Id == item.getId()) {
				itemToReturn = item;
			}
		}

		return itemToReturn;
	}

	public Weapon getNewRandomItem(WeaponSlot slot) {
		Weapon weaponToReturn;
		List<Weapon> allWeaponsForThatSlot = new ArrayList<Weapon>();
		for (Weapon w : allWeapons) {
			if (w.getWeaponSlot() == slot) {
				allWeaponsForThatSlot.add(w);
			}
		}

		int myRandom = r.nextInt(allWeaponsForThatSlot.size());
		weaponToReturn = allWeaponsForThatSlot.get(myRandom);
		return weaponToReturn;
	}

	public Armor getNewRandomItem(ArmorSlot slot) {
		Armor armorToReturn;
		List<Armor> allArmorForThatSlot = new ArrayList<Armor>();
		for (Armor a : allArmor) {
			if (a.getArmorSlot() == slot) {
				allArmorForThatSlot.add(a);
			}
		}

		int myRandom = r.nextInt(allArmorForThatSlot.size());
		// TODO should I (Item) cast this?
		armorToReturn = allArmorForThatSlot.get(myRandom);
		return armorToReturn;
	}

	public Item getNewRandomItem() {
		int sizeOfList = allItems.size();

		Random r = new Random();
		int myRandom = r.nextInt(sizeOfList);
		Item newRandomItem = allItems.get(myRandom);
		return newRandomItem;
	}

	public Item getNewRandomWeapon() {
		int sizeOfList = allWeapons.size();

		Random r = new Random();
		int myRandom = r.nextInt(sizeOfList);
		Item newRandomItem = allWeapons.get(myRandom);
		return newRandomItem;
	}

	public List<Item> getNewRandomWeapons() {
		List<Item> weaponsToReturn = new ArrayList<Item>();
		Item weapon = getNewRandomWeapon();
		// add the weapon
		weaponsToReturn.add(weapon);
		// check to see if this weapon was a 1hand weapon. if so, find an
		// opposite-hand weapon and return it too
		if (((Weapon) weapon).getWeaponSlot() == WeaponSlot.MainHand) {
			Item offHandWeapon = getNewRandomItem(WeaponSlot.OffHand);
			weaponsToReturn.add(offHandWeapon);
		} else if (((Weapon) weapon).getWeaponSlot() == WeaponSlot.OffHand) {
			Item mainHandWeapon = getNewRandomItem(WeaponSlot.MainHand);
			weaponsToReturn.add(mainHandWeapon);
		}

		return weaponsToReturn;

	}

	private void createItems() {

		allWeapons.add(new Weapon("Steel Longsword", 101, 10, "weapon", 0, 0,
				"sword", WeaponSlot.MainHand, 4, 100));
		allWeapons.add(new Weapon("Rusty Dagger", 102, 2, "weapon", 0, 0,
				"dagger", WeaponSlot.OffHand, 2, 100));
		allWeapons.add(new Weapon("Enchanted Axe", 103, 7400, "weapon", 2, 3,
				"axe", WeaponSlot.TwoHand, 7, 100));
		allWeapons.add(new Weapon("Saber Claw", 104, 600, "weapon", 2, 2,
				"dagger", WeaponSlot.OffHand, 2, 100));

		allArmor.add(new Armor("Fur Hat", 206, 7, "armor", 0, 0, "clothes",
				ArmorSlot.Head, 1));
		allArmor.add(new Armor("Chainmail Coif", 203, 140, "armor", 0, 0,
				"helm", ArmorSlot.Head, 3));
		allArmor.add(new Armor("Cloth Robe", 202, 16, "armor", 0, 0, "robe",
				ArmorSlot.Chest, 1));
		allArmor.add(new Armor("King's Plate Cuirass", 204, 42500, "armor", 0,
				2, "cuirass", ArmorSlot.Chest, 8));
		allArmor.add(new Armor("Leather Gloves", 207, 24, "armor", 0, 0,
				"gloves", ArmorSlot.Arms, 1));
		allArmor.add(new Armor("Flame Atronach Bracers", 205, 2100, "armor", 1,
				1, "gloves", ArmorSlot.Arms, 2));
		allArmor.add(new Armor("Slippers", 208, 17, "armor", 0, 0, "clothes",
				ArmorSlot.Legs, 0));
		allArmor.add(new Armor("Boots of Speed", 201, 450, "armor", 0, 0,
				"boots", ArmorSlot.Legs, 2));

		allOtherItems.add(new Item("Amulet of Doom", 1001, 1200, "necklace", 3,
				2));
		allOtherItems.add(new Item("Torn Boot Laces", 1002, 1, "misc", 0, 0));

	}
}
