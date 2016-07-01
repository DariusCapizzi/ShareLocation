package com.example.darius.sharelocation.models;

/**
 * Created by Guest on 7/1/16.
 */
public class Direction {
    private String mDistance;
    private String mDuration;
    private String mHtmlInstruction;

    public Direction(String distance, String duration, String htmlInstruction){
        this.mDistance = distance;
        this.mDuration = duration;
        this.mHtmlInstruction = htmlInstruction;
    }

    public String getDistance() {
        return mDistance;
    }
    public String getDuration() {
        return mDuration;
    }
    public String getHtmlInstruction() {
        return mHtmlInstruction;
    }
}
