package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_custom extends Fragment {


    String[] str = {"a","b","c","d"};


    public fragment_custom() {
        // Required empty public constructor
    }

    public static fragment_custom newInstance() {
        fragment_custom fragment = new fragment_custom();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_custom_exe, container, false);




        return rootview;
    }

}
