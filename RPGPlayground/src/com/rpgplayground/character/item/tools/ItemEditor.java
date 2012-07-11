package com.rpgplayground.character.item.tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpgplayground.character.item.Item;
import com.rpgplayground.resourcetools.CsvReader;
import com.rpgplayground.resourcetools.CsvWriter;


/**
 * 
 * @author klemeilleur
 *
 * CSV file should contain in the columns:
 * 1. Name
 * 2. ItemID (please make sure this is unique)
 * 3. Gold Value
 * 4. Type of Item
 * 5. Damage
 * 6. Resistance
 * There should be 1 unique item per row.
 *
 */

public class ItemEditor {

	public List<Item> ReadAllItems(String path) {
		List<Item> allItemsRead = new ArrayList<Item>();
		
		try {
			
			CsvReader items = new CsvReader(path + ".csv");
		
			items.readHeaders();

			while (items.readRecord())
			{
				String itemName = items.get("Name");
				int itemId = Integer.decode(items.get("ID"));
				int itemGold = Integer.decode(items.get("Gold"));
				String itemType = items.get("Type");
				int itemDamage = Integer.decode(items.get("Damage"));
				int itemResistance = Integer.decode(items.get("Resistance"));
				
				Item thisItem = new Item(itemName, itemId, itemGold, itemType, itemDamage, itemResistance);
				allItemsRead.add(thisItem);
			}
	
			items.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allItemsRead;
	}
	
	public void WriteAllItems(List<Item> allItemsToWrite, String path, Boolean replaceYN) {
		
		String outputFile = path;
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile + ".csv", true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Name");
				csvOutput.write("ID");
				csvOutput.write("Gold");
				csvOutput.write("Type");
				csvOutput.write("Damage");
				csvOutput.write("Resistance");
				csvOutput.endRecord();
			} else 	if (replaceYN) {
				Random r = new Random();
				csvOutput = new CsvWriter(new FileWriter(outputFile + r.nextInt(100) +".csv", true), ',');
				
				csvOutput.write("Name");
				csvOutput.write("ID");
				csvOutput.write("Gold");
				csvOutput.write("Type");
				csvOutput.write("Damage");
				csvOutput.write("Resistance");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			
			// write out a few records
			for (Item item: allItemsToWrite) {
				csvOutput.write(item.getName());
				csvOutput.write(String.valueOf(item.getId()));
				csvOutput.write(String.valueOf(item.getGoldValue()));
				csvOutput.write(item.getType());
				csvOutput.write(String.valueOf(item.getDamage()));
				csvOutput.write(String.valueOf(item.getResistance()));
				csvOutput.endRecord();
			}
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}