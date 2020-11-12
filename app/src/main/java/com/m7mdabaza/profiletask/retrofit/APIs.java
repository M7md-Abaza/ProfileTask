package com.m7mdabaza.profiletask.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//APIs class to handle api by Retrofit

public class APIs {

    // URL1 = http://i0sa.com/bit/task/home
    // URL2 = http://i0sa.com/bit/task/profile

    private static Retrofit retrofit;

    // for Home data
    public static Retrofit getHomeApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://i0sa.com/bit/task/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // for Profile data
    public static Retrofit getProfileApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://i0sa.com/bit/task/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
