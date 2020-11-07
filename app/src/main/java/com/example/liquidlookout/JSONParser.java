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
import java.util.List;

public class JSONParser implements Runnable{

    String url;
    JsonReader reader;

    JSONParser(final String url)
    {
        this.url = url;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        try
        {
            URL url = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                reader = getJSONReader(in);
                List<Pair<ZonedDateTime,List<String>>> info = getContentArray();
                displayContent(info);
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Pair<ZonedDateTime, List<String>>> getContentArray() throws IOException
    {
        List<Pair<ZonedDateTime, List<String>>> contents = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext())
        {
            contents.add(readObject());
        }
        reader.endArray();
        return contents;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Pair<ZonedDateTime, List<String>> readObject() throws IOException
    {
        String begin = "";
        List<String> opponents = new ArrayList<>();
        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            if(name.equals("opponents") && reader.peek() != JsonToken.NULL) opponents = readOpponentArray();
            else if(name.equals("begin_at")) begin = reader.nextString();
            else reader.skipValue();
        }
        reader.endObject();
        if(opponents.isEmpty()) return null;
        return new Pair<ZonedDateTime, List<String>>(ZonedDateTime.parse(begin), opponents);
    }
    private List<String> readOpponentArray() throws IOException
    {
        List<String> opponents = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext())
        {
            opponents.add(readOpponent());
        }
        reader.endArray();
        return opponents;
    }

    private String readOpponent() throws IOException
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
        Reader reader = new InputStreamReader(stream);
        JsonReader jsonReader = new JsonReader(reader);
        return jsonReader;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayContent(List<Pair<ZonedDateTime, List<String>>> text)
    {
        for(Pair<ZonedDateTime, List<String>> p : text)
        {
            if(p != null)
            {
                if(p.second.size() > 1) System.out.println(formatDateAndTime(p.first) + " " + p.second.get(0) + " vs " + p.second.get(1));
            }
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

}
