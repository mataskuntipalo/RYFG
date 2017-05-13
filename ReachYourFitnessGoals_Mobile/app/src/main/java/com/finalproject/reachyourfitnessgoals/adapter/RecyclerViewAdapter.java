package com.finalproject.reachyourfitnessgoals.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private Activity activity;
    private SetUpCalorieAndExe setUpCalorieAndExe;
    OnItemClickListener mItemClickListener;
    private int sumCalorieInType;
    public static int sumCalorieInDay;


    public RecyclerViewAdapter(Activity activity, int type) {
        Log.i("typeExe",type+"");
        this.vdoDataArrayList = new handleTABLE_VDO(activity).getVdoExercise();
        this.activity = activity;
        this.type = type;
        if(type == ListType.TYPE_RANDOM_EXERCISE){
            setUpCalorieAndExe = new SetUpCalorieAndExe(activity);
            randomExe();
        }
    }


    public RecyclerViewAdapter(Context mContext , String type) {
        Log.i("typeExe",type);
        this.vdoDataArrayList = new handleTABLE_VDO(mContext).getCustomVdoExercise(type);
        this.type = ListType.TYPE_CUSTOM_EXERCISE;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if(type == ListType.TYPE_SHOW_ALL){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_show_all, parent, false);
            Log.i("inShowAll","inShowAll");
            return new showAllExeViewHolder(view);
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
            if(this.vdoDataArrayList.get(viewType).isHead()){
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
            setupShowAllExe(showAllExeViewHolder,vdoDataArrayList.get(position),position);

        }else if(holder instanceof customExeViewHolder){
            customExeViewHolder customExeViewHolder = (customExeViewHolder) holder;
            customExeViewHolder.itemView.setTag(R.id.name,vdoDataArrayList.get(position).getName());
            customExeViewHolder.itemView.setTag(R.id.calorie,"5");
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
        return position;
    }

    public void randomExe(){
        this.vdoDataArrayList.clear();
        for(int i = 0 ; i < 5 ; i++){
            ArrayList<vdoData> data = new handleTABLE_VDO(activity).getCustomVdoExercise(ExeType.TYPE[i]);
            int maxCalorieEachStep = setUpCalorieAndExe.getMaxCalorieForEachStep(ExeType.TYPE[i],i);

            // add Head in vdoDataArrayList
            vdoData vdoData = new vdoData("",((GlobalData) activity.getApplication()).getExeForGlobalData().get(i).getType(),"","",0);
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
           // Log.d("RecyclerViewAdapter", "tempRandom:" + sumCalorieRandom);

            numRandom = r.nextInt(data.size());
            //data.get(numRandom).getCalorie();
            sumCalorieInType = sumCalorieInType + 5;
            list.add(new userSelectData(data.get(numRandom).getName(),5,data.get(numRandom).getVdo_id()));
            Log.i("nameList",data.get(numRandom).getName());
            this.vdoDataArrayList.add(data.get(numRandom));
        }
        sumCalorieInDay = sumCalorieInDay + sumCalorieInType;
        ((GlobalData)activity.getApplication()).updataData(id,list,maxCalorie,sumCalorieInType);
    }

    public void addToDataBaseRandomStep(){
        setUpCalorieAndExe.addExeInDay(sumCalorieInDay);
    }


    private void setupShowAllExe (showAllExeViewHolder showAllExeViewHolder , vdoData vdoData,int position){
        Log.i("dataVDO",position+"");
        showAllExeViewHolder.exePic_all.setBackgroundResource(R.drawable.pic);
        showAllExeViewHolder.exeName_all.setText(vdoData.getName());
        //showAllExeViewHolder.exeName_all.setText("ss");
    }

    private void setupCustomExe (customExeViewHolder customExeViewHolder , vdoData vdoData){
        //customExeViewHolder.exePic_custom.setBackground(Drawable.createFromPath(vdoData.getName()));
        customExeViewHolder.exePic_custom.setBackgroundResource(R.drawable.pic);
        customExeViewHolder.exeName_custom.setText(vdoData.getName());
        Log.d("RecyclerViewAdapter", vdoData.getName());
    }

    private void setupRandomExe (randomExeViewHolder randomExeViewHolder , vdoData vdoData,int position){
        //customExeViewHolder.exePic_custom.setBackground(Drawable.createFromPath(vdoData.getName()));
        randomExeViewHolder.exePic_all.setBackgroundResource(R.drawable.pic);
        randomExeViewHolder.exeName_all.setText(vdoData.getName());
        randomExeViewHolder.exeNumber_all.setText(position+"");

    }

    public interface OnItemClickListener {
        public void onItemClick(View view , String name,String calorie,String  vdo_id);
        public void onItemLongClick();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
