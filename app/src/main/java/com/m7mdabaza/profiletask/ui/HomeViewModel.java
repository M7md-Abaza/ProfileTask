package com.m7mdabaza.profiletask.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.m7mdabaza.profiletask.pojo.ImagesPosts;
import com.m7mdabaza.profiletask.pojo.ProfileInfo;
import com.m7mdabaza.profiletask.pojo.RecycleHomeModel;
import com.m7mdabaza.profiletask.retrofit.APIs;
import com.m7mdabaza.profiletask.retrofit.ApisObjectResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    ArrayList<RecycleHomeModel> recycleHomeModels = new ArrayList<>();

    MutableLiveData<ArrayList<RecycleHomeModel>> homeMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> uImageMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> uNameMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> uLocationMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> uBioMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> uPostsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> uFollowersMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> uFollowingMutableLiveData = new MutableLiveData<>();

    void getHttpHomeRequest() {
        ApisObjectResponse apisHomeResponse = APIs.getHomeApi().create(ApisObjectResponse.class);
        Call<ImagesPosts> imagesPostsCall = apisHomeResponse.getHomeResponse();
        imagesPostsCall.enqueue(new Callback<ImagesPosts>() {
            @Override
            public void onResponse(Call<ImagesPosts> call, Response<ImagesPosts> response) {
                Log.d(TAG, "MAA onResponse: Home done ");
                if (response.body() != null) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        recycleHomeModels.add(new RecycleHomeModel(
                                response.body().getData().get(i).getId(),
                                response.body().getData().get(i).getTitle(),
                                response.body().getData().get(i).getImage()));
                    }
                    homeMutableLiveData.setValue(recycleHomeModels);
                } else {
                    Log.d(TAG, "MAA onResponse: Home is null ");
                }

            }

            @Override
            public void onFailure(Call<ImagesPosts> call, Throwable t) {
                Log.d(TAG, "MAA onFailure: Home Failed " + t.getMessage());

            }
        });
    }

    void getHttpProfileRequest() {
        ApisObjectResponse apisProfileResponse = APIs.getProfileApi().create(ApisObjectResponse.class);
        Call<ProfileInfo> profileInfoCall = apisProfileResponse.getProfileResponse();
        profileInfoCall.enqueue(new Callback<ProfileInfo>() {
            @Override
            public void onResponse(Call<ProfileInfo> call, Response<ProfileInfo> response) {
                Log.d(TAG, "MAA onResponse: Profile done ");
                if (response.body() != null) {
                    String uImage = response.body().getData().getProfilePicture();
                    String uName = response.body().getData().getFullName();
                    String uLocation = response.body().getData().getLocation();
                    String uBio = response.body().getData().getBio();
                    Integer uPosts = response.body().getData().getCounts().getPosts();
                    Integer uFollowers = response.body().getData().getCounts().getFollowers();
                    Integer uFollowing = response.body().getData().getCounts().getFollowing();

                    uImageMutableLiveData.setValue(uImage);
                    uNameMutableLiveData.setValue(uName);
                    uLocationMutableLiveData.setValue(uLocation);
                    uBioMutableLiveData.setValue(uBio);
                    uPostsMutableLiveData.setValue(uPosts);
                    uFollowersMutableLiveData.setValue(uFollowers);
                    uFollowingMutableLiveData.setValue(uFollowing);
                } else {
                    Log.d(TAG, "MAA onResponse: Profile is null ");
                }
            }

            @Override
            public void onFailure(Call<ProfileInfo> call, Throwable t) {
                Log.d(TAG, "MAA onFailure: Profile Failed " + t.getMessage());
            }
        });
    }
}
