package com.example.darius.sharelocation;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;

public class FriendsActivity extends AppCompatActivity {
    @Bind()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");

        mTitle.setTypeface(Amatic);
    }
}
