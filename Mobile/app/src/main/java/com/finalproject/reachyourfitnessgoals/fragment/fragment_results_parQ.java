package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_results_parQ extends Fragment {


    public fragment_results_parQ() {
        // Required empty public constructor
    }

    public static fragment_results_parQ newInstance() {
        fragment_results_parQ fragment = new fragment_results_parQ();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_results_par_q, container, false);

        Button confirm = (Button) rootview.findViewById(R.id.confirm_Button_resultParQ);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_goal goal = fragment_goal.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_login, goal, "fragment_goal")
                        .addToBackStack("fragment_goal")
                        .commit();
            }
        });

        return rootview;
    }

}
