package com.rpgplayground.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rpgplayground.R;
import com.rpgplayground.character.item.Item;
import com.rpgplayground.character.item.tools.ItemEditor;
import com.rpgplayground.character.item.tools.ItemSerializer;
import com.rpgplayground.resourcetools.AudioUtility;

public class Test extends Activity implements OnClickListener {

	Boolean disabled = true;

	ItemSerializer is = new ItemSerializer();
	ItemEditor ie = new ItemEditor();
	StringBuilder builder;
	List<Item> allItems = new ArrayList<Item>();
	AudioUtility au;
	Boolean playing = false;

	Button rCSV, rDAT, wCSV, wDAT, startRec, stopRec, playRec;

	TextView textView, ResultOfRecording;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		textView = (TextView) findViewById(R.id.tvText);
		ResultOfRecording = (TextView) findViewById(R.id.tvResultOfRecording);
		rCSV = (Button) findViewById(R.id.bRCSV);
		rDAT = (Button) findViewById(R.id.bRDAT);
		wCSV = (Button) findViewById(R.id.bWCSV);
		wDAT = (Button) findViewById(R.id.bWDAT);
		startRec = (Button) findViewById(R.id.bStartRec);
		stopRec = (Button) findViewById(R.id.bStopRec);
		playRec = (Button) findViewById(R.id.bPlayRec);

		rCSV.setOnClickListener(this);
		rDAT.setOnClickListener(this);
		wCSV.setOnClickListener(this);
		wDAT.setOnClickListener(this);
		startRec.setOnClickListener(this);
		stopRec.setOnClickListener(this);
		playRec.setOnClickListener(this);

		Item item1 = new Item("rusty iron sword", 144, 11, "sword", 7, 0);
		Item item2 = new Item("jeweled iron sword", 147, 156, "sword", 12, 0);
		Item item3 = new Item("iron mace", 167, 42, "mace", 16, 0);
		Item item4 = new Item("steel polearm", 210, 88, "polearm", 10, 0);
		allItems.add(item1);
		allItems.add(item2);
		allItems.add(item3);
		allItems.add(item4);
	}

	public void onClick(View v) {
		if (disabled) {
			switch (v.getId()) {
			case R.id.bRCSV:
				showDisabledToast();
				break;
			case R.id.bRDAT:
				showDisabledToast();
				break;
			case R.id.bWCSV:
				showDisabledToast();
				break;
			case R.id.bWDAT:
				showDisabledToast();
				break;
			case R.id.bStartRec:
				showDisabledToast();
				break;
			case R.id.bStopRec:
				showDisabledToast();
				break;
			case R.id.bPlayRec:
				showDisabledToast();
				break;
			}
		} else {

			String recordingPath = "/test.3gp";
			au = new AudioUtility(recordingPath);

			switch (v.getId()) {
			case R.id.bRCSV:
				// path should be "/sdcard/"and then the filename (without .csv)
				List<Item> allItemsCSV = ie
						.ReadAllItems("/sdcard/items/allitems");
				builder = new StringBuilder();
				for (int i = 0; i < allItemsCSV.size(); i++) {
					String itemName = allItemsCSV.get(i).getName();
					String itemId = Integer
							.toString(allItemsCSV.get(i).getId());
					String itemType = allItemsCSV.get(i).getName();
					String itemDamage = Integer.toString(allItemsCSV.get(i)
							.getDamage());
					builder.append(itemName);
					builder.append(" / ");
					builder.append(itemId);
					builder.append(" / ");
					builder.append(itemType);
					builder.append(" / ");
					builder.append(itemDamage);
					builder.append("\n");
				}
				textView.setText(builder.toString());
				break;
			case R.id.bRDAT:
				// path should be "/sdcard/" and then the directory ONLY (all
				// files
				// in the directory will be read)
				List<Item> allItemsDAT = is
						.ReadAllItemsFromDisk("/sdcard/items");
				builder = new StringBuilder();
				for (int i = 0; i < allItemsDAT.size(); i++) {
					String itemName = allItemsDAT.get(i).getName();
					String itemId = Integer
							.toString(allItemsDAT.get(i).getId());
					String itemType = allItemsDAT.get(i).getName();
					String itemDamage = Integer.toString(allItemsDAT.get(i)
							.getDamage());
					builder.append(itemName);
					builder.append(" / ");
					builder.append(itemId);
					builder.append(" / ");
					builder.append(itemType);
					builder.append(" / ");
					builder.append(itemDamage);
					builder.append("\n");
				}
				textView.setText(builder.toString());
				break;
			case R.id.bWCSV:
				// path should be "/sdcard/" and then the filename (without
				// .csv)
				ie.WriteAllItems(allItems, "/sdcard/items/allitems", true);
				textView.setText("CSV written to sdcard.");
				break;
			case R.id.bWDAT:
				// path should be "/sdcard/" and then the filename (without
				// .dat)
				is.WriteAllItemsToDisk(allItems, "/sdcard/items/allitems");
				textView.setText("DATs written to sdcard.");
				break;
			case R.id.bStartRec:
				try {
					au.startRecording();
					ResultOfRecording.setText("Recording...");
				} catch (IOException e) {

					e.printStackTrace();
					ResultOfRecording.setText("Failed to Begin Recording.");
				}
				break;
			case R.id.bStopRec:
				try {
					au.stopRecording();
					ResultOfRecording.setText("Recording Complete");
				} catch (IOException e) {
					e.printStackTrace();
					ResultOfRecording.setText("Failed to Stop Recording.");
				}
				break;
			case R.id.bPlayRec:
				playing = !playing;
				if (!playing) {
					try {
						au.playRecording();
						ResultOfRecording.setText("Playing the Recording");
					} catch (IOException e) {
						e.printStackTrace();
						ResultOfRecording.setText("Failed to Play Recording.");
					}
				} else {
					try {
						au.stopPlayingRecording();
						ResultOfRecording.setText("Stopped Playing.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						ResultOfRecording.setText("Failed to Stop Playing.");
					}
				}

				break;
			}
		}
	}

	private void showDisabledToast() {
		Toast.makeText(getApplicationContext(),
				"Button disabled due to potential errors",
				Toast.LENGTH_SHORT).show();
	}

}
