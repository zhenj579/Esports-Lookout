package com.example.liquidlookout;

import android.util.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONParser implements Runnable{

    String url;
    JsonReader reader;

    JSONParser(final String url)
    {
        this.url = url;
    }

    @Override
    public void run() {
        try
        {
            URL url = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                reader = getJSONReader(in);
                List<String> info = getContentArray();
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
    private List<String> getContentArray() throws IOException
    {
        List<String> contents = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext())
        {
            contents.add(readObject());
        }
        reader.endArray();
        return contents;
    }

//    private List<String> readMatches() throws IOException {
//        List<String> matches = new ArrayList<>();
//        reader.beginArray();
//        while(reader.hasNext())
//        {
//        }
//        reader.endArray();
//        return matches;
//    }
    private String readObject() throws IOException
    {
        String begin = "";
        String slugOfMatch = "";
        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            if(name.equals("begin_at")) begin = reader.nextString();
            else if(name.equals("slug")) slugOfMatch = reader.nextString();
            else reader.skipValue();
        }
        reader.endObject();
        return slugOfMatch + " " + formatDateAndTime(begin);
    }
    private JsonReader getJSONReader(InputStream stream)
    {
        Reader reader = new InputStreamReader(stream);
        JsonReader jsonReader = new JsonReader(reader);
        return jsonReader;
    }
    public void displayContent(List<String> text)
    {
        for(String content : text) System.out.println(content);
    }
    private String formatDateAndTime(String dateAndTime)
    {
        String date = dateAndTime.substring(0, 10);
        String time = dateAndTime.substring(11, dateAndTime.length()-1);
        return date + " " + time + " UTC";
    }
}
