package com.rpgplayground.character.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpgplayground.R;
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
		enemy = new EnemyCharacter("Scout", 1, CharacterClassChoice.Barbarian, 13, 21, 4, 0, 3, R.drawable.scout);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Dwarven Smith", 2, CharacterClassChoice.Barbarian, 28, 14, 7, 0, 4, R.drawable.dwarvensmith);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Bandit Leader", 3, CharacterClassChoice.Fighter, 23, 10, 6, 0, 2, R.drawable.banditleader);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("DarkElf Amazon", 4, CharacterClassChoice.Fighter, 14, 37, 11, 0, 7, R.drawable.darkelfamazon);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Fire Elemental", 5, CharacterClassChoice.Fighter, 20, 20, 12, 0, 9, R.drawable.firemonster);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
		enemy = new EnemyCharacter("Stone Golem", 6, CharacterClassChoice.Fighter, 60, 4, 18, 2, 12, R.drawable.stonegolem);
		enemy.setNewAbility(10101);
		allEnemies.add(enemy);
	}
	
	
	
}
