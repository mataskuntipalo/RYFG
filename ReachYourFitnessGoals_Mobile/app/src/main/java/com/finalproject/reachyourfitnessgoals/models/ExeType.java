package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 21/4/2560.
 */

public class ExeType {
    public static final String TYPE_STRETCHING = "Stretching/Cool Down";
    public static final String TYPE_COOlDOWN = "Stretching/Cool Down";
    public static final String TYPE_STRENGTH = "Strength";
    public static final String TYPE_WARMUP = "Warm Up/Cardio";
    public static final String TYPE_CARDIO = "Warm Up/Cardio";
    public static final String[] TYPE = {TYPE_STRETCHING , TYPE_WARMUP , TYPE_STRENGTH , TYPE_CARDIO, TYPE_COOlDOWN };

    public static final int TYPE_PROGRAM_WEIGHT = 0;
    public static final int TYPE_PROGRAM_MUSCLE = 1;
    public static final int TYPE_PROGRAM_BLANK = 3;
}
