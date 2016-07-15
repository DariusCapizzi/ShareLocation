package com.example.darius.sharelocation.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Guest on 7/8/16.
 */
public class Friend implements Parcelable{
    public static ArrayList<Friend> friendArrayList = new ArrayList<>();
    private ArrayList<Route> mRouteArray = new ArrayList<>();


    private Bitmap mThumb;
    private String mFriendName;
    private String mNumber;

    public Friend(Bitmap thumb, String name, String number) {
        this.mThumb = thumb;
        this.mFriendName = name;
        this.mNumber = number;
        friendArrayList.add(this);
    }

    protected Friend(Parcel in) {
        mThumb = in.readParcelable(Bitmap.class.getClassLoader());
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

    public Bitmap getThumb() {
        return mThumb;
    }

    public void setThumb(Bitmap thumb) {
        this.mThumb = thumb;
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
        parcel.writeParcelable(mThumb, i);
        parcel.writeString(mFriendName);
        parcel.writeString(mNumber);
    }

    public ArrayList<Route> getDirectionArray() {
        return mRouteArray;
    }

    public void addDirectionArray(Route route) {
        this.mRouteArray.add(route);
    }
}
