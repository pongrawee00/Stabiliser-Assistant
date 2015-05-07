package com.starboy.karav.SA;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class SensorsManager extends ActionBarActivity implements SensorEventListener {

	private SensorManager senSensorManager;
	private Sensor senAccelerometer;
	private float x;
	private float y;
	private float z;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
	}

}
