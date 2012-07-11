package com.rpgplayground.character.inventory;

import java.util.ArrayList;
import java.util.List;

import com.rpgplayground.character.item.Item;

public class CharacterInventory {

	List<Item> myInventory = new ArrayList<Item>();
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (Item item : myInventory) {
			count += 1;
			sb.append(Integer.toString(count));
			sb.append(": ");
			sb.append(item.getName());
			sb.append(" / ");
			sb.append(Integer.toString(item.getId()));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void add(Item item) {
		myInventory.add(item);
	}
	
	public void addMore(List<Item> itemsToAdd) {
		for (Item item : itemsToAdd) {
			myInventory.add(item);
		}
	}
	
	public List<Item> getAllItemsInInventory() {
		return myInventory;
	}
	
	public Item getItemById(int Id) {
		Item itemFound = null;
		for (Item item : myInventory) {
			if (item.getId() == Id) {
				itemFound = item;
			}
		}
		return itemFound; 
	}
	
	public Item getItemByString(String name) {
		Item itemFound = null;
		for (Item item : myInventory) {
			if (item.getName() == name) {
				itemFound = item;
			}
		}
		return itemFound; 
	}
	
	public Item getItemByPosition(int position) {
		Item itemFromPosition = null;
		int numberOfInventoryItems = myInventory.size();
		int positionToReturn = position;
		
		if (position > numberOfInventoryItems - 1) {
			positionToReturn = numberOfInventoryItems - 1;
		} else if (position < 0) {
			positionToReturn = 0;
		}
		
		return itemFromPosition;
	}
	
	public void clearInventory(Boolean confirm) {
		if (confirm) {
			myInventory = new ArrayList<Item>();
		}
	}
}
