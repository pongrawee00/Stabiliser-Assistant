package com.starboy.karav.SA;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SenderActivity extends ColourBarActivity {

	private static final int REQUEST_ENABLE_BT = 3;
	/**
	 * Name of the connected device
	 */
	private String mConnectedDeviceName = null;
	/**
	 * Member object for the chat services
	 */
	private BluetoothChatService mChatService = null;
	/**
	 * Local Bluetooth adapter
	 */
	private BluetoothAdapter mBluetoothAdapter = null;
	private TextView display;
	/**
	 * The Handler that gets information back from the BluetoothChatService
	 */
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Activity activity = (Activity) getApplicationContext();
			switch (msg.what) {
				case Constants.MESSAGE_STATE_CHANGE:
					switch (msg.arg1) {
						case BluetoothChatService.STATE_CONNECTED:
//                            (getActivity()).setTitle(getString(R.string.title_connected_to, mConnectedDeviceName));
//                            mConversationArrayAdapter.clear();
							break;
						case BluetoothChatService.STATE_CONNECTING:
//                            setStatus(R.string.title_connecting);
							break;
						case BluetoothChatService.STATE_LISTEN:
							break;
						case BluetoothChatService.STATE_NONE:
//                            setStatus(R.string.title_not_connected);
							break;
					}
					break;
				case Constants.MESSAGE_READ:    //get the message
					byte[] readBuf = (byte[]) msg.obj;
					// construct a string from the valid bytes in the buffer
					displayMessage(new String(readBuf, 0, msg.arg1));
//                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
					break;
				case Constants.MESSAGE_DEVICE_NAME:
					// save the connected device's name
					mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
					if (null != activity) {
						Toast.makeText(activity, "Connected to " + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
					}
					break;
				case Constants.MESSAGE_TOAST:
					if (null != activity) {
						Toast.makeText(activity, msg.getData().getString(Constants.TOAST), Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};
	private Button discover;

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
	}

	@Override
	public void onStart() {
		super.onStart();
		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		// If BT is not on, request that it be enabled.
		// setupChat() will then be called during onActivityResult
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
			// Otherwise, setup the chat session
		} else if (mChatService == null) {
			// Initialize the BluetoothChatService to perform bluetooth connections
			mChatService = new BluetoothChatService(getApplicationContext(), mHandler);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mChatService != null) {
			mChatService.stop();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		// Performing this check in onResume() covers the case in which BT was
		// not enabled during onStart(), so we were paused to enable it...
		// onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
		if (mChatService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't started already
			if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
				// Start the Bluetooth chat services
				mChatService.start();
			}
		}
	}

	/**
	 * Makes this device discoverable.
	 */
	private void ensureDiscoverable() {
		if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}
	}

	private void displayMessage(String message) {
		display.setText(message);
	}


}
