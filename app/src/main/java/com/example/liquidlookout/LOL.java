package com.example.liquidlookout;

import java.util.ArrayList;

public class LOL{

    public ArrayList<Match> getUpcomingMatches() {
        return upcomingMatches;
    }

    private ArrayList<Match> upcomingMatches;
    public LOL() {
        upcomingMatches = DataLoader.fetchUpcomingMatches(Games.LOL);
    }


}
