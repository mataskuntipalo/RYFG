package com.finalproject.reachyourfitnessgoals.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * Created by Papang on 19/4/2560.
 */

public class randomExeViewHolder extends RecyclerView.ViewHolder {

    public ImageView exePic_all;
    public TextView exeName_all;
    public TextView exeNumber_all;

    public randomExeViewHolder(View itemView) {
        super(itemView);
        exePic_all = (ImageView) itemView.findViewById(R.id.pic_showAll);
        exeName_all = (TextView) itemView.findViewById(R.id.name_text_showAll);
        exeNumber_all = (TextView) itemView.findViewById(R.id.number_text_showAll);

    }

}
