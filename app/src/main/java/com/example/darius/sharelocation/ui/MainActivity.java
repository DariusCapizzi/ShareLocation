package com.example.darius.sharelocation.ui;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.adapters.FirebaseTripViewHolder;
import com.example.darius.sharelocation.models.Route;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_FRIENDS = 0;
    public static final String EXTRA_FRIEND = "EXTRA_FRIEND";
    public static final String EXTRA_DIRECTION = "EXTRA_DIRECTION";
    public static final String EXTRA_LIST_POSITION = "EXTRA_LIST_POSITION";

    private DatabaseReference mTripReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.findTripButton) Button mFindTripButton;
    @Bind(R.id.seeFriendsButton) Button mSeeFriendsButton;
    @Bind(R.id.departureEditText) EditText mDepartureEditText;
    @Bind(R.id.arrivalEditText) EditText mArrivalEditText;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
        mTitle.setTypeface(Amatic);
        mSeeFriendsButton.setOnClickListener(this);
        mFindTripButton.setOnClickListener(this);

        mTripReference = FirebaseDatabase.getInstance().getReference("trip");
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Route, FirebaseTripViewHolder>
                (Route.class, R.layout.direction_list_item, FirebaseTripViewHolder.class,
                        mTripReference) {

            @Override
            protected void populateViewHolder(FirebaseTripViewHolder viewHolder,
                                              Route model, int position) {
                viewHolder.bindTrip(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindTripButton) {
            String departure = mDepartureEditText.getText().toString();
            String arrival = mArrivalEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, TripActivity.class);
            intent.putExtra("departure", departure);
            intent.putExtra("arrival", arrival);
            startActivity(intent);
        } else if(v == mSeeFriendsButton) {
            Intent intent = new Intent(MainActivity.this, FriendsActivity.class);
            startActivityForResult(intent, REQUEST_FRIENDS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FRIENDS){
            if(resultCode == RESULT_OK){

//                Log.d(TAG, "onActivityResult: "+ data.getStringExtra(EXTRA_FRIEND)  );
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
