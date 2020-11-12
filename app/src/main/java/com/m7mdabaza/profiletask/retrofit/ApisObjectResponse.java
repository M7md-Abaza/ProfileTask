package com.m7mdabaza.profiletask.retrofit;

import com.m7mdabaza.profiletask.pojo.ImagesPosts;
import com.m7mdabaza.profiletask.pojo.ProfileInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApisObjectResponse {

    @GET("home")
    Call<ImagesPosts> getHomeResponse();

    @GET("profile")
    Call<ProfileInfo> getProfileResponse();

}
