package com.finalproject.reachyourfitnessgoals.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_ParQ_form;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by Papang on 9/5/2560.
 */

public class ParQStepperAdapter extends AbstractFragmentStepAdapter {

    public ParQStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        final fragment_ParQ_form step = fragment_ParQ_form.newInstance();
        Bundle b = new Bundle();
        b.putInt("key", position);
        step.setArguments(b);
        return step;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return new StepViewModel.Builder(context)
               // .setTitle(R.string.app_name) //can be a CharSequence instead
                .create();
    }
}
