package com.finalproject.reachyourfitnessgoals.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.finalproject.reachyourfitnessgoals.BuildConfig;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.ViewHolder.customExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.headerExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.randomExeViewHolder;
import com.finalproject.reachyourfitnessgoals.ViewHolder.showAllExeViewHolder;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.userSelectData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
import com.finalproject.reachyourfitnessgoals.models.ListType;
import com.finalproject.reachyourfitnessgoals.setting.SetUpCalorieAndExe;
import com.google.android.youtube.player.internal.m;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Papang on 18/4/2560.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter {
    private int type;
    private ArrayList<vdoData> vdoDataArrayList;
    private Context context;
    private Activity activity;
    private SetUpCalorieAndExe setUpCalorieAndExe;
    OnItemClickListener mItemClickListener;
    OnItemClickListenerShowAll mItemClickListenerShowAll;
    private int sumCalorieInType;
    public static int sumCalorieInDay;



    public RecyclerViewAdapter(Activity activity, int type) {
        this.vdoDataArrayList = new handleTABLE_VDO(activity).getVdoExercise();
        this.activity = activity;
        this.type = type;
        if(type == ListType.TYPE_RANDOM_EXERCISE){
            setUpCalorieAndExe = new SetUpCalorieAndExe(activity);
            randomExe();
        }
    }


    public RecyclerViewAdapter(Context mContext , String type) {
        this.vdoDataArrayList = new handleTABLE_VDO(mContext).getCustomVdoExercise(type);
        this.type = ListType.TYPE_CUSTOM_EXERCISE;
        context = mContext;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if(type == ListType.TYPE_SHOW_ALL){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_show_all, parent, false);
            showAllExeViewHolder showAllExeViewHolder = new showAllExeViewHolder(view, new showAllExeViewHolder.showAllExeViewHolderClicks() {
                @Override
                public void select(View caller) {
                    if (mItemClickListenerShowAll != null) {
                        mItemClickListenerShowAll.onItemClickShowVDO(caller.getTag(R.id.name)+"");
                    }
                }
            });
            return showAllExeViewHolder;
        }else if(type == ListType.TYPE_CUSTOM_EXERCISE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_custom_exe, parent, false);
            customExeViewHolder customExeViewHolder = new customExeViewHolder(view, new customExeViewHolder.customViewHolderClicks() {
                @Override
                public void select(View caller) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(caller, caller.getTag(R.id.name)+"" , caller.getTag(R.id.calorie)+"" , caller.getTag(R.id.vdo_id)+"");
                    }
                }

                @Override
                public void longSelect() {

                    if (mItemClickListener != null) {
                        mItemClickListener.onItemLongClick();
                    }
                }

            });
            return customExeViewHolder;
        }else if(type == ListType.TYPE_RANDOM_EXERCISE){
            if(viewType == 1){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_listview_row, parent, false);
                return new headerExeViewHolder(view);
            }else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_show_all, parent, false);
                return  new randomExeViewHolder(view);
            }

        }
        throw new NullPointerException("View Type " + viewType + " doesn't match with any existing type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof showAllExeViewHolder) {
            showAllExeViewHolder showAllExeViewHolder = (showAllExeViewHolder) holder;
            showAllExeViewHolder.itemView.setTag(R.id.name,vdoDataArrayList.get(position).getName());
            setupShowAllExe(showAllExeViewHolder,vdoDataArrayList.get(position));

        }else if(holder instanceof customExeViewHolder){
            customExeViewHolder customExeViewHolder = (customExeViewHolder) holder;
            customExeViewHolder.itemView.setTag(R.id.name,vdoDataArrayList.get(position).getName());
            customExeViewHolder.itemView.setTag(R.id.calorie,vdoDataArrayList.get(position).getCalorie()+"");
            customExeViewHolder.itemView.setTag(R.id.vdo_id,vdoDataArrayList.get(position).getVdo_id());
            setupCustomExe(customExeViewHolder, vdoDataArrayList.get(position));

        }else if(holder instanceof randomExeViewHolder){
            randomExeViewHolder randomExeViewHolder = (randomExeViewHolder) holder;
            setupRandomExe(randomExeViewHolder, vdoDataArrayList.get(position),position);

        }else if(holder instanceof headerExeViewHolder){
            //Custom header (Add blank header data to vdoDataArrayList)
            headerExeViewHolder headerExeViewHolder = (headerExeViewHolder) holder;
            headerExeViewHolder.headerExe.setText(this.vdoDataArrayList.get(position).getType());
        }
    }

    // Sticky Header lib
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

    @Override
    public int getItemViewType(int position) {
        if(this.vdoDataArrayList.get(position).isHead()){
            return 1;
        }else {
            return 0;
        }
    }

    public void randomExe(){
        this.vdoDataArrayList.clear();
        for(int i = 0 ; i < 5 ; i++){
            ArrayList<vdoData> data = new handleTABLE_VDO(activity).getCustomVdoExercise(ExeType.TYPE[i]);
            int maxCalorieEachStep = setUpCalorieAndExe.getMaxCalorieForEachStep(ExeType.TYPE[i],i);

            // add Head in vdoDataArrayList
            vdoData vdoData = new vdoData("",((GlobalData) activity.getApplication()).type[i],"","",0);
            vdoData.setHead(true);
            // end add Head

            this.vdoDataArrayList.add(vdoData);
            random(i,maxCalorieEachStep,data);
        }
        notifyDataSetChanged();
    }

    private void random(int id,int maxCalorie,ArrayList<vdoData> data){
        ArrayList<userSelectData> list = new ArrayList<>();
        Random r = new Random();
        int numRandom;
        sumCalorieInType = 0;
        while (sumCalorieInType<maxCalorie){
            numRandom = r.nextInt(data.size());
            sumCalorieInType = sumCalorieInType + data.get(numRandom).getCalorie();
            list.add(new userSelectData(data.get(numRandom).getName(),data.get(numRandom).getCalorie(),data.get(numRandom).getVdo_id()));
            data.get(numRandom).setHead(false);
            this.vdoDataArrayList.add(data.get(numRandom));
        }
        sumCalorieInDay = sumCalorieInDay + sumCalorieInType;
        ((GlobalData)activity.getApplication()).updataData(id,list,maxCalorie,sumCalorieInType);
    }

    public void addToDataBaseRandomStep(){
        setUpCalorieAndExe.addExeInDay(sumCalorieInDay);
    }


    private void setupShowAllExe (showAllExeViewHolder showAllExeViewHolder , vdoData vdoData){
        File mFile = new File(Environment.getExternalStorageDirectory()+ File.separator  + "Android" + File.separator + "data" + File.separator + "com.finalproject.reachyourfitnessgoals" + File.separator + "image" + File.separator + vdoData.getName() + ".jpg");
        Glide.with(activity).load(mFile).into(showAllExeViewHolder.exePic_all);
        showAllExeViewHolder.exeName_all.setText(vdoData.getName());
        showAllExeViewHolder.exeNumber_all.setText(vdoData.getCalorie()+"");
    }

    private void setupCustomExe (customExeViewHolder customExeViewHolder , vdoData vdoData){
        //customExeViewHolder.exePic_custom.setBackground(Drawable.createFromPath(vdoData.getName()));
        File mFile = new File(Environment.getExternalStorageDirectory()+ File.separator  + "Android" + File.separator + "data" + File.separator + "com.finalproject.reachyourfitnessgoals" + File.separator + "image" + File.separator + vdoData.getName() + ".jpg");
        Glide.with(context).load(mFile).into(customExeViewHolder.exePic_custom);
        customExeViewHolder.exeName_custom.setText(vdoData.getName());
    }

    private void setupRandomExe (randomExeViewHolder randomExeViewHolder , vdoData vdoData,int position){
        File mFile = new File(Environment.getExternalStorageDirectory()+ File.separator  + "Android" + File.separator + "data" + File.separator + "com.finalproject.reachyourfitnessgoals" + File.separator + "image" + File.separator + vdoData.getName() + ".jpg");
        Glide.with(activity).load(mFile).into(randomExeViewHolder.exePic_all);
        randomExeViewHolder.exeName_all.setText(vdoData.getName());
        randomExeViewHolder.exeNumber_all.setText(vdoData.getCalorie()+"");

    }


    public interface OnItemClickListener {
        public void onItemClick(View view , String name,String calorie,String  vdo_id);
        public void onItemLongClick();
    }

    public interface OnItemClickListenerShowAll{
        public void onItemClickShowVDO(String name);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void SetOnItemClickListener(final OnItemClickListenerShowAll mItemClickListener) {
        this.mItemClickListenerShowAll = mItemClickListener;
    }

}
