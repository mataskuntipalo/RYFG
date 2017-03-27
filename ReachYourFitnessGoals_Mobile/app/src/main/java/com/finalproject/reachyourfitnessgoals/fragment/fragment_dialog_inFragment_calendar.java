package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_dialog_inFragment_calendar extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setTitle("เลือกท่าออกกำลังกาย");

        View rootview = inflater.inflate(R.layout.fragment_dialog_in_fragment_calendar, container, false);
        return rootview;
    }


}
