package com.example.mobdeveartistics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Data> dataList;

    public MyAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        // Change activity_main to data_item
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data = dataList.get(position);

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