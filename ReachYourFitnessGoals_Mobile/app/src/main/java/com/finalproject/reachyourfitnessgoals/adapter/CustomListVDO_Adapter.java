package com.finalproject.reachyourfitnessgoals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Papang on 18/12/2559.
 */

public class CustomListVDO_Adapter extends BaseAdapter implements StickyListHeadersAdapter {
    Context mContext;
    String[] strName;
    int[] id;

    public CustomListVDO_Adapter(Context mContext, String[] strName, int[] id) {
        this.mContext = mContext;
        this.strName = strName;
        this.id = id;
    }

    @Override
    public int getCount() {
        return strName.length;
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
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, viewGroup, false);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(strName[position]);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(id[position]);

        return view;
    }

    @Override
    public View getHeaderView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = mInflater.inflate(R.layout.header_listview_row, viewGroup, false);
        }

        //set header text as first char in name
        TextView textView = (TextView)view.findViewById(R.id.header_listView);
        textView.setText(strName[position]);
        return view;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }
}
