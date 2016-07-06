package com.example.darius.sharelocation.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darius.sharelocation.R;

/**
 * Created by Guest on 7/6/16.
 */
public class FriendsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
