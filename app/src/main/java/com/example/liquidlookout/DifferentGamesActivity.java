package com.example.liquidlookout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
        listOfGames = findViewById(R.id.gameList);

        //making two game objects league and csgo
        Game league = new Game("league", R.drawable.leaguelogo, Games.LOL);
        Game CSGO = new Game("CSGO", R.drawable.csgologo, Games.CSGO);
        Game dota2 = new Game("dota2", R.drawable.dotalogo, Games.DOTA2);
        Game overwatch = new Game("Overwatch", R.drawable.overwatchlogo,Games.OVERWATCH);
        //making a list of those games
        ArrayList<Game> gameList = new ArrayList<>();
        gameList.add(league);
        gameList.add(CSGO);
        gameList.add(dota2);
        gameList.add(overwatch);

        //making adapter for the games in the list of the adapter
        GamesListAdapter gameAdapter = new GamesListAdapter(this,R.layout.diff_games_items_for_list, gameList);
        listOfGames.setAdapter(gameAdapter);


    }
    public void onClicks(View v){
        //if home is clicked return home
        if(v == DiffGamesHomeButton){
            finish();

        }
    }

}