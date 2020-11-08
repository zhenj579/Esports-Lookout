package com.example.liquidlookout;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Match {

    private String matchName;
    private ArrayList<Team> teams;
    private ZonedDateTime begin;
    private boolean subscribed;

    public Match() {
        teams = new ArrayList<>();
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ZonedDateTime getBegin() {
        return begin;
    }

    public void setBegin(ZonedDateTime begin) {
        this.begin = begin;
    }

    public void subscribe() {subscribed = true; }

    public void unsubscribe() {subscribed = false; }

    public boolean isSubscribed() { return subscribed; }

    @Override
    public String toString() {
        String result = "";
        result += "Match Name: " + matchName  + "\n";
        result += "Match Start: " + begin + "\n";
        for(Team e : teams) {
            result += e.toString();
        }
        return result;
    }

}
