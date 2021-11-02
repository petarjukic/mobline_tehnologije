package com.example.vjezba4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Note> notes;
    //final OnItemClickListener listener; // DODANO - STARO

    private OnItemClickListener listener; // DODANO

    public MyAdapter(List<Note> notes, OnItemClickListener listener) {  //LISTENER DODANO
        this.notes = notes;
        this.listener = listener; // DODANO , OnItemClickListener listener
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view, parent, false);
        return  new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.column.setText(notes.get(position).column);
        holder.title.setText(notes.get(position).title);

        //holder.bind(notes.get(position), listener); // DODANO

        //LinearLayout layout = holder.layout;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
