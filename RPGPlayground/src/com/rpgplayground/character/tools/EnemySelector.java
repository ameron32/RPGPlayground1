package com.rpgplayground.character.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpgplayground.character.EnemyCharacter;
import com.rpgplayground.character.chclass.CharacterClassChoice;


public class EnemySelector {

	List<EnemyCharacter> allEnemies = new ArrayList<EnemyCharacter>();
	EnemyCharacter enemy = new EnemyCharacter();
	
	public EnemySelector() {
		createEnemies();
	}
	
	public EnemyCharacter getNewEnemy() {
		int sizeOfList = allEnemies.size();
		
		Random r = new Random();
		int myRandom = r.nextInt(sizeOfList);
		EnemyCharacter newRandomEnemy = allEnemies.get(myRandom);
		return newRandomEnemy;
	}
	
	private void createEnemies() {
		enemy = new EnemyCharacter("Goblin", 1, CharacterClassChoice.Barbarian, 18, 7, 4, 0, 3);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Tough Goblin", 2, CharacterClassChoice.Barbarian, 21, 10, 5, 0, 4);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Cowardly Goblin", 3, CharacterClassChoice.Barbarian, 14, 5, 3, 0, 2);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Goblin Warrior", 4, CharacterClassChoice.Fighter, 25, 14, 7, 0, 7);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Gargoyle", 5, CharacterClassChoice.Fighter, 44, 5, 12, 0, 9);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Stone Golem", 6, CharacterClassChoice.Fighter, 60, 9, 18, 2, 12);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
	}
	
	
	
}
