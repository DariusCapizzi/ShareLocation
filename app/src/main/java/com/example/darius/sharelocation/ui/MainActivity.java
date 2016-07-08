package com.example.darius.sharelocation.ui;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.darius.sharelocation.R;
import butterknife.Bind;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_FRIENDS = 0;
    public static final String EXTRA_FRIEND = "EXTRA_FRIEND";
    public static final String EXTRA_DIRECTION = "EXTRA_DIRECTION";
    public static final String EXTRA_LIST_POSITION = "EXTRA_LIST_POSITION";
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.findTripButton) Button mFindTripButton;
    @Bind(R.id.seeFriendsButton) Button mSeeFriendsButton;
    @Bind(R.id.departureEditText) EditText mDepartureEditText;
    @Bind(R.id.arrivalEditText) EditText mArrivalEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
        mTitle.setTypeface(Amatic);
        mSeeFriendsButton.setOnClickListener(this);
        mFindTripButton.setOnClickListener(this);
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
            FriendsFragment fragment = new FriendsFragment();
            FragmentManager fragmentManager =  getFragmentManager();
            FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeHolder, fragment);
            fragmentTransaction.commit();


            Intent intent = new Intent(MainActivity.this, FriendsActivity.class);
            startActivityForResult(intent, REQUEST_FRIENDS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FRIENDS){
            if(resultCode == RESULT_OK){

                Log.d(TAG, "onActivityResult: "+ data.getStringExtra(EXTRA_FRIEND)  );
            }
        }
    }
}
