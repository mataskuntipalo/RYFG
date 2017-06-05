package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PERSONAL;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_connect_server;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_intro_slideEnd;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_list;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_signUp;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.UrlServer;
import com.finalproject.reachyourfitnessgoals.setting.JsonSingleton;


import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences shared;
    SharedPreferences.Editor editor;

    RelativeLayout content1;
    RelativeLayout content2;
    Button buttonAbout;
    ProgressBar progress;
    TextView forgot;
    TextView signUp;
    EditText email , pass ;
    boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();

        if(shared.getBoolean(getResources().getString(R.string.sharedBoolLogIn), false) == true){
            goToMain();
        }


        //showIntro();
        email = (EditText)findViewById(R.id.editEmail_EditText_logIn);
        pass = (EditText)findViewById(R.id.editPass_EditText_logIn);
        signUp = (TextView)findViewById(R.id.signUp_text);
        forgot = (TextView)findViewById(R.id.forgetPass);
        progress = ( ProgressBar ) this.findViewById ( R.id.login_progress );
        content1 = (RelativeLayout)findViewById ( R.id.content1 );
        content2 = (RelativeLayout)findViewById ( R.id.content2 );
        buttonAbout = (Button) findViewById(R.id.loginButton);
        showView();

        if(getIntent().getExtras() != null){

        }else {
            fragment_intro_slideEnd goToIntro = fragment_intro_slideEnd.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_login, goToIntro);
            transaction.addToBackStack("goToIntro");
            transaction.commit();
        }

        buttonAbout.setOnClickListener(userLogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_signUp signUp = new fragment_signUp();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_login, signUp);
                transaction.commit();
            }
        });
    }

    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void goToLoadData(){
        fragment_connect_server connect_server = fragment_connect_server.newInstance(UrlServer.DOWNLOAD);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                .replace(R.id.activity_login, connect_server, "fragment_connect_server")
                .commit();
    }


    private void showView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // Hide your View after 3 seconds
                content1.setVisibility(View.VISIBLE);
                content2.setVisibility(View.VISIBLE);
                buttonAbout.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.VISIBLE);
                forgot.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }
        }, 2000);

    }

    private View.OnClickListener userLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlServer.LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.trim().equals("failure")){
                                Toast.makeText(LoginActivity.this,"email not correct",Toast.LENGTH_LONG).show();
                            }else{
                                editor.putBoolean(getResources().getString(R.string.sharedBoolLogIn), true);
                                editor.putString(getResources().getString(R.string.sharedStringMemberId), response.trim());
                                editor.commit();
                                goToLoadData();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"โปรดเชื่อมต่ออินเตอร์เน็ต",Toast.LENGTH_LONG ).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("username",email.getText().toString().trim());
                    map.put("password",pass.getText().toString().trim());
                    return map;
                }
            };
            JsonSingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest);
        }
    };



//    private void showIntro(){
//        //  Declare a new thread to do a preference check
////        Thread t = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                //  Initialize SharedPreferences
////                SharedPreferences getPrefs = PreferenceManager
////                        .getDefaultSharedPreferences(getBaseContext());
////
////                //  Create a new boolean and preference and set it to true
////                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
////
////                //  If the activity has never started before...
////                if (isFirstStart) {
//
//        //  Launch app intro
//        Intent i = new Intent(this, EndProgramActivity.class);
//        startActivity(i);
//
//                 /*   //  Make a new preferences editor
//                    SharedPreferences.Editor e = getPrefs.edit();
//
//                    //  Edit preference to make it false because we don't want this to run again
//                    e.putBoolean("firstStart", false);
//
//                    //  Apply changes
//                    e.apply();
//                }
//            }
//        });
//
//        // Start the thread
//        t.start();*/
//    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 2){
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,R.style.LightDialogTheme);
            builder.setMessage("ต้องการยกเลิกการสมัครสมาชิก");
            builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    getSupportFragmentManager().popBackStack();
                }
            });
            builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }else {
            super.onBackPressed();
        }

    }



    @Override // ต้องใส่อันนี้ถึงจะเปลี่ยนฟ้อน
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
