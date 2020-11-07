package com.example.liquidlookout;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataLoader{

    public static final ZonedDateTime today = ZonedDateTime.now();
    public static final String api = "https://api.pandascore.co/";
    public static final String lolUrl = api + "lol/matches/upcoming?";
    public static final String csUrl = api + "csgo/matches/upcoming?";
    public static final String team = api + "teams/";
    public static final String searchRange = "range[begin_at]="+today.getYear() + "-" + today.getMonth().getValue() + "-" + (today.getDayOfMonth() < 10 ? "0"+ today.getDayOfMonth() : today.getDayOfMonth()) +",3000-12-26&";
    public static final String token = "token=l5U9gyKracl0VKg_p-73677Gd9aOsNdduej6R0lEVPXhQu-5rbQ";

    private static DataLoader dt = null;
    private static Thread loader = null;

    private static boolean loaded = false;

    private LOL lol;
    private CSGO csgo;
    private JSONParser parser;

    private DataLoader(final Thread caller) {
        if(loader == null) {
            loader = new Thread(new Runnable() {
                @Override
                public void run() {
                    lol = new LOL();
                    csgo = new CSGO();
                    synchronized (caller) {
                        caller.notify();
                    }
                }
            });
        }
        loader.start();
    }

    public static synchronized void reloadData(Thread caller) {
        dt = new DataLoader(caller);
    }

    public static synchronized void loadData(Thread caller) {
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

    public static String getWebPageAsString(String purl) {
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
}