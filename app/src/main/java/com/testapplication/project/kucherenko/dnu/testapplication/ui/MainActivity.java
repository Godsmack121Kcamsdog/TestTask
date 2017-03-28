package com.testapplication.project.kucherenko.dnu.testapplication.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
    private ArrayList<Match> matchList;
    private static final String TAG = MainActivity.class.getSimpleName();
    private NotificationManager nManager;
    private final int NOTIFICATION_ID = 228;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading, please wait...");
        matchList = new ArrayList<>();
        getMatchData();
        nManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

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
                    showNotification("Статус: успешно");
                    for (Match m : response.body().getList())
                        if (m.getMatchday() == 4) matchList.add(m);
                    recView = (RecyclerView) findViewById(R.id.recycler);
                    recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recView.setAdapter(new MatchAdapter(matchList, MainActivity.this));
                    //adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.hide();
                showNotification("Статус: error");
            }
        });
    }

    private void showNotification(String message) {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent intent = new Intent();
        PendingIntent intentP = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intentP)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.mipmap.ic_launcher))
                .setTicker("Загрузка завершена").setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setContentTitle("Загрузка завершена").setContentText(message);

        nManager.notify(NOTIFICATION_ID, builder.build());
    }

}