package com.example.tinybian.myphone;

import static android.content.Intent.ACTION_MAIN;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


public class LauncherActivity extends Activity {
    private WaitServiceReadyThread mThread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

        mHandler = new Handler();

        if(MyPhoneService.isReady()){
            onServiceReady();
        }
        else{
            //if service hasn't started, start it first.
            Log.d("LuancherActivity", "start MyPhoneService");
            startService(new Intent(ACTION_MAIN).setClass(this, MyPhoneService.class));
            //execute onServiceReady() when service started.
            mThread = new WaitServiceReadyThread();
            mThread.start();
        }
    }

    protected void onServiceReady(){
        final Class<? extends Activity> classToStart;
        classToStart = MyPhoneActivity.class;

        //make the launcher screen stop for 1 second
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LauncherActivity.this, classToStart).setData(getIntent().getData()));
                finish();
            }
        }, 1000);

    }

    private class WaitServiceReadyThread extends Thread{
        @Override
        public void run() {
            while(!MyPhoneService.isReady())
            {
                try {
                    Log.d("wait service ready", "waiting.....");
                    sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException("wait Service sleep() has been interrupt!");
                }
            }

            //take onServiceReady() to handler so that this thread can be stop as soon as possible
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onServiceReady();
                }
            });

            mThread = null;
        }
    }

}
