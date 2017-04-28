package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
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
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.ExeInWeekData;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.workoutOfWeekData;
import com.finalproject.reachyourfitnessgoals.setting.EventDecorator;
import com.finalproject.reachyourfitnessgoals.setting.MyReceiver;
import com.finalproject.reachyourfitnessgoals.setting.handleCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class SetExerciseInWeekActivity extends AppCompatActivity implements VerticalStepperForm {

    private VerticalStepperFormLayout verticalStepperForm;
    private LinearLayout daysStepContent;
    private LinearLayout selectDaysStepContent;
    private handleCalendar calendar;
    private handleTABLE_EXERCISE handleTableExercise;
    private fragment_calendar fragmentCalendar;
    int today;
    int maxDay=4;// id layout 1-4
    int tempDay,tempTotalSelectDay=0;
    public workoutOfWeekData workoutOfWeekData;
    public SharedPreferences shared;
    public SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exercise_in_week);

        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        workoutOfWeekData = new workoutOfWeekData();
        calendar = new handleCalendar();
        today = calendar.getCurrentDay();
        handleTableExercise = new handleTABLE_EXERCISE(this);
        fragmentCalendar = fragment_calendar.newInstance();
        //Log.i("testDate",thaiTime.getCurrentTime()+"");
        checkWeekEndDay(today);
        tempDay = 7 - today;

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


        calendar.getAllDayInWeek();

    }

    private void checkWeekEndDay(int day){
        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
            AlertDialog.Builder builder = new AlertDialog.Builder(SetExerciseInWeekActivity.this,R.style.LightDialogTheme);
            builder.setMessage("ไม่สามารถเลือกวันออกกำลังกาย เนื่องจากเป็นวันสุดสัปดาห์ กรุณากลับมาใหม่วันจันทร์");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent();
                    intent.putExtra("key","weekEnd");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent intent = new Intent();
                    intent.putExtra("key","weekEnd");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
            builder.show();
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
        editor.putBoolean(getResources().getString(R.string.sharedBoolSetExe), true);
        editor.commit();
        dayHighLight();
        setDateCheckWeekly();
        Intent intent = new Intent();
        intent.putExtra("key","ok");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    // Begin step 1

    private View createDayOfWeekStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        daysStepContent = (LinearLayout) inflater.inflate(R.layout.workout_per_week_layout, null, false);
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
        return daysStepContent;
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

    // Begin step 2

    private View createSelectDayStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        selectDaysStepContent = (LinearLayout) inflater.inflate(R.layout.day_of_workout_layout, null, false);
        int tempSelectDay = today-1;
        if(today == Calendar.SUNDAY){
            tempSelectDay = 7;
        }
        for(int i=7 ; i>=tempSelectDay ; i--){
            LinearLayout selectDayLayout = getSelectDayLayout(i);
            selectDayLayout.setOnClickListener(selectDaySet);
            TextView dayInLayout = (TextView) selectDayLayout.findViewById(R.id.day_text_dayOfWork);
            dayInLayout.setTextColor(getResources().getColor(R.color.colorBlack));
            dayInLayout.setAlpha(1);
        }

        return selectDaysStepContent;
    }

    private void checkSelectDayStep(){
        TextView setTotalDay = getTotalDayID(1);
        setTotalDay.setText(workoutOfWeekData.getWorkoutPerWeek()+"");
        checkSelectDayAndTotalDay();
    }

    private LinearLayout getSelectDayLayout(int i) {
        int id = selectDaysStepContent.getResources().getIdentifier(
                "dayOfWork_" + i, "id",getPackageName());
        return (LinearLayout) selectDaysStepContent.findViewById(id);
    }

    private TextView getTotalDayID(int key){
        int id = 0;
        switch (key) {
            case 0:
                id = selectDaysStepContent.getResources().getIdentifier(
                        "totalSelectDay_Text_dayOfWork", "id",getPackageName());
                break;
            case 1:
                id = selectDaysStepContent.getResources().getIdentifier(
                        "totalDay_Text_dayOfWork", "id",getPackageName());
                break;
            case 2:
                id = selectDaysStepContent.getResources().getIdentifier(
                        "alert_Text_dayOfWork", "id",getPackageName());
                break;
        }
        return (TextView) selectDaysStepContent.findViewById(id);
    }

    private View.OnClickListener selectDaySet = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            addValueSelectDay(v);
            checkSelectDayAndTotalDay();
        }
    };

    private void addValueSelectDay(View v){
        TextView dayText = (TextView) v.findViewById(R.id.day_text_dayOfWork);
        if(v.getTag().toString().equals("false")){
            v.setBackground(getResources().getDrawable(R.drawable.layout_circle));
            v.setTag("true");
            dayText.setTextColor(getResources().getColor(R.color.colorWhite));
            tempTotalSelectDay++;
        }else{
            v.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            v.setTag("false");
            dayText.setTextColor(getResources().getColor(R.color.colorBlack));
            tempTotalSelectDay--;
        }
        TextView setTotalSelectDay = getTotalDayID(0);
        setTotalSelectDay.setText(tempTotalSelectDay+"");
    }

    private void checkSelectDayAndTotalDay(){
        TextView alert = getTotalDayID(2);
        if(tempTotalSelectDay > workoutOfWeekData.getWorkoutPerWeek()){
            alert.setVisibility(View.VISIBLE);
        }else{
            alert.setVisibility(View.INVISIBLE);
        }

        if(tempTotalSelectDay == workoutOfWeekData.getWorkoutPerWeek()){
            verticalStepperForm.setActiveStepAsCompleted();
        }else {
            verticalStepperForm.setActiveStepAsUncompleted();
        }
    }

    //end step 2

    // other setting

    private void dayHighLight(){
        ArrayList<DateData> dateList = calendar.getAllDayInWeek();
        ArrayList<DateData> data = new ArrayList<>();
        for(int i=1 ; i<=7 ; i++){
            LinearLayout selectDayLayout = getSelectDayLayout(i);
            if(selectDayLayout.getTag().toString().equals("true")){
                editor.putBoolean(getResources().getString(R.string.sharedBoolDayHighLight)+ i , true);
                addToDataBase(dateList.get(i-1));
                data.add(dateList.get(i-1));
            }else{
                editor.putBoolean(getResources().getString(R.string.sharedBoolDayHighLight)+ i , false);
            }
        }
        fragmentCalendar.addEvent(data,getResources().getColor(R.color.colorPrimary));
        editor.commit();
    }


    private void setDateCheckWeekly(){
        long midnightTimeNextMonday = (AlarmManager.INTERVAL_DAY * (getNextMonday()-1))+(AlarmManager.INTERVAL_DAY - calendar.getTimeToMidnight());
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+midnightTimeNextMonday,pendingIntent);

        Log.i("timeINTERVALDAY",""+AlarmManager.INTERVAL_DAY);
        Log.i("timeINTERVALDAY",""+System.currentTimeMillis());
        Log.i("time",""+calendar.getTimeToMidnight());
    }

    private int getNextMonday(){
        int nextMonday;
        if(today == Calendar.MONDAY){
            nextMonday = 7;
            return  nextMonday;
        }else {
            nextMonday = (Calendar.SATURDAY - today + 2) % 7;
            return nextMonday;
        }
    }

    private void addToDataBase(DateData data){
        ExeInWeekData exeInWeekData = new ExeInWeekData(data.getDay(),data.getMonth(),data.getYear());
        handleTABLE_PROGRAM handleTABLEProgram = new handleTABLE_PROGRAM(this);
        exeInWeekData.setCalorieTotal(handleTABLEProgram.getProgramDate().getKgPerWeek()/workoutOfWeekData.getWorkoutPerWeek());
        handleTableExercise.addExercise(exeInWeekData);
    }
}
