package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_selectGoal;

public class EndProgramActivity extends AppCompatActivity {


    Button end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_program);

        end = (Button)findViewById(R.id.newProgram_Button_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new handleTABLE_PROGRAM(getBaseContext()).endProgram();
                fragment_selectGoal selectGoal = fragment_selectGoal.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_end_program, selectGoal, "fragment_iselectGoal")
                        .commit();
            }
        });

    }
}
