package com.example.darius.sharelocation.models;

import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Guest on 7/15/16.
 */
public class Step{

    ArrayList<Step> stepArray =  new ArrayList<>();

    private Direction parentDirection;

    public Step(String distance, String duration, String htmlInstruction){
        this.mDistance = distance;
        this.mDuration = duration;
        this.mHtmlInstruction = htmlInstruction;

        stepArray.add(this);
    }

    public Direction getParentDirection() {
        return parentDirection;
    }

    public void setParentDirection(Direction parentDirection) {
        this.parentDirection = parentDirection;
    }


    public String getHtmlInstruction() {
        return mHtmlInstruction;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getDistance() {
        return mDistance;
    }

    private final String mHtmlInstruction;
    private final String mDuration;
    private final String mDistance;


}
