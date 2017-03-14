package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.reachyourfitnessgoals.R;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_setExercise_inWeek extends Fragment implements VerticalStepperForm {

    private VerticalStepperFormLayout verticalStepperForm;

    public fragment_setExercise_inWeek() {
        // Required empty public constructor
    }

    public static fragment_setExercise_inWeek newInstance() {
        fragment_setExercise_inWeek fragment = new fragment_setExercise_inWeek();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_set_exercise_in_week, container, false);

        String[] stepsTitles = getResources().getStringArray(R.array.steps_titles);
        int colorPrimary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) rootview.findViewById(R.id.stepper_stepView_setExe);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm,stepsTitles,this,getActivity())
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();

        return rootview;
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createDayOfWeekStep();
                break;
            case 1:
                view = createSeleteDayStep();
                break;
        }
        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {

    }

    @Override
    public void sendData() {

    }

    private View createDayOfWeekStep() {
        View view = null;
        
        return view;
    }

    private View createSeleteDayStep() {
        View view = null;
        return view;
    }


}
