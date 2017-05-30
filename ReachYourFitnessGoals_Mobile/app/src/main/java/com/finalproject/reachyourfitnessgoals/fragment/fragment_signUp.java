package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.ParQActivity;
import com.finalproject.reachyourfitnessgoals.models.PersonalData;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_signUp extends Fragment {

    private RelativeLayout selectBirthday;
    private Button confirm;
    private Calendar mCalendar;
    private TextView textBirthday;
    private EditText email , pass , f_name , l_name , weight , height;
    private RadioGroup groupRadioGender;
    private PersonalData personalData;
    int mYear,mMonth,mDay,gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_sign_up, container, false);

        personalData = new PersonalData();
        email = (EditText)rootview.findViewById(R.id.editEmail_EditText_signUP);
        pass  = (EditText)rootview.findViewById(R.id.editPass_EditText_signUP);
        f_name  = (EditText)rootview.findViewById(R.id.editName_EditText_signUP);
        l_name = (EditText)rootview.findViewById(R.id.editSurname_EditText_signUP);
        weight = (EditText)rootview.findViewById(R.id.editWeight_EditText_signUP);
        height = (EditText)rootview.findViewById(R.id.editHeight_EditText_signUP);



        groupRadioGender = (RadioGroup)rootview.findViewById(R.id.group_RadioButton_signUp);
        selectBirthday = (RelativeLayout) rootview.findViewById(R.id.content5_signUP);
        confirm = (Button) rootview.findViewById(R.id.confirm_Button_signUP);
        textBirthday = (TextView)rootview.findViewById(R.id.birthDay_Text_signUP);
        mCalendar = Calendar.getInstance();

        groupRadioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)rootview.findViewById(i);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectAns(v);
                    }
                });

            }
        });
        selectBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataToDataBase();
//                fragment_selectGoal selectGoal = fragment_selectGoal.newInstance();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager
//                        .beginTransaction()
////                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
//                        .replace(R.id.activity_login, selectGoal, "fragment_intro_parQ")
//                        .commit();
            }
        });

        return rootview;
    }

    private void selectAns(View v) {
        switch (v.getId()){
            case R.id.man_RadioButton_signUp :
                gender = 0;
                break;
            case R.id.women_RadioButton_signUp:
                gender = 1;
                break;
        }
    }

    private void setDataToDataBase(){
        Toast.makeText(getContext(), f_name.getText(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), l_name.getText(), Toast.LENGTH_SHORT).show();
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
        textBirthday.setTextColor(getResources().getColor(R.color.colorBlack));
    }


}
