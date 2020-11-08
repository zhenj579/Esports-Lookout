package com.example.liquidlookout;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UpcomingMatchesActivity extends AppCompatActivity {
    Button backButton;
    TextView title;
    ListView upcomingMatches;
    String testUpcoming[] = {"your mom vs ur dad", "ur dog vs ur cat", "tim vs mouse"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_matches);
        backButton = findViewById(R.id.backButton);
        title = findViewById(R.id.upMatchText);
        upcomingMatches = findViewById(R.id.upcomingMatchesList);

        MyCustomAdapter ad = new MyCustomAdapter(this, testUpcoming);
        upcomingMatches.setAdapter(ad);
    }
}
