package com.example.liquidlookout;

import java.util.ArrayList;

public class Dota2{

    private ArrayList<Match> upcomingMatches;
    public Dota2()
    {
        upcomingMatches = DataLoader.fetchUpcomingMatches(Games.DOTA2);
    }

    public ArrayList<Match> getUpcomingMatches() { return upcomingMatches; }


}
