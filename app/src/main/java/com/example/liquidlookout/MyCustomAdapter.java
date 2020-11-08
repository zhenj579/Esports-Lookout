package com.example.liquidlookout;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<Match> {
    Context context;
    List<Match> matches;


    public MyCustomAdapter(Context context, int resource, List<Match> matches) {
        super(context,resource, matches);
        this.context = context;
        this.matches = matches;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.upcoming_match_layout, parent, false);
        Button subscribe = (Button)convertView.findViewById(R.id.subscribeButton);
        final Match m = matches.get(pos);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.isSubscribed()) Toast.makeText(context, "Unsubscribed!", Toast.LENGTH_SHORT);
                else Toast.makeText(context, "Subscribed!", Toast.LENGTH_SHORT);
                m.subscribe();
                sendNotification();
            }
        });
        TextView text = (TextView) convertView.findViewById(R.id.teamMatchText);
        ImageView im1 = (ImageView)convertView.findViewById(R.id.team1Image);
        ImageView im2 = (ImageView)convertView.findViewById(R.id.team2Image);
        text.setText(m.getMatchName());
        im1.setImageBitmap(m.getTeams().get(0).getLogo());
        im2.setImageBitmap(m.getTeams().get(0).getLogo());

        return convertView;
    }

    public void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,"12")
                        .setSmallIcon(R.drawable.teamliquid)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }

}
