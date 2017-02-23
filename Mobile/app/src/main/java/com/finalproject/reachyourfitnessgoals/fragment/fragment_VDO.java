package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_VDO extends Fragment {

    ProgressDialog pd;
    VideoView vdo;
    String url ="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public fragment_VDO() {
        // Required empty public constructor
    }

    public static fragment_VDO newInstance() {
        fragment_VDO fragment = new fragment_VDO();
        //fragment.setArguments(args);
        //fragment.setHasOptionsMenu(true);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_vdo, container, false);

        vdo = (VideoView)rootview.findViewById(R.id.vdo_container);
        pd = new ProgressDialog(getActivity());
        pd.setTitle("Demo");
        pd.setMessage("Buffering...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();

        try{

            MediaController controller = new MediaController(getActivity());
            controller.setAnchorView(vdo);
            Uri vid = Uri.parse(url);
            vdo.setMediaController(controller);
            vdo.setVideoURI(vid);


        }catch (Exception e){

            Log.e("Error",e.getMessage());
            e.printStackTrace();
        }

        vdo.requestFocus();
        vdo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                pd.dismiss();
                vdo.start();

            }
        });


        return rootview;
    }

}
