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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button DiffGamesButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiffGamesButton = findViewById(R.id.gamesButton);
        //https://api.pandascore.co/lol/matches/upcoming?range[begin_at]=2020-11-07T17:00:00Z,2039-04-08T17:00:00Z&token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ
        JSONParser parser = new JSONParser(DataLoader.lolUrl+DataLoader.searchRange+DataLoader.token);
        //https://api.pandascore.co/csgo/matches?range[begin_at]=2020-11-07T17:00:00Z,2039-04-08T17:00:00Z&token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ
        Thread t = new Thread(parser);
        t.start();
        DataLoader.loadData(Thread.currentThread());
    }

    public void ChangeScreen(View v) {
        if (v == DiffGamesButton) {
            Intent DiffGamesIntent = new Intent(this, DifferentGamesActivity.class);
            startActivity(DiffGamesIntent);
        }
    }

    //test commit Richard
}