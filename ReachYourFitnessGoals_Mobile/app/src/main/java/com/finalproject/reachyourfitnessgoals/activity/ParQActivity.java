package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.ParQStepperAdapter;
import com.finalproject.reachyourfitnessgoals.interfaces.AnswerParQDataManager;
import com.stepstone.stepper.StepperLayout;

public class ParQActivity extends AppCompatActivity implements AnswerParQDataManager {

    private StepperLayout mStepperLayout;
    private boolean[] ansArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_q);

        ansArray = new boolean[7];
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new ParQStepperAdapter(getSupportFragmentManager(), this));

    }


    @Override
    public void saveAnswer(boolean ans , int position) {
        ansArray[position] = ans;
    }

    @Override
    public boolean[] getAnswer() {
        return ansArray;
    }
}
