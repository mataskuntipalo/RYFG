package com.finalproject.reachyourfitnessgoals.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.ViewHolder.showAllExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.userSelectExeViewHolder;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.util.ArrayList;

/**
 * Created by Papang on 22/4/2560.
 */

public class RecyclerViewAdapter_userSelect extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<userSelectData> userSelectDataList;

    public RecyclerViewAdapter_userSelect(ArrayList<userSelectData> userSelectDataList) {
        this.userSelectDataList = userSelectDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_bottom_sheet, parent, false);
        return new userSelectExeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        userSelectExeViewHolder userSelectExeViewHolder = (userSelectExeViewHolder) holder;
        userSelectExeViewHolder.exePic_bottomSheet.setBackgroundResource(R.drawable.pic);
        userSelectExeViewHolder.exeName_bottomSheet.setText(userSelectDataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userSelectDataList.size();
    }

    public void addExe(String name){
        this.userSelectDataList.add(new userSelectData(name,5));
        notifyDataSetChanged();
    }

    public ArrayList<userSelectData> getData(){
        for (int i = 0 ;i < this.userSelectDataList.size() ; i++) {
            this.userSelectDataList.get(i).setId(i);
        }
        return this.userSelectDataList;
    }
}
