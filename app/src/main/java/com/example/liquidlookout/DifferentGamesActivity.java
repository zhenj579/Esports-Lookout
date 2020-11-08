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

public class DifferentGamesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button DiffGamesHomeButton,TestAdd;
    ListView listOfGames;
    String testArray[]={"a","a","a","a","a","a","a","a","a","a","a","a"};
    int[] ListViewImages = new int[]{
      R.drawable.leaguelogo, R.drawable.leaguelogo,R.drawable.leaguelogo,R.drawable.leaguelogo,R.drawable.leaguelogo,R.drawable.leaguelogo,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.different_games_activity);
        DiffGamesHomeButton = findViewById(R.id.DiffGamesHomeButton);
        listOfGames = findViewById(R.id.gamesList);


        Games league = new Games("league", R.drawable.leaguelogo);
        Games CSGO = new Games("CS GO", R.drawable.csgologo);
        ArrayList<Games> gamesList = new ArrayList<>();
        gamesList.add(league);
        gamesList.add(CSGO);


        GamesListAdapter gameAdapter = new GamesListAdapter(this,R.layout.diff_games_items_for_list,gamesList);
        listOfGames.setAdapter(gameAdapter);





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