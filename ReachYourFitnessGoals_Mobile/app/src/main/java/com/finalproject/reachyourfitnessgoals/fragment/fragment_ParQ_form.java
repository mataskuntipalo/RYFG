package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.finalproject.reachyourfitnessgoals.R;

import static android.R.attr.fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_ParQ_form extends Fragment {


    public fragment_ParQ_form() {
        // Required empty public constructor
    }

    public static fragment_ParQ_form newInstance() {
        fragment_ParQ_form fragment = new fragment_ParQ_form();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment__par_q_form, container, false);
        Button process = (Button) rootview.findViewById(R.id.process_Button_parQ);

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_results_parQ resultParQ = fragment_results_parQ.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_login, resultParQ, "fragment_results_parQ")
                        .addToBackStack("fragment_results_parQ")
                        .commit();
            }
        });


        return rootview;
    }

}
