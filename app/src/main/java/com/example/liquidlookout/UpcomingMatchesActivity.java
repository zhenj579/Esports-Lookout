package com.example.liquidlookout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMatchesActivity extends AppCompatActivity {
    Button backButton;
    TextView title;
    ListView upcomingMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_matches);
        backButton = findViewById(R.id.backButton);
        title = findViewById(R.id.upMatchText);
        upcomingMatches = findViewById(R.id.upcomingMatchesList);
        Game g = (Game)getIntent().getSerializableExtra("game_name");
        List<Match> matches = new ArrayList<>();
        if(g == Game.CSGO)
        {
            matches = DataLoader.getInstance().getCsgo().getUpcomingMatches();
        }
        else if(g == Game.LOL)
        {
            matches = DataLoader.getInstance().getLol().getUpcomingMatches();
        }
        MyCustomAdapter ad = new MyCustomAdapter(this, R.layout.upcoming_match_layout, matches);
        upcomingMatches.setAdapter(ad);
    }

    public void goToPrevPage(View v)
    {
        if (v == backButton)
            finish();
    }
}
