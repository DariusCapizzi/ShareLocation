package com.example.darius.sharelocation.ui;
import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Direction;
import com.example.darius.sharelocation.services.UserContactsService;

import butterknife.Bind;
import butterknife.ButterKnife;
public class FriendsActivity extends AppCompatActivity {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.friends) ListView mListView;
    public static final String TAG = MainActivity.class.getSimpleName();
    private String[] names = new String[] {  "Abbott", "Acevedo", "Acosta", "Adams", "Adkins", "Aguilar", "Aguirre", "Albert", "Alexander", "Alford", "Allen", "Allison", "Alston", "Alvarado", "Alvarez", "Anderson", "Andrews", "Anthony", "Armstrong", "Arnold", "Ashley", "Atkins", "Atkinson", "Austin", "Avery", "Avila", "Ayala", "Ayers", "Bailey", "Baird", "Baker", "Baldwin", "Ball", "Ballard", "Banks", "Barber", "Barker", "Barlow", "Barnes", "Barnett", "Barr", "Barrera", "Barrett", "Barron", "Barry", "Bartlett", "Barton", "Bass", "Bates", "Battle", "Bauer", "Baxter", "Beach", "Bean", "Beard", "Beasley", "Beck", "Becker", "Bell", "Bender", "Benjamin", "Bennett", "Benson", "Bentley", "Benton", "Berg", "Berger", "Bernard", "Berry", "Best", "Bird", "Bishop", "Black", "Blackburn", "Blackwell", "Blair", "Blake", "Blanchard", "Blankenship", "Blevins", "Bolton", "Bond", "Bonner", "Booker", "Boone", "Booth", "Bowen", "Bowers", "Bowman", "Boyd", "Boyer", "Boyle", "Bradford", "Bradley", "Bradshaw", "Brady", "Branch", "Bray", "Brennan", };
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    @Bind(R.id.quickbadge)
    QuickContactBadge mBadge;

    private void makeContact(){
        UserContactsService userContactsService = new UserContactsService();
        Log.d(TAG, "makeContact: "+ userContactsService.getContactList(this).size());
        Bitmap mThumbnail =
                userContactsService.loadContactPhotoThumbnail(this, userContactsService.getContactList(this).get(1).get("PHOTO"));
        mBadge.setImageBitmap(mThumbnail);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");

        FriendDetailFragment fragment = new FriendDetailFragment();
        FragmentManager fragmentManager =  getFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder, fragment);
        fragmentTransaction.commit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            makeContact();
        }


        Intent intent = getIntent();

        if (intent.getParcelableExtra(MainActivity.EXTRA_DIRECTION) != null){
            Direction direction =  intent.getParcelableExtra(MainActivity.EXTRA_DIRECTION);
            mTitle.setText(Html.fromHtml(direction.getHtmlInstruction()));
        }

        final int listPosition = intent.getIntExtra(MainActivity.EXTRA_LIST_POSITION, 0);

        mTitle.setTypeface(Amatic);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String friend = ((TextView) view).getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_FRIEND, friend);
                resultIntent.putExtra(MainActivity.EXTRA_LIST_POSITION, listPosition);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
