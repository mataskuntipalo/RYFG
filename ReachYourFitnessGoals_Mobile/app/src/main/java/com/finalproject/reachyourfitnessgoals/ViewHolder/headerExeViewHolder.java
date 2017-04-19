package com.finalproject.reachyourfitnessgoals.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * Created by Papang on 20/4/2560.
 */

public class headerExeViewHolder extends RecyclerView.ViewHolder{


    public TextView headerExe;

    public  headerExeViewHolder(View itemView) {
        super(itemView);
        headerExe = (TextView) itemView.findViewById(R.id.header_listView);
    }

}
