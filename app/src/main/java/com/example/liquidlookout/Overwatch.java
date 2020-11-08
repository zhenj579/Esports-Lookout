package com.example.liquidlookout;

import java.util.ArrayList;

public class Overwatch{

    public ArrayList<Match> getUpcomingMatches() {
        return upcomingMatches;
    }

    private ArrayList<Match> upcomingMatches;
    public Overwatch()
    {
        upcomingMatches = DataLoader.fetchUpcomingMatches(Games.OVERWATCH);
    }
}
