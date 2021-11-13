package com.example.vjezba4;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title, column;
    OnItemClickListener onItemClickListener;

    public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.textView);
        this.column = (TextView) itemView.findViewById(R.id.textView2);

        this.onItemClickListener = onItemClickListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onNoteClick(getAdapterPosition());
    }
}
