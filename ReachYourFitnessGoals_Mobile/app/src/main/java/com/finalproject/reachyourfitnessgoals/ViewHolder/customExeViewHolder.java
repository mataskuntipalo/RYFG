package com.finalproject.reachyourfitnessgoals.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * Created by Papang on 18/4/2560.
 */

public class customExeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public ImageView exePic_custom;
    public TextView exeName_custom;
    public customViewHolderClicks mListener;

    public customExeViewHolder(View itemView,customViewHolderClicks listener) {
        super(itemView);
        mListener = listener;
        exePic_custom = (ImageView) itemView.findViewById(R.id.pic_customExe);
        exeName_custom = (TextView) itemView.findViewById(R.id.name_text_customExe);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mListener.select(v);
    }


    @Override
    public boolean onLongClick(View v) {
        mListener.longSelect();
        return false;
    }


    public static interface customViewHolderClicks {
        public void select(View caller);
        public void longSelect();
    }


}
