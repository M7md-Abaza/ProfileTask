package com.m7mdabaza.profiletask.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.m7mdabaza.profiletask.adapters.PostsRecyclerAdapter;
import com.m7mdabaza.profiletask.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView profileName, profileLocation, profileBio,
            profilePosts, profileFollowers, profileFollowing;
    ShapeableImageView profileImage;
    RecyclerView imagesRecycler;

    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByID();
        displayProfileData();
        displayImagePosts();

    }

    // method to observe the data which in the HomeViewModel and display it in the recycleView
    private void displayImagePosts() {
        // to tell the activity to be a listener for ViewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        //getting Data from ViewModel
        homeViewModel.getHttpHomeRequest();

        // get data from MutableLiveData and display on PostsRecycler
        homeViewModel.homeMutableLiveData.observe(this, recycleHomeModels -> {
            imagesRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            PostsRecyclerAdapter postsRecyclerAdapter = new PostsRecyclerAdapter();
            postsRecyclerAdapter.setList(recycleHomeModels,MainActivity.this);
            imagesRecycler.setAdapter(postsRecyclerAdapter);
        });
    }

    @SuppressLint("SetTextI18n")
    private void displayProfileData() {
        // to tell the activity to be a listener for ViewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        //getting Data from ViewModel
        homeViewModel.getHttpProfileRequest();

        // get data from MutableLiveData and display on Profile Info
        homeViewModel.uImageMutableLiveData.observe(this, s -> Picasso.get().load(s).into(profileImage));
        homeViewModel.uNameMutableLiveData.observe(this, s -> profileName.setText(s));
        homeViewModel.uLocationMutableLiveData.observe(this, s -> profileLocation.setText(s));
        homeViewModel.uBioMutableLiveData.observe(this, s -> profileBio.setText(s));
        homeViewModel.uPostsMutableLiveData.observe(this, integer -> profilePosts.setText(integer.toString()));
        homeViewModel.uFollowersMutableLiveData.observe(this, integer -> profileFollowers.setText(integer.toString()));
        homeViewModel.uFollowingMutableLiveData.observe(this, integer -> profileFollowing.setText(integer.toString()));
    }

    void findViewByID() {
        //ImageView
        profileImage = findViewById(R.id.imgProfile);
        //TextView
        profileName = findViewById(R.id.txtProfileName);
        profileLocation = findViewById(R.id.txtLocation);
        profileBio = findViewById(R.id.txtBio);
        profilePosts = findViewById(R.id.txtPostsNumber);
        profileFollowers = findViewById(R.id.txtFollowersNumber);
        profileFollowing = findViewById(R.id.txtFollowingNumber);
        //RecyclerView
        imagesRecycler = findViewById(R.id.recyclerImages);
    }

}