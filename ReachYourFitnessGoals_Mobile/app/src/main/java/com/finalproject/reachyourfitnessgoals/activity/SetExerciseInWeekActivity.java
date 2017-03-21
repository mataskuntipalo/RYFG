package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;



import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.content.ContextCompat;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.models.workoutOfWeekData;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class SetExerciseInWeekActivity extends AppCompatActivity implements VerticalStepperForm {

    private VerticalStepperFormLayout verticalStepperForm;
    private LinearLayout daysStepContent;
    private LinearLayout selectDaysStepContent;
    private Calendar thaiTime;
    private int day;
    int maxDay;
    int tempDay;
    public com.finalproject.reachyourfitnessgoals.models.workoutOfWeekData workoutOfWeekData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exercise_in_week);

        workoutOfWeekData = new workoutOfWeekData();
        thaiTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+07:00"));
        day = thaiTime.get(Calendar.DAY_OF_WEEK);
        //day = Calendar.FRIDAY;
//        Log.i("test",day+"");
//        Log.i("test","This is Friday"+Calendar.FRIDAY);
        checkWeekEndDay(day);
        maxDay = 4; // id layout 1-4
        tempDay = 7 - day;

        String[] stepsTitles = {getResources().getString(R.string.steps_titles1), getResources().getString(R.string.steps_titles2)};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.stepper_stepView_setExe);


        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm,stepsTitles, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();



    }

    private void checkWeekEndDay(int day){
        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
            AlertDialog.Builder builder = new AlertDialog.Builder(SetExerciseInWeekActivity.this,R.style.LightDialogTheme);
            builder.setMessage("ไม่สามารถเลือกวันออกกำลังกาย เนื่องจากเป็นวันสุดสัปดาห์ กรุณากลับมาใหม่วันจันทร์");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    sendData();
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    sendData();
                }
            });
            builder.show();
        }
//        switch (day) {
//            case Calendar.SUNDAY:
//                currentDay_WeekEnd();
//                break;
//
//            case Calendar.MONDAY:
//                currentDay_MondayTuesDay(Calendar.MONDAY);
//                break;
//
//            case Calendar.TUESDAY:
//                currentDay_MondayTuesDay(Calendar.TUESDAY);
//                break;
//
//            case Calendar.WEDNESDAY:
//                break;
//
//            case Calendar.THURSDAY:
//                break;
//
//            case Calendar.FRIDAY:
//                break;
//
//            case Calendar.SATURDAY:
//                currentDay_WeekEnd();
//                break;
//        }
    }

    private void currentDay_MondayTuesDay(int day){
        if(day == Calendar.MONDAY){

        }else {

        }
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createDayOfWeekStep();
                break;
            case 1:
                view = createSelectDayStep();
                break;
        }
        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                checkDayOfWeekStep();
                break;
            case 1:
                checkSelectDayStep();
                break;
            case 2:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(2);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
        }
    }

    @Override
    public void sendData() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private View createDayOfWeekStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        daysStepContent = (LinearLayout) inflater.inflate(R.layout.workout_per_week_layout, null, false);
        return daysStepContent;
    }

    private void checkDayOfWeekStep(){
        if(tempDay < maxDay){
            maxDay = tempDay;
        }
        for(int i=maxDay ; i>=1 ; i--){
            LinearLayout dayLayout = getDayLayout(i);
            dayLayout.setAlpha(1);
            TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
            dayText.setTextColor(getResources().getColor(R.color.colorBlack));
            dayText.setAlpha(1);
            dayLayout.setOnClickListener(daySet);
        }
    }

    private LinearLayout getDayLayout(int i) {
        int id = daysStepContent.getResources().getIdentifier(
                "day_" + i, "id",getPackageName());
        return (LinearLayout) daysStepContent.findViewById(id);
    }

    private View.OnClickListener daySet = new View.OnClickListener(){
        int tempValue;
        String tempTag;
        @Override
        public void onClick(View v) {
            for(int i=maxDay ; i>=1 ; i--){
                LinearLayout dayLayout = getDayLayout(i);
                dayLayout.setBackground(getResources().getDrawable(R.drawable.border_bottom));
                TextView oldDayText = (TextView) dayLayout.findViewById(R.id.day);
                oldDayText.setTextColor(getResources().getColor(R.color.colorBlack));
            }
            v.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            TextView dayText = (TextView) v.findViewById(R.id.day);
            dayText.setTextColor(getResources().getColor(R.color.colorWhite));
            tempTag = String.valueOf(v.getTag());
            //Log.i("test",tempTag);
            tempValue = Integer.parseInt(tempTag);
            //Log.i("test",workoutOfWeekData.getWorkoutPerWeek()+"");
            workoutOfWeekData.setWorkoutPerWeek(tempValue);
            verticalStepperForm.setActiveStepAsCompleted();
            verticalStepperForm.setStepTitle(0, getResources().getString(R.string.steps_titles1)+" : " + dayText.getText());
        }
    };


    // end step 1

    private View createSelectDayStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        selectDaysStepContent = (LinearLayout) inflater.inflate(R.layout.day_of_workout_layout, null, false);
        return selectDaysStepContent;
    }

    private void checkSelectDayStep(){
        for(int i=7 ; i>=day ; i--){
            LinearLayout selectDayLayout = getSelectDayLayout(i);
            selectDayLayout.setOnClickListener(selectDaySet);
            selectDayLayout.setTag("false");
            TextView dayInLayout = (TextView) selectDayLayout.findViewById(R.id.day_text_dayOfWork);
            dayInLayout.setTextColor(getResources().getColor(R.color.colorBlack));
            dayInLayout.setAlpha(1);
        }
        TextView setTotalDay = getTotalDayID(2);
        setTotalDay.setText(workoutOfWeekData.getWorkoutPerWeek()+"");
    }

    private LinearLayout getSelectDayLayout(int i) {
        int id = selectDaysStepContent.getResources().getIdentifier(
                "dayOfWork_" + i, "id",getPackageName());
        return (LinearLayout) selectDaysStepContent.findViewById(id);
    }

    private TextView getTotalDayID(int key){
        int id;
        if(key == 1){
            id = selectDaysStepContent.getResources().getIdentifier(
                    "totalSelectDay_Text_dayOfWork", "id",getPackageName());
        }else{
            id = selectDaysStepContent.getResources().getIdentifier(
                    "totalDay_Text_dayOfWork", "id",getPackageName());
        }
        return (TextView) selectDaysStepContent.findViewById(id);
    }

    private View.OnClickListener selectDaySet = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v.getTag() == "false"){
                v.setBackground(getResources().getDrawable(R.drawable.layout_circle));
            }else{
                v.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                v.setTag("true");
            }

            TextView dayText = (TextView) v.findViewById(R.id.day_text_dayOfWork);
            dayText.setTextColor(getResources().getColor(R.color.colorWhite));
            verticalStepperForm.setActiveStepAsCompleted();
        }
    };
}
