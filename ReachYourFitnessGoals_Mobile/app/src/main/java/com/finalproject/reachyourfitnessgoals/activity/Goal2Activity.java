package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.setting.CalculateShape;

public class Goal2Activity extends Activity {

    CalculateShape calculateShape;
    TextView percentFat;
    Button confirmBtn;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal2);

        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();

        calculateShape = new CalculateShape(this);
        percentFat = (TextView)findViewById(R.id.percentFat_TextView_goal2);
        confirmBtn = (Button)findViewById(R.id.confirm_Button_goal2);

        percentFat.setText(calculateShape.getPercentFat()+" %");

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(getResources().getString(R.string.sharedBoolLogIn), true);
                editor.commit();
                Intent intent = new Intent(Goal2Activity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
