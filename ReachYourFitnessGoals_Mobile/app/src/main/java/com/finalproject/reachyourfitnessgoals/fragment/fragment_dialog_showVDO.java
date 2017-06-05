package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.ExerciseActivity;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_dialog_showVDO extends DialogFragment implements YouTubePlayer.OnInitializedListener {

    TextView name;

    public fragment_dialog_showVDO() {
        // Required empty public constructor
    }

    public static fragment_dialog_showVDO newInstance(String name) {
        // Required empty public constructor
        fragment_dialog_showVDO fragment = new fragment_dialog_showVDO();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_dialog_show_vdo, container, false);

        name = (TextView)rootview.findViewById(R.id.nameExe_TextView_showVDO);
        name.setText(getArguments().getString("name"));
        YouTubePlayerSupportFragment youtubePlayerFragment = (YouTubePlayerSupportFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        if (youtubePlayerFragment == null) {
            youtubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(R.id.youtube_fragment, youtubePlayerFragment).commit();
        }
        youtubePlayerFragment.initialize(ExerciseActivity.API_KEY, this);


        return rootview;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        youTubePlayer.loadVideo(ExerciseActivity.VIDEO_CAMEL);
        youTubePlayer.setFullscreen(false);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
