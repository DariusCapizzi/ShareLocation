package com.example.darius.sharelocation.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.adapters.StepListAdapter;
import com.example.darius.sharelocation.models.Route;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/15/16.
 */
public class TripDetailFragment  extends Fragment {
    private Route mRoute;
    private LinearLayoutManager layoutManager;
    private StepListAdapter mAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    public static final String TAG = TripDetailFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);
        mRoute = getArguments().getParcelable("route");

        mAdapter = new StepListAdapter(getActivity().getApplicationContext(), mRoute.getStepArray());
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

//        Log.d(TAG, "onCreateView: made it to fragment" + mRoute.getStepArray().size());
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
