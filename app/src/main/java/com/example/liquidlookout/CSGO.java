package com.example.liquidlookout;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class CSGO {

    private ArrayList<Match> upcoming;

    public CSGO() {
        upcoming = DataLoader.fetchUpcomingMatches(Game.CSGO);
    }

    public ArrayList<Match> getUpcomingMatches() {
        return upcoming;
    }

}