package com.finalproject.reachyourfitnessgoals.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Papang on 2/5/2560.
 */

public class RecyclerViewAdapter_exeSummary extends AbstractExpandableItemAdapter<RecyclerViewAdapter_exeSummary.MyGroupViewHolder, RecyclerViewAdapter_exeSummary.MyChildViewHolder>{

    private static Activity mActivity;



    static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        TextView type_textView;
        TextView calorie_textView;
        TextView maxCalorie_textView;

        ImageView pic;
        TextView name_textView_child;
        TextView calorie_textView_child;

        public MyBaseViewHolder(View itemView) {
            super(itemView);
            type_textView = (TextView) itemView.findViewById(R.id.typeName_TextView_exe_sum);
            calorie_textView = (TextView) itemView.findViewById(R.id.calorie_TextView_exe_sum);
            maxCalorie_textView = (TextView) itemView.findViewById(R.id.maxCalorie_TextView_exe_sum);

            name_textView_child = (TextView) itemView.findViewById(R.id.name_TextView_child_exe_sum);
            calorie_textView_child = (TextView) itemView.findViewById(R.id.calorie_TextView_child_exe_sum);
        }
    }

    static class MyGroupViewHolder extends MyBaseViewHolder {
        public MyGroupViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View itemView) {
            super(itemView);
        }
    }


        public RecyclerViewAdapter_exeSummary(Activity activity) {
            mActivity = activity;
            setHasStableIds(true); // this is required for expandable feature.
        }

        @Override
        public int getGroupCount() {
            //return mItems.size();
            return ((GlobalData) mActivity.getApplication()).getExeForGlobalData().size();
        }

        @Override
        public int getChildCount(int groupPosition) {
            return ((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getUserSelectDatas().size();
            //return mItems.get(groupPosition).children.size();

        }

        @Override
        public long getGroupId(int groupPosition) {
            // This method need to return unique value within all group items.
            //return mItems.get(groupPosition).id;
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // This method need to return unique value within the group.
            //return mItems.get(groupPosition).children.get(childPosition).id;
            return childPosition;
            //return ((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getUserSelectDatas().get(childPosition).getId();
        }

        @Override
        public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_header_exe_sum, parent, false);
            return new MyGroupViewHolder(v);
        }

        @Override
        public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_child_exe_sum, parent, false);
            return new MyChildViewHolder(v);
        }

        @Override
        public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
            //MyGroupItem group = mItems.get(groupPosition);
            //holder.textView.setText(group.text);
            holder.type_textView.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getType());
            holder.calorie_textView.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getCalorie()+"");
            holder.maxCalorie_textView.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getMaxCalorie()+"");
        }

        @Override
        public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {
            //MyChildItem child = mItems.get(groupPosition).children.get(childPosition);
            //holder.textView.setText(child.text);
            holder.name_textView_child.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getUserSelectDatas().get(childPosition).getName());
            holder.calorie_textView_child.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getUserSelectDatas().get(childPosition).getName());
        }

        @Override
        public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
            return true;
        }


}
