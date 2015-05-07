package com.starboy.karav.SA;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button Sender = (Button) findViewById(R.id.senderBut);
		Button Reciever = (Button) findViewById(R.id.recieverBut);
		Sender.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, SenderActivity.class));
			}
		});

		Reciever.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, ReceiverActivity.class));
			}
		});
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new BluetoothDiscoveryFragment()).commit();
	}

	public void setTitleBar(int colour) {

		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(getResources().getColor(colour));
	}

	public void setActionBar(String heading, int colour) {
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(colour)));
		actionBar.setTitle(heading);
		actionBar.show();
	}

}
