package com.example.darius.sharelocation.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 7/1/16.
 */


public class Route implements Parcelable {
    public static ArrayList<Route> routeArray = new ArrayList<Route>();
    private List<Step> stepArray = new ArrayList<>();

    private List<Friend> friendArray = new ArrayList<>();
    private String departure;
    private String arrival;
    private String distance;
    private String duration;
    private String summary;

    public Route(){}

    public Route(String departure, String arrival, String summary, String distance, String duration){
        this.departure = departure;
        this.arrival = arrival;
        this.summary = summary;
        this.distance = distance;
        this.duration =  duration;
        routeArray.add(this);
    }

    protected Route(Parcel in) {
        departure = in.readString();
        arrival = in.readString();
        distance = in.readString();
        duration = in.readString();
        summary = in.readString();
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
        return distance;
    }
    public String getDuration() {
        return duration;
    }
    public String getDeparture() { return departure;  }
    public String getArrival() {
        return arrival;
    }
    public List<Friend> getFriendArray() {
        return friendArray;
    }

    public void addFriend(Friend friend){
        friendArray.add(friend);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(departure);
        parcel.writeString(arrival);
        parcel.writeString(distance);
        parcel.writeString(duration);
    }

    public String getSummary() {
        return summary;
    }


    public void setSummary(String mSummary) {
        this.summary = mSummary;
    }

    public List<Step> getStepArray() {
        return stepArray;
    }

    public void setStepArray(ArrayList<Step> mStepArray) {
        this.stepArray = mStepArray;
    }


}
