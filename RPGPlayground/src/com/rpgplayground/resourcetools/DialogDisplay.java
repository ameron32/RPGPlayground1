package com.rpgplayground.resourcetools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rpgplayground.R;

public class DialogDisplay extends Activity implements OnClickListener {

	// Does not work yet.
	
	final Context context = this;
	final Dialog dialog = new Dialog(context);
	
	TextView text;
	ImageView image;
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dialog.setContentView(R.layout.attack_dialog);
	}

	public void declare (int textInt, int imageInt, int buttonInt) {
		text = (TextView) dialog.findViewById(textInt);
		image = (ImageView) dialog.findViewById(imageInt);
		button = (Button) dialog.findViewById(buttonInt);
	}
	
	public void set(String textString, int imageResource, String buttonText) {
		text.setText(textString);
		image.setImageResource(imageResource);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
	}
	
	public void show() {
		dialog.show();
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
