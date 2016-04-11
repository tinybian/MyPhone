package com.example.tinybian.myphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPhoneActivity extends Activity {

    Button destoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_phone);

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
