package com.example.pocketlist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.pocketlist.databinding.ActivitySplashBinding;

public class splash extends AppCompatActivity {
ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        binding= ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();;
        Thread thread=new Thread(){

            @RequiresApi(api = Build.VERSION_CODES.Q)
            public void run() {
                try {


                    sleep(2000);
                  finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent =new Intent(splash.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}