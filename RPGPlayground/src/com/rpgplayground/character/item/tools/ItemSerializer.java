package com.rpgplayground.character.item.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.rpgplayground.character.item.Item;

public class ItemSerializer implements Serializable {

	public void WriteAllItemsToDisk(List<Item> allItemsToWrite, String path) {
		try {
			for (Item item : allItemsToWrite) {
				FileOutputStream fos = new FileOutputStream(path + String.valueOf(item.getId()) + ".dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(item);
				oos.close();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Item> ReadAllItemsFromDisk(String path) {
		List<Item> allItems = new ArrayList<Item>();;
		File directory = new File(path);
        File[] allFiles = directory.listFiles(); 
        for (File f : allFiles) { 
            Item newItem;
			try {
				FileInputStream fis = new FileInputStream(f.getAbsolutePath());
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				newItem = (Item)ois.readObject();
				ois.close();
				fis.close();
	            allItems.add(newItem);
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OptionalDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
        return allItems;
	}
}
