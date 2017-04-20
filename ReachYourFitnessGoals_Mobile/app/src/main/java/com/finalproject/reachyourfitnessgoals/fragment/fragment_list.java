package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.google.gson.Gson;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_list extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public static fragment_list newInstance() {
        fragment_list fragment = new fragment_list();
        return fragment;
    }


    public fragment_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_list, container, false);



        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_showAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),0);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(recyclerViewAdapter));


//        CustomListVDO_Adapter adapter = new CustomListVDO_Adapter(getActivity(), list, resId);
//        ListView listView = (ListView)rootview.findViewById(R.id.listview);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                fragment_VDO vdo = fragment_VDO.newInstance();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager
//                        .beginTransaction()
////                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
//                        .replace(R.id.activity_main, vdo, "fragment_list")
//                        .addToBackStack("fragment_list")
//                        .commit();
//
//            }
//        });
        return rootview;
    }

}
