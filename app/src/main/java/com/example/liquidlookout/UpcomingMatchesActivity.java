package com.example.liquidlookout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Games g = (Games)getIntent().getSerializableExtra("game_name");

        List<Match> matches = null;
        if(g == Games.OVERWATCH)
        {
            matches = DataLoader.getInstance().getOW().getUpcomingMatches();
        }
        else if(g == Games.DOTA2)
        {
            matches = DataLoader.getInstance().getDota2().getUpcomingMatches();
        }
        else if(g == Games.CSGO)
        {
            matches = DataLoader.getInstance().getCsgo().getUpcomingMatches();
        }
        else if(g == Games.LOL)
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
