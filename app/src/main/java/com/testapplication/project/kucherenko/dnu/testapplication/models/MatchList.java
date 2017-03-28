package com.testapplication.project.kucherenko.dnu.testapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchList {

    @SerializedName("fixtures")
    @Expose
    private ArrayList<Match> list = new ArrayList<>();

    public ArrayList<Match> getList() {
        return list;
    }

    public void setList(ArrayList<Match> list) {
        this.list = list;
    }
}
