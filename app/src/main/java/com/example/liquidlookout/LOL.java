package com.example.liquidlookout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class LOL {

    private ArrayList<Match> upcoming;

    public LOL() {
        upcoming = DataLoader.fetchUpcomingMatches(Game.LOL);
    }


    public ArrayList<Match> getUpcomingMatches() {
        return upcoming;
    }
}
