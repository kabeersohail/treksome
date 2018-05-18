package com.example.sohail.treksome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sohail.treksome.Main2Activity;
import com.firebase.ui.auth.AuthUI;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import br.com.bloder.magic.view.MagicButton;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    MagicButton magicGoogleButton;
    FloatingActionButton floatingActionButton;
    FirebaseAuth.AuthStateListener stateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();



        magicGoogleButton = findViewById(R.id.XmlGoogleSignIn);
        magicGoogleButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build()
                                ))
                                .build(),
                        RC_SIGN_IN);
            }
        });
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser == null)
                {
//                    Toast.makeText(MainActivity.this,"Signed out",Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    Toast.makeText(MainActivity.this,"Signed in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Main3Activity.class));
                }
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(stateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(stateListener);
    }
}
