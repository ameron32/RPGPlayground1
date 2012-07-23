package com.rpgplayground.character.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpgplayground.character.Character;
import com.rpgplayground.character.chclass.CharacterClassChoice;


public class PlayerSelector {

	List<Character> allPlayerTemplates = new ArrayList<Character>();
	Character player = new Character();
	
	public PlayerSelector() {
		createEnemies();
	}
	
	public Character getNewEnemy() {
		int sizeOfList = allPlayerTemplates.size();
		
		Random r = new Random();
		int myRandom = r.nextInt(sizeOfList);
		Character newRandomEnemy = allPlayerTemplates.get(myRandom);
		return newRandomEnemy;
	}
	
	private void createEnemies() {
		player = new Character("Goblin", 1, CharacterClassChoice.Barbarian, 18, 7, 4, 0);
		player.setNewAbility(10101);
		allPlayerTemplates.add(player);
		player = new Character("Tough Goblin", 2, CharacterClassChoice.Barbarian, 21, 10, 5, 0);
		player.setNewAbility(10101);
		allPlayerTemplates.add(player);
		player = new Character("Cowardly Goblin", 3, CharacterClassChoice.Barbarian, 14, 5, 3, 0);
		player.setNewAbility(10101);
		allPlayerTemplates.add(player);
		player = new Character("Goblin Warrior", 4, CharacterClassChoice.Fighter, 25, 14, 7, 0);
		player.setNewAbility(10101);
		allPlayerTemplates.add(player);
		player = new Character("Gargoyle", 5, CharacterClassChoice.Fighter, 44, 5, 12, 0);
		player.setNewAbility(10101);
		allPlayerTemplates.add(player);
		player = new Character("Stone Golem", 6, CharacterClassChoice.Fighter, 60, 9, 18, 2);
		player.setNewAbility(10101);
		allPlayerTemplates.add(player);
	}
	
	
	
}
