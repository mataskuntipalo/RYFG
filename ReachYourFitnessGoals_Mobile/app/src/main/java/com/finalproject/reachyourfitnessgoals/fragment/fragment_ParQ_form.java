package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.reachyourfitnessgoals.BuildConfig;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;
import com.finalproject.reachyourfitnessgoals.interfaces.AnswerParQDataManager;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import static android.R.attr.fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_ParQ_form extends Fragment implements BlockingStep {

    TextView questionText;
    RadioGroup groupRadio;
    boolean ans;
    private AnswerParQDataManager ansManager;

    public fragment_ParQ_form() {
        // Required empty public constructor
    }

    public static fragment_ParQ_form newInstance() {
        fragment_ParQ_form fragment = new fragment_ParQ_form();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AnswerParQDataManager) {
            ansManager = (AnswerParQDataManager) context;
        } else {
            throw new IllegalStateException("Activity must implement DataManager interface!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview =  inflater.inflate(R.layout.fragment__par_q_form, container, false);

        String[] questionArray = getResources().getStringArray(R.array.parQ);
        questionText = (TextView)rootview.findViewById(R.id.question_TextView_parQ);
        questionText.setText(questionArray[getArguments().getInt("step")]);
        groupRadio = (RadioGroup)rootview.findViewById(R.id.group_RadioButton_parQ);

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)rootview.findViewById(i);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectAns(v);
                        ansManager.onProceed();
                    }
                });

            }
        });


        return rootview;
    }

    public void selectAns(View v){
        switch (v.getId()){
            case R.id.no_RadioButton_parQ :
                ans = false;
                break;
            case R.id.yes_RadioButton_parQ :
                ans = true;
                break;
        }
    }


    @Override
    public VerificationError verifyStep() {
        if(groupRadio.getCheckedRadioButtonId() == -1){
            VerificationError error = new VerificationError("โปรดเลือกคำตอบ");
            Toast.makeText(this.getContext(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();
            return error;
        }else {
            return null;
        }
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
            ansManager.saveAnswer(ans, (int) getArguments().getInt("step"));
            callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
        Intent intent = new Intent();
        intent.putExtra("ansArray",ansManager.getAnswer());
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().onBackPressed();
    }


    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }
}
