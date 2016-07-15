package com.example.darius.sharelocation.ui;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;
import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.adapters.FriendListAdapter;
import com.example.darius.sharelocation.models.Route;
import com.example.darius.sharelocation.models.Friend;
import com.example.darius.sharelocation.services.UserContactsService;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
public class FriendsActivity extends AppCompatActivity {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.friendsRecyclerView) RecyclerView mFriendsRecyclerView;
    private FriendListAdapter mAdapter;
    private Route inRoute;

    public static final String TAG = FriendsActivity.class.getSimpleName();
    private String[] names = new String[] {  "Abbott", "Acevedo", "Acosta", "Adams", "Adkins", "Aguilar", "Aguirre", "Albert", "Alexander", "Alford", "Allen", "Allison", "Alston", "Alvarado", "Alvarez", "Anderson", "Andrews", "Anthony", "Armstrong", "Arnold", "Ashley", "Atkins", "Atkinson", "Austin", "Avery", "Avila", "Ayala", "Ayers", "Bailey", "Baird", "Baker", "Baldwin", "Ball", "Ballard", "Banks", "Barber", "Barker", "Barlow", "Barnes", "Barnett", "Barr", "Barrera", "Barrett", "Barron", "Barry", "Bartlett", "Barton", "Bass", "Bates", "Battle", "Bauer", "Baxter", "Beach", "Bean", "Beard", "Beasley", "Beck", "Becker", "Bell", "Bender", "Benjamin", "Bennett", "Benson", "Bentley", "Benton", "Berg", "Berger", "Bernard", "Berry", "Best", "Bird", "Bishop", "Black", "Blackburn", "Blackwell", "Blair", "Blake", "Blanchard", "Blankenship", "Blevins", "Bolton", "Bond", "Bonner", "Booker", "Boone", "Booth", "Bowen", "Bowers", "Bowman", "Boyd", "Boyer", "Boyle", "Bradford", "Bradley", "Bradshaw", "Brady", "Branch", "Bray", "Brennan", };
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


//    @Bind(R.id.quickbadge)
//    QuickContactBadge mBadge;

    private void makeContact(){
        UserContactsService userContactsService = new UserContactsService();
//        Log.d(TAG, "makeContact: "+ userContactsService.getContactList(this).size());
        ArrayList<Bitmap> thumbs = new ArrayList<>();


        for(int i = 0; i<userContactsService.getContactList(this).size(); i++){

            new Friend(
                    userContactsService.loadContactPhotoThumbnail(this, userContactsService.getContactList(this).get(i).get("PHOTO")),
                    userContactsService.getContactList(this).get(i).get("NAME"),
                    userContactsService.getContactList(this).get(i).get("NUMBER")
                    );


        }

//        mBadge.setImageBitmap(thumbs.get(0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");

//        FriendDetailFragment fragment = new FriendDetailFragment();
//        FragmentManager fragmentManager =  getFragmentManager();
//        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.placeHolder, fragment);
//        fragmentTransaction.commit();
        Intent intent = getIntent();
        if (intent.getParcelableExtra(MainActivity.EXTRA_DIRECTION) != null){

            inRoute = intent.getParcelableExtra(MainActivity.EXTRA_DIRECTION);
            mTitle.setText(Html.fromHtml(inRoute.getEndAddress()));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            makeContact();
        }

        final int listPosition = intent.getIntExtra(MainActivity.EXTRA_LIST_POSITION, 0);

        mTitle.setTypeface(Amatic);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Friend.friendArrayList);
//        mListView.setAdapter(adapter);
        mAdapter = new FriendListAdapter(getApplicationContext(), Friend.friendArrayList, listPosition);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(FriendsActivity.this);
        mFriendsRecyclerView.setLayoutManager(layoutManager);
        mFriendsRecyclerView.setAdapter(mAdapter);
        mFriendsRecyclerView.setHasFixedSize(true);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String friend = ((TextView) view).getText().toString();
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra(MainActivity.EXTRA_FRIEND, friend);
//                resultIntent.putExtra(MainActivity.EXTRA_LIST_POSITION, listPosition);
//                setResult(RESULT_OK, resultIntent);
//                finish();
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeContact();
            }
        }
    }

    @Override
    protected void onDestroy() {
        Friend.friendArrayList.clear();
        super.onDestroy();
    }
}
