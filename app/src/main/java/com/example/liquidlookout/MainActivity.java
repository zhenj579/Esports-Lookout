package com.example.liquidlookout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiffGamesButton = findViewById(R.id.gamesButton);
        JSONParser parser = new JSONParser("https://api.pandascore.co/teams/liquid-cs-go/tournaments?token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ");
        Thread t = new Thread(parser);
        t.start();
    }

    public void ChangeScreen(View v) {
        if (v == DiffGamesButton) {
            Intent DiffGamesIntent = new Intent(this, DifferentGamesActivity.class);
            startActivity(DiffGamesIntent);
        }
    }

    //test commit Richard
}