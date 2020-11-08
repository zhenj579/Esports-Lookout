package com.example.liquidlookout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SubscribedMatchesActivity extends AppCompatActivity {
    Button goHomeButton;
    ImageView leagueLogo, csgoLogo, apexLogo, fortLogo, dotaLogo, smashLogo, valorantLogo;
    ListView subMatches;
    String testLeague[] = {"tsm", "tsm", "tsm", "tsm", "tsm", "tsm", "tsm", "tsm"};
    String testCSGO[] = {"tsmcs", "tsmcs","tsmcs","tsmcs","tsmcs","tsmcs","tsmcs"};
    String sad[] = {"Not implemented yet :("};

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
    }

    public void goHome(View v)
    {
        finish();
    }

    public void showMatches(String[] a)
    {
        ArrayAdapter<String> matchesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,a);
        subMatches.setAdapter(matchesAdapter);
        subMatches.setVisibility(View.VISIBLE);
    }

    public void subPageOnClick(View v)
    {
        if (v == leagueLogo)
            showMatches(testLeague);
        if (v == csgoLogo)
            showMatches(testCSGO);
        if (v == apexLogo || v == fortLogo || v == dotaLogo || v == smashLogo || v == valorantLogo)
            showMatches(sad);

    }

}
