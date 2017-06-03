package com.finalproject.reachyourfitnessgoals.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * Created by Papang on 18/4/2560.
 */

public class showAllExeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView exePic_all;
    public TextView exeName_all;
    public TextView exeNumber_all;
    public showAllExeViewHolderClicks  mListener;

    public showAllExeViewHolder(View itemView,showAllExeViewHolderClicks listener) {
        super(itemView);
        mListener = listener;
        exePic_all = (ImageView) itemView.findViewById(R.id.pic_showAll);
        exeName_all = (TextView) itemView.findViewById(R.id.name_text_showAll);
        exeNumber_all = (TextView) itemView.findViewById(R.id.number_text_showAll);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mListener.select(v);
    }

    public static interface showAllExeViewHolderClicks {
        public void select(View caller);
    }
}
