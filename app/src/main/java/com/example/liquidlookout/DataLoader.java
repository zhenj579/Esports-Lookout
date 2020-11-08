package com.example.liquidlookout;

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataLoader{

    public static final ZonedDateTime today = ZonedDateTime.now();
    public static final String api = "https://api.pandascore.co/";
    public static final String lolUrl = api + "lol/matches/upcoming?";
    public static final String csUrl = api + "csgo/matches/upcoming?";
    public static final String team = api + "teams/";
    public static final String token = "token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ";

    private static DataLoader dt = null;

    private static boolean loaded = false;

    private LOL lol;
    private CSGO csgo;

    private ExecutorService es = Executors.newFixedThreadPool(2);

    private final static int NUM_OF_GAMES = 2;
    private int count = 0;

    private DataLoader(final DataObserver caller) {
        es.execute(new Runnable() {
            @Override
            public void run() {
                lol = new LOL();
                checkIfDone(caller);
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                csgo = new CSGO();
                checkIfDone(caller);
            }
        });
    }

    private DataLoader(final Object caller) {
        es.execute(new Runnable() {
            @Override
            public void run() {
                lol = new LOL();
                checkIfDone(caller);
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                csgo = new CSGO();
                checkIfDone(caller);
            }
        });
        synchronized (caller) {
            caller.notify();
        }
    }

    private void checkIfDone(Object caller) {
        if(count == NUM_OF_GAMES) {
            releaseObj(caller);
        } else {
            count++;
        }
    }

    private void releaseObj(Object caller) {
        if(caller instanceof DataObserver){
            ((DataObserver)caller).update();
            es.shutdown();
            return;
        }
        synchronized (caller) {
            es.shutdown();
            caller.notify();
        }
    }

    public static synchronized void reloadData(Object caller) {
        dt = new DataLoader(caller);
    }

    public static synchronized void loadData(DataObserver caller) {
        if(dt == null) {
            dt = new DataLoader(caller);
            loaded = true;
        }
    }

    public static synchronized void loadData(Object caller) {
        if(dt == null) {
            synchronized (caller) {
                try {
                    dt = new DataLoader(caller);
                    caller.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            loaded = true;
        }
    }

    public static DataLoader getInstance() {
        if(loaded)
            return dt;
        else {
            System.err.println("loadData() must be called before getInstance()");
            return null;
        }
    }

    public LOL getLol() {
        return lol;
    }

    public CSGO getCsgo() {
        return csgo;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static String getWebPageAsString(String purl) {
        String result = "-1";
        try {
            URL url = new URL(purl);
            HttpURLConnection hurl = (HttpURLConnection) url.openConnection();
            try {
                InputStream is = hurl.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                result = readAll(rd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Match> fetchUpcomingMatches(Game g) {
        ArrayList<Match> upcoming = new ArrayList<>();
        String url = null;

        if(g == Game.LOL)
        {
            url = getWebPageAsString(lolUrl + token);
        }
        else if(g == Game.CSGO)
        {
            url = getWebPageAsString(csUrl + token);
        }
        try {
            JSONArray jsonArr = new JSONArray(url);
            for(int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                addMatch(upcoming, obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return upcoming;
    }

    private static void addMatch(ArrayList<Match> upcoming, JSONObject matchJson) throws JSONException {
        Match match = new Match();
        match.setMatchName(matchJson.getString("name"));
        if(DataLoader.checkForTBD(match.getMatchName()))
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

    private static boolean checkForTBD(String name) {
        int l = name.length();
        if(name.charAt(l-3) == 'T' && name.charAt(l-2) == 'B' && name.charAt(l-1) == 'D')
            return true;
        return false;
    }
}