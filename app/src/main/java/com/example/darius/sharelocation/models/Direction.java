package com.example.darius.sharelocation.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Guest on 7/1/16.
 */
public class Direction implements Parcelable {
    public static ArrayList<Direction> routeArray = new ArrayList<Direction>();
    private ArrayList<Step> mStepArray = new ArrayList<>();

    private ArrayList<Friend> mFriendArray = new ArrayList<>();
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
        routeArray.add(this);
    }

    protected Direction(Parcel in) {
        mStartAddress = in.readString();
        mEndAddress = in.readString();
        mDistance = in.readString();
        mDuration = in.readString();
        mHtmlInstruction = in.readString();
    }

    public static final Creator<Direction> CREATOR = new Creator<Direction>() {
        @Override
        public Direction createFromParcel(Parcel in) {
            return new Direction(in);
        }

        @Override
        public Direction[] newArray(int size) {
            return new Direction[size];
        }
    };

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
    public ArrayList<Friend> getFriendArray() {
        return mFriendArray;
    }

    public void addFriend(Friend friend){
        mFriendArray.add(friend);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mStartAddress);
        parcel.writeString(mEndAddress);
        parcel.writeString(mDistance);
        parcel.writeString(mDuration);
        parcel.writeString(mHtmlInstruction);
    }
}
