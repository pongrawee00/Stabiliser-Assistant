package com.starboy.karav.SA;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;


public class SenderActivity extends ActionBarActivity {
    private String TAG = "SenderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);
        Log.d(TAG, "Start Discovery");
        getSupportFragmentManager().beginTransaction().replace(R.id.sender_fragment, new BluetoothDiscoveryFragment()).commit();
    }

    public void replaceFragment(Fragment fragment) {
        Log.d(TAG, "Start new activity");
        getSupportFragmentManager().beginTransaction().replace(R.id.sender_fragment, fragment).addToBackStack(null).commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sender, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
