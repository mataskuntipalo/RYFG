package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.setting.CalculateShape;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Goal2Activity extends Activity {

    CalculateShape calculateShape;
    TextView percentFat;
    Button confirmBtn;
    GoalData goalData;
    RadioGroup groupRadio;
    EditText programName;
    private handleTABLE_PROGRAM handleTableProgram;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal2);

        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();

        handleTableProgram = new handleTABLE_PROGRAM(this);
        calculateShape = new CalculateShape(this);
        percentFat = (TextView)findViewById(R.id.percentFat_TextView_goal2);
        confirmBtn = (Button)findViewById(R.id.confirm_Button_goal2);
        groupRadio = (RadioGroup)findViewById(R.id.group_RadioButton_goal2);
        programName = (EditText)findViewById(R.id.programName_EditText_goal2);

        percentFat.setText(calculateShape.getPercentFat()+" %");

        goalData = new GoalData();

        setUpData();

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)findViewById(i);
                selectKgPerWeek(radioButton);
            }
        });


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalData.setProgramName(programName.getText().toString().trim());
                handleTableProgram.addProgram(goalData);
                editor.putBoolean(getResources().getString(R.string.sharedBoolLogIn), true);
                editor.commit();
                Intent intent = new Intent(Goal2Activity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

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
                goalData.setKgPerWeek(5390);
                Log.i("test","7");
                break;
        }
    }

    private void setUpData() {
        goalData.setTypeGoal(ExeType.TYPE_PROGRAM_MUSCLE);
        goalData.setKgPerWeek(1540);
        goalData.setPercentFat(calculateShape.getPercentFat());
        calDateOfProgram();
        goalData.setStatus(1);
    }

    public void calDateOfProgram(){
        Calendar thaiTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+07:00"));
        int year_begin = thaiTime.get(Calendar.YEAR);
        int month_begin = thaiTime.get(Calendar.MONTH);
        int day_begin = thaiTime.get(Calendar.DAY_OF_MONTH);

        goalData.setYear_date_begin(year_begin);
        goalData.setMonth_date_begin(month_begin);
        goalData.setDay_date_begin(day_begin);


//        int tempDateEnd = goalData.getTotalDuration() * 7;
//        thaiTime.add(Calendar.DATE,tempDateEnd);


    }
}
