package com.example.darius.sharelocation.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Guest on 7/1/16.
 */
public class Route implements Parcelable {
    public static ArrayList<Route> routeArray = new ArrayList<Route>();
    private ArrayList<Step> mStepArray = new ArrayList<>();

    private ArrayList<Friend> mFriendArray = new ArrayList<>();
    private String mStartAddress;
    private String mEndAddress;
    private String mDistance;
    private String mDuration;
    private String mHtmlInstruction;
    private String mSummary;


    public Route(String startAddress, String endAddress, String summary, String distance, String duration){
        this.mStartAddress = startAddress;
        this.mEndAddress = endAddress;
        this.mSummary = summary;
        this.mDistance = distance;
        this.mDuration =  duration;
        routeArray.add(this);
    }

    protected Route(Parcel in) {
        mStartAddress = in.readString();
        mEndAddress = in.readString();
        mDistance = in.readString();
        mDuration = in.readString();
        mHtmlInstruction = in.readString();
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
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

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public ArrayList<Step> getStepArray() {
        return mStepArray;
    }

    public void setStepArray(ArrayList<Step> mStepArray) {
        this.mStepArray = mStepArray;
    }
}
