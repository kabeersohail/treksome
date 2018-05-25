package com.example.sohail.treksome;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Dialog dialog;
    Button JavaFabK,JavaPhotos;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ViewFlipper viewFlipper;
    Context context;
    public Main3Activity(Context context){
        this.context = context;
    }

    public Main3Activity(){ }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final int images[] = {R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4};


//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        drawerLayout = findViewById(R.id.layoutdraw);
        navigationView = findViewById(R.id.navigation_viewk);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        dialog = new Dialog(this);
        JavaPhotos = findViewById(R.id.Xmlphotos);
        JavaPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button viewall;
                TextView close;
                dialog.setContentView(R.layout.photos);
                viewall = dialog.findViewById(R.id.xmlviewphotos);
                close = dialog.findViewById(R.id.xmlclose);
                viewFlipper = dialog.findViewById(R.id.xmlflipper);
                for (int image:images) {
                    flipperImages(image);
                }

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                viewall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openImagesActivity();
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                dialog.show();
            }
        });
        JavaFabK = findViewById(R.id.fabk);
        JavaFabK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Button viewall;
                TextView close;
                dialog.setContentView(R.layout.articles);
//                viewall = dialog.findViewById(R.id.xmlviewall);
                close = dialog.findViewById(R.id.xmlclose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                dialog.show();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.signoutdrawar){
            finish();
            AuthUI.getInstance().signOut(this);
            startActivity(new Intent(this,MainActivity.class));
        }

        if(id == R.id.uploadimage){
//            Main2Activity main2Activity = new Main2Activity(Main3Activity.this);
            startActivity(new Intent(this,Main4Activity.class));
        }



        return false;
        }

    private void openImagesActivity() {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);// 4 sec
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,R.anim.fui_slide_in_right);
        viewFlipper.setOutAnimation(this,R.anim.fui_slide_out_left);
    }
}
