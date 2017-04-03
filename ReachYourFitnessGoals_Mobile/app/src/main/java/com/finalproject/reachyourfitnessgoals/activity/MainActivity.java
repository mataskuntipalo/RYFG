package com.finalproject.reachyourfitnessgoals.activity;



import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.PagerAdapter;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.fragment;
import static android.R.attr.id;



public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ViewPager pager;
    PagerAdapter adapter;
    String PageName;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    handleTABLE_VDO handleTABLE_vdo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        handleTABLE_vdo = new handleTABLE_VDO(this);

        if(shared.getBoolean("firstTimeUsed", false) == false){
            downloadVDO();
        }

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        adapter = new PagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager_ViewPager_main);
        pager.setAdapter(adapter);

        for (int i = 0; i < adapter.PAGE_NUM; i++) {
            if(i == 0){
                PageName = "หน้าหลัก";
            }else if(i == 1){
                PageName = "การออกกำลังกาย";
            }else{
                PageName = "ท่าออกกำลังกาย";
            }
            actionBar.addTab(actionBar.newTab()
                    .setText(PageName)
                    .setTabListener(this));
        }

        // set tab bar change when swipe
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void downloadVDO() {
        String url = "http://192.168.1.35/ryfg/getJSON.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<vdoData> dataArrayList = new ArrayList<>();
                    Log.i("download",response.length()+"");
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        dataArrayList.add(new vdoData(jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getString("position"),jsonObject.getString("duration"),jsonObject.getInt("calorie")));
                    }
                    handleTABLE_vdo.addVdoExercise(dataArrayList);
                    editor.putBoolean("firstTimeUsed", true);
                    editor.commit();
                    Log.i("download","downloadFinish");
                }catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("download",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.i("download",error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }



    // set page change when swipe
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


}
