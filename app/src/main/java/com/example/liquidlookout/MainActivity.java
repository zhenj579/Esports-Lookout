package com.example.liquidlookout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button DiffGamesButton;
    Button subMatchButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiffGamesButton = findViewById(R.id.gamesButton);
        subMatchButton = findViewById(R.id.subscribedButton);
    }

    private void printData() {
        ArrayList<Match> matches = DataLoader.getInstance().getLol().getUpcomingMatches();
        for(Match e : matches) {
            Log.e("Match", e.toString());
        }
        ArrayList<Match> matchesC = DataLoader.getInstance().getCsgo().getUpcomingMatches();
        Log.i("CSGO", "///////////////////////////////////////////////////////////");
        for(Match e : matchesC) {
            Log.e("Match", e.toString());
        }
    }

    public void ChangeScreen(View v) {
        if (v == DiffGamesButton) {
            Intent DiffGamesIntent = new Intent(this, DifferentGamesActivity.class);
            startActivity(DiffGamesIntent);
        }
        if (v == subMatchButton)
        {
            Intent subMatchesIntent = new Intent(this, UpcomingMatchesActivity.class);
            startActivity(subMatchesIntent);
        }

    }

    //test commit Richard
}