package com.aure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aure.UiModels.SignupModel;
import com.aure.UiModels.Utils.NetworkUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class WelcomeActivity extends AppCompatActivity {

    LinearLayout googleSignInLayout,emailSigninLayout;
    private static final int GC_SIGN_IN = 1;
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
                // startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                startGoogleSignIn();
            }
        });
        emailSigninLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,ConnectWithEmail.class));
            }
        });
    }


    @Override
    public void onResume() {

        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }

    private void startGoogleSignIn(){

        GoogleSignInClient mSignInClient;
        GoogleSignInOptions options =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile()
                        .build();
        mSignInClient = GoogleSignIn.getClient(this, options);
        Intent intent = mSignInClient.getSignInIntent();
        startActivityForResult(intent, GC_SIGN_IN);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GC_SIGN_IN){
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                GoogleSignInAccount acct = task.getResult();
                handleSignInResult(acct);
            } else {

                Toast.makeText(this, "Error Signing in Please try again", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void handleSignInResult(GoogleSignInAccount account){
        String firstname = account.getGivenName();
        String lastname = account.getFamilyName();
        String userEmail = account.getEmail();
        String userPhotoUrl = String.valueOf(account.getPhotoUrl());

        SignupModel signupModel = new SignupModel(firstname,lastname,userEmail,userPhotoUrl,WelcomeActivity.this);
        if(!new NetworkUtils(WelcomeActivity.this).isNetworkAvailable()) {
            Toast.makeText(WelcomeActivity.this, "No Network", Toast.LENGTH_SHORT).show();
        }
        else {
            signupModel.SignupUsingGmail();
        }
        signupModel.setSignupListener(new SignupModel.SignupListener() {
            @Override
            public void isSuccessful(String message) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                intent.putExtra("email",userEmail);
                startActivity(intent);
            }

            @Override
            public void isFailed(String message) {
                Toast.makeText(WelcomeActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
