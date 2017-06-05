package com.finalproject.reachyourfitnessgoals.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_intro_slideEnd extends Fragment {

    TextView logIn;
    Button signUp;

    public static fragment_intro_slideEnd newInstance() {
        fragment_intro_slideEnd fragment = new fragment_intro_slideEnd();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_intro_slide_end, container, false);

        logIn = (TextView)rootview.findViewById(R.id.logIn_TextView_intro);
        signUp = (Button)rootview.findViewById(R.id.signUp_button) ;

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("state",1);
                startActivity(intent);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_signUp signUp = new fragment_signUp();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_login, signUp);
                transaction.addToBackStack("fragment_signUp");
                transaction.commit();
            }
        });


        return rootview;
    }


}
