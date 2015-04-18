package com.starboy.karav.sender;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class RecieverFragment extends Fragment {

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
    private View view;
    private TextView display;
    private Button discover;
    private TextView status;
    private TextView level;
    private Button start;
    private Button stop;
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button level5;

    public RecieverFragment() {
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
            mChatService = new BluetoothChatService(getActivity(), mHandler);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reciever, container, false);
        display = (TextView) view.findViewById(R.id.waitForConnect);
        discover = (Button) view.findViewById(R.id.discover_rec);
        discover.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ensureDiscoverable();
            }
        });

        status = (TextView) view.findViewById(R.id.status);
        level = (TextView) view.findViewById(R.id.status_level);
        start = (Button) view.findViewById(R.id.start_but);
        stop = (Button) view.findViewById(R.id.stop_but);

        level1 = (Button) view.findViewById(R.id.level1);
        level2 = (Button) view.findViewById(R.id.level2);
        level3 = (Button) view.findViewById(R.id.level3);
        level4 = (Button) view.findViewById(R.id.level4);
        level5 = (Button) view.findViewById(R.id.level5);
        return view;
    }

    public static View.OnClickListener onClick = new OnClickListener() {

        @Override
        private void Onclick(View view) {
            switch (view.getId()) {
                case R.id.start_but:
                    break;
                case R.id.stop_but:
                    break;
                case R.id.level1:
                    break;
                case R.id.level2:
                    break;
                case R.id.level3:
                    break;
                case R.id.level4:
                    break;
                case R.id.level5:
                    break;
            }
        }

    };

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
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    private void displayMessage(String message) {
        display.setText(message);
    }

    /**
     * The Handler that gets information back from the BluetoothChatService
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = getActivity();
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
//                case Constants.MESSAGE_WRITE:
//                    byte[] writeBuf = (byte[]) msg.obj;
//                    // construct a string from the buffer
//                    String writeMessage = new String(writeBuf);
//                    mConversationArrayAdapter.add("Me:  " + writeMessage);
//                    break;
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
                        Toast.makeText(activity, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
