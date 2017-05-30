package com.finalproject.reachyourfitnessgoals.setting;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Papang on 31/5/2560.
 */

public class JsonSingleton {
    private static JsonSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private JsonSingleton(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized JsonSingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new JsonSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

}

