package com.finalproject.reachyourfitnessgoals.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * Created by Papang on 18/4/2560.
 */

public class customExeViewHolder extends RecyclerView.ViewHolder {

    public ImageView exePic_custom;
    public TextView exeName_custom;

    public customExeViewHolder(View itemView) {
        super(itemView);
        exePic_custom = (ImageView) itemView.findViewById(R.id.pic_customExe);
        exeName_custom = (TextView) itemView.findViewById(R.id.name_text_customExe);
    }
}
