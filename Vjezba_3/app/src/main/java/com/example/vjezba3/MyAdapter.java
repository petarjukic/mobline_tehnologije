package com.example.vjezba3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Repozitorij> items;

    public MyAdapter(List<Repozitorij> repos) {
        this.items = repos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        items.get(position).getName();
        holder.getName().setText(items.get(position).getName());
        holder.getStarCount().setText(items.get(position).getStargazers_count());
        Picasso.get().load(items.get(position).owner.getAvatar_url()).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
