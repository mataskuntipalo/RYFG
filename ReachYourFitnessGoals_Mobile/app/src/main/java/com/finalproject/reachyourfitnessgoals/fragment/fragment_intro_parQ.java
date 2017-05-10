package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.ParQActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_intro_parQ extends Fragment {

    Button nextButton;

    public fragment_intro_parQ() {
        // Required empty public constructor
    }

    public static fragment_intro_parQ newInstance() {
        fragment_intro_parQ fragment = new fragment_intro_parQ();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_intro_par_q, container, false);

        nextButton = (Button)rootview.findViewById(R.id.next_Button_introParQ);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ParQActivity.class);
                startActivityForResult(intent, 12345);
            }
        });

        return rootview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 12345 && data != null) {
            Toast.makeText(this.getContext(), "completeEnd111", Toast.LENGTH_SHORT).show();
            fragment_results_parQ resultParQ = fragment_results_parQ.newInstance(data.getBooleanArrayExtra("ansArray"));
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                    .replace(R.id.activity_login, resultParQ, "fragment_results_parQ")
                    .commit();
        }else {

        }
    }

}
