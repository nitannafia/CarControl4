package com.example.yasha.carcontrol;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

//*
// * Created by citrapsara on 11/6/2017.
//


public class NotificationActivity {
        public static void showNotification(Context context) {
                // Do something. For example, fetch fresh data from backend to create a rich notification?
                final int NOTIFICATION_ID_OPEN_ACTIVITY = 9;

                final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                final NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                Intent notifyIntent = new Intent(context, Feature.class);
                notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                builder.setSmallIcon(R.drawable.logo_car_control)
                        .setAutoCancel(true)
                        .setContentTitle("Car Control Alarm")
                        .setContentText("Your car alarm is on!");

                manager.notify(NOTIFICATION_ID_OPEN_ACTIVITY, builder.build());
        }
}