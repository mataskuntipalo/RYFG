package com.finalproject.reachyourfitnessgoals.setting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

/**
 * Created by Papang on 13/4/2560.
 */

public class abstractItemListView extends AbstractItem<abstractItemListView, abstractItemListView.ViewHolder> {

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return null;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView description;

        public ViewHolder(View view) {
            super(view);
            //this.name = (TextView) view.findViewById(com.mikepenz.materialdrawer.R.id.material_drawer_name);
            //this.description = (TextView) view.findViewById(com.mikepenz.materialdrawer.R.id.material_drawer_description);
        }
    }
}
