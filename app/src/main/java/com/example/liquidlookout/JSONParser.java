package com.example.liquidlookout;

import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {

    String url;

    JSONParser(final String url)
    {
        this.url = url;
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void run() {
//        try
//        {
//            URL url = new URL(this.url);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            try {
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                reader = getJSONReader(in);
////                List<Pair<ZonedDateTime,List<String>>> upcomingMatches = getUpcomingMatches();
////                List<String> slugs = getSlugs(upcomingMatches);
////                List<Pair<String,String>> acronymsAndLogoUrls = getAcronymsAndLogoUrls(slugs);
////                Map<String, Pair<String,String>> upcomingMatchesTeamInfo = getTeamInfo(slugs,acronymsAndLogoUrls);
////                displayTeamContent(upcomingMatchesTeamInfo);
////                displayMatchContent(upcomingMatches);
//            } finally {
//                urlConnection.disconnect();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Pair<String,String>> getAcronymsAndLogoUrls(List<String> slugs) throws IOException {
        List<Pair<String,String>> acronymsAndLogoUrls = new ArrayList<>();
        for(String slug : slugs)
        {
            acronymsAndLogoUrls.add(readJson(getTeamURL(slug)));
        }
        return acronymsAndLogoUrls;
    }

    public List<String> getSlugs(List<Pair<ZonedDateTime, List<String>>> matches)
    {
        List<String> slugs = new ArrayList<>();
        for(Pair<ZonedDateTime,List<String>> p : matches)
        {
            if(p != null && p.second.size() > 1)
            {
                slugs.add(p.second.get(0));
                slugs.add(p.second.get(1));
            }
        }
        return slugs;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public final List<Pair<ZonedDateTime, List<String>>> getUpcomingMatches(JsonReader reader) throws IOException
    {
        List<Pair<ZonedDateTime, List<String>>> contents = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext())
        {
            contents.add(readMatchObject(reader));
        }
        reader.endArray();
        return contents;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Pair<ZonedDateTime, List<String>> readMatchObject(JsonReader reader) throws IOException
    {
        String begin = "";
        List<String> opponents = new ArrayList<>();
        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            if(name.equals("opponents") && reader.peek() != JsonToken.NULL) opponents = readOpponentArray(reader);
            else if(name.equals("begin_at")) begin = reader.nextString();
            else reader.skipValue();
        }
        reader.endObject();
        if(opponents.isEmpty()) return null;
        return new Pair<ZonedDateTime, List<String>>(ZonedDateTime.parse(begin), opponents);
    }
    private List<String> readOpponentArray(JsonReader reader) throws IOException
    {
        List<String> opponents = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext())
        {
            opponents.add(readOpponent(reader));
        }
        reader.endArray();
        return opponents;
    }

    private Pair<String,String> readJson(String url)
    {
        try
        {
            URL teamUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) teamUrl.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                JsonReader teamReader = getJSONReader(in);
                Pair<String,String> data = readTeamObject(teamReader);
                return data;
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getTeamURL(String slug) throws IOException
    {
        String teamUrl = DataLoader.team+slug+"?"+DataLoader.token;
        return teamUrl;
    }

    private Pair<String, String> readTeamObject(JsonReader teamReader) throws IOException
    {
        String acronym = "null";
        String logoURL = "null";
        teamReader.beginObject();
        while(teamReader.hasNext())
        {
            String name = teamReader.nextName();
            if(name.equals("acronym") && teamReader.peek() != JsonToken.NULL) acronym = teamReader.nextString();
            else if(name.equals("image_url") && teamReader.peek() != JsonToken.NULL) logoURL = teamReader.nextString();
            else teamReader.skipValue();
        }
        teamReader.endObject();
        return Pair.create(acronym, logoURL);
    }

    private String readOpponent(JsonReader reader) throws IOException
    {
        String opponent = "";
        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            if(name.equals("opponent"))
            {
                reader.beginObject();
                while(reader.hasNext())
                {
                    String nestedName = reader.nextName();
                    if(nestedName.equals("slug")) opponent = reader.nextString();
                    else reader.skipValue();
                }
                reader.endObject();
            }
            else reader.skipValue();
        }
        reader.endObject();
        return opponent;
    }
    private JsonReader getJSONReader(InputStream stream)
    {
        Reader rd = new InputStreamReader(stream);
        JsonReader jsonReader = new JsonReader(rd);
        return jsonReader;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayMatchContent(List<Pair<ZonedDateTime, List<String>>> text)
    {
        for(Pair<ZonedDateTime, List<String>> p : text)
        {
            if(p != null && p.second.size() > 1) System.out.println(formatDateAndTime(p.first) + " " + p.second.get(0) + " vs " + p.second.get(1));
        }
    }

    public void displayTeamContent(Map<String,Pair<String,String>> teams)
    {
        for(String key : teams.keySet())
        {
            System.out.println("Acronym:" + teams.get(key).first + " Logo URL:" + teams.get(key).second);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String formatDateAndTime(ZonedDateTime formatted)
    {
        int minutes = formatted.getMinute();
        String stringminutes = Integer.toString(minutes);
        if(minutes == 0) stringminutes+="0";
        return formatted.getMonth() + " " + formatted.getDayOfMonth() + " " + formatted.getYear() + " " + formatted.getHour() + ":" + stringminutes + " UTC";
    }

    @RequiresApi(api = Build.VERSION_CODES.O) // returns a map w/ key:slugs value: pair(acronym,teamlogourl)
    public final Map<String, Pair<String,String>> getTeamInfo(List<String> slugs, List<Pair<String,String>> acronymsAndLogoUrls) throws IOException {
        Map<String, Pair<String,String>> teamInfo = new HashMap<>(); // key = team slug, pair.first = team acronym, pair.second = team logo url
        for(int i = 0; i < slugs.size(); i++)
        {
            teamInfo.put(slugs.get(i), acronymsAndLogoUrls.get(i));
        }
        return teamInfo;
    }

    public final String getUrl()
    {
        return url;
    }

}
