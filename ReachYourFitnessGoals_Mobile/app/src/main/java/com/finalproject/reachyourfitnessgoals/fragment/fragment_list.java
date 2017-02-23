package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.CustomListVDO_Adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_list extends Fragment {

    int[] resId = { R.drawable.pic};

    String[] list = { "Aerith Gainsborough"};

    public static fragment_list newInstance() {
        fragment_list fragment = new fragment_list();
        //fragment.setArguments(args);
        //fragment.setHasOptionsMenu(true);
        return fragment;
    }


    public fragment_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_list, container, false);

        CustomListVDO_Adapter adapter = new CustomListVDO_Adapter(getActivity(), list, resId);
        ListView listView = (ListView)rootview.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                fragment_VDO vdo = fragment_VDO.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_main, vdo, "fragment_list")
                        .addToBackStack("fragment_list")
                        .commit();

            }
        });

        return rootview;
    }

}
