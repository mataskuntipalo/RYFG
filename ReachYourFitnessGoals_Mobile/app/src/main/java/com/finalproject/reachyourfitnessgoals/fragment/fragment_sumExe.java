package com.finalproject.reachyourfitnessgoals.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_sumExe extends android.app.Fragment {


    public fragment_sumExe() {
        // Required empty public constructor
    }

    public static fragment_sumExe newInstance(String time) {
        fragment_sumExe fragment = new fragment_sumExe();
        Bundle bundle = new Bundle();
        bundle.putString("time",time);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View rootview = inflater.inflate(R.layout.fragment_sum_exe, container, false);

        Button button = (Button) rootview.findViewById(R.id.done_Button_sumExe);
        TextView textView =(TextView)rootview.findViewById(R.id.time_TextView_sumExe);
        textView.setText("เวลาที่ใช้ " + getArguments().getString("time") + " นาที");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return rootview;
    }

}
