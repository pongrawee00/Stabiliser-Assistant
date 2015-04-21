package com.starboy.karav.SA;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Karav on 21/04/2015.
 */
public class ColourActionBarActivity extends ActionBarActivity {

    public void setStatusBarColour(int colour) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(colour));
    }

    private ActionBar setAColour(int colour) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(colour)));
        return actionBar;
    }

    public void setActionBarColour(int colour) {
        setAColour(colour).show();
    }

    public void setActionBarColour(String heading, int colour) {
        ActionBar actionBar = setAColour(colour);
        actionBar.setTitle(heading);

        actionBar.show();
    }
}
