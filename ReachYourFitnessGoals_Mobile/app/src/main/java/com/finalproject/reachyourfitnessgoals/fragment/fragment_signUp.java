package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.ParQActivity;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_signUp extends Fragment {


    private Button selectBirthday,confirm;
    private Calendar mCalendar;
    private TextView textBirthday;
    int mYear,mMonth,mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_sign_up, container, false);
        selectBirthday = (Button) rootview.findViewById(R.id.selectBirthDay_Button_signUP);
        confirm = (Button) rootview.findViewById(R.id.confirm_Button_signUP);
        textBirthday = (TextView)rootview.findViewById(R.id.birthDay_Text_signUP);
        mCalendar = Calendar.getInstance();


        selectBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ParQActivity.class);
                startActivity(intent);
            }
        });

        return rootview;
    }


    public void setDate(){
            new DatePickerDialog(getActivity(),AlertDialog.THEME_HOLO_LIGHT,onDateSetListener,mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mYear = year;
            mMonth = month+1;
            mDay = day;

            updateDate();
        }
    };

    public void updateDate(){
        textBirthday.setText(new StringBuffer().append(mDay).append("/").append(mMonth).append("/").append(mYear));
    }


}
