package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.models.GoalData;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_goal extends Fragment {

    RadioGroup groupRadio;
    TextView confirm;
    GoalData goalData;
    EditText weightGoal;

    public fragment_goal() {
        // Required empty public constructor
    }

    public static fragment_goal newInstance() {
        fragment_goal fragment = new fragment_goal();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_goal, container, false);
        goalData = new GoalData();
        confirm = (TextView) rootview.findViewById(R.id.confirm_TextView_goal);
        groupRadio = (RadioGroup)rootview.findViewById(R.id.group_RadioButton_goal);
        weightGoal = (EditText)rootview.findViewById(R.id.weightGoal_EditText_goal);



        goalData.setKgPerWeek(1540);

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)rootview.findViewById(i);
                selectKgPerWeek(radioButton);
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalData.setWeightGoal(Float.parseFloat(weightGoal.getText().toString()));
                calTimeOfProgramExe(goalData);
            }
        });


        return rootview;
    }


    public void selectKgPerWeek(View v){
        switch (v.getId()){
            case R.id.two_RadioButton_goal:
                goalData.setKgPerWeek(1540);
                Log.i("test","2");
                break;
            case R.id.five_RadioButton_goal:
                goalData.setKgPerWeek(3850);
                Log.i("test","5");
                break;
            case R.id.seven_RadioButton_goal:
                goalData.setKgPerWeek(6160);
                Log.i("test","7");
                break;
        }
    }


    public void calTimeOfProgramExe(GoalData data){
        float temp = data.getWeightGoal()*7700;
        temp = temp / data.getKgPerWeek();
        if(temp % 1 == 0){
            goalData.setTotalDuration((int)temp);
            Log.i("temp",goalData.getTotalDuration()+"");
        }else{
            goalData.setTotalDuration((int)temp+1);
            Log.i("temp2",goalData.getTotalDuration()+"");
        }

    }

}
