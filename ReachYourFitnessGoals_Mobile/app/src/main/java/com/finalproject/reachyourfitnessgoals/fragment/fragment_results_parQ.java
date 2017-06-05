package com.finalproject.reachyourfitnessgoals.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.reachyourfitnessgoals.BuildConfig;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.GoalActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_results_parQ extends Fragment {

    boolean[] boole;
    TextView resultText;
    CheckBox checkBox;

    public fragment_results_parQ() {
        // Required empty public constructor
    }

    public static fragment_results_parQ newInstance(boolean[] ans) {
        fragment_results_parQ fragment = new fragment_results_parQ();
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("ansArray",ans);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_results_par_q, container, false);

        resultText = (TextView)rootview.findViewById(R.id.result_TextView_resultParQ);
        checkBox = (CheckBox)rootview.findViewById(R.id.check_CheckBox_parQ);
        Button confirm = (Button) rootview.findViewById(R.id.confirm_Button_resultParQ);
        boole = getArguments().getBooleanArray("ansArray");
        resultText.setText(checkResult());



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    fragment_selectGoal selectGoal = fragment_selectGoal.newInstance();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                            .replace(R.id.activity_login, selectGoal, "fragment_iselectGoal")
                            .commit();
                }else {
                    Toast.makeText(getActivity(), "โปรดยอมรับ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return rootview;
    }

    private String checkResult() {
        String[] resultArray = getResources().getStringArray(R.array.results);
        String result = "";
        for (boolean b: boole) {
            if(b == true){
                result = resultArray[1];
                resultText.setTextColor(getResources().getColor(R.color.colorRed));
                return result;
            }else {
                result = resultArray[0];
                resultText.setTextColor(getResources().getColor(R.color.green));
            }
        }
        return result;
    }

    private boolean check(){
        if(checkBox.isChecked()){
            return true;
        }else {
            return false;
        }
    }



}
