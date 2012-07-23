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

	
	
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "Working...", Toast.LENGTH_SHORT).show();
		switch (v.getId()) {
		
		
		
		}
		
	}
	
}
