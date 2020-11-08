package com.example.liquidlookout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SubscribedMatchesActivity extends AppCompatActivity {
    Button goHomeButton;
    ImageView leagueLogo, csgoLogo, apexLogo, fortLogo, dotaLogo, smashLogo, valorantLogo, overwatchLogo;
    ListView subMatches;
    ArrayList<String> sad = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribed_matches);
        leagueLogo = findViewById(R.id.lolView);
        csgoLogo = findViewById(R.id.csgoView);
        apexLogo = findViewById(R.id.apexView);
        fortLogo = findViewById(R.id.fortniteView);
        dotaLogo = findViewById(R.id.dotaView);
        smashLogo = findViewById(R.id.smashView);
        valorantLogo = findViewById(R.id.valorantView);
        goHomeButton = findViewById(R.id.subscribedHomeButton);
        subMatches = findViewById(R.id.subListView);
        overwatchLogo = findViewById(R.id.overwatchView);

        sad.add("Not implemented yet :(");
    }

    public void goHome(View v)
    {
        finish();
    }

    public void showMatches(Games g)
    {
        MyCustomAdapter ad = new MyCustomAdapter(this, R.layout.upcoming_match_layout, NotificationDispatcher.getSubscribedMatches(g));
        subMatches.setAdapter(ad);
        subMatches.setVisibility(View.VISIBLE);
    }

    public void subPageOnClick(View v)
    {
        if (v == leagueLogo)
        {
            showMatches(Games.LOL);
        }
        if (v == csgoLogo)
        {
            showMatches(Games.CSGO);
        }
        if (v == dotaLogo)
        {
            showMatches(Games.DOTA2);
        }
        if (v == overwatchLogo)
        {
            showMatches(Games.OVERWATCH);
        }
        if (v == apexLogo || v == fortLogo || v == smashLogo || v == valorantLogo)
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sad);
            subMatches.setAdapter(adapter);
        }

    }

}
