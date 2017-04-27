package com.finalproject.reachyourfitnessgoals.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_customExe_list;
import com.finalproject.reachyourfitnessgoals.models.ExeType;

public class CustomSetExerciseInDayActivity extends AppCompatActivity {

    private LinearLayout typeExeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_set_exercise_in_day);

        typeExeLayout = (LinearLayout)findViewById(R.id.typeExeLayout_LinearLayout_customSetExe);
        setLayout();


    }

    private void setLayout(){
        String[] typeExe = getResources().getStringArray(R.array.type_exe);
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
            fragment_customExe_list custom = fragment_customExe_list.newInstance(v.getTag()+"");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_set_exercise_in_day, custom);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };
}
