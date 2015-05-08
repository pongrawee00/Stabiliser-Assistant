package com.starboy.karav.SA;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;


public class BluetoothDeviceListActivity extends ColourBarActivity {
	private String TAG = "BluetoothDeviceListActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int currentOrientation = getResources().getConfiguration().orientation;
		if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		}
		setContentView(R.layout.activity_sender);
		Log.d(TAG, "Start Discovery");
		getFragmentManager().beginTransaction().replace(R.id.sender_fragment, new BluetoothDiscoveryFragment()).commit();
	}

	public void replaceFragment(Fragment fragment) {
		Log.d(TAG, "Start new activity");
		getFragmentManager().beginTransaction().replace(R.id.sender_fragment, fragment).addToBackStack(null).commit();
	}

	public void sentBackData(Intent intent) {
		// Set result and finish this Activity
		setResult(Activity.RESULT_OK, intent);
		finish();
	}


}
