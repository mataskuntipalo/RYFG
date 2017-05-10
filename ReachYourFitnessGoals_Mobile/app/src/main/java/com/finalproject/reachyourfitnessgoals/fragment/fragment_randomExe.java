package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_randomExe extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Button button;
    private Button confirmButton;

    public fragment_randomExe() {
        // Required empty public constructor
    }

    public static fragment_randomExe newInstance() {
        fragment_randomExe fragment = new fragment_randomExe();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_random_exe, container, false);

        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_randomExe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),2);
        recyclerView.setAdapter(recyclerViewAdapter);

        button = (Button)rootview.findViewById(R.id.randomAgain_Button_randomExe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewAdapter.randomExe();
            }
        });

        confirmButton = (Button) rootview.findViewById(R.id.confirm_Button_randomExe);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewAdapter.addToDataBaseRandomStep();
            }
        });


        return rootview;
    }

}
