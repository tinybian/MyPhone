package com.example.tinybian.myphone;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;

public class MyPhoneActivity extends Activity {

    Button destoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_phone);

        Log.d("MyPhoneActivity", "onCreate");
        //test destory button
        destoryBtn = (Button)findViewById(R.id.destory_myphoneservice);
        destoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MyPhoneActivity.this, MyPhoneService.class));
            }
        });
    }

}
