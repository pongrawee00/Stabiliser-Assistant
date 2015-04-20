package com.starboy.karav.SA;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.RippleDrawable;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class ReceiverFragment extends Fragment {

//    private static final int REQUEST_ENABLE_BT = 3;
//    /**
//     * Name of the connected device
//     */
//    private String mConnectedDeviceName = null;
//
//    /**
//     * Member object for the chat services
//     */
//    private BluetoothChatService mChatService = null;
//
//    /**
//     * Local Bluetooth adapter
//     */
//    private BluetoothAdapter mBluetoothAdapter = null;

    private String TAG = "ReceiverFragment";

    private View view;
    private TextView display;
    private Button discover;
    private TextView status;
    private TextView status_level;
    private Button start;
    private Button stop;
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button level5;
    private Chronometer timer;

    private int level;

    private boolean timeOn;

    private int currentColour;

    public ReceiverFragment() {
    }

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }
    @Override
    public void onStart() {
        super.onStart();
//        // Get local Bluetooth adapter
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        // If BT is not on, request that it be enabled.
//        // setupChat() will then be called during onActivityResult
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//            // Otherwise, setup the chat session
//        } else if (mChatService == null) {
//            // Initialize the BluetoothChatService to perform bluetooth connections
//            mChatService = new BluetoothChatService(getActivity(), mHandler);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_receiver, container, false);
//        display = (TextView) view.findViewById(R.id.waitForConnect);
        status = (TextView) view.findViewById(R.id.status);
        status_level = (TextView) view.findViewById(R.id.status_level);
        status_level.setText("");
        timer = (Chronometer) view.findViewById(R.id.timer);
        level = 1;
        timeOn = false;
        currentColour = R.color.c_l1;
        setButton();

        return view;
    }

    private void startTime() {
        if (level1.isShown()) {
            level1.setVisibility(View.INVISIBLE);
            level2.setVisibility(View.INVISIBLE);
            level3.setVisibility(View.INVISIBLE);
            level4.setVisibility(View.INVISIBLE);
            level5.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.VISIBLE);
            //TODO sent process message
        }
        if (timeOn) {
            start.setText(getResources().getString(R.string.resume));
            timeOn = false;
            timer.stop();
            setColourAnimation(start, R.color.clear, R.color.black, 100);
            //TODO sent pause message to sender
        } else {
            start.setText(getResources().getString(R.string.pause));
            timeOn = true;
            int stoppedSeconds = timeStopped();
            // Amount of time elapsed since the start button was pressed, minus the time paused
            timer.setBase(SystemClock.elapsedRealtime() -
                    stoppedSeconds * 1000);
            timer.start();
            setColourAnimation(start, R.color.black, R.color.clear, 100);
            //TODO sent start message to sender
        }
    }

    private int timeStopped() {
        //Holds the number of milliseconds paused
        int stoppedSeconds = 0;
        // Get time from the chronometer
        String chronoText = timer.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            // Find the seconds
            stoppedSeconds = Integer.parseInt(array[0]) * 60
                    + Integer.parseInt(array[1]);
        } else if (array.length == 3) {
            //Find the minutes
            stoppedSeconds = Integer.parseInt(array[0]) * 60 * 60
                    + Integer.parseInt(array[1]) * 60
                    + Integer.parseInt(array[2]);
        }
        return stoppedSeconds;
    }


    private void stopTime() {

        // stop timer
        timer.stop();
        int totalTime = timeStopped();
        Log.d(TAG, totalTime + " ");
        //TODO sent stop message to sender
    }

    private void setButton() {
//        discover = (Button) view.findViewById(R.id.discover_rec);
//        discover.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ensureDiscoverable();
//            }
//        });

        start = (Button) view.findViewById(R.id.start_but);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime();
            }
        });
        stop = (Button) view.findViewById(R.id.stop_but);
        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTime();
            }
        });
        level1 = (Button) view.findViewById(R.id.level1);
        level1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLevel(1);
                level = 1;
            }
        });
        level1.setBackgroundColor(getResources().getColor(R.color.black_alpha));
        level2 = (Button) view.findViewById(R.id.level2);
        level2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLevel(2);
                level = 2;
            }
        });
        level3 = (Button) view.findViewById(R.id.level3);
        level3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLevel(3);
                level = 3;
            }
        });
        level4 = (Button) view.findViewById(R.id.level4);
        level4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLevel(4);
                level = 4;
            }
        });
        level5 = (Button) view.findViewById(R.id.level5);
        level5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLevel(5);
                level = 5;
            }
        });
        start.setBackgroundColor(getResources().getColor(R.color.black));
    }

    private void resetColour() {
        level1.setBackgroundColor(getResources().getColor(R.color.clear));
        level2.setBackgroundColor(getResources().getColor(R.color.clear));
        level3.setBackgroundColor(getResources().getColor(R.color.clear));
        level4.setBackgroundColor(getResources().getColor(R.color.clear));
        level5.setBackgroundColor(getResources().getColor(R.color.clear));
    }

    private void setLevel(int level) {
        resetColour();
        switch (level) {
            case 1:
                setColourAnimation(status_level, currentColour, R.color.c_l1, 300);
                currentColour = R.color.c_l1;
//                status_level.setBackgroundColor(getResources().getColor(R.color.c_l1));
                setColourAnimation(level1, R.color.clear, R.color.c_l1d, 250);
                break;
            case 2:
                setColourAnimation(status_level, currentColour, R.color.c_l2, 300);
                currentColour = R.color.c_l2;
                setColourAnimation(level2, R.color.clear, R.color.c_l2d, 250);
                break;
            case 3:
                setColourAnimation(status_level, currentColour, R.color.c_l3, 300);
                currentColour = R.color.c_l3;
                setColourAnimation(level3, R.color.clear, R.color.c_l3d, 250);
                break;
            case 4:
                setColourAnimation(status_level, currentColour, R.color.c_l4, 300);
                currentColour = R.color.c_l4;
                setColourAnimation(level4, R.color.clear, R.color.c_l4d, 250);
                break;
            case 5:
                setColourAnimation(status_level, currentColour, R.color.c_l5, 300);
                currentColour = R.color.c_l5;
                setColourAnimation(level5, R.color.clear, R.color.c_l5d, 250);
                break;
            default:
                break;
        }
    }

    private void setColourAnimation(final TextView textView, int from, int to, int duration) {
        Integer colorFrom = getResources().getColor(from);
        Integer colorTo = getResources().getColor(to);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private void setColourAnimation(final Button textView, int from, int to, int duration) {
        Integer colorFrom = getResources().getColor(from);
        Integer colorTo = getResources().getColor(to);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mChatService != null) {
//            mChatService.stop();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        // Performing this check in onResume() covers the case in which BT was
//        // not enabled during onStart(), so we were paused to enable it...
//        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
//        if (mChatService != null) {
//            // Only if the state is STATE_NONE, do we know that we haven't started already
//            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
//                // Start the Bluetooth chat services
//                mChatService.start();
//            }
//        }
//    }
//
//    /**
//     * Makes this device discoverable.
//     */
//    private void ensureDiscoverable() {
//        if (mBluetoothAdapter.getScanMode() !=
//                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
//            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//            startActivity(discoverableIntent);
//        }
//    }
//
//    private void displayMessage(String message) {
//        display.setText(message);
//    }
//
//    /**
//     * The Handler that gets information back from the BluetoothChatService
//     */
//    private final Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            FragmentActivity activity = getActivity();
//            switch (msg.what) {
//                case Constants.MESSAGE_STATE_CHANGE:
//                    switch (msg.arg1) {
//                        case BluetoothChatService.STATE_CONNECTED:
////                            (getActivity()).setTitle(getString(R.string.title_connected_to, mConnectedDeviceName));
////                            mConversationArrayAdapter.clear();
//                            break;
//                        case BluetoothChatService.STATE_CONNECTING:
////                            setStatus(R.string.title_connecting);
//                            break;
//                        case BluetoothChatService.STATE_LISTEN:
//                            break;
//                        case BluetoothChatService.STATE_NONE:
////                            setStatus(R.string.title_not_connected);
//                            break;
//                    }
//                    break;
////                case Constants.MESSAGE_WRITE:
////                    byte[] writeBuf = (byte[]) msg.obj;
////                    // construct a string from the buffer
////                    String writeMessage = new String(writeBuf);
////                    mConversationArrayAdapter.add("Me:  " + writeMessage);
////                    break;
//                case Constants.MESSAGE_READ:    //get the message
//                    byte[] readBuf = (byte[]) msg.obj;
//                    // construct a string from the valid bytes in the buffer
//                    displayMessage(new String(readBuf, 0, msg.arg1));
////                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
//                    break;
//                case Constants.MESSAGE_DEVICE_NAME:
//                    // save the connected device's name
//                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
//                    if (null != activity) {
//                        Toast.makeText(activity, "Connected to "
//                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case Constants.MESSAGE_TOAST:
//                    if (null != activity) {
//                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//            }
//        }
//    };
}
