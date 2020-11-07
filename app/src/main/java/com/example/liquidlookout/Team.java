package com.example.liquidlookout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Team {

    private String name;
    private String slug;
    private int numOfPlayers;
    private String logoUrl;
    private Bitmap logo;
    private ArrayList<Player> players;

    public Team() {

    }

    public Team(String name, int numOfPlayers, String logoUrl, ArrayList<Player> players) {
        this.name = name;
        this.numOfPlayers = numOfPlayers;
        this.logoUrl = logoUrl;
        this.players = players;

        fetchLogo();
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public Bitmap getLogo() {
        return logo;
    }

    private void fetchLogo() {
        if(logoUrl.charAt(0) != 'n') {
            try {
                URL imgUrl = new URL(logoUrl);
                logo = BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        fetchLogo();
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        String result = "";
        result += "\tTeam Name: " + name + "\n";
        result += "\tSlug: " + name + "\n";
        return result;
    }
}
