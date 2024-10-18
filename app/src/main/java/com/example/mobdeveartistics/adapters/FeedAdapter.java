package com.example.mobdeveartistics.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdeveartistics.models.Data;
import com.example.mobdeveartistics.viewholders.FeedViewHolder;
import com.example.mobdeveartistics.R;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private ArrayList<Data> dataList;

    public FeedAdapter(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_data_item, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Data data = (Data) dataList.get(position);

        holder.setmMediaBackground(data.getMediaBackgroundResource());
        holder.setmProfileImage(data.getProfileImageResource());
        holder.setmCommentCount(String.valueOf(data.getCommentCount()));
        holder.setmLikeCount(String.valueOf(data.getLikeCount()));
        holder.setmOriginalPoster(data.getOriginalPoster());
        holder.setmCaption(data.getCaption());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}