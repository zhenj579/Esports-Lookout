package com.example.liquidlookout;

import java.util.ArrayList;

public class Team {

    private String name;
    private int numOfPlayers;
    private String logoUrl;
    private ArrayList<Player> players;

    public Team() {

    }

    public Team(String name, int numOfPlayers, String logoUrl, ArrayList<Player> players) {
        this.name = name;
        this.numOfPlayers = numOfPlayers;
        this.logoUrl = logoUrl;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
