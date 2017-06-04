package com.finalproject.reachyourfitnessgoals.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.Goal2Activity;
import com.finalproject.reachyourfitnessgoals.activity.GoalActivity;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_selectGoal extends Fragment {

    Button loseWeightButton;
    Button muscleButton;

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
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
            }
        });

        muscleButton =(Button)rootview.findViewById(R.id.muscle_Button_selectGoal);
        muscleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Goal2Activity.class);
                startActivity(intent);
            }
        });

        return rootview;
    }

}
