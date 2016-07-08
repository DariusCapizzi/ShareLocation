package com.example.darius.sharelocation.ui;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Direction;

import butterknife.Bind;
import butterknife.ButterKnife;
public class FriendsActivity extends AppCompatActivity {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.friends) ListView mListView;
    public static final String TAG = MainActivity.class.getSimpleName();
    private String[] names = new String[] {  "Abbott", "Acevedo", "Acosta", "Adams", "Adkins", "Aguilar", "Aguirre", "Albert", "Alexander", "Alford", "Allen", "Allison", "Alston", "Alvarado", "Alvarez", "Anderson", "Andrews", "Anthony", "Armstrong", "Arnold", "Ashley", "Atkins", "Atkinson", "Austin", "Avery", "Avila", "Ayala", "Ayers", "Bailey", "Baird", "Baker", "Baldwin", "Ball", "Ballard", "Banks", "Barber", "Barker", "Barlow", "Barnes", "Barnett", "Barr", "Barrera", "Barrett", "Barron", "Barry", "Bartlett", "Barton", "Bass", "Bates", "Battle", "Bauer", "Baxter", "Beach", "Bean", "Beard", "Beasley", "Beck", "Becker", "Bell", "Bender", "Benjamin", "Bennett", "Benson", "Bentley", "Benton", "Berg", "Berger", "Bernard", "Berry", "Best", "Bird", "Bishop", "Black", "Blackburn", "Blackwell", "Blair", "Blake", "Blanchard", "Blankenship", "Blevins", "Bolton", "Bond", "Bonner", "Booker", "Boone", "Booth", "Bowen", "Bowers", "Bowman", "Boyd", "Boyer", "Boyle", "Bradford", "Bradley", "Bradshaw", "Brady", "Branch", "Bray", "Brennan", };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);
        Typeface Amatic = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");


        Intent intent = getIntent();

        if (intent.getParcelableExtra(MainActivity.EXTRA_DIRECTION) != null){
            Direction direction =  intent.getParcelableExtra(MainActivity.EXTRA_DIRECTION);
            mTitle.setText(Html.fromHtml(direction.getHtmlInstruction()));
        } else {
            Log.d(TAG, "FAILLLLLURRREEE" );
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
