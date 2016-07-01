package com.example.darius.sharelocation;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TripActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.tripInfo) ListView mTripInfo;
    public List<Direction> mDirections = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trip);

        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
        mTitle.setTypeface(Amatic);

        Bundle extras = getIntent().getExtras();
        String departure;
        String pattern = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$\n";
        boolean isMatch = Pattern.matches(pattern, extras.getString("departure"));
        if (extras != null) {
            if(isMatch){
                departure = extras.getString("departure");
                getRoute("45.467166,-122.610763", "45.520705,-122.677408");
            } else {
                getRoute("45.467166,-122.610763", "45.520705,-122.677408");
                Toast.makeText(TripActivity.this, "not a literal coordinate, so we will use", Toast.LENGTH_LONG).show();
            }
        }



        getRoute("45.467166,-122.610763", "45.520705,-122.677408");
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
                        ArrayList<String> outDirections = new ArrayList<String>();

                        for (Direction direction : mDirections) {
                            outDirections.add(direction.getDistance() + " " +  direction.getDuration() + " " + direction.getHtmlInstruction());
//                            Log.d(TAG, "distance: " + direction.getDistance());
//                            Log.d(TAG, "duration: " + direction.getDuration());
//                            Log.d(TAG, "html instruction : " + direction.getHtmlInstruction());
                        }
                        ArrayAdapter adapter = new ArrayAdapter(TripActivity.this, android.R.layout.simple_list_item_1, outDirections);
                        mTripInfo.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
