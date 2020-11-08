package com.example.liquidlookout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GamesListAdapter extends ArrayAdapter<Game> {
    private String TAG = "gamesAdapter";
    private Context context;
    private int res;

    public GamesListAdapter(Context context, int resource, ArrayList<Game> list){
        super(context,resource,list);
        this.context=context;
        res=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //geting input name and pictures
        int  imageUrl = getItem(position).getImgUrl();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(res,parent,false);

        ImageView imagePic = convertView.findViewById(R.id.gamePic);
        ImageView BlackArrow = convertView.findViewById(R.id.blackArrow);

        imagePic.setImageResource(imageUrl);
        BlackArrow.setImageResource(R.drawable.arrow_right);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upcomingMatchIntent = new Intent(context,UpcomingMatchesActivity.class);
                String gameName = getItem(position).getName();
                Games g = null;
                if(gameName.equals("league")) g = Games.LOL;
                else if(gameName.equals("CSGO")) g = Games.CSGO;
                else if(gameName.equals("dota2")) g = Games.DOTA2;
                else if(gameName.equals("Overwatch")) g = Games.OVERWATCH;
                upcomingMatchIntent.putExtra("game_name", g);
                context.startActivity(upcomingMatchIntent);
            }
        });

        return convertView;
    }




}
