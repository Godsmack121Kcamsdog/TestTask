package com.testapplication.project.kucherenko.dnu.testapplication.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.project.kucherenko.dnu.testapplication.R;
import com.testapplication.project.kucherenko.dnu.testapplication.models.Match;
import com.testapplication.project.kucherenko.dnu.testapplication.models.MatchList;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchHolder> {

    private static final String TAG = MatchAdapter.class.getSimpleName();
    private MatchList list;
    private Context context;

    public MatchAdapter(ArrayList<Match> list, Context context) {
        this.list = new MatchList();
        this.list = new MatchList();
        this.list.setList(list);
        this.context = context;
    }

    @Override
    public MatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
        return new MatchHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchHolder holder, int position) {
        holder.team1 = list
                .getList()
                .get(position)
                .getHomeTeamName();
        holder.team2 = list
                .getList()
                .get(position)
                .getAwayTeamName();
        int count1 = list
                .getList()
                .get(position)
                .getResult().getGoalsHomeTeam();
        int count2 = list
                .getList()
                .get(position)
                .getResult().getGoalsAwayTeam();

        holder.textCount.setText(holder.team1
                .concat("-").concat(count1 + " ").concat(":").concat(" " + count2).concat("-").concat(holder.team2));
        holder.setTeamImages(holder.team1, holder.team2);
    }

    @Override
    public int getItemCount() {
        return list.getList().size();
    }

    class MatchHolder extends RecyclerView.ViewHolder {

        private final ImageView homeCountryImage;
        private final ImageView anotherCountryImage;
        private String team1;
        private String team2;
        private final TextView textCount;

        public MatchHolder(View itemView) {
            super(itemView);
            homeCountryImage = (ImageView) itemView.findViewById(R.id.imageView);
            anotherCountryImage = (ImageView) itemView.findViewById(R.id.imageView2);
            textCount = (TextView) itemView.findViewById(R.id.textView);
        }

        private void setTeamImages(String team1, String team2) {
            int id1;
            int id2;

            switch (team1) {
                case "Switzerland": {
                    id1 = R.drawable.switzerland;
                    break;
                }
                case "Wales": {
                    id1 = R.drawable.wales;
                    break;
                }
                case "Croatia": {
                    id1 = R.drawable.croatia;
                    break;
                }
                case "France": {
                    id1 = R.drawable.france;
                    break;
                }
                case "Germany": {
                    id1 = R.drawable.germany;
                    break;
                }
                case "Hungary": {
                    id1 = R.drawable.hungary;
                    break;
                }
                case "Italy": {
                    id1 = R.drawable.italy;
                    break;
                }
                case "England": {
                    id1 = R.drawable.england;
                    break;
                }
                default: {
                    id1 = R.mipmap.ic_launcher;
                }
            }

            switch (team2) {
                case "Poland": {
                    id2 = R.drawable.poland;
                    break;
                }
                case "NorthernIreland": {
                    id2 = R.drawable.northern_irland;
                    break;
                }
                case "Portugal": {
                    id2 = R.drawable.portugal;
                    break;
                }
                case "Republic of Ireland": {
                    id2 = R.drawable.irland;
                    break;
                }
                case "Slovakia": {
                    id2 = R.drawable.slovakia;
                    break;
                }
                case "Belgium": {
                    id2 = R.drawable.belgium;
                    break;
                }
                case "Spain": {
                    id2 = R.drawable.spain;
                    break;
                }
                case "Iceland": {
                    id2 = R.drawable.iceland;
                    break;
                }
                default: {
                    id2 = R.mipmap.ic_launcher;
                }
            }
            homeCountryImage.setBackground((context).getDrawable(id1));
            anotherCountryImage.setBackground((context).getDrawable(id2));
        }
    }
}
