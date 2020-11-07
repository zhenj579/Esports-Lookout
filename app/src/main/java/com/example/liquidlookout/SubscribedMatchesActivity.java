package com.example.liquidlookout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SubscribedMatchesActivity extends AppCompatActivity {
    Button goHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribed_matches);
        goHomeButton = findViewById(R.id.subscribedHomeButton);
    }

    public void goHome(View v)
    {
        Intent goHomeIntent = new Intent(this, MainActivity.class);
        startActivity(goHomeIntent);
    }

}
