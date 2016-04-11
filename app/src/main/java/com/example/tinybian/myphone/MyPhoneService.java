package com.example.tinybian.myphone;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tinybian on 2016/4/6.
 */
public final class MyPhoneService extends Service {

    private static MyPhoneService instance;

    public static boolean isReady(){
        return instance!=null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyPhoneService", "start!");
        //when service is ready
        instance = this;
    }

    @Override
    public void onDestroy() {
        instance = null;

        super.onDestroy();
        Log.d("MyPhoneService", "destory");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
