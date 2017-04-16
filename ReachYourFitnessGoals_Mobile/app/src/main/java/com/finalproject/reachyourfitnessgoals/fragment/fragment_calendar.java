package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.setting.EventDecorator;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_calendar extends Fragment {

    private static MaterialCalendarView MCV;
    private handleTABLE_PROGRAM handleTableProgram;
    private handleTABLE_EXERCISE handleTableExercise;
    private boolean checkUserUsed = true;
    private GoalData goalData;
    private ScrollView scrollView;
    private RelativeLayout layout;
    private Button setExeButton;



    public fragment_calendar() {
        // Required empty public constructor
    }

    public static fragment_calendar newInstance() {
        fragment_calendar fragment = new fragment_calendar();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_calendar, container, false);



        handleTableProgram = new handleTABLE_PROGRAM(getActivity());
        handleTableExercise = new handleTABLE_EXERCISE(getActivity());
        //goalData = handleTableProgram.getDateToCalendar();
        MCV = (MaterialCalendarView) rootview.findViewById(R.id.datePicker_DatePicker_calendar);
        layout = (RelativeLayout)  rootview.findViewById(R.id.layoutCalendar_RelativeLayout_calendar);
        scrollView = (ScrollView) rootview.findViewById(R.id.SV_ScrollView_calendar);
        setExeButton = (Button) rootview.findViewById(R.id.setExeOfDay_button_calendar);
//        MCV.state().edit()
//                .setFirstDayOfWeek(Calendar.MONDAY)
//                .setMinimumDate(CalendarDay.from(goalData.getYear_date_begin(), goalData.getMonth_date_begin(), goalData.getDay_date_begin()))
//                .setMaximumDate(CalendarDay.from(goalData.getYear_date_end()+100, 12, 31))
//                .setCalendarDisplayMode(CalendarMode.MONTHS)
//                .commit();


        // Month begin 0 = january
        MCV.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2017, 2, 1))
                .setMaximumDate(CalendarDay.from(2017, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        MCV.setDateSelected(CalendarDay.today(),true);
        MCV.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                checkExeInDay(date);
            }
        });

        setExeButton.setOnClickListener(setExe);
        addEvent(handleTableExercise.getDateExerciseToCalendar(),getResources().getColor(R.color.colorPrimary));

        return rootview;
    }


    public void addEvent(ArrayList<DateData> dateDatas,int color){
            HashSet<CalendarDay> event = new HashSet<CalendarDay>();
            for(DateData date : dateDatas){
                event.add(CalendarDay.from(date.getYear(),date.getMonth(),date.getDay()));
            }

            MCV.addDecorator(new EventDecorator(color, event));
    }

    private void checkExeInDay(CalendarDay date){
        ExerciseData exerciseData = handleTableExercise.getDetailExercise(date);
        Log.i("exerciseData",exerciseData+"");
        if(exerciseData != null){
            if(exerciseData.getVdo_id().equals("ยังไม่ได้เลือกท่าออกกำลังกาย")){
                setExeButton.setVisibility(View.VISIBLE);
                setExeButton.setOnClickListener(setExe);
            }else {
                setExeButton.setVisibility(View.INVISIBLE);
            }

        }else{

        }

    }

    private View.OnClickListener setExe = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            fragment_dialog_inFragment_calendar dialogFragment = new fragment_dialog_inFragment_calendar ();
            dialogFragment.show(fm, "Sample Fragment");
        }
    };

    private View.OnClickListener syn = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };





}
