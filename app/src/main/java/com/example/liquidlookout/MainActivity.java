package com.example.liquidlookout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
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

    public static final String CHANNEL_ID = "12";

    Button DiffGamesButton;
    Button subMatchButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiffGamesButton = findViewById(R.id.gamesButton);
        subMatchButton = findViewById(R.id.subscribedButton);
        createNotificationChannel();
//        //https://api.pandascore.co/lo    l/matches/upcoming?range[begin_at]=2020-11-07T17:00:00Z,2039-04-08T17:00:00Z&token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ
//        JSONParser parser = new JSONParser(DataLoader.lolUrl+DataLoader.searchRange+DataLoader.token);
//        //https://api.pandascore.co/csgo/matches?range[begin_at]=2020-11-07T17:00:00Z,2039-04-08T17:00:00Z&token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ
//        Thread t = new Thread(parser);
//        t.start();
        long start = System.currentTimeMillis();
        DataLoader.loadData(this);
        Log.e("Elapsed Time", ((System.currentTimeMillis() - start) / 1000.0) + " seconds");
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
            Intent subMatchesIntent = new Intent(this, SubscribedMatchesActivity.class);
            startActivity(subMatchesIntent);
        }

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notif test";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }


    //test commit Richard
}