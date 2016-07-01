package com.example.darius.sharelocation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.findTripButton) Button mFindTripButton;
    @Bind(R.id.departureEditText) EditText mDepartureEditText;
    @Bind(R.id.arrivalEditText) EditText mArrivalEditText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Hello??? ");

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
        mTitle.setTypeface(Amatic);




        mFindTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String departure = mDepartureEditText.getText().toString();
                Log.d("log", "im a log");
                String arrival = mArrivalEditText.getText().toString();
//                Toast.makeText(MainActivity.this, arrival, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, TripActivity.class);
                intent.putExtra("departure", departure);
                intent.putExtra("arrival", arrival);
                startActivity(intent);
            }
        });
    }




}
