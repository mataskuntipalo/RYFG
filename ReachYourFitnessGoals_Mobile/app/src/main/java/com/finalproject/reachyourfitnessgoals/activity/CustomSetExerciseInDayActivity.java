package com.finalproject.reachyourfitnessgoals.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_customExe_list;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_exeSummary_ExpandView;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.setting.SetUpCalorieAndExe;

import static android.R.attr.fragment;
import static android.R.attr.visibility;

public class CustomSetExerciseInDayActivity extends AppCompatActivity {

    private LinearLayout typeExeLayout;
    private LinearLayout exeSummary_layout;
    fragment_exeSummary_ExpandView expandView;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    private TextView calorie;
    private TextView maxCalorie;
    private int totalCalorie;
    private int tempMaxCalorie;
    private Button confirmButton;
    private SetUpCalorieAndExe setUpCalorieAndExe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_set_exercise_in_day);

        setUpCalorieAndExe = new SetUpCalorieAndExe(this);
        typeExeLayout = (LinearLayout)findViewById(R.id.typeExeLayout_LinearLayout_customSetExe);
        exeSummary_layout = (LinearLayout)findViewById(R.id.exeSummary_layout);
        calorie = (TextView)findViewById(R.id.calorie_TextView_customSetExe);
        maxCalorie = (TextView)findViewById(R.id.maxCalorieInDay_TextView_customSetExe);
        confirmButton = (Button) findViewById(R.id.confirm_Button_bottomSheet_customSetExe);
        setLayout();
        tempMaxCalorie = setUpCalorieAndExe.getMaxCalorieInDay();
        maxCalorie.setText(tempMaxCalorie+"");

       expandView = fragment_exeSummary_ExpandView.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.exeSummary_layout, expandView,TAG_FRAGMENT);
        transaction.commit();


    }

    private void setLayout(){
        String[] typeExe = getResources().getStringArray(R.array.type_exe);
        Log.i("typeExe.length",typeExe.length +"");
        for(int i = 0 ; i<typeExe.length ; i++){
            LinearLayout typeLayout = getTypeLayout(i);
            typeLayout.setTag(ExeType.TYPE[i]);
            TextView typeText = (TextView) typeLayout.findViewById(R.id.typeName_text_itemView_typeExe);
            typeText.setText(typeExe[i]);
            typeLayout.setOnClickListener(selectExe);
        }
    }

    private LinearLayout getTypeLayout(int number) {
        int id = typeExeLayout.getResources().getIdentifier(
                "type_" + number, "id",getPackageName());
        return (LinearLayout) typeExeLayout.findViewById(id);
    }

    private View.OnClickListener selectExe = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragment_customExe_list custom = fragment_customExe_list.newInstance(v.getTag()+"",v.getId());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_set_exercise_in_day, custom);
            transaction.addToBackStack("custom");
            transaction.commit();
        }
    };

    @Override
    public void onBackPressed() {
        expandView = (fragment_exeSummary_ExpandView) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);

        if (expandView.allowBackPressed()) {
            super.onBackPressed();
            expandView.updateView();
            getTotalCalorie();
            calorie.setText(totalCalorie+"");
        }
    }


    public void getTotalCalorie(){
        int size = setUpCalorieAndExe.getExeDataList().size();
        totalCalorie = 0;
        for(int i = 0 ; i<size ; i++){
            totalCalorie = totalCalorie + setUpCalorieAndExe.getExeDataList().get(i).getCalorieInType();
        }
        checkConfirm();
    }

    public void checkConfirm(){
        if(totalCalorie >= tempMaxCalorie){
            confirmButton.setVisibility(View.VISIBLE);
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUpCalorieAndExe.addExeInDay(totalCalorie);
                }
            });
        }
    }

}
