package com.rpgplayground.activity;

import com.rpgplayground.R;
import com.rpgplayground.R.id;
import com.rpgplayground.R.menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = { "Battle", "Test", "AudioRecordTest", "DialogTest", "More" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//fullscreen
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this, 
				android.R.layout.simple_list_item_1, classes));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String local = classes[position];
		try {
			Class ourClass = Class.forName("com.rpgplayground.activity." + local);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("MenuClassCatch", "Class not found exception in onListItemClick");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cool_menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.aboutUs:
			i = new Intent("com.rpgplayground.ABOUT");
			startActivity(i);
			
			break;
		case R.id.preferences:	
			i = new Intent("com.rpgplayground.PREFS");
			startActivity(i);
			
			break;
		case R.id.exit:	
			finish();
			break;
		}
		return false;
	}
}