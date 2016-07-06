package com.example.darius.sharelocation.models;
/**
 * Created by Guest on 7/1/16.
 */
public class Direction {


    private String mStartAddress;
    private String mEndAddress;
    private String mDistance;
    private String mDuration;
    private String mHtmlInstruction;
    public Direction(String startAddress, String endAddress, String distance, String duration, String htmlInstruction){
        this.mStartAddress = startAddress;
        this.mEndAddress = endAddress;
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
    public String getStartAddress() { return mStartAddress;  }
    public String getEndAddress() {
        return mEndAddress;
    }
}
