package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter_exeSummary;
import com.finalproject.reachyourfitnessgoals.models.ExeForGlobalData;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.ListType;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_exeSummary_ExpandView extends Fragment  {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_exeSummary exeSummaryAdapter;


    public fragment_exeSummary_ExpandView() {
        // Required empty public constructor
    }

    public static fragment_exeSummary_ExpandView newInstance() {
        fragment_exeSummary_ExpandView fragment = new fragment_exeSummary_ExpandView();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_exe_summary_expand_view, container, false);

        exeSummaryAdapter = new RecyclerViewAdapter_exeSummary(getActivity());

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView_exeSummary);
        RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(null);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(expMgr.createWrappedAdapter(exeSummaryAdapter));

        // NOTE: need to disable change animations to ripple effect work properly
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        expMgr.attachRecyclerView(mRecyclerView);


        return rootview;
    }

    public void updateView(){
        exeSummaryAdapter.notifyDataSetChanged();
    }

    public boolean allowBackPressed(){
        return true;
    }

}
