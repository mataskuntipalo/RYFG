package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter_userSelect;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_bottomSheet extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_userSelect recyclerViewAdapter_userSelect;
    public ArrayList<userSelectData> userSelectDataArrayList;


    public fragment_bottomSheet() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View rootview = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);

        userSelectDataArrayList = new ArrayList<>();
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));
        userSelectDataArrayList.add(new userSelectData("Pang",5));


        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_bottomSheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter_userSelect = new RecyclerViewAdapter_userSelect(userSelectDataArrayList);
        recyclerView.setAdapter(recyclerViewAdapter_userSelect);

        dialog.setContentView(rootview);
        mBehavior = BottomSheetBehavior.from((View) rootview.getParent());
        return dialog;
    }

}
