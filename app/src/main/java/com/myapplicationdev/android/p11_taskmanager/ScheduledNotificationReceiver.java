package com.myapplicationdev.android.p11_taskmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class ScheduledNotificationReceiver extends BroadcastReceiver {
    ArrayList<Task> al = new ArrayList<Task>();

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
            channel.enableVibration(true);
            channel.enableLights(true);
        }

        long id = intent.getLongExtra("id", 0);
        String task_name = intent.getStringExtra("task_name");

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, Integer.parseInt("" + id), i, PendingIntent.FLAG_CANCEL_CURRENT);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info);

        // build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(task_name);
        builder.setSmallIcon(android.R.drawable.star_big_off);
        builder.setLargeIcon(largeIcon);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(largeIcon).bigLargeIcon(null));
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);

        //set vibrate
        builder.setVibrate(new long[] { 500, 500 });

        //LED lights
        builder.setLights(Color.RED, 3000, 3000);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        builder.setPriority(Notification.PRIORITY_HIGH);

        Notification n = builder.build();
        notificationManager.notify(Integer.parseInt("" + id), n);

    }
}