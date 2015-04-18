package com.starboy.karav.sender;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Vibrator;

import java.util.List;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private TextView tv;
    private SeekBar xbar;
    private VerticalSeekBar ybar;
    private Vibrator v;
    private boolean flag;
    private float x;
    private float y;
    private float z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        xbar = (SeekBar) findViewById(R.id.xbar);
        ybar = (VerticalSeekBar) findViewById(R.id.ybar);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        flag = false;
        senSensorManager = (SensorManager) this.getSystemService((SENSOR_SERVICE));
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];
            xbar.setProgress(1000 + (int) (x * 100));
            ybar.setProgress(1000 + (int) (y * 100));
            if ((-0.5 < x) && (x < 0.5) && ((-0.5 < y) && (y < 0.5))) {
                if(!flag)
                v.vibrate(200);
                flag = true;
            }
            else{
                flag = false;
            }
            tv.setText("x = " + x + "\ny = " + y + "\nz = " + z);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer,  SensorManager.SENSOR_DELAY_FASTEST);
    }
}
