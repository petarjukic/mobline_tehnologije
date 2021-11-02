package com.example.vjezba4;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title, column;

    OnItemClickListener onItemClickListener; //DODANO

    //LinearLayout layout;

    public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) { //DODANO ONITEMCLICK
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.textView);
        this.column = (TextView) itemView.findViewById(R.id.textView2);

        this.onItemClickListener = onItemClickListener; //DODANO

        itemView.setOnClickListener(this); // DODANO

        //this.layout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
    }

    @Override //DODANO
    public void onClick(View view) {
        onItemClickListener.onNoteClick(getAdapterPosition());  //DODANO
    }

    // DODANO - staro
    /*
    public void bind(Note note, OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(note);
            }
        });
    }
     */
}
