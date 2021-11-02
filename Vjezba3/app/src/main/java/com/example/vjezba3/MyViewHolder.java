package com.example.vjezba3;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private ImageView avatar_url;
    private TextView name, stargazers_count;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.textView);
        stargazers_count = (TextView) itemView.findViewById(R.id.textView2);
        avatar_url = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public ImageView getImageView() {
        return avatar_url;
    }

    public TextView getName() {
        return name;
    }

    public TextView getStarCount() {
        return stargazers_count;
    }
}
