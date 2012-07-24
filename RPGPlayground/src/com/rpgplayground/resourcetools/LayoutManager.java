package com.rpgplayground.resourcetools;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rpgplayground.R;
import com.rpgplayground.character.EnemyCharacter;

public class LayoutManager extends Activity implements OnClickListener {

	Context context;
	View loadedView;
	ViewGroup parent;
	ImageButton attack, eProfile;
	TextView eHealth, eEnergy, eName, eClass;
	ProgressBar eHealthBar, eEnergyBar; 
	
	public LayoutManager(Context context, int parentViewGroupId) {
		this.context = context;
		changeViewGroup(parentViewGroupId);
		attack = (ImageButton) findViewById(R.id.ibAttack);
		eProfile = (ImageButton) findViewById(R.id.ibEProfile);
		eHealth = (TextView) findViewById(R.id.tvEHealth);
		eEnergy = (TextView) findViewById(R.id.tvEEnergy);
		eName = (TextView) findViewById(R.id.tvEName);
		eClass = (TextView) findViewById(R.id.tvEClass);
		eHealthBar = (ProgressBar) findViewById(R.id.pbEHealth);
		eEnergyBar = (ProgressBar) findViewById(R.id.pbEEnergy);
	}
	
	public void changeViewGroup(int parentViewGroupId) {
		parent = (ViewGroup) findViewById(parentViewGroupId);
	}
	
	public void loadParent(int layoutGroup) {
		loadedView = LayoutInflater.from(context).inflate(layoutGroup, parent, false);
	}
	
	public void updateDisplay(EnemyCharacter e) {
		eHealth.setText(e.getCurrentHealth() + " / " + e.getMaxHealth());
		eEnergy.setText(e.getCurrentEnergy() + " / " + e.getMaxEnergy());
		eName.setText(e.getName());
		eClass.setText(e.getCharacterClassChoice().toString());
		eProfile.setImageResource(((EnemyCharacter) e).getImageResource());
		if (e.getMaxHealth() <= 0) {
			eHealthBar.setMax(1);
			eHealthBar.setProgress(0);
		} else {
			eHealthBar.setMax(e.getMaxHealth());
			eHealthBar.setProgress(e.getCurrentHealth());
		}
		if (e.getMaxEnergy() <= 0) {
			eEnergyBar.setMax(1);
			eEnergyBar.setProgress(0);
		} else {
			eEnergyBar.setMax(e.getMaxEnergy());
			eEnergyBar.setProgress(e.getCurrentEnergy());
		}
	}
	
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "Working...", Toast.LENGTH_SHORT).show();
		switch (v.getId()) {
		
		
		
		}
		
	}
	
	
	
}
