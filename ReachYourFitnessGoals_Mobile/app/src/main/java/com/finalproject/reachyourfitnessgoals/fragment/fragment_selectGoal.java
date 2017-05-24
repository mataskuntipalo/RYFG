package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
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
public class fragment_selectGoal extends Fragment {

    Button loseWeightButton;

    public fragment_selectGoal() {
        // Required empty public constructor
    }

    public static fragment_selectGoal newInstance() {
        fragment_selectGoal fragment = new fragment_selectGoal();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_select_goal, container, false);

        loseWeightButton = (Button)rootview.findViewById(R.id.loseWeight_Button_selectGoal);
        loseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_intro_parQ intro_parQ = fragment_intro_parQ.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_login, intro_parQ, "fragment_intro_parQ")
                        .addToBackStack("fragment_intro_parQ")
                        .commit();
            }
        });

        return rootview;
    }

}