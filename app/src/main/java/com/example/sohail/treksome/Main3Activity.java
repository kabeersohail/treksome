package com.example.sohail.treksome;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Dialog dialog;
    Button JavaFabK;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Context context;
    public Main3Activity(Context context){
        this.context = context;
    }

    public Main3Activity(){ }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        drawerLayout = findViewById(R.id.layoutdraw);
        navigationView = findViewById(R.id.navigation_viewk);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new Dialog(this);
        JavaFabK = findViewById(R.id.fabk);
        JavaFabK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button viewall;
                TextView close;
                dialog.setContentView(R.layout.articles);
                viewall = dialog.findViewById(R.id.xmlviewall);
                close = dialog.findViewById(R.id.xmlclose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

    }

    public void showPopup(){


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.signoutdrawar){
            AuthUI.getInstance().signOut(this);
            startActivity(new Intent(this,MainActivity.class));
        }

        if(id == R.id.uploadimage){
//            Main2Activity main2Activity = new Main2Activity(Main3Activity.this);
            startActivity(new Intent(this,Main2Activity.class));
        }



        return false;
        }
}
