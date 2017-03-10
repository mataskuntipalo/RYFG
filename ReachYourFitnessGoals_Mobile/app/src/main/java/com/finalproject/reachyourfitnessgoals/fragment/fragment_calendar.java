package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.setting.EventDecorator;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

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

    private MaterialCalendarView MCV;
    private handleTABLE_PROGRAM handleTableProgram;
    GoalData goalData;
    ScrollView scrollView;
    RelativeLayout layout;



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
        //goalData = handleTableProgram.getDateToCalendar();
        MCV = (MaterialCalendarView) rootview.findViewById(R.id.datePicker_DatePicker_calendar);
        layout = (RelativeLayout)  rootview.findViewById(R.id.layoutCalendar_RelativeLayout_calendar);
        scrollView = (ScrollView) rootview.findViewById(R.id.SV_ScrollView_calendar);

//        MCV.state().edit()
//                .setFirstDayOfWeek(Calendar.MONDAY)
//                .setMinimumDate(CalendarDay.from(goalData.getYear_date_begin(), goalData.getMonth_date_begin(), goalData.getDay_date_begin()))
//                .setMaximumDate(CalendarDay.from(goalData.getYear_date_end()+100, 12, 31))
//                .setCalendarDisplayMode(CalendarMode.MONTHS)
//                .commit();

        MCV.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2017, 3, 1))
                .setMaximumDate(CalendarDay.from(2017, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();



        MCV.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

            }
        });


        return rootview;
    }

    public void addEvent(){
        HashSet<CalendarDay> event = new HashSet<CalendarDay>();
        event.add(CalendarDay.today());
        int myColor = R.color.colorRed;
        MCV.addDecorator(new EventDecorator(myColor, event));
    }

}
