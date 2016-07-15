package com.example.darius.sharelocation.models;

import java.util.ArrayList;

/**
 * Created by Guest on 7/15/16.
 */
public class Step {

    public static ArrayList<Step> stepArray =  new ArrayList<>();

    private Route parentRoute;

    public Step(String distance, String duration, String htmlInstruction){
        this.mDistance = distance;
        this.mDuration = duration;
        this.mHtmlInstruction = htmlInstruction;

        stepArray.add(this);
    }

    public Route getParentRoute() {
        return parentRoute;
    }

    public void setParentRoute(Route parentRoute) {
        this.parentRoute = parentRoute;
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
