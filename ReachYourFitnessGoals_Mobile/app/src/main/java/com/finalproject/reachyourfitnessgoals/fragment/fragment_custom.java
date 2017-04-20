package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_custom extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


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

        final LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.showExe_customExe);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_customExe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),1);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name) {
                layout.addView(createNewTextView(name));
            }
        });





        return rootview;
    }

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(getContext());
        textView.setLayoutParams(lparams);
        textView.setText(text + " ");
        return textView;
    }

}
