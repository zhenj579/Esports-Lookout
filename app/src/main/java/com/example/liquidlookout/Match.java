package com.example.liquidlookout;

import android.content.Context;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Match {

    private String matchName;
    private ArrayList<Team> teams;
    private ZonedDateTime begin;
    private boolean subscribed;
    private boolean wasNotified = false;
    private Games game;

    public Match(Games game) {

        teams = new ArrayList<>();
        subscribed = false;
        this.game = game;
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

    public Games getGame() { return game; }

    public void setBegin(ZonedDateTime begin) {
        this.begin = begin;
    }

    public void subscribe() {
        if(!subscribed)
        {
            subscribed = true;
            NotificationDispatcher.addMatch(this);
        }
        else {
            subscribed = false;
            NotificationDispatcher.removeMatch(this);
        }
    }

    public void sendNotification(Context context) {
        if(!wasNotified) {
            NotificationDispatcher.sendNotification("It's time!", matchName + " is starting now!");
            wasNotified = true;
        }
    }

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
