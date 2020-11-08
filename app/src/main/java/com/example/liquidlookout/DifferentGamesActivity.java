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

import java.util.ArrayList;

public class DifferentGamesActivity extends AppCompatActivity  {
    //declating buttons and list
    Button DiffGamesHomeButton;
    ListView listOfGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //seting up the activity by geting info from the xml
        setContentView(R.layout.different_games_activity);
        DiffGamesHomeButton = findViewById(R.id.DiffGamesHomeButton);
        listOfGames = findViewById(R.id.gamesList);

        //making two game objects league and csgo
        Games league = new Games("league", R.drawable.leaguelogo);
        Games CSGO = new Games("CS GO", R.drawable.csgologo);
        //making a list of those games
        ArrayList<Games> gamesList = new ArrayList<>();
        gamesList.add(league);
        gamesList.add(CSGO);

        //making adapter for the games in the list of the adapter
        GamesListAdapter gameAdapter = new GamesListAdapter(this,R.layout.diff_games_items_for_list,gamesList);
        listOfGames.setAdapter(gameAdapter);


    }
    public void onClicks(View v){
        //if home is clicked return home
        if(v == DiffGamesHomeButton){
            finish();

        }
    }

}