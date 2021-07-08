package com.aure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {

    LinearLayout googleSignInLayout,emailSigninLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    private void initView(){
        googleSignInLayout = findViewById(R.id.signin_with_google_layout);
        emailSigninLayout = findViewById(R.id.signin_with_email_layout);
        googleSignInLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }
        });
        emailSigninLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,ConnectWithEmail.class));
            }
        });
    }
}
