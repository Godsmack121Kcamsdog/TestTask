package com.testapplication.project.kucherenko.dnu.testapplication.client;

import com.testapplication.project.kucherenko.dnu.testapplication.interfaces.ILink;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitClient {

    private static final String ROOT_URL = "http://api.football-data.org";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ILink getILink() {
        return getRetrofitInstance().create(ILink.class);
    }
}
