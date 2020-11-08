package com.example.liquidlookout;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationDispatcher {

    private static ArrayList<Match> subscribedMatches = new ArrayList<>();
    private static ExecutorService es = Executors.newSingleThreadExecutor();
    private static Context context;

    public static void initDispatcher(final Context context){
        NotificationDispatcher.context = context;
        es.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        for(Match e : subscribedMatches) {
                            if (Math.abs(ZonedDateTime.now().toEpochSecond() - e.getBegin().toEpochSecond()) <= 60) {
                                e.sendNotification(context);
                            }
                        }
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static ArrayList<Match> getSubscribedMatches() {
        return subscribedMatches;
    }

    public static void addMatch(Match m) {
        subscribedMatches.add(m);
    }

    public static void removeMatch(Match m) {
        subscribedMatches.remove(m);
    }

    public static void sendNotification(String title, String msg) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,"12")
                        .setSmallIcon(R.drawable.appicon)
                        .setContentTitle(title)
                        .setContentText(msg);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }

}