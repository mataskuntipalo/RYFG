package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter_exeSummary;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.DetailExeListData;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.ListType;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_detailExe_inCalendar extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter_exeSummary recyclerViewAdapter;

    private handleTABLE_VDO handleTABLE_vdo;
    String[] id;

    public fragment_detailExe_inCalendar() {
        // Required empty public constructor
    }

    public static fragment_detailExe_inCalendar newInstance(ExerciseData exerciseData) {
        fragment_detailExe_inCalendar fragment = new fragment_detailExe_inCalendar();
        Bundle b = new Bundle();
        b.putParcelable("ExerciseData", Parcels.wrap(exerciseData));
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_detail_exe_in_calendar, container, false);

        handleTABLE_vdo = new handleTABLE_VDO(getActivity());



        recyclerViewAdapter = new RecyclerViewAdapter_exeSummary(getActivity(), addExeToList());
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_detailExe);
        RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(expMgr.createWrappedAdapter(recyclerViewAdapter));

        // NOTE: need to disable change animations to ripple effect work properly
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        expMgr.attachRecyclerView(recyclerView);


        return rootview;
    }

    public ArrayList<DetailExeListData> addExeToList(){
        ArrayList<DetailExeListData> list = new ArrayList<>();
        ArrayList<vdoData> datas = getVdoList();
        String[] typeExeArray = getResources().getStringArray(R.array.type_exe);
        for(int i = 0; i < 5 ; i++){
            list.add(new DetailExeListData(typeExeArray[i]));
        }

        for(int i = 0; i < datas.size() ; i++){
            int type = getTypeExe(datas.get(i).getType());
            if(i>datas.size()/2){
                if(type == 0){

                    list.get(4).addItem(datas.get(i));

                }else if(type == 1){

                    list.get(2).addItem(datas.get(i));

                }else {

                    list.get(3).addItem(datas.get(i));

                }
            }else {
                if(type == 0){

                    list.get(0).addItem(datas.get(i));

                }else if(type == 1){

                    list.get(2).addItem(datas.get(i));

                }else {

                    list.get(1).addItem(datas.get(i));

                }
            }
        }

        return list;
    }

    public ArrayList<vdoData> getVdoList(){
        ExerciseData data =  Parcels.unwrap(getArguments().getParcelable("ExerciseData"));
        id = data.getVdo_id().split(" ");
        ArrayList<vdoData> datas = new ArrayList<>();
        for(int i = 0; i < id.length; i++) {
            datas.add(handleTABLE_vdo.getVdoFromID(id[i]));
        }
        return datas;
    }

    private int getTypeExe(String type){
        int typeExe = 0;
        switch (type){
            case ExeType.TYPE_STRETCHING:
                typeExe = 0;
                break;
            case ExeType.TYPE_STRENGTH:
                typeExe = 1;
                break;
            case ExeType.TYPE_CARDIO:
                typeExe = 2;
                break;
        }
        return typeExe;
    }

}
