package com.finalproject.reachyourfitnessgoals.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.ViewHolder.customExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.headerExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.randomExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.showAllExeViewHolder;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.finalproject.reachyourfitnessgoals.setting.ListType;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Papang on 18/4/2560.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter {
    private int type;
    private ArrayList<vdoData> vdoDataArrayList;
    private Context context;


    public RecyclerViewAdapter(Context mContext, int type) {
        this.vdoDataArrayList = new handleTABLE_VDO(mContext).getVdoExercise();
        this.context = mContext;
        this.type = type;
        if(type == ListType.TYPE_RANDOM_EXERCISE){
            randomExe();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(type == ListType.TYPE_SHOW_ALL){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_show_all, parent, false);
            return new showAllExeViewHolder(view);
        }else if(type == ListType.TYPE_CUSTOM_EXERCISE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_custom_exe, parent, false);
            return new customExeViewHolder(view);
        }else if(type == ListType.TYPE_RANDOM_EXERCISE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_show_all, parent, false);
            return  new randomExeViewHolder(view);
        }
        throw new NullPointerException("View Type " + viewType + " doesn't match with any existing type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof showAllExeViewHolder) {

            showAllExeViewHolder showAllExeViewHolder = (showAllExeViewHolder) holder;
            setupShowAllExe(showAllExeViewHolder,vdoDataArrayList.get(position));

        }else if(holder instanceof customExeViewHolder){

            customExeViewHolder customExeViewHolder = (customExeViewHolder) holder;
            setupCustomExe(customExeViewHolder, vdoDataArrayList.get(position));

        }else if(holder instanceof randomExeViewHolder){

            randomExeViewHolder randomExeViewHolder = (randomExeViewHolder) holder;
            setupRandomExe(randomExeViewHolder, vdoDataArrayList.get(position),position);
        }
    }


    @Override
    public long getHeaderId(int position) {
            return vdoDataArrayList.get(position).getType().charAt(4);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_listview_row, parent, false);
        return new headerExeViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        headerExeViewHolder headerExeViewHolder = (headerExeViewHolder) holder;
        headerExeViewHolder.headerExe.setText(vdoDataArrayList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return vdoDataArrayList.size();
    }

    public void randomExe(){
        ArrayList<vdoData> data = new handleTABLE_VDO(context).getVdoExercise();
        Random r = new Random();
        int r_int;
        this.vdoDataArrayList.clear();
        for(int i = 0 ; i < 20 ; i++){
            r_int = r.nextInt(data.size());
            Log.i("randomNumber: ",i+": " + r_int);
            this.vdoDataArrayList.add(data.get(r_int));
        }
        notifyDataSetChanged();
    }


    private void setupShowAllExe (showAllExeViewHolder showAllExeViewHolder , vdoData vdoData){
        showAllExeViewHolder.exePic_all.setBackgroundResource(R.drawable.pic);
        showAllExeViewHolder.exeName_all.setText(vdoData.getName());

    }

    private void setupCustomExe (customExeViewHolder customExeViewHolder , vdoData vdoData){
        //customExeViewHolder.exePic_custom.setBackground(Drawable.createFromPath(vdoData.getName()));
        customExeViewHolder.exePic_custom.setBackgroundResource(R.drawable.pic);
        customExeViewHolder.exeName_custom.setText(vdoData.getName());
    }

    private void setupRandomExe (randomExeViewHolder randomExeViewHolder , vdoData vdoData,int position){
        //customExeViewHolder.exePic_custom.setBackground(Drawable.createFromPath(vdoData.getName()));
        randomExeViewHolder.exePic_all.setBackgroundResource(R.drawable.pic);
        randomExeViewHolder.exeName_all.setText(vdoData.getName());
        randomExeViewHolder.exeNumber_all.setText(position+"");
    }
}
