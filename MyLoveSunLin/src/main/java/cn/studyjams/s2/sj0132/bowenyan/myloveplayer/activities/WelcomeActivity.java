package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.R;


public class WelcomeActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //Delay 3 seconds
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (preferences.getBoolean("firstBoot", true)) {
                    editor = preferences.edit();
                    //将登录标志位设置为false，下次登录时不在显示首次登录界面
                    editor.putBoolean("firstBoot", false);
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, AndyViewPagerActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();

                }

            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}