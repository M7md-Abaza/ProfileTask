package com.m7mdabaza.profiletask.adapters;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.m7mdabaza.profiletask.R;
import com.m7mdabaza.profiletask.pojo.RecycleHomeModel;
import com.m7mdabaza.profiletask.ui.LargeImageActivity;
import com.m7mdabaza.profiletask.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.PostsViewHolder> {

    private static final String TAG = "PostsRecyclerAdapter";
    private ArrayList<RecycleHomeModel> RecycleHomeModel = new ArrayList<>();

    private MainActivity mContext;

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_image_item, parent, false);
        return new PostsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        RecycleHomeModel recycleHomeModel = RecycleHomeModel.get(position);
        String title = recycleHomeModel.getTitle();
        String imageURL = recycleHomeModel.getImage();
        Picasso.get().load(imageURL).into(holder.postImage);

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(mContext, LargeImageActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("imageURL", imageURL);

            // Pairs used for animation transition from recycle item to ChatActivity.java
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(holder.postImage, "ImageTransition");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mContext, pairs);
            mContext.startActivity(intent, options.toBundle());

        });
    }

    @Override
    public int getItemCount() {
        return RecycleHomeModel.size();
    }

    static class PostsViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView postImage;

        PostsViewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.imgRecycleItem);
        }

    }

    public void setList(ArrayList<RecycleHomeModel> RecycleHomeModel, MainActivity mContext) {
        this.RecycleHomeModel = RecycleHomeModel;
        this.mContext = mContext;
    }
}
