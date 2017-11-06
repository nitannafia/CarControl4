package com.example.yasha.carcontrol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class WelcomeScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    public static String MY_SHARED_PREFERENCES = "My_Pref";
    public static String KEY_LOGIN = "Key_Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_SHARED_PREFERENCES,0);
                if(pref.getBoolean(KEY_LOGIN, false)){
                    Intent homeIntent = new Intent(WelcomeScreen.this, Feature.class);
                    startActivity(homeIntent);
                    finish();
                }
                else {
                    Intent loginIntent = new Intent(WelcomeScreen.this, MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                }

            }

        }, SPLASH_TIME_OUT);
    }
}
