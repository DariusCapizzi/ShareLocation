package com.example.darius.sharelocation.ui;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.darius.sharelocation.R;
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
        mTitle.setTypeface(Amatic);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        mListView.setAdapter(adapter);
    }
}
