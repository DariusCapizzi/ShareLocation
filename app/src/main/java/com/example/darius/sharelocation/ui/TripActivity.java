package com.example.darius.sharelocation.ui;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.darius.sharelocation.adapters.DirectionListAdapter;
import com.example.darius.sharelocation.services.GoogleDirectionsService;
import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Direction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
public class TripActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.title) TextView mTitle;


    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DirectionListAdapter mAdapter;

    public List<Direction> mDirections = new ArrayList<>();
    private boolean mIsMatch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
        mTitle.setTypeface(Amatic);

        Bundle extras = getIntent().getExtras();
        Log.d(TAG, "onCreate: " + Direction.directionArray.size());
        if (Direction.directionArray.size() == 0){
            if (extras != null && mIsMatch) {
                getRoute(extras.getString("departure"), extras.getString("arrival"));
            } else {
                getRoute("disneyland", "legoland");
            }
        }
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
                mDirections = googleDirectionService.processResults(response);
                Log.d(TAG, "onResponse: "+ mDirections);
                TripActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Direction> outDirections = new ArrayList<Direction>();
                        for (Direction direction : mDirections) {
                            outDirections.add(direction);
                        }
                        mAdapter = new DirectionListAdapter(getApplicationContext(), outDirections);
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
                Direction.directionArray.get(position).setFriend(data.getStringExtra(MainActivity.EXTRA_FRIEND));
                mAdapter.notifyItemChanged(position);
                Log.d(TAG, "trip activity: "+  Direction.directionArray.get(position).getFriend());
            }
        }
    }
}
