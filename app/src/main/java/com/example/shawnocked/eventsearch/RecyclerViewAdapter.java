package com.example.shawnocked.eventsearch;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mUpNameArr = new ArrayList<>();
    private ArrayList<String> mUpUrlArr = new ArrayList<>();
    private ArrayList<String> mUpArtArr = new ArrayList<>();
    private ArrayList<String> mUpTimeArr = new ArrayList<>();
    private ArrayList<String> mUpTypeArr = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mUpNameArr, ArrayList<String> mUpUrlArr, ArrayList<String> mUpArtArr, ArrayList<String> mUpTimeArr, ArrayList<String> mUpTypeArr, Context mContext) {
        this.mUpNameArr = mUpNameArr;
        this.mUpUrlArr = mUpUrlArr;
        this.mUpArtArr = mUpArtArr;
        this.mUpTimeArr = mUpTimeArr;
        this.mUpTypeArr = mUpTypeArr;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycleitem, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.upeventName.setText(mUpNameArr.get(position));
        holder.upeventUrl.setText(mUpUrlArr.get(position));
        holder.upeventArt.setText(mUpArtArr.get(position));
        holder.upeventTime.setText(mUpTimeArr.get(position));
        holder.upeventType.setText(mUpTypeArr.get(position));
        final String upUrl = mUpUrlArr.get(position);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do sth if clicked
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(upUrl));
                mContext.startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mUpNameArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView parentLayout;
        TextView upeventName;
        TextView upeventUrl;
        TextView upeventArt;
        TextView upeventTime;
        TextView upeventType;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.card_view);
            upeventName = itemView.findViewById(R.id.upevent_name);
            upeventUrl = itemView.findViewById(R.id.upevent_url);
            upeventArt = itemView.findViewById(R.id.upevent_art);
            upeventTime = itemView.findViewById(R.id.upevent_time);
            upeventType = itemView.findViewById(R.id.upevent_type);
        }
    }
}
