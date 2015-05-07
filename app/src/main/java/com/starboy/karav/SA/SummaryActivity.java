package com.starboy.karav.SA;

import android.os.Bundle;


public class SummaryActivity extends ColourActionBarActivity {
	private Bundle extra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		extra = getIntent().getExtras();
		setColour(extra.getInt("Level"));
		setContentView(R.layout.activity_summary);
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_summary, new SummaryFragment())..commit();
	}

	private void setColour(int grade) {
		switch (grade) {
			case 1:
				setStatusBarColour(R.color.c_l1d);
				setActionBarColour(R.color.c_l1d);
				break;
			case 2:
				setStatusBarColour(R.color.c_l2d);
				setActionBarColour(R.color.c_l2d);
				break;
			case 3:
				setStatusBarColour(R.color.c_l3d);
				setActionBarColour(R.color.c_l3d);
				break;
			case 4:
				setStatusBarColour(R.color.c_l4d);
				setActionBarColour(R.color.c_l4d);
				break;
			case 5:
				setStatusBarColour(R.color.c_l5d);
				setActionBarColour(R.color.c_l5d);
				break;
		}
	}

	public Bundle getBundle() {
		return extra;
	}


}
