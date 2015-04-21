package com.starboy.karav.SA;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class SummaryFragment extends Fragment {
    private String TAG = "SummaryF";

    private int time;
    private int grade;
    private TextView level;
    private RatingBar rbar;
    private Chronometer timer;

    public SummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reciever_finish, container, false);
        getBundle();
        level = (TextView) view.findViewById(R.id.s_level);
        timer = (Chronometer) view.findViewById(R.id.s_timer);
        rbar = (RatingBar) view.findViewById(R.id.s_ratingBar);
        setColour();
        setTime();
        setRating();

        return view;
    }

    private void getBundle() {
        Bundle bundle = ((SummaryActivity) getActivity()).getBundle();
        time = bundle.getInt("Time");
        Log.d(TAG, Integer.toString(time));
        grade = bundle.getInt("Level");
    }

    private void setColour() {
        switch (grade) {
            case 1:
                level.setBackgroundColor(getResources().getColor(R.color.c_l1));
                break;
            case 2:
                level.setBackgroundColor(getResources().getColor(R.color.c_l2));
                break;
            case 3:
                level.setBackgroundColor(getResources().getColor(R.color.c_l3));
                break;
            case 4:
                level.setBackgroundColor(getResources().getColor(R.color.c_l4));
                break;
            case 5:
                level.setBackgroundColor(getResources().getColor(R.color.c_l5));
                break;
        }
    }

    private void setRating() {
        rbar.setProgress(grade);
    }

    private void setTime() {
        timer.setBase(SystemClock.elapsedRealtime() - (time * 1000));
    }
}
