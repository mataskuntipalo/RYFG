package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.LoginActivity;
import com.finalproject.reachyourfitnessgoals.activity.ParQActivity;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PERSONAL;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.models.PersonalData;
import com.finalproject.reachyourfitnessgoals.models.UrlServer;
import com.finalproject.reachyourfitnessgoals.setting.JsonSingleton;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_signUp extends Fragment {

    SharedPreferences shared;
    SharedPreferences.Editor editor;
    private Button confirm;
    private EditText email , pass , f_name , l_name , age , weight , height;
    private String strEmail , strPass , strFName , strLName , strAge , strWeight , strHeight , strGender;
    private double activity;
    private RadioGroup groupRadioGender;
    private Spinner spinner;
    private PersonalData personalData;
    private static final String[]paths = {"ไม่ได้ออกกำลังกายเลย", "ออกกำลังกายเล็กน้อย 1-3 วัน", "ออกกำลังกายเปานกลาง 3-5 วัน","ออกกำลังกายอย่างหนัก 6-7 วัน","ออกกำลังกายอย่างหนักทุกวันเช้าเย็น"};
    int gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_sign_up, container, false);

        shared = getActivity().getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();


        email = (EditText)rootview.findViewById(R.id.editEmail_EditText_signUP);
        pass  = (EditText)rootview.findViewById(R.id.editPass_EditText_signUP);
        f_name  = (EditText)rootview.findViewById(R.id.editName_EditText_signUP);
        l_name = (EditText)rootview.findViewById(R.id.editSurname_EditText_signUP);
        age = (EditText)rootview.findViewById(R.id.editAge_EditText_signUP);
        weight = (EditText)rootview.findViewById(R.id.editWeight_EditText_signUP);
        height = (EditText)rootview.findViewById(R.id.editHeight_EditText_signUP);



        groupRadioGender = (RadioGroup)rootview.findViewById(R.id.group_RadioButton_signUp);
        confirm = (Button) rootview.findViewById(R.id.confirm_Button_signUP);
        //textBirthday = (TextView)rootview.findViewById(R.id.birthDay_Text_signUP);

        gender = 0;
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

        spinner = (Spinner)rootview.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        activity = 1.2;
                        break;
                    case 1:
                        activity = 1.375;
                        break;
                    case 2:
                        activity = 1.55;
                        break;
                    case 3:
                        activity = 1.725;
                        break;
                    case 4:
                        activity = 1.9;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                activity = 1.2;
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpData();
                ((GlobalData)getActivity().getApplication()).setEmail(strEmail);
                ((GlobalData)getActivity().getApplication()).setPass(strPass);
                fragment_intro_parQ intro_parQ = fragment_intro_parQ.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_login, intro_parQ, "fragment_intro_parQ")
                        .commit();
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

    private void setUpData() {
        strEmail = email.getText().toString().trim();
        strPass = pass.getText().toString().trim();
        strFName = f_name.getText().toString().trim();
        strLName = l_name.getText().toString().trim();
        strGender = String.valueOf(gender);
        strAge = age.getText().toString().trim();
        strWeight = weight.getText().toString().trim();
        strHeight = height.getText().toString().trim();
        Log.i("signUp",strGender);
        personalData = new PersonalData(strFName,strLName,
                Integer.parseInt(strAge),
                gender,
                Double.parseDouble(strWeight),
                Double.parseDouble(strHeight),
                activity);
        ((GlobalData)getActivity().getApplication()).setPersonalData(personalData);
    }

}
