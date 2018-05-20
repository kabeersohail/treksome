package com.example.sohail.treksome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageDetails extends AppCompatActivity {
    private List<Upload> mUploads;

    ImageView imageView;
    private ImageAdapter mAdapter;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter(ImageDetails.this, mUploads);
        mStorage = FirebaseStorage.getInstance();

        imageView = findViewById(R.id.details);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        Upload selectedItem = ImagesActivity.mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        Picasso.get()
                .load(selectedItem.getImageUrl())
                .placeholder(R.drawable.loading)
//                .centerCrop()
                .into(imageView);
    }
}
