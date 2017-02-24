package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.finalproject.reachyourfitnessgoals.R;
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

        MCV = (MaterialCalendarView) rootview.findViewById(R.id.datePicker_DatePicker_calendar);

        MCV.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2016, 4, 3))
                .setMaximumDate(CalendarDay.from(2017, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        HashSet<CalendarDay> type2 = new HashSet<CalendarDay>();
        type2.add(CalendarDay.today());
        int myColor = R.color.colorRed;
        MCV.addDecorator(new EventDecorator(myColor, type2));

        MCV.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

            }
        });



        return rootview;
    }


}
