package com.m7mdabaza.profiletask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.m7mdabaza.profiletask.R;
import com.squareup.picasso.Picasso;

public class LargeImageActivity extends AppCompatActivity {

    ImageView largeImage;
    TextView titleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);

        findViewByID();
        getIntentData();

    }

    // receive animated transition data from Intent
    private void getIntentData() {
        if (getIntent().hasExtra("title") && getIntent().hasExtra("imageURL")) {
            String title = getIntent().getStringExtra("title");
            titleImage.setText(title);
            String imageUrl = getIntent().getStringExtra("imageURL");
            Picasso.get().load(imageUrl).into(largeImage);
        }
    }

    void findViewByID() {
        //ImageView
        largeImage = findViewById(R.id.largeImage);
        //TextView
        titleImage = findViewById(R.id.imageTitle);

    }
}