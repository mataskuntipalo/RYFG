package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PERSONAL;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_selectGoal;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.ExerciseFromServerData;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.models.PersonalData;
import com.finalproject.reachyourfitnessgoals.models.UrlServer;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.finalproject.reachyourfitnessgoals.setting.CalculateShape;
import com.finalproject.reachyourfitnessgoals.setting.JsonSingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Goal2Activity extends AppCompatActivity {

    CalculateShape calculateShape;
    TextView percentFat;
    Button confirmBtn;
    GoalData goalData;
    RadioGroup groupRadio;
    EditText programName;
    private handleTABLE_PROGRAM handleTableProgram;
    SharedPreferences shared;
    PersonalData personalData;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal2);

        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();

        personalData = ((GlobalData)this.getApplication()).getPersonalData();

        handleTableProgram = new handleTABLE_PROGRAM(this);
        calculateShape = new CalculateShape(this);
        percentFat = (TextView)findViewById(R.id.percentFat_TextView_goal2);
        confirmBtn = (Button)findViewById(R.id.confirm_Button_goal2);
        groupRadio = (RadioGroup)findViewById(R.id.group_RadioButton_goal2);
        programName = (EditText)findViewById(R.id.programName_EditText_goal2);

        percentFat.setText(calculateShape.getPercentFat()+" %");

        goalData = new GoalData();

        setUpData();

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)findViewById(i);
                selectKgPerWeek(radioButton);
            }
        });


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalData.setProgramName(programName.getText().toString().trim());
                handleTableProgram.addProgram(goalData);
                setDataToServer();
                Intent intent = new Intent(Goal2Activity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        new handleTABLE_PERSONAL(this).addPersonal(personalData);

    }

    public void selectKgPerWeek(View v){
        switch (v.getId()){
            case R.id.two_RadioButton_goal:
                goalData.setKgPerWeek(1540);
                Log.i("test","2");
                break;
            case R.id.five_RadioButton_goal:
                goalData.setKgPerWeek(3850);
                Log.i("test","5");
                break;
            case R.id.seven_RadioButton_goal:
                goalData.setKgPerWeek(5390);
                Log.i("test","7");
                break;
        }
    }

    private void setUpData() {
        goalData.setTypeGoal(ExeType.TYPE_PROGRAM_MUSCLE);
        goalData.setKgPerWeek(1540);
        goalData.setPercentFat(calculateShape.getPercentFat());
        calDateOfProgram();
        goalData.setStatus(1);
    }

    public void calDateOfProgram(){
        Calendar thaiTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+07:00"));
        int year_begin = thaiTime.get(Calendar.YEAR);
        int month_begin = thaiTime.get(Calendar.MONTH);
        int day_begin = thaiTime.get(Calendar.DAY_OF_MONTH);

        goalData.setYear_date_begin(year_begin);
        goalData.setMonth_date_begin(month_begin);
        goalData.setDay_date_begin(day_begin);


//        int tempDateEnd = goalData.getTotalDuration() * 7;
//        thaiTime.add(Calendar.DATE,tempDateEnd);


    }

    private void setDataToServer(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlServer.SINGUP,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Could not register")){
                    Toast.makeText(Goal2Activity.this,"ไม่สามารถสมัครสมาชิกได้ โปรดลองใหม่อีกครั้ง",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Goal2Activity.this,"สมัครสมาชิกสำเร็จ",Toast.LENGTH_LONG).show();
                    editor.putBoolean(getResources().getString(R.string.sharedBoolLogIn), true);
                    editor.putString(getResources().getString(R.string.sharedStringMemberId), response.trim());
                    editor.commit();
                    downloadVDO();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Goal2Activity.this,"โปรดเชื่อมต่ออินเตอร์เน็ต",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("email",((GlobalData)Goal2Activity.this.getApplication()).getEmail());
                map.put("pass",((GlobalData)Goal2Activity.this.getApplication()).getPass());
                map.put("f_name",personalData.getF_name());
                map.put("l_name",personalData.getL_name());
                map.put("gender",personalData.getGender()+"");
                map.put("age",personalData.getAge()+"");
                map.put("weight",personalData.getWeight()+"");
                map.put("height",personalData.getHeight()+"");
                return map;
            }
        };

        JsonSingleton.getInstance(Goal2Activity.this).addToRequestQueue(stringRequest);
    }

    private void downloadVDO() {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, UrlServer.GETJSONVDO, null, new Response.Listener<JSONArray>() {

                     @Override
             public void onResponse(JSONArray response) {
                                try {
                                    ArrayList<vdoData> dataArrayList = new ArrayList<>();
                                        for(int i = 0; i < response.length(); i++){
                                                final JSONObject jsonObject = response.getJSONObject(i);
                                                dataArrayList.add(new vdoData(jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getString("position"),jsonObject.getString("duration"),jsonObject.getInt("calorie")));
                                                final String nameExe = jsonObject.getString("name");
                                                Glide
                                                               .with(getBaseContext())
                                                                .load("http://192.168.1.35/ryfg/image/" + nameExe + ".jpg")
                                                                .asBitmap()
                                                                .toBytes(Bitmap.CompressFormat.JPEG, 80)
                                                                .into(new SimpleTarget<byte[]>() {
                                                                                @Override
                                                                                public void onResourceReady(final byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                                                                                File sdcard = Environment.getExternalStorageDirectory();
                                                                                File file = new File(sdcard + File.separator  + "Android" + File.separator + "data" + File.separator + getPackageName() + File.separator + "image" + File.separator + nameExe+ ".jpg");
                                                                                File dir = file.getParentFile();
                                                                                try {
                                                                                    if (!dir.mkdirs() && (!dir.exists() || !dir.isDirectory())) {
                                                                                              throw new IOException("Cannot ensure parent directory for file " + file);
                                                                                    }
                                                                                        BufferedOutputStream s = new BufferedOutputStream(new FileOutputStream(file));
                                                                                        s.write(resource);
                                                                                        s.flush();
                                                                                        s.close();
                                                                                    } catch (IOException e) {
                                                                                        e.printStackTrace();
                                                                                    }
                                                                            }
                                     }
                                                                );
                                            }
                                        new handleTABLE_VDO(Goal2Activity.this).addVdoExercise(dataArrayList);
                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.i("download",e.getMessage());
                                    }
                            }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

                                    }
         });

                        JsonSingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
            }
}
