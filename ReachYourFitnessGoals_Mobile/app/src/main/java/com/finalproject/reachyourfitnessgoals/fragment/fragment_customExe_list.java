package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter_userSelect;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_customExe_list extends Fragment{


    private static String KEY_TYPE;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView recyclerViewBottomSheet;
    private RecyclerViewAdapter_userSelect recyclerViewAdapterBottomSheet;
    private ArrayList<userSelectData> userSelectDataArrayList;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView maxCalorie;
    private TextView calorie;
    private Button confirmButton;
    private int tempMaxCalorie;
    private int tempCalorie;

    private handleTABLE_E




    public fragment_customExe_list() {
        // Required empty public constructor
    }

    public static fragment_customExe_list newInstance(String type) {
        fragment_customExe_list fragment = new fragment_customExe_list();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_custom_exe_list, container, false);

        initBottomSheet(rootview);



        //final LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.showExe_customExe);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_customExe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),getArguments().getString(KEY_TYPE));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name, String calorie) {

                if(tempCalorie >= tempMaxCalorie){
                    check();
                }else {
                    selectExe(name);
                    addCalorie(calorie);
                }

                //new fragment_bottomSheet().show(getFragmentManager(), "dialog");
            }

            @Override
            public void onItemLongClick() {
                //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return rootview;
    }

    private void selectExe(String name) {
        recyclerViewAdapterBottomSheet.addExe(name);
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void initBottomSheet(View rootview){
        userSelectDataArrayList = new ArrayList<>();
        bottomSheetBehavior = BottomSheetBehavior.from(rootview.findViewById(R.id.layout_bottomSheet));
        calorie = (TextView)rootview.findViewById(R.id.calorie_TextView_bottomSheet);
        maxCalorie = (TextView)rootview.findViewById(R.id.maxCalorie_TextView_bottomSheet);
        confirmButton = (Button)rootview.findViewById(R.id.confirm_Button_bottomSheet);
        recyclerViewBottomSheet = (RecyclerView)rootview.findViewById(R.id.recyclerView_bottomSheet);
        recyclerViewBottomSheet.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapterBottomSheet = new RecyclerViewAdapter_userSelect(userSelectDataArrayList);
        recyclerViewBottomSheet.setAdapter(recyclerViewAdapterBottomSheet);
        setMaxCalorie();
    }

    private void setMaxCalorie(){
        tempMaxCalorie = 20;
        maxCalorie.setText("20");
    }

    private void addCalorie(String calorie){
        tempCalorie = tempCalorie + Integer.parseInt(calorie);
        if(tempCalorie >= tempMaxCalorie){
            check();
        }else {
            //tempCalorie = tempCalorie + Integer.parseInt(calorie);
        }
        this.calorie.setText(tempCalorie+"");
    }

    private void check(){
        confirmButton.setVisibility(View.VISIBLE);
    }

}
