package com.testapplication.project.kucherenko.dnu.testapplication.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.testapplication.project.kucherenko.dnu.testapplication.R;
import com.testapplication.project.kucherenko.dnu.testapplication.adapters.MatchAdapter;
import com.testapplication.project.kucherenko.dnu.testapplication.client.RetrofitClient;
import com.testapplication.project.kucherenko.dnu.testapplication.interfaces.ILink;
import com.testapplication.project.kucherenko.dnu.testapplication.models.Match;
import com.testapplication.project.kucherenko.dnu.testapplication.models.MatchList;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private ProgressDialog dialog;
    private MatchAdapter adapter;
    private ArrayList<Match> matchList;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading, please wait...");
        matchList = new ArrayList<>();
        getMatchData();
    }

    private void getMatchData() {
        ILink link = RetrofitClient.getILink();
        Call<MatchList> call = link.getMatchData();
        dialog.show();
        call.enqueue(new Callback<MatchList>() {
            @Override
            public void onResponse(Response<MatchList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    dialog.hide();
                    Toast.makeText(MainActivity.this, "SuccessfullyLoaded", Toast.LENGTH_SHORT).show();
                    matchList = response.body().getList();
                    Log.e(TAG, matchList.size()+"");
                    recView = (RecyclerView) findViewById(R.id.recycler);
                    recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter = new MatchAdapter(matchList, MainActivity.this);
                    Log.e(TAG, adapter.getItemCount()+"" );
                    recView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.hide();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}