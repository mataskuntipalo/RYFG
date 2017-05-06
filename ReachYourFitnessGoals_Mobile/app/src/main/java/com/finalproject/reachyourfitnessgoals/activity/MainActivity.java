package com.finalproject.reachyourfitnessgoals.activity;



import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;


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
import com.finalproject.reachyourfitnessgoals.fragment.fragment_list;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.fragment;
import static android.R.attr.id;
import static com.github.florent37.materialviewpager.R.attr.viewpager_hideToolbarAndTitle;


public class MainActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    handleTABLE_VDO handleTABLE_vdo;
    public final int PAGE_NUM = 3;



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

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager_main);

        Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        //pager = mViewPager.getViewPager();
        //pager.setAdapter(new RecyclerViewMaterialAdapter(new PagerAdapter(getSupportFragmentManager())));

//        mViewPager.getToolbar().setVisibility(View.GONE);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()){

            @Override
            public int getCount() {
                return PAGE_NUM;
            }

            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new fragment_home().newInstance();
                    case 1:
                        return new fragment_calendar().newInstance();
                    case 2:
                        Log.i("download","downloadFinish1");
                        return new fragment_list().newInstance();
                    default:
                        return null;
                }

            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % PAGE_NUM) {
                    case 0:
                        return "หน้าหลัก";
                    case 1:
                        return "การออกกำลังกาย";
                    case 2:
                        return "ท่าออกกำลังกาย";
                }
                return "";
            }
        });






        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());


//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


//        adapter = new PagerAdapter(getSupportFragmentManager());
//        //pager = (ViewPager) findViewById(R.id.pager_ViewPager_main);
//        pager = mViewPager.getViewPager();
//        pager.setAdapter(adapter);
//        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

//        for (int i = 0; i < adapter.PAGE_NUM; i++) {
//            if(i == 0){
//                PageName = "หน้าหลัก";
//            }else if(i == 1){
//                PageName = "การออกกำลังกาย";
//            }else{
//                PageName = "ท่าออกกำลังกาย";
//            }
//            actionBar.addTab(actionBar.newTab()
//                    .setText(PageName)
//                    .setTabListener(this));
//        }

        // set tab bar change when swipe
//        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                getSupportActionBar().setSelectedNavigationItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorBlack,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorBlack,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorBlack,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
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


    @Override // ต้องใส่อันนี้ถึงจะเปลี่ยนฟ้อน
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



//    // set page change when swipe
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        pager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }
//
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }


}
