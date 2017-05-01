package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_exeSummary_ExpandView extends Fragment  {


    private static Activity mActivity;
    private RecyclerView mRecyclerView;



    public fragment_exeSummary_ExpandView() {
        // Required empty public constructor
    }

    public static fragment_exeSummary_ExpandView newInstance(Activity activity) {
        mActivity = activity;
        fragment_exeSummary_ExpandView fragment = new fragment_exeSummary_ExpandView();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_exe_summary_expand_view, container, false);

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView_exeSummary);
        RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(null);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(expMgr.createWrappedAdapter(new MyAdapter()));

        // NOTE: need to disable change animations to ripple effect work properly
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        expMgr.attachRecyclerView(mRecyclerView);


        return rootview;
    }



//    static abstract class MyBaseItem {
//        public final long id;
//        public final String text;
//
//        public MyBaseItem(long id, String text) {
//            this.id = id;
//            this.text = text;
//        }
//    }
//
//    static class MyGroupItem extends MyBaseItem {
//        public final List<MyChildItem> children;
//
//        public MyGroupItem(long id, String text) {
//            super(id, text);
//            children = new ArrayList<>();
//        }
//    }

//    static class MyChildItem extends MyBaseItem {
//        public MyChildItem(long id, String text) {
//            super(id, text);
//        }
//    }

    static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        TextView textView;

        public MyBaseViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.header_listView);
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

    static class MyAdapter extends AbstractExpandableItemAdapter<MyGroupViewHolder, MyChildViewHolder> {
        //List<MyGroupItem> mItems;

        public MyAdapter() {
            setHasStableIds(true); // this is required for expandable feature.
        }

        @Override
        public int getGroupCount() {
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_listview_row, parent, false);
            return new MyGroupViewHolder(v);
        }

        @Override
        public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_listview_row, parent, false);
            return new MyChildViewHolder(v);
        }

        @Override
        public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
            holder.textView.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getType());
        }

        @Override
        public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {
            holder.textView.setText(((GlobalData) mActivity.getApplication()).getExeForGlobalData().get(groupPosition).getUserSelectDatas().get(childPosition).getName());
        }

        @Override
        public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
            return true;
        }
    }


}
