package com.example.mobdeveartistics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<Data> dataList;

    public MyAdapter(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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