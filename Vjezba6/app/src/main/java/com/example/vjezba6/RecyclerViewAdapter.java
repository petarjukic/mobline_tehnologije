package com.example.vjezba6;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    List<Predmet> predmetList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView imeTxt;
        ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imeTxt = itemView.findViewById(R.id.imePredmeta);
            layout = itemView.findViewById(R.id.itemListing);
        }
    }

    public RecyclerViewAdapter(List<Predmet> predmetList) {
        this.predmetList = predmetList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder)holder;
        Predmet predmet = predmetList.get(position);

        Log.d("listaPred", predmetList.toString());
        Log.d("listaPred", "onBindViewHolder: ");
        //((MyViewHolder) holder).ime.setText(predmet.getIme());

        viewHolder.imeTxt.setText(predmet.getIme());
        Log.d("predmet", predmetList.get(position).getIme());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditFieldActivity.class);

                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return predmetList.size();
    }
}
