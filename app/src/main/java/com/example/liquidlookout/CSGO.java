package com.example.liquidlookout;

import java.util.ArrayList;

public class CSGO{


    private ArrayList<Match> upcomingMatches;

    public CSGO() {
        upcomingMatches = DataLoader.fetchUpcomingMatches(Games.CSGO);
    }

    public ArrayList<Match> getUpcomingMatches() {
        return upcomingMatches;
    }
}