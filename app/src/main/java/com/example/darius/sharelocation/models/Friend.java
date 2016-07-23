package com.example.darius.sharelocation.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Guest on 7/8/16.
 */
public class Friend implements Parcelable{
    public static ArrayList<Friend> friendArrayList = new ArrayList<>();
    private ArrayList<Route> mRouteArray = new ArrayList<>();


    private String name;
    private String email;

    private String mThumbUri;
    private String mFriendName;
    private String mNumber;

    public Friend() {
    }

    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Friend(String thumb, String name, String number) {
        this.mThumbUri = thumb;
        this.mFriendName = name;
        this.mNumber = number;
        friendArrayList.add(this);
    }

    protected Friend(Parcel in) {
        mThumbUri = in.readString();
        mFriendName = in.readString();
        mNumber = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public String getThumbUri() {
        return mThumbUri;
    }

    public void setThumb(String thumb) {
        this.mThumbUri = thumb;
    }

    public String getFriendName() {
        return mFriendName;
    }

    public void setFriendName(String name) {
        this.mFriendName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mThumbUri);
        parcel.writeString(mFriendName);
        parcel.writeString(mNumber);
    }

    public ArrayList<Route> getDirectionArray() {
        return mRouteArray;
    }

    public void addDirectionArray(Route route) {
        this.mRouteArray.add(route);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
