package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_dialog_inFragment_calendar extends DialogFragment {

    Button customButton,autoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setTitle("เลือกท่าออกกำลังกาย");

        View rootview = inflater.inflate(R.layout.fragment_dialog_in_fragment_calendar, container, false);
        customButton = (Button)rootview.findViewById(R.id.customSetExe_Button_dialog);
        autoButton = (Button)rootview.findViewById(R.id.autoSetExe_Button_dialog);

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_custom custom = fragment_custom.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_main, custom, "fragment_custom")
                        .addToBackStack("fragment_custom")
                        .commit();
            }
        });





        return rootview;
    }


}
