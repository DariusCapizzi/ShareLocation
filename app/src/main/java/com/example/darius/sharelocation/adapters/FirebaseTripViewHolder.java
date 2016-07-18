package com.example.darius.sharelocation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Route;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/17/16.
 */
public class FirebaseTripViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    
    public FirebaseTripViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindTrip(Route route) {
        (mView.findViewById(R.id.saveTripButton)).setVisibility(View.GONE);


        TextView mDirectionView = (TextView) mView.findViewById(R.id.tripInfo);
        Log.d("----", "bindTrip: "+route);
        mDirectionView.setText(route.getStartAddress() + "  to  "+ route.getEndAddress());
    }

    @Override
    public void onClick(View view) {

    }


}
