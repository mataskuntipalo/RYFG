package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.GoalData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GoalActivity extends AppCompatActivity {

    RadioGroup groupRadio;
    TextView confirm;
    GoalData goalData;
    EditText weightGoal;
    private handleTABLE_PROGRAM handleTableProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);



        handleTableProgram = new handleTABLE_PROGRAM(this);
        goalData = new GoalData();
        confirm = (TextView) findViewById(R.id.confirm_TextView_goal);
        groupRadio = (RadioGroup)findViewById(R.id.group_RadioButton_goal);
        weightGoal = (EditText)findViewById(R.id.weightGoal_EditText_goal);



        goalData.setKgPerWeek(1540);

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)findViewById(i);
                selectKgPerWeek(radioButton);
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalData.setWeightGoal(Float.parseFloat(weightGoal.getText().toString()));
                calDurationOfProgramExe(goalData);
                calDateOfProgram();
                //handleTableProgram.addProgram(goalData);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
                goalData.setKgPerWeek(3850);
                Log.i("test","7");
                break;
        }
    }


    public void calDurationOfProgramExe(GoalData data){
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

    public void calDateOfProgram(){
        Calendar thaiTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+07:00"));
        int year_begin = thaiTime.get(Calendar.YEAR);
        int month_begin = thaiTime.get(Calendar.MONTH)+1;
        int day_begin = thaiTime.get(Calendar.DAY_OF_MONTH);

        goalData.setYear_date_begin(year_begin);
        goalData.setMonth_date_begin(month_begin);
        goalData.setDay_date_begin(day_begin);

        Log.i("dateBegin",goalData.getDay_date_begin()+"");
        Log.i("dateBegin",goalData.getMonth_date_begin()+"");
        Log.i("dateBegin",goalData.getYear_date_begin()+"");

        int tempDateEnd = goalData.getTotalDuration() * 7;
        thaiTime.add(Calendar.DATE,tempDateEnd);
        int year_end = thaiTime.get(Calendar.YEAR);
        int month_end = thaiTime.get(Calendar.MONTH)+1;
        int day_end = thaiTime.get(Calendar.DAY_OF_MONTH);


    }

    @Override // ต้องใส่อันนี้ถึงจะเปลี่ยนฟ้อน
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
