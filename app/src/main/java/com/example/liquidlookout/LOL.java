package com.example.liquidlookout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class LOL {

    private ArrayList<Match> upcoming;

    public LOL() {
        upcoming = new ArrayList<>();
        fetchUpcomingMatches();
    }

    private void fetchUpcomingMatches() {
        String test = DataLoader.getWebPageAsString(DataLoader.lolUrl + DataLoader.token);
        try {
            JSONArray jsonArr = new JSONArray(test);
            for(int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                addMatch(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addMatch(JSONObject matchJson) throws JSONException {
        Match match = new Match();
        match.setMatchName(matchJson.getString("name"));
        if(match.getMatchName().contains("TBD vs TBD"))
            return;
        match.setBegin(ZonedDateTime.parse(matchJson.getString("begin_at")));
        JSONArray opponents = matchJson.getJSONArray("opponents");
        for(int i = 0; i < opponents.length(); i++) {
            JSONObject teamJson = opponents.getJSONObject(i).getJSONObject("opponent");
            Team team = new Team();
            team.setName(teamJson.getString("name"));
            team.setSlug(teamJson.getString("slug"));
            team.setLogoUrl(teamJson.getString("image_url"));
            match.getTeams().add(team);
        }
        upcoming.add(match);
    }

    public ArrayList<Match> getUpcomingMatches() {
        return upcoming;
    }
}
