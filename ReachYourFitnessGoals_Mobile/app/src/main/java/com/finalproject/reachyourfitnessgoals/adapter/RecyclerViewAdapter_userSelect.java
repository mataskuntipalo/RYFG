package com.finalproject.reachyourfitnessgoals.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.ViewHolder.showAllExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.userSelectExeViewHolder;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Papang on 22/4/2560.
 */

public class RecyclerViewAdapter_userSelect extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<userSelectData> userSelectDataList;
    private Context mContext;

    public RecyclerViewAdapter_userSelect(ArrayList<userSelectData> userSelectDataList,Context context) {
        this.userSelectDataList = userSelectDataList;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_bottom_sheet, parent, false);
        return new userSelectExeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        userSelectExeViewHolder userSelectExeViewHolder = (userSelectExeViewHolder) holder;
        File mFile = new File(Environment.getExternalStorageDirectory()+ File.separator  + "Android" + File.separator + "data" + File.separator + "com.finalproject.reachyourfitnessgoals" + File.separator + "image" + File.separator + userSelectDataList.get(position).getName() + ".jpg");
        Glide.with(mContext).load(mFile).into(userSelectExeViewHolder.exePic_bottomSheet);
        userSelectExeViewHolder.exeName_bottomSheet.setText(userSelectDataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userSelectDataList.size();
    }

    public void addExe(String name , String vdo_id){
        this.userSelectDataList.add(new userSelectData(name,5,vdo_id));
        notifyDataSetChanged();
    }


}
