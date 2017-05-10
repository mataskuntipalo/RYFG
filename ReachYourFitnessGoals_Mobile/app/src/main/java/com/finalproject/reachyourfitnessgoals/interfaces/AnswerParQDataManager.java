package com.finalproject.reachyourfitnessgoals.interfaces;

/**
 * Created by Papang on 10/5/2560.
 */

public interface AnswerParQDataManager {
        void saveAnswer(boolean ans,int position);
        boolean[] getAnswer();
        void onProceed();
}
