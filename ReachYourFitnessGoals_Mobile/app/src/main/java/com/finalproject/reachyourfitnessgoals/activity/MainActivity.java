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
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.PagerAdapter;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_list;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
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


public class MainActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;
    private ResideMenu resideMenu;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    handleTABLE_VDO handleTABLE_vdo;
    FrameLayout ignored_view;
    public final int PAGE_NUM = 3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        shared = this.getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        handleTABLE_vdo = new handleTABLE_VDO(this);

        if(shared.getBoolean("firstTimeUsed", false) == false){
            downloadVDO(this);
        }

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.background_sidemenu);
        resideMenu.attachToActivity(this);
        ignored_view = (FrameLayout) findViewById(R.id.ignored_view);

        String titles[] = { "Home", "Profile"};
        int icon[] = { R.drawable.ic_username, R.drawable.ic_username};

        for (int i = 0; i < titles.length; i++){
            //item.setOnClickListener(this);
            CustomizableResideMenuItem item = new CustomizableResideMenuItem(this, icon[i], titles[i]);
            item.setTypeface();
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }


        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

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

    private void downloadVDO(final Context context) {
        String url = "http://192.168.1.35/ryfg/getJSON.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<vdoData> dataArrayList = new ArrayList<>();
                    Log.i("download",response.length()+"");
                    for(int i = 0; i < response.length(); i++){
                        final JSONObject jsonObject = response.getJSONObject(i);
                        dataArrayList.add(new vdoData(jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getString("position"),jsonObject.getString("duration"),jsonObject.getInt("calorie")));
                        final String nameExe = jsonObject.getString("name");
                        Glide
                                .with(context)
                                .load("http://192.168.1.35/ryfg/image/" + nameExe + ".jpg")
                                .asBitmap()
                                .toBytes(Bitmap.CompressFormat.JPEG, 80)
                                .into(new SimpleTarget<byte[]>() {
                                    @Override public void onResourceReady(final byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
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
