package com.finalproject.reachyourfitnessgoals.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * Created by Papang on 22/4/2560.
 */


public class userSelectExeViewHolder extends RecyclerView.ViewHolder{

    public ImageView exePic_bottomSheet;
    public TextView exeName_bottomSheet;
    public TextView exeCalorie_bottomSheet;

    public userSelectExeViewHolder(View itemView) {
        super(itemView);
        exePic_bottomSheet = (ImageView) itemView.findViewById(R.id.pic_bottomSheet);
        exeName_bottomSheet = (TextView) itemView.findViewById(R.id.name_TextView_bottomSheet);
        exeCalorie_bottomSheet = (TextView) itemView.findViewById(R.id.calorie_TextView_bottomSheet);
    }

}

