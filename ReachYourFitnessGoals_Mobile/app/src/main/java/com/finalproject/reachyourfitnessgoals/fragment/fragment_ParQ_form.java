package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.BuildConfig;
import com.finalproject.reachyourfitnessgoals.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import static android.R.attr.fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_ParQ_form extends Fragment implements Step {

    Button process,yesButton,noButton;
    TextView questionText;


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
        
        process = (Button) rootview.findViewById(R.id.process_Button_parQ);
        questionText = (TextView)rootview.findViewById(R.id.question_TextView_parQ);
        yesButton = (Button) rootview.findViewById(R.id.yes_Button_parQ);
        noButton = (Button) rootview.findViewById(R.id.no_Button_parQ);

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fragment_ParQ_form", "ss");
                fragment_results_parQ resultParQ = fragment_results_parQ.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_parQ, resultParQ, "fragment_results_parQ")
                        .addToBackStack("fragment_results_parQ")
                        .commit();
            }
        });


        return rootview;
    }

    private void runQuestion(){
        String[] questionArray = getResources().getStringArray(R.array.parQ);
        for (int i = 0 ; i < 7 ; i++){
            questionText.setText(questionArray[i]);
        }
    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
