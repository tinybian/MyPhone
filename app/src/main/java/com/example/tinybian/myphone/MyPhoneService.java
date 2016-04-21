package com.example.tinybian.myphone;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by tinybian on 2016/4/6.
 */
public final class MyPhoneService extends Service {

    private static MyPhoneService instance;

    private NotificationManager mNotifyManager;
    private Class<? extends Activity> notifyStartActivity = MyPhoneActivity.class;

    private final static int SERVICE_NOTIF_ID = 0;

    public static boolean isReady(){
        return instance!=null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyPhoneService", "onCreate!");

        //service notification
        mNotifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        String mNotifyTitle = getString(R.string.service_name);
        Bitmap bm = null;
        try{
            bm = BitmapFactory.decodeResource(getResources(), R.drawable.phone);
        }
        catch(Exception e){
            Log.e("bitmap", "decodeResource error");
        }
        String mContentInfo = "contentInfo";
        String mContentText = "contentText";
        Intent mNotifyIntent = new Intent(this, notifyStartActivity);
        PendingIntent mNotifyContentIntent = PendingIntent.getActivity(this, 0, mNotifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification ntf;
        ntf = createServiceNotification(this, bm, mNotifyTitle, mContentText, mNotifyContentIntent);
        startForeground(ONGOING_NOTIFICATION, ntf);

        //when service is ready
        instance = this;

        mNotifyManager.notify(SERVICE_NOTIF_ID, ntf);
    }

    private Notification createServiceNotification(Context context, Bitmap largeIcon,
                                                   String contentTitle, String contentText, PendingIntent intent){
        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.phone)
                //.setLargeIcon(largeIcon)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(intent)
                .build();
    }

    @Override
    public void onDestroy() {
        instance = null;

        super.onDestroy();
        Log.d("MyPhoneService", "onDestory");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
