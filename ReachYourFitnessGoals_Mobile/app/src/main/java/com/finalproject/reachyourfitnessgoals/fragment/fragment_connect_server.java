package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.finalproject.reachyourfitnessgoals.BuildConfig;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.LoginActivity;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PERSONAL;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.ExerciseFromServerData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.models.ListType;
import com.finalproject.reachyourfitnessgoals.models.PersonalData;
import com.finalproject.reachyourfitnessgoals.models.UrlServer;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.finalproject.reachyourfitnessgoals.setting.JsonSingleton;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.FillableLoaderBuilder;
import com.github.jorgecastillo.clippingtransforms.PlainClippingTransform;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.github.jorgecastillo.library.R.dimen.strokeWidth;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_connect_server extends Fragment {


    FillableLoader fillableLoader;
    String[] json_key;
    String member_id;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    handleTABLE_VDO tableVdo;
    handleTABLE_PERSONAL tablePersonal;
    handleTABLE_PROGRAM tableProgram;
    handleTABLE_EXERCISE tableExercise;
    TextView textView;
    ProgressDialog progressDialog;


    public fragment_connect_server() {
        // Required empty public constructor
    }

    public static fragment_connect_server newInstance(int state) {
        fragment_connect_server fragment = new fragment_connect_server();
        Bundle bundle = new Bundle();
        bundle.putInt("state",state);
        fragment.setArguments(bundle);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_connect_server, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        textView = (TextView) rootview.findViewById(R.id.jsonText);

        shared = getActivity().getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        member_id = shared.getString(getResources().getString(R.string.sharedStringMemberId),"0");


        tableVdo = new handleTABLE_VDO(getContext());
        tablePersonal = new handleTABLE_PERSONAL(getContext());
        tableProgram = new handleTABLE_PROGRAM(getContext());
        tableExercise = new handleTABLE_EXERCISE(getContext());

        if(getArguments().getInt("state") == UrlServer.DOWNLOAD){
            download();
        }else{
            upload();
        }


        return rootview;
    }

    private void download(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("member_id", member_id);
        json_key = getResources().getStringArray(R.array.json_key);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlServer.GETJSON, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<vdoData> vdoDataArrayList = new ArrayList<>();
                        PersonalData personalData = null;
                        ArrayList<GoalData> goalDataArrayList = new ArrayList<>();
                        ArrayList<ExerciseFromServerData> exerciseFromServerDatas = new ArrayList<>();
                        try {
                            Gson gson = new Gson();
                            for(int i = 0; i < response.length(); i++){
                                final JSONArray jsonArray = response.getJSONArray(json_key[i]);
                                for (int j = 0 ; j<jsonArray.length() ; j++){
                                    if(i==0){
                                        vdoDataArrayList.add(gson.fromJson(String.valueOf(jsonArray.getJSONObject(j)), vdoData.class));
                                        final String nameExe = jsonArray.getJSONObject(j).getString("name");
                                        Glide
                                                .with(getContext())
                                                .load("http://172.25.84.26/ryfg/image/" + nameExe + ".jpg")
                                                .asBitmap()
                                                .toBytes(Bitmap.CompressFormat.JPEG, 80)
                                                .into(new SimpleTarget<byte[]>() {
                                                          @Override public void onResourceReady(final byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                                                              File sdcard = Environment.getExternalStorageDirectory();
                                                              File file = new File(sdcard + File.separator  + "Android" + File.separator + "data" + File.separator + getContext().getPackageName() + File.separator + "image" + File.separator + nameExe+ ".jpg");
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
                                    }else if(i==1){
                                        personalData = gson.fromJson(String.valueOf(jsonArray.getJSONObject(j)), PersonalData.class);
                                    }else if(i==2){
                                        goalDataArrayList.add(gson.fromJson(String.valueOf(jsonArray.getJSONObject(j)), GoalData.class));
                                    }else if(i==3){
                                        exerciseFromServerDatas.add(gson.fromJson(String.valueOf(jsonArray.getJSONObject(j)), ExerciseFromServerData.class));
                                        Log.i("exerciseData0",jsonArray.getJSONObject(j).get("check_state_workout").toString());
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tableVdo.addVdoExercise(vdoDataArrayList);
                        tableProgram.addProgramList(goalDataArrayList);
                        tablePersonal.addPersonal(personalData);
                        tableExercise.addExerciseList(exerciseFromServerDatas);
                        showView();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"โปรดเชื่อมต่ออินเตอร์",Toast.LENGTH_LONG ).show();
                    }
                });
        JsonSingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void showView() {
        int typeGoal = getTypeGoal();
        Handler handler = new Handler();
        while (typeGoal == -1){
            final int finalTypeGoal = typeGoal;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("typeGal", finalTypeGoal +"");
                    // Hide your View after 3 seconds
                }
            }, 1000);
            typeGoal = getTypeGoal();
        }
        progressDialog.dismiss();
        goToMain();
    }

    private int getTypeGoal(){
        GoalData goalData = new handleTABLE_PROGRAM(getContext()).getCurrentProgramDate();
        return goalData.getTypeGoal();
    }

    private void upload() {
        Gson gson = new Gson();
        Map<String, String> params = new HashMap<String, String>();
        params.put("member_id",member_id);
        params.put("personal",gson.toJson(tablePersonal.getPersonal()));
        params.put("program",gson.toJson(tableProgram.getProgramDateList()));
        params.put("exercise",gson.toJson(tableExercise.getExerciseData()));
        textView.setText(new JSONObject(params).toString());

        JsonObjectRequest jsonObjectRequestUpload = new JsonObjectRequest(Request.Method.POST,UrlServer.SENDJSON,new JSONObject(params),new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("complete").equals("uploadComplete")){
                        Log.i("uploadComplete",response.toString());
                        goToLogin();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG ).show();
            }
        });
        JsonSingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequestUpload);
        editor.putBoolean(getResources().getString(R.string.sharedBoolLogIn), false);
        editor.putBoolean(getResources().getString(R.string.sharedBoolSetExe), false);
        editor.commit();
        clearData();
    }

    private void goToLogin(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void clearData() {
        tableVdo.delete();
        tablePersonal.delete();
        tableProgram.delete();
        tableExercise.delete();
    }

    private void goToMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
