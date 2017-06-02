package com.finalproject.reachyourfitnessgoals.activity;



import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.PagerAdapter;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_connect_server;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_list;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.UrlServer;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.finalproject.reachyourfitnessgoals.setting.JsonSingleton;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.fragment;
import static android.R.attr.id;
import static com.github.florent37.materialviewpager.R.attr.viewpager_hideToolbarAndTitle;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MaterialViewPager mViewPager;
    private ResideMenu resideMenu;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    FrameLayout ignored_view;
    public final int PAGE_NUM = 3;
    private CustomizableResideMenuItem itemHome;
    private CustomizableResideMenuItem itemProfile;
    private CustomizableResideMenuItem itemLogOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();

        setUpMenuSideBar();


        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager_main);



        Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                }
            });
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }


        mViewPager.getViewPager().setAdapter(StatePagerAdapter);
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        resideMenu.clearIgnoredViewList();
                        return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.colorBlack),getResources().getDrawable(R.drawable.background_main1));
                    case 1:
                        resideMenu.addIgnoredView(ignored_view);
                        return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.colorBlack),getResources().getDrawable(R.drawable.background_main2));
                    case 2:
                        resideMenu.addIgnoredView(ignored_view);
                        return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.colorBlack),getResources().getDrawable(R.drawable.background_main3));
                }


                return null;
            }
        });

    }

    private void setUpMenuSideBar(){
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.background_sidemenu);
        resideMenu.attachToActivity(this);
        ignored_view = (FrameLayout) findViewById(R.id.ignored_view);

        itemHome     = new CustomizableResideMenuItem(this, R.drawable.ic_username,     "Home");
        itemProfile  = new CustomizableResideMenuItem(this, R.drawable.ic_username,  "Profile");
        itemLogOut = new CustomizableResideMenuItem(this, R.drawable.ic_username, "LogOut");


        itemLogOut.setOnClickListener(this);


        itemProfile.setTypeface();
        itemLogOut.setTypeface();

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLogOut, ResideMenu.DIRECTION_LEFT);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
    }

    @Override
    public void onClick(View v) {
        if (v == itemLogOut){
            goToLoadData();
        }
    }

    private void goToLoadData(){
        fragment_connect_server resultParQ = fragment_connect_server.newInstance(UrlServer.UPLOAD);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                .replace(R.id.activity_main, resultParQ, "fragment_results_parQ")
                .commit();
    }

    FragmentStatePagerAdapter StatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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
    };



    @Override // ต้องใส่อันนี้ถึงจะเปลี่ยนฟ้อน
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }




    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){
            ((GlobalData)getApplication()).resetData();
        }
        super.onBackPressed();
    }


    public class CustomizableResideMenuItem extends ResideMenuItem {

        public CustomizableResideMenuItem(Context context) {
            super(context);
        }

        public CustomizableResideMenuItem(Context context, int icon, int title) {
            super(context, icon, title);
        }

        public CustomizableResideMenuItem(Context context, int icon, String title) {
            super(context, icon, title);
        }

        public void setTypeface() {
            try {
                // getting type of the field from superClass
                Field privateTextView = ResideMenuItem.class.getDeclaredField("tv_title");
                // transform this field to public
                privateTextView.setAccessible(true);
                // getting value from this field which is reference to a TextView
                TextView tv = (TextView)privateTextView.get(this);
                //finaly setting the Typface
                tv.setTextSize(36);
                tv.setTextColor(getResources().getColor(R.color.colorBlack));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


    }

}
