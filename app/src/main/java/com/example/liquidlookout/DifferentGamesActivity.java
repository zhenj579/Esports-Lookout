package com.example.liquidlookout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DifferentGamesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button DiffGamesHomeButton,TestAdd;
    ListView listOfGames;
    String testArray[]={"a","a","a","a","a","a","a","a","a","a","a","a"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.different_games_activity);
        DiffGamesHomeButton = findViewById(R.id.DiffGamesHomeButton);
        listOfGames = findViewById(R.id.gamesList);

        ArrayAdapter<String> gamesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,testArray);
        listOfGames.setAdapter(gamesAdapter);
        listOfGames.setOnItemClickListener(this);
    }
    public void onClicks(View v){
        if(v == DiffGamesHomeButton){
            finish();

        }else if(v==TestAdd){

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}