package com.rpgplayground.activity;

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

public class DialogTest extends Activity {

	final Context context = this;
	private Button button;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.buttonShowCustomDialog);

		button.setOnClickListener(new OnClickListener() {
//			@Override
			public void onClick(View arg0) {

				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.attack_dialog);
				dialog.setTitle("Title...");

				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.ad1text);
				text.setText("Android custom dialog example!");
				ImageView image = (ImageView) dialog.findViewById(R.id.ad1image);
				image.setImageResource(R.drawable.ic_launcher);

				Button dialogButton = (Button) dialog
						.findViewById(R.id.ad1button);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
//					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});
	}
}
