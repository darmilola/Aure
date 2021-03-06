package com.aure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    CountDownTimer countDownTimer;
    Intent intent;
    LinearLayout logoRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logoRoot = findViewById(R.id.logo_root);
        countDownTimer = new CountDownTimer(4000,1000);
        countDownTimer.start();

    }

    public class CountDownTimer extends android.os.CountDownTimer {


        public CountDownTimer(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {


        }

        @Override
        public void onFinish() {

            Animation zoomout = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom_out);
            logoRoot.startAnimation(zoomout);

            zoomout.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                    String userEmail = preferences.getString("userEmail","");
                    if(userEmail.equalsIgnoreCase("")){
                        intent = new Intent(SplashScreen.this,WelcomeActivity.class);
                    }
                    else{
                        intent = new Intent(SplashScreen.this,MainActivity.class);
                        intent.putExtra("email",userEmail);
                    }
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }

    }

}
