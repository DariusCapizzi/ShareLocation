package com.example.darius.sharelocation.ui;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.darius.sharelocation.adapters.RouteListAdapter;
import com.example.darius.sharelocation.models.Friend;
import com.example.darius.sharelocation.services.GoogleDirectionsService;
import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
public class TripActivity extends AppCompatActivity{


    public static final String TAG = TripActivity.class.getSimpleName();
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private RouteListAdapter mAdapter;
    private String mInDeparture;
    private String mInArrival;

    public List<Route> mRoutes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
        mTitle.setTypeface(Amatic);

        Bundle extras = getIntent().getExtras();
//        Log.d(TAG, "mRoutes: " + mRoutes.size());
//        Log.d(TAG, "routeArray: " + Route.routeArray.size());


        if (extras != null) {
            mInArrival = extras.getString("arrival");
            mInDeparture = extras.getString("departure");
            if (false) {
                getRoute(mInDeparture, mInArrival);
            }else {
                getRoute("disneyland", "legoland");
            }

        }



    }

    @Override
    protected void onStart() {
        super.onStart();
//
//        Log.d(TAG, "On Start mRoutes: " + mRoutes.size());
//        Log.d(TAG, "On Start routeArray: " + Route.routeArray.size());
    }

    private void getRoute(String origin, String destination) {
        final GoogleDirectionsService googleDirectionService = new GoogleDirectionsService();
        googleDirectionService.findTrip(origin, destination, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response){
                mRoutes = googleDirectionService.processResults(response);
                Log.d(TAG, "onResponse: "+ mRoutes);
                TripActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Route> outRoutes = new ArrayList<>();
                        for (Route route : mRoutes) {
                            outRoutes.add(route);
                        }
                        mAdapter = new RouteListAdapter(getApplicationContext(), outRoutes);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(TripActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.REQUEST_FRIENDS){
            if(resultCode == RESULT_OK){

                int position = data.getIntExtra(MainActivity.EXTRA_LIST_POSITION, 0);
                Friend oldFriend = data.getParcelableExtra(MainActivity.EXTRA_FRIEND);
                oldFriend.addDirectionArray(Route.routeArray.get(position));
                Route.routeArray.get(position).addFriend(oldFriend);

                mRoutes = Route.routeArray;
                mAdapter.notifyItemChanged(position);
            }
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Route.routeArray.clear();
//        Log.d(TAG, "On Destroy mRoutes: " + mRoutes.size());
//        Log.d(TAG, "On Destroy routeArray: " + Route.routeArray.size());
    }


}
