package com.finalproject.reachyourfitnessgoals.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Papang on 18/12/2559.
 */

public class CustomListVDO_Adapter extends BaseAdapter implements StickyListHeadersAdapter {
    Context mContext;
    String[] strName;
    ArrayList<vdoData> vdoDataArrayList;
    int id = R.drawable.pic;

    public CustomListVDO_Adapter(Context mContext) {
        this.mContext = mContext;
        vdoDataArrayList = new handleTABLE_VDO(mContext).getVdoExercise();
    }

    @Override
    public int getCount() {
        return vdoDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Log.i("inGetView",position+"");
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, viewGroup, false);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(vdoDataArrayList.get(position).getName());

        TextView textView1 = (TextView)view.findViewById(R.id.number);
        textView1.setText(position+"");

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(id);

        return view;
    }

    @Override
    public View getHeaderView(int position, View view, ViewGroup viewGroup) {
        Log.i("inGetViewHead",position+"");
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = mInflater.inflate(R.layout.header_listview_row, viewGroup, false);
        }

        //set header text as first char in name
        TextView textView = (TextView)view.findViewById(R.id.header_listView);
        textView.setText(vdoDataArrayList.get(position).getType());
        return view;
    }

    @Override
    public long getHeaderId(int position) {
        return vdoDataArrayList.get(position).getType().charAt(0);
    }
}
