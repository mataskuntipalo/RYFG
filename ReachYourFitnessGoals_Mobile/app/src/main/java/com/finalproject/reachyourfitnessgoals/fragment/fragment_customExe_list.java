package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter;
import com.finalproject.reachyourfitnessgoals.adapter.RecyclerViewAdapter_userSelect;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.models.ExeForGlobalData;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.setting.SetUpCalorieAndExe;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_customExe_list extends Fragment{


    private static String KEY_TYPE = "type";
    private static String KEY_ID = "id";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView recyclerViewBottomSheet;
    private RecyclerViewAdapter_userSelect recyclerViewAdapterBottomSheet;
    private ArrayList<userSelectData> userSelectDataArrayList;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView maxCalorieText;
    private TextView calorieText;
    private Button confirmButton;
    private int tempMaxCalorie;
    private int tempCalorie;
    private int ExeForGlobalData_id;
    private SetUpCalorieAndExe setUpCalorieAndExe;




    public fragment_customExe_list() {
        // Required empty public constructor
    }

    public static fragment_customExe_list newInstance(String type , int id) {
        fragment_customExe_list fragment = new fragment_customExe_list();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TYPE, type);
        bundle.putInt(KEY_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_custom_exe_list, container, false);


        setUpCalorieAndExe = new SetUpCalorieAndExe(getActivity());
        initBottomSheet(rootview);
        ExeForGlobalData_id = setUpCalorieAndExe.getExeForGlobalDataId(getArguments().getInt(KEY_ID));




        //final LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.showExe_customExe);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView_customExe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),getArguments().getString(KEY_TYPE));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, String name, String calorie , String vdo_id) {
                if(tempCalorie >= tempMaxCalorie){
                    check();
                }else {
                    selectExe(name,vdo_id,Integer.parseInt(calorie));
                    addCalorie(calorie);
                }
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

    private void selectExe(String name,String vdo_id,int calorie) {
        recyclerViewAdapterBottomSheet.addExe(name,vdo_id,calorie);
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void initBottomSheet(View rootview){
        userSelectDataArrayList = new ArrayList<>();
        bottomSheetBehavior = BottomSheetBehavior.from(rootview.findViewById(R.id.layout_bottomSheet));
        calorieText = (TextView)rootview.findViewById(R.id.calorie_TextView_bottomSheet);
        maxCalorieText = (TextView)rootview.findViewById(R.id.maxCalorie_TextView_bottomSheet);
        maxCalorieText.setText(setUpCalorieAndExe.getMaxCalorieForEachStep(getArguments().getString(KEY_TYPE),getArguments().getInt(KEY_ID))+"");
        tempMaxCalorie = setUpCalorieAndExe.getMaxCalorieForEachStep(getArguments().getString(KEY_TYPE),getArguments().getInt(KEY_ID));
        confirmButton = (Button)rootview.findViewById(R.id.confirm_Button_bottomSheet);
        recyclerViewBottomSheet = (RecyclerView)rootview.findViewById(R.id.recyclerView_bottomSheet);
        recyclerViewBottomSheet.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapterBottomSheet = new RecyclerViewAdapter_userSelect(userSelectDataArrayList,getContext());
        recyclerViewBottomSheet.setAdapter(recyclerViewAdapterBottomSheet);
        recyclerViewAdapterBottomSheet.SetOnItemDeleteListener(new RecyclerViewAdapter_userSelect.OnItemClickListener() {

            @Override
            public void onDeleteClick(int calorie, int position) {
                deleteCalorie(calorie);
                recyclerViewAdapterBottomSheet.DeleteExe(position);
            }
        });
    }


    private void addCalorie(String calorie){
        tempCalorie = tempCalorie + Integer.parseInt(calorie);
        if(tempCalorie >= tempMaxCalorie){
            check();
        }else {

        }
        this.calorieText.setText(tempCalorie+"");
    }

    private void deleteCalorie(int calorie){
        tempCalorie = tempCalorie - calorie;
        this.calorieText.setText(tempCalorie+"");
    }

    private void check(){
        confirmButton.setVisibility(View.VISIBLE);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalData)getActivity().getApplication()).updataData(ExeForGlobalData_id,userSelectDataArrayList,tempMaxCalorie,tempCalorie);
                //getActivity().onBackPressed();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }


}
