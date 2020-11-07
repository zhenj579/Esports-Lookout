package com.example.liquidlookout;

import android.util.Log;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class CSGO {

    private ArrayList<Match> upcoming;

    public CSGO() {
        fetchUpcomingMatches();
    }

    private void fetchUpcomingMatches() {
        ZonedDateTime now = ZonedDateTime.now();
        now = now.withZoneSameLocal(ZoneId.of("Z"));
        String test = DataLoader.getWebPageAsString(DataLoader.lolUrl+ "matches?range[begin_at]=" + now.toString() + ","  + now.plusYears(1000) + "&" + DataLoader.token);
        Log.e("test", test);
    }

    public ArrayList<Match> getUpcomingMatches() {
        return null;
    }

}