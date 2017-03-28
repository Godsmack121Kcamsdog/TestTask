package com.testapplication.project.kucherenko.dnu.testapplication.interfaces;

import com.testapplication.project.kucherenko.dnu.testapplication.models.MatchList;

import retrofit.Call;
import retrofit.http.GET;

public interface ILink {

    @GET("/v1/soccerseasons/424/fixtures")
    Call<MatchList> getMatchData();
}
