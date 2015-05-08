package com.starboy.karav.SA;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Karav on 21/04/2015.
 */
public class ColourBarActivity extends Activity {

	public void setStatusBarColour(int colour) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(getResources().getColor(colour));
		}
	}

	private ActionBar setAColour(int colour) {
		ActionBar actionBar = getActionBar();
		if (actionBar != null)
			actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(colour)));
		return actionBar;
	}

	public void setActionBarColour(int colour) {
		setAColour(colour);
	}

	public void setActionBarColour(String heading, int colour) {
		ActionBar actionBar = setAColour(colour);
		if (actionBar != null) actionBar.setTitle(heading);

	}
}
