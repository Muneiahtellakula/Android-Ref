package com.muneiah.example.loginandregfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txtTitle = findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
        txtTitle.startAnimation(animation);


        handler.sendEmptyMessageDelayed(101,3000);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(SplashActivity.this,LoginUserActivity.class);
            startActivity(intent);
            finish();
        }
    };
}