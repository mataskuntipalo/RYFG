package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

public class CustomSetExerciseInDayActivity extends Activity {

    private LinearLayout typeExeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_set_exercise_in_day);

        setLayout();


    }

    private void setLayout(){
        String[] typeExe = getResources().getStringArray(R.array.type_exe);
        for(int i = 1 ; i<typeExe.length ; i++){
            LinearLayout typeLayout = getTypeLayout(i);
            TextView typeText = (TextView) typeLayout.findViewById(R.id.typeName_text_itemView_typeExe);
            typeText.setText(typeExe[i]);
        }
    }

    private LinearLayout getTypeLayout(int number) {
        int id = typeExeLayout.getResources().getIdentifier(
                "type_" + number, "id",getPackageName());
        return (LinearLayout) typeExeLayout.findViewById(id);
    }
}
